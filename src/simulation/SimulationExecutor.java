package simulation;

import calculation.*;
import db.DataInterface;
import db.DataLoader;
import db.DateHandler;
import master.Product;
import master.TLane;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SimulationExecutor {

    private boolean IsSimulationRunning;
    private DataInterface di;
    private DataLoader dl;
    private int TickCounter;
    private static Logger logger;

    public SimulationExecutor () throws SQLException {
        logger = Logger.getLogger("simlog");
        //ScenarioParser.ParseXmlScenario();
        IsSimulationRunning = false;
        di = new DataInterface();
        dl = new DataLoader();
        TickCounter = 0;
    }

    public void runSimulation() throws SQLException {
        for (int i = 0; i < 360; i++) {
            Tick();
        }
    }

    public void Tick() throws SQLException {
        boolean DayPassed = false;
        TickCounter++;
        if(TickCounter == 24 / GlobalParameters.Tick) {
            DayPassed = true;
            TickCounter = 0;
        }

        UpdateSimulationTime();
        LogToFile("TICK: " + GlobalParameters.currentTime);

        int PlantsTable[] = new int[]{2621, 2751, 9979, 4850, 4853, 5053, 2725};
        for (int plant : PlantsTable) {
            List<Integer> productList = dl.getProductsPerPlant(plant);
            for (int product : productList) {
                UnloadShipments(product, plant);
                UpdateProduction(product, plant);
                ReleaseQmLots(product, plant);
                ShipDeliveries(product, plant);
                InsertOrders(product, plant);
                CreateDeliveries(product, plant);
                if(DayPassed){
                    RunMrp(product, plant);
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
            if (ShipmentDate.before(GlobalParameters.currentTime)) {
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
            if(ReplenishmentInDate.before(DateHandler.getRelativeDate(GlobalParameters.currentTime,
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
            if(ProcessOrderStart.before(GlobalParameters.currentTime)) {
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
                if(QmLotReleaseDate.before(GlobalParameters.currentTime)) {
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
            ReplenishmentOut ro = ReplenishmentList.get(0);
            TLane tl = dl.getTLaneDetails(Plant, ro.getLocationTo());

            int LocationFrom = ro.getLocationFrom();
            int LocationTo = ro.getLocationTo();
            int DeliveryNumber = di.incrementAndGetDocumentNumber("DELIV");
            String LoadingTime = DateHandler.getRandomTime();
            String UnloadingTime = DateHandler.getRandomTime();
            String LoadingDate = DateHandler.getRelativeDate(DateHandler.getStringDate(GlobalParameters.currentTime),1);
            String UnloadingDate = DateHandler.getRelativeDate(LoadingDate, tl.getDuration()/24);
            int product = ro.getProduct();
            int Quantity = 0;
            String DlvParty = "Procter & Gamble";

            if(AvailableToDeployQuantity > Math.abs(ro.getQuantity())) {
                 Quantity =  Math.abs(ro.getQuantity());
            } else {
                Quantity = CurrentStock.getQuantity() / p.getRoundingValue() * p.getRoundingValue();
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
            if(DeliveryDate.before(GlobalParameters.currentTime)) {

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
        // TODO: Implementation
    }

    private void CreateDeliveries(int Product, int Plant) {
        // TODO: Implementation
    }

    private void RunMrp(int Product, int Plant) throws SQLException {
        // TODO: Implement mechanism for automatic hierarchy recognizing within given network
        MRPList mrpList = new MRPList();
        mrpList.setMRPList(Product,Plant);
        mrpList.runMRP();
    }

    private void UpdateSimulationTime() {
        int tick = GlobalParameters.Tick;
        Calendar cal = Calendar.getInstance();
        cal.setTime(GlobalParameters.currentTime);
        cal.add(Calendar.HOUR_OF_DAY, tick);
        GlobalParameters.currentTime = cal.getTime();
    }

    public void LogToFile (String parameter){
        if(logger.isDebugEnabled()){
            logger.debug(parameter);
        }
    }

}
