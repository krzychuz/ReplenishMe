package simulation;

import calculation.MRPList;
import db.DataInterface;
import db.DataLoader;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SimulationExecutor {

    private boolean IsSimulationRunning;
    private DataInterface di;
    private DataLoader dl;
    private int TickCounter;

    public SimulationExecutor () throws SQLException {
        new InitParameters();
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
        System.out.println("TICK: " + GlobalParameters.currentTime);



        UnloadShipments();
        UpdateProduction();
        ReleaseQmLots();
        DeployStock();
        ShipDeliveries();
        InsertOrders();
        CreateDeliveries();

        if(DayPassed) RunMrpInWholeNetwork();
    }

    private void UnloadShipments() {
        // TODO: Implementation
    }

    private void UpdateProduction() {
        // TODO: Implementation
    }

    private void ReleaseQmLots() {
        // TODO: Implementation
    }

    private void DeployStock() {
        // TODO: Implementation
    }

    private void ShipDeliveries() {
        // TODO: Implementation
    }

    private void InsertOrders() {
        // TODO: Implementation
    }

    private void CreateDeliveries() {
        // TODO: Implementation
    }

    private void RunMrpInWholeNetwork() throws SQLException {
        int PlantsTable[] = new int[]{2621, 2751, 9979, 4850, 4853, 5053, 2725};
        // TODO: Implement mechanism for automatic hierarchy recognizing within given network
        System.out.println("\n*** Global MRP run ***\n");

        for (int plant : PlantsTable) {
            List<Integer> productList = dl.getProductsPerPlant(plant);
            for (int product : productList) {
                MRPList mrpList = new MRPList();
                mrpList.setMRPList(product,plant);
                mrpList.runMRP();
            }
        }
    }

    private void UpdateSimulationTime() {
        int tick = GlobalParameters.Tick;
        Calendar cal = Calendar.getInstance();
        cal.setTime(GlobalParameters.currentTime);
        cal.add(Calendar.HOUR_OF_DAY, tick);
        GlobalParameters.currentTime = cal.getTime();
    }

}
