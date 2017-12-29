package simulation;

import calculation.Delivery;
import calculation.MRPList;
import calculation.Shipment;
import calculation.Stock;
import db.DataInterface;
import db.DataLoader;
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
        new InitParameters();
        logger = Logger.getLogger("simlog");
        //ScenarioParser.ParseXmlScenario();
        IsSimulationRunning = false;
        di = new DataInterface();
        dl = new DataLoader();
        TickCounter = 0;
    }

    public void runSimulation() throws SQLException {
        for (int i = 0; i < 36; i++) {
            Tick();
        }
    }

    private void Tick() throws SQLException {
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
                DeployStock(product, plant);
                ShipDeliveries(product, plant);
                InsertOrders(product, plant);
                CreateDeliveries(product, plant);
                if(DayPassed) RunMrp(product, plant);
            }
        }
    }

    private void UnloadShipments(int Product, int Plant) {
        List<Shipment> ShipmentList = dl.getShipmentPerProductLocation(Product, Plant);
        for (Shipment s : ShipmentList) {
            Date ShipmentDate = GetDate(s.getUnloadingDate(), s.getUnloadingTime());
            if (ShipmentDate.before(GlobalParameters.currentTime)) {
                Stock CurrentStock = dl.getStockPerProductLocation(Product, Plant);
                CurrentStock.setQuantity(CurrentStock.getQuantity() + s.getQuantity());
                di.DeleteShipmentFromDb(s);
                di.UpdateStockInDb(CurrentStock);
            }
        }
    }

    private void UpdateProduction(int Product, int Plant) {
        // TODO: Implementation
    }

    private void ReleaseQmLots(int Product, int Plant) {
        // TODO: Implementation
    }

    private void DeployStock(int Product, int Plant) {
        // TODO: Implementation
    }

    private void ShipDeliveries(int Product, int Plant) {
        List<Delivery> DeliveryList = dl.getDeliveryPerProductLocation(Product, Plant);
        for(Delivery d : DeliveryList) {
            Date DeliveryDate = GetDate(d.getLoadingDate(), d.getLoadingTime());
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

                di.InsertShipmentIntoDb(s);
                di.DeleteDeliveryFromDb(d);
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

    private static Date GetDate(String Day, String Hour) {
        String[] DayTab = Day.split("-");
        String[] HourTab = Hour.split(":");

        int year = Integer.parseInt(DayTab[0]);
        int month = Integer.parseInt(DayTab[1]);
        int day = Integer.parseInt(DayTab[2]);
        int hour = Integer.parseInt(HourTab[0]);
        int minute = Integer.parseInt(HourTab[1]);
        // int second = Integer.parseInt(TmpTab[0]);
        // TODO: Check why splitting string does not work in this case

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

}
