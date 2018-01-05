package simulation;

import calculation.*;
import db.DataInterface;
import db.DataLoader;
import db.DateHandler;
import enums.OrderType;
import enums.SafetyStrategy;
import enums.StockType;
import init.DataImporter;
import master.Product;
import master.TLane;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SimulationExecutor {

    private DataInterface di;
    private DataLoader dl;
    private int TickCounter;
    private int DayCounter;
    private static Logger logger;
    private DataImporter DataSource;
    private boolean IsSimulationPrepared;

    public SimulationExecutor () throws SQLException {
        logger = Logger.getLogger("simlog");
        //ScenarioParser.ParseXmlScenario();
        di = new DataInterface();
        dl = new DataLoader();
        DataSource = new DataImporter();
        TickCounter = 0;
        DayCounter = 0;
    }

    private void PrepareSimulation() {
        di.TruncateMrpTables();
        di.TruncateStatisticTables();
        DataSource.GetFreshForecast();
        IsSimulationPrepared = true;
    }

    public void RunTestSimulation() throws SQLException {
        DataSource.loadCustomerOrders("import_data/shipments.csv");
        DataSource.loadForecast("import_data/forecast.csv");
        if(!IsSimulationPrepared) PrepareSimulation();
        for (int i = 0; i < 960; i++) {
            Tick();
        }
    }

    public void RunScenario_1_1() throws SQLException {
        DataSource.loadCustomerOrders("import_data/orders_scenario_1_1.csv");
        DataSource.loadForecast("import_data/forecast_scenario_1_1.csv");
        if(!IsSimulationPrepared) PrepareSimulation();
        while (GlobalParameters.CurrentTime.before(GlobalParameters.SimulationEndDate)) {
            Tick();
        }
    }

    public void RunScenario_3_2 () throws SQLException {
        GlobalParameters.GlobalSafetyStrategy = SafetyStrategy.ST;
        DataSource.loadCustomerOrders("import_data/orders_scenario_1_1.csv");
        DataSource.loadForecast("import_data/forecast_scenario_1_1.csv");
        if(!IsSimulationPrepared) PrepareSimulation();
        while (GlobalParameters.CurrentTime.before(GlobalParameters.SimulationEndDate)) {
            Tick();
        }
    }

    public void Tick() throws SQLException {
        if(!IsSimulationPrepared) PrepareSimulation();
        boolean DayPassed = false;
        TickCounter++;
        if(TickCounter == 24 / GlobalParameters.Tick) {
            DayPassed = true;
            DayCounter++;
            TickCounter = 0;
        }

        if(DayCounter == 7) {
            DataSource.GetFreshForecast();
            DayCounter = 0;
        }

        UpdateSimulationTime();
        LogToFile("TICK: " + GlobalParameters.CurrentTime);

        int PlantsTable[] = new int[]{2621, 2751, 9979, 4850, 4853, 5053, 2725};
        for (int plant : PlantsTable) {
            List<Integer> productList = dl.getProductsPerPlant(plant);
            for (int product : productList) {
                UnloadShipments(product, plant);
                UpdateProduction(product, plant);
                ReleaseQmLots(product, plant);
                ShipDeliveries(product, plant);
                InsertOrders(product, plant);
                ShipOrders(product, plant);
                if(DayPassed){
                    RunMrp(product, plant);
                    ReportInventoryStatus(product, plant);
                    PlanProduction(product,plant);
                    DeployStock(product, plant);
                }
            }
        }
    }

    private void UnloadShipments(int Product, int Plant) {
        List<Shipment> ShipmentList = dl.getShipmentPerProductLocation(Product, Plant);
        for (Shipment s : ShipmentList) {
            Date ShipmentDate = DateHandler.GetDate(s.getUnloadingDate(), s.getUnloadingTime());
            if (ShipmentDate.before(GlobalParameters.CurrentTime)) {
                Stock CurrentStock = dl.getStockPerProductLocation(Product, Plant);
                CurrentStock.setQuantity(CurrentStock.getQuantity() + s.getQuantity());
                di.DeleteShipmentFromDb(s);
                di.UpdateStockInDb(CurrentStock);
            }
        }
    }

    private void PlanProduction(int Product, int Plant) {
        Product p = dl.getProductMaster(Product, Plant);
        if (Plant != p.getLocationFrom()) return;

        List<ReplenishmentIn> ReplenishmentList = dl.getReplenishmentInPerProductLocation(Product,Plant);
        for (ReplenishmentIn ri : ReplenishmentList) {
            Date ReplenishmentInDate = DateHandler.GetDate(DateHandler.getRelativeDate(ri.getDate(),1),
                    DateHandler.getRandomTime());
            // Potential end of production date
            if(ReplenishmentInDate.before(DateHandler.getRelativeDate(GlobalParameters.CurrentTime,
                    GlobalParameters.FirmedZone))) {

                int ProcessOrderNumber = di.incrementAndGetDocumentNumber("PRCORD");
                int Location = ri.getLocationFrom();
                int product = ri.getProduct();
                String StartDate = ri.getDate();
                String StartTime = DateHandler.getRandomTime();
                String EndDate = DateHandler.getRelativeDate(ri.getDate(),1);
                String EndTime = DateHandler.getRandomTime();
                int Quantity = ri.getQuantity();

                ProcessOrder po = new ProcessOrder(ProcessOrderNumber, Location, product, StartDate, StartTime,
                        EndDate, EndTime, Quantity);

                di.InsertProcessOrderIntoDb(po);
                di.DeleteReplenishmentInFromDb(ri);
            }
        }
    }

    private void UpdateProduction(int Product, int Plant) {
        Product p = dl.getProductMaster(Product, Plant);
        if (Plant != p.getLocationFrom()) return;

        List<ProcessOrder> ProcessOrderList = dl.getProcessOrderPerProductLocation(Product, Plant);
        for(ProcessOrder po : ProcessOrderList) {
            Date ProcessOrderStart = DateHandler.GetDate(po.getStartDate(), po.getStartTime());
            Date ProcessOrderEnd = DateHandler.GetDate(po.getEndDate(), po.getEndTime());
            if(ProcessOrderStart.before(GlobalParameters.CurrentTime)) {
                int PercentOfProductionLeft = DateHandler.getPercentOfTimeLeft(ProcessOrderStart,
                        ProcessOrderEnd);
                int CorrespondingQmLot = dl.getIdocReferenceNumber(po.getProcessOrderNumber());
                if (CorrespondingQmLot == 0) {
                    int RemainingProduction = po.getQuantity() * PercentOfProductionLeft / 100;

                    int Location = po.getLocation();
                    int QualityLotNumber = di.incrementAndGetDocumentNumber("QMLOT");
                    String ReleaseDate = po.getEndDate();
                    String ReleaseTime = po.getEndTime();
                    int product = po.getProduct();
                    int Quantity = po.getQuantity() - RemainingProduction;

                    QualityLot qmlot = new QualityLot(Location, QualityLotNumber, ReleaseDate, ReleaseTime,
                            product, Quantity);
                    di.InsertQmLotIntoDb(qmlot);
                    po.setQuantity(RemainingProduction);
                    di.UpdateProcessOrderInDb(po);
                    di.InsertIdocRefIntoDb(po.getProcessOrderNumber(), qmlot.getQualityLotNumber());
                } else {
                    QualityLot qmlot = dl.getQualityLotByNumber(CorrespondingQmLot);
                    int TotalProduction = qmlot.getQuantity() + po.getQuantity();
                    int RemainingProduction = TotalProduction * PercentOfProductionLeft / 100;
                    if(RemainingProduction == 0) {
                        di.DeleteProcessOrderFromDb(po);
                        di.DeleteIdocReference(po.getProcessOrderNumber());
                    } else {
                        po.setQuantity(RemainingProduction);
                        di.UpdateProcessOrderInDb(po);
                    }
                    qmlot.setQuantity(TotalProduction - RemainingProduction);
                    di.UpdateQmLotInDb(qmlot);
                }
            }
        }
    }

    private void ReleaseQmLots(int Product, int Plant) {
        Product p = dl.getProductMaster(Product, Plant);
        if (Plant != p.getLocationFrom()) return;

        List<QualityLot> QualityLotList = dl.getQualityLotPerProductLocation(Product, Plant);
        for(QualityLot qmlot : QualityLotList) {
            int ProcessOrderReference = dl.getIdocPreviousReference(qmlot.getQualityLotNumber());
            if (ProcessOrderReference != 0) {
                return;
            } else {
                Date ProductionFinishDate = DateHandler.GetDate(qmlot.getReleaseDate(), qmlot.getReleaseTime());
                Date QmLotReleaseDate = DateHandler.getRelativeTime(ProductionFinishDate, GlobalParameters.QualityCheck);
                if(QmLotReleaseDate.before(GlobalParameters.CurrentTime)) {
                    Stock CurrentStock = dl.getStockPerProductLocation(Product, Plant);
                    CurrentStock.setQuantity(CurrentStock.getQuantity() + qmlot.getQuantity());
                    di.DeleteQualityLotFromDb(qmlot);
                    di.UpdateStockInDb(CurrentStock);
                }
            }

        }
    }

    private void DeployStock(int Product, int Plant) {
        List<ReplenishmentOut> ReplenishmentList = dl.getReplenishmentOutPerProductLocation(Product, Plant);
        if (ReplenishmentList.size() == 0) return;
        Stock CurrentStock = dl.getStockPerProductLocation(Product, Plant);
        Product p = dl.getProductMaster(Product, Plant);

        int AvailableToDeployQuantity = CurrentStock.getQuantity();
        List<Delivery> DeliveryList = dl.getDeliveryPerProductLocation(Product,Plant);

        for (Delivery d : DeliveryList) {
            AvailableToDeployQuantity -= Math.abs(d.getQuantity());
        }
        if (AvailableToDeployQuantity < p.getRoundingValue()) return;

        while(AvailableToDeployQuantity > p.getRoundingValue()) {
            ReplenishmentOut ro = null;
            TLane tl = null;
            try {
                ro = ReplenishmentList.get(0);
                tl = dl.getTLaneDetails(Plant, ro.getLocationTo());
            } catch (IndexOutOfBoundsException e) {
                return;
            }


            int LocationFrom = ro.getLocationFrom();
            int LocationTo = ro.getLocationTo();
            int DeliveryNumber = di.incrementAndGetDocumentNumber("DELIV");
            String LoadingTime = DateHandler.getRandomTime();
            String UnloadingTime = DateHandler.getRandomTime();
            String LoadingDate = DateHandler.getRelativeDate(DateHandler.getStringDate(GlobalParameters.CurrentTime),1);
            String UnloadingDate = DateHandler.getRelativeDate(LoadingDate, tl.getDuration()/24);
            int product = ro.getProduct();
            int Quantity = 0;
            String DlvParty = "Procter & Gamble";

            if(AvailableToDeployQuantity > Math.abs(ro.getQuantity())) {
                 Quantity =  Math.abs(ro.getQuantity());
            } else if (AvailableToDeployQuantity > p.getRoundingValue()){
                Quantity = AvailableToDeployQuantity / p.getRoundingValue() * p.getRoundingValue();
            } else {
                return;
            }

            Delivery d = new Delivery(LocationFrom, LocationTo, DeliveryNumber, LoadingDate, LoadingTime, UnloadingDate,
                    UnloadingTime, product, -Quantity, DlvParty);
            di.InsertDeliveryIntoDb(d);

            int PoNumber = di.incrementAndGetDocumentNumber("PCHORD");
            String OrdParty = "Procter & Gamble";
            PurchaseOrder po = new PurchaseOrder(LocationFrom,LocationTo,PoNumber,LoadingDate,LoadingTime,UnloadingDate,
                    UnloadingTime,product, Quantity, OrdParty);
            di.InsertPurchaseOrderIntoDb(po);

            di.InsertIdocRefIntoDb(DeliveryNumber,PoNumber);

            ReplenishmentList.remove(ro);
            AvailableToDeployQuantity -= Math.abs(Quantity);
        }
    }

    private void ShipDeliveries(int Product, int Plant) {
        List<Delivery> DeliveryList = dl.getDeliveryPerProductLocation(Product, Plant);
        for(Delivery d : DeliveryList) {
            Date DeliveryDate = DateHandler.GetDate(d.getLoadingDate(), d.getLoadingTime());
            if(DeliveryDate.before(GlobalParameters.CurrentTime)) {

                int LocationFrom = d.getLocationFrom();
                int LocationTo = d.getLocationTo();
                int ShipmentNumber = di.incrementAndGetDocumentNumber("SHIPNT");
                String LoadingTime = d.getLoadingTime();
                String UnloadingTime = d.getUnloadingTime();
                String LoadingDate = d.getLoadingDate();
                String UnloadingDate = d.getUnloadingDate();
                int Product_ = d.getProduct();
                int  Quantity = -d.getQuantity();
                String ShipParty = d.getDlvParty();

                Shipment s = new Shipment(LocationFrom, LocationTo, ShipmentNumber, LoadingDate, LoadingTime,
                        UnloadingDate, UnloadingTime, Product_, Quantity, ShipParty);

                int CorrespondingPurchaseOrder = dl.getIdocReferenceNumber(d.getDeliveryNumber());
                PurchaseOrder po = new PurchaseOrder();
                po.setPoNumber(CorrespondingPurchaseOrder);

                di.InsertShipmentIntoDb(s);
                di.DeleteDeliveryFromDb(d);
                di.DeletePurchaseOrderFromDb(po);

                Stock CurrentStock = dl.getStockPerProductLocation(Product, Plant);
                CurrentStock.setQuantity(CurrentStock.getQuantity() - s.getQuantity());
                di.UpdateStockInDb(CurrentStock);
            }
        }
    }

    private void InsertOrders(int Product, int Plant) {
        List<Order> OrderList = DataSource.GetOrdersPerLocation(Plant, Product);
        for(Order o : OrderList) {
            Date OrderDate = DateHandler.GetDate(o.getLoadingDate(), o.getLoadingTime());
            if (OrderDate.before(GlobalParameters.CurrentTime)) {
                DataSource.RemoveInsertedOrder(o);
                o.setLoadingDate(DateHandler.getRelativeTime(o.getLoadingDate(),GlobalParameters.OrderLeadTime));
                di.InsertOrderIntoDb(o);
                List<Forecast> ForecastList = dl.getForecastsPerProductLocation(Product, Plant);
                List<String> DatesToConsumeForecast = GetForecastDatesToBeChecked();
                List<Forecast> ForecastToBeConsumed = new ArrayList<>();
                for(String s : DatesToConsumeForecast) {
                    for(Forecast f : ForecastList) {
                        if(f.getDate().equals(s)) ForecastToBeConsumed.add(f);
                    }
                }
                int TotalToBeConsumed = o.getQuantity();
                for (Forecast f : ForecastToBeConsumed) {
                    if(f.getQuantity() < TotalToBeConsumed) {
                        f.setQuantity(f.getQuantity() - TotalToBeConsumed);
                        di.UpdateForecastInDb(f);
                        return;
                    } else {
                        TotalToBeConsumed -= f.getQuantity();
                        di.DeleteForecastFromDb(f);
                    }
                }
            }
        }
    }

    private void ShipOrders(int Product, int Plant) {
        List<Order> OrderList = dl.getOrderPerProductLocation(Product, Plant);
        Stock  AvailableToPromise = dl.getStockPerProductLocation(Product, Plant);
        for(Order o : OrderList) {
            Date OrderDate = DateHandler.GetDate(o.getLoadingDate(), o.getLoadingTime());
            if (OrderDate.before(GlobalParameters.CurrentTime) && AvailableToPromise.getQuantity() > 0) {
                if(Math.abs(o.getQuantity()) < AvailableToPromise.getQuantity()) {
                    AvailableToPromise.setQuantity(AvailableToPromise.getQuantity() + o.getQuantity());
                    di.DeleteOrderFromDb(o);
                    ReportShipment(o, Math.abs(o.getQuantity()));
                } else {
                    int CutQuantity = Math.abs(o.getQuantity()) - AvailableToPromise.getQuantity();
                    ReportCut(o, CutQuantity);
                    ReportShipment(o, AvailableToPromise.getQuantity());
                    AvailableToPromise.setQuantity(0);
                    di.DeleteOrderFromDb(o);
                }
            } else if (OrderDate.before(GlobalParameters.CurrentTime) && AvailableToPromise.getQuantity() <= 0) {
                ReportCut(o, Math.abs(o.getQuantity()));
                di.DeleteOrderFromDb(o);
            }
        }
        di.UpdateStockInDb(AvailableToPromise);
    }

    private void RunMrp(int Product, int Plant) throws SQLException {
        MRPList mrpList = new MRPList();
        mrpList.setMRPList(Product,Plant);
        mrpList.runMRP();
    }

    private void UpdateSimulationTime() {
        int tick = GlobalParameters.Tick;
        Calendar cal = Calendar.getInstance();
        cal.setTime(GlobalParameters.CurrentTime);
        cal.add(Calendar.HOUR_OF_DAY, tick);
        GlobalParameters.CurrentTime = cal.getTime();
    }

    public void LogToFile (String parameter){
        if(logger.isDebugEnabled()){
            logger.debug(parameter);
        }
    }

    private List<String> GetForecastDatesToBeChecked () {
        List<String> DatesList = new ArrayList<>();
        for(int i = -GlobalParameters.BwForecastConsumption; i < GlobalParameters.FwForecastConsumption; i++) {
            Date d = DateHandler.getRelativeDate(GlobalParameters.CurrentTime,i);
            DatesList.add(DateHandler.getStringDate(d));
        }
        return DatesList;
    }

    private void ReportCut (Order o, int CutQuantity) {
        OrderData od = new OrderData();
        od.setOrderType(OrderType.Cut);
        od.setDate(o.getLoadingDate());
        od.setLocation(o.getLocation());
        od.setProduct(o.getProduct());
        od.setOrderNumber(o.getOrderNumber());
        od.setCustomer(o.getCustomer());
        od.setQuantity(CutQuantity);
        di.InsertOrderStatistic(od);
    }

    private void ReportShipment (Order o, int ShipmentQuantity) {
        OrderData od = new OrderData();
        od.setOrderType(OrderType.Shipment);
        od.setDate(o.getLoadingDate());
        od.setLocation(o.getLocation());
        od.setProduct(o.getProduct());
        od.setOrderNumber(o.getOrderNumber());
        od.setCustomer(o.getCustomer());
        od.setQuantity(ShipmentQuantity);
        di.InsertOrderStatistic(od);
    }

    private void ReportInventoryStatus (int Product, int Plant) {
        Stock s = dl.getStockPerProductLocation(Product, Plant);
        InventoryData OnHand = new InventoryData();
        OnHand.setStockType(StockType.OnHand);
        OnHand.setDate(DateHandler.getStringDate(GlobalParameters.CurrentTime));
        OnHand.setLocation(s.getLocation());
        OnHand.setProduct(s.getProduct());
        OnHand.setQuantity(s.getQuantity());
        di.InsertInventoryStatistic(OnHand);

        int SafetyTarget = dl.getProductMaster(Product, Plant).getTarget();
        InventoryData Safety = new InventoryData();
        Safety.setStockType(StockType.Safety);
        Safety.setDate(DateHandler.getStringDate(GlobalParameters.CurrentTime));
        Safety.setLocation(s.getLocation());
        Safety.setProduct(s.getProduct());
        Safety.setQuantity(SafetyTarget);
        di.InsertInventoryStatistic(Safety);

        List<Shipment> StockInTransit = dl.getShipmentPerProductLocation(Product, Plant);
        int TotalInTransit = 0;
        for(Shipment sh : StockInTransit) {
            TotalInTransit += sh.getQuantity();
        }
        InventoryData InTransit = new InventoryData();
        InTransit.setStockType(StockType.InTransit);
        InTransit.setDate(DateHandler.getStringDate(GlobalParameters.CurrentTime));
        InTransit.setLocation(s.getLocation());
        InTransit.setProduct(s.getProduct());
        InTransit.setQuantity(TotalInTransit);
        di.InsertInventoryStatistic(InTransit);
    }

}
