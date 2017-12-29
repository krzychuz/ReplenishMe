package gui;

import org.apache.log4j.PropertyConfigurator;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 * Created by Krzysiek on 29.10.2017.
 */
public class MainMenu extends JFrame {

    public MainMenu() {
        initUI();
    }

    private void initUI() {

        setTitle("ReplenishMe - Main menu");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();

        JMenu DbRecords = new JMenu("DB Records");;
        menuBar.add(DbRecords);

        JMenu ImportData = new JMenu("Import data");
        menuBar.add(ImportData);

        JMenu Simulation = new JMenu("Simulation");
        menuBar.add(Simulation);

        JMenuItem miMaterialMaster = new JMenuItem("Material master");
        DbRecords.add(miMaterialMaster);

        JMenuItem miImportForecast = new JMenuItem("Import forecast");
        ImportData.add(miImportForecast);

        JMenuItem miImportMaterialMaster = new JMenuItem("Import material master");
        ImportData.add(miImportMaterialMaster);

        JMenuItem miImportCustomerOrders = new JMenuItem("Import customer orders");
        ImportData.add(miImportCustomerOrders);

        JMenuItem miGenerateDummyData = new JMenuItem("Generate dummy data");
        ImportData.add(miGenerateDummyData);

        JMenuItem miGenerateDummyStocks = new JMenuItem("Generate dummy stocks");
        ImportData.add(miGenerateDummyStocks);

        JMenuItem miTruncateMrpTables = new JMenuItem("Truncate MRP tables");
        ImportData.add(miTruncateMrpTables);

        JMenuItem miTestScenario = new JMenuItem("Test scenario");
        Simulation.add(miTestScenario);

        JMenuItem miScenario1 = new JMenuItem("Scenario 1");
        Simulation.add(miScenario1);

        JMenuItem miScenario2 = new JMenuItem("Scenario 2");
        Simulation.add(miScenario2);

        JMenuItem miScenario3 = new JMenuItem("Scenario 3");
        Simulation.add(miScenario3);

        JMenuItem miScenario4 = new JMenuItem("Scenario 4");
        Simulation.add(miScenario4);

        JButton MrpListButton = new JButton("Browse MRP lists");

        miMaterialMaster.setActionCommand("browseMaterialMaster");
        miImportForecast.setActionCommand("importForecast");
        miImportMaterialMaster.setActionCommand("importMaterialMaster");
        miImportCustomerOrders.setActionCommand("importCustomerOrders");
        miGenerateDummyData.setActionCommand("generateDummyData");
        miGenerateDummyStocks.setActionCommand("generateDummyStocks");
        miTruncateMrpTables.setActionCommand("truncateMrpTables");
        miTestScenario.setActionCommand("runTestScenario");
        miScenario1.setActionCommand("runScenario1");
        miScenario2.setActionCommand("runScenario2");
        miScenario3.setActionCommand("runScenario3");
        miScenario4.setActionCommand("runScenario4");
        MrpListButton.setActionCommand("browseMrpList");


        ActionClass actionEvent = null;
        try {
            actionEvent = new ActionClass();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        miMaterialMaster.addActionListener(actionEvent);
        miImportForecast.addActionListener(actionEvent);
        miImportMaterialMaster.addActionListener(actionEvent);
        miImportCustomerOrders.addActionListener(actionEvent);
        miGenerateDummyData.addActionListener(actionEvent);
        miGenerateDummyStocks.addActionListener(actionEvent);
        miTruncateMrpTables.addActionListener(actionEvent);
        miTestScenario.addActionListener(actionEvent);
        miScenario1.addActionListener(actionEvent);
        miScenario2.addActionListener(actionEvent);
        miScenario3.addActionListener(actionEvent);
        miScenario4.addActionListener(actionEvent);
        MrpListButton.addActionListener(actionEvent);

        this.setLayout(new FlowLayout());
        this.add(MrpListButton);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) throws SQLException {

        EventQueue.invokeLater(() -> {
            MainMenu ex = new MainMenu();
            ex.setVisible(true);
        });

        PropertyConfigurator.configure("libs/log4j.properties");

    }
}
