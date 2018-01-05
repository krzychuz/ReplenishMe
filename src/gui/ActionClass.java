package gui;

import init.DataImporter;
import init.DummyDataGenerator;
import simulation.SimulationExecutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Krzysiek on 05.11.2017.
 */
public class ActionClass implements ActionListener  {

    private DataImporter dataImporter = new DataImporter();
    private SimulationExecutor simulationExecutor = new SimulationExecutor();
    private final JOptionPane optionPane = new JOptionPane("Simulation in progress...",
            JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{},
            null);
    private final JDialog dialog = new JDialog();

    public ActionClass() throws SQLException {
    }

    private void PrepareDialog() {
        dialog.setTitle("Simulation status");
        dialog.setModal(true);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
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
                    new GridView();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "browseMrpList":
                try {
                    new MrpListBrowser();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case "importForecast":
                //dataImporter.loadForecast();
                break;
            case "importMaterialMaster":
                dataImporter.loadMaterialMaster();
                break;
            case "importCustomerOrders":
                //dataImporter.loadCustomerOrders();
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
              try {
                    simulationExecutor.RunTestSimulation();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case "runScenario_1_1":
                PrepareDialog();
                Timer timer = new Timer(1000, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            simulationExecutor.RunScenario1_1();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        dialog.dispose();
                    }
                });
                timer.setRepeats(false);
                timer.start();
                dialog.setVisible(true);
                dialog.setVisible(false);
                break;
            case "runScenario_1_2":
                break;
            default:
                break;
        }
    }

}
