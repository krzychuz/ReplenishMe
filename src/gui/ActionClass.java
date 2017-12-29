package gui;

import init.DataImporter;
import init.DummyDataGenerator;
import simulation.SimulationExecutor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Krzysiek on 05.11.2017.
 */
public class ActionClass implements ActionListener  {

    DataImporter dataImporter = new DataImporter();

    public ActionClass() throws SQLException {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                String action = (e.getActionCommand());
        DummyDataGenerator dummyDataGenerator = null;
        try {
            dummyDataGenerator = new DummyDataGenerator();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        switch (action) {
            case "browseMaterialMaster":
                try{
                    GridView gv = new GridView();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "browseMrpList":
                try {
                    MrpListBrowser mrp = new MrpListBrowser();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case "importForecast":
                dataImporter.loadForecast();
                break;
            case "importMaterialMaster":
                dataImporter.loadMaterialMaster();
                break;
            case "importCustomerOrders":
                dataImporter.loadCustomerOrders();
                break;
            case "generateDummyData":
                dummyDataGenerator.GenerateDummyShipments(5053,2751,83732410,10);
                dummyDataGenerator.GenerateDummyShipments(2751,2621,83732410,10);
                dummyDataGenerator.GenerateDummyDeliveries(4853,2751,83732410,10);
                break;
            case "generateDummyStocks":
                dummyDataGenerator.GenerateDummyStocksForAllProducts();
                break;
            case "truncateMrpTables":
                dummyDataGenerator.TruncateMrpTables();
                break;
            case "runTestScenario":
                SimulationExecutor simulationExecutor = null;
                try {
                    simulationExecutor = new SimulationExecutor();
                    simulationExecutor.runSimulation();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case "runScenario1":
                break;
            case "runScenario2":
                break;
            case "runScenario3":
                break;
            case "runScenario4":
                break;
            default:
                break;
        }
    }

}
