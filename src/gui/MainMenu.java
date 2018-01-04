package gui;

import org.apache.log4j.PropertyConfigurator;
import simulation.InitParameters;

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
        setSize(800, 600);
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

        JButton btnMrpList = new JButton("Browse MRP lists");
        JButton btnScenario_1_1 = new JButton("Scenario 1 - Shipments in line with forecast");
        JButton btnScenario_1_2 = new JButton("Scenario 1 - Undershipments");
        JButton btnScenario_1_3 = new JButton("Scenario 1 - Overshipments");
        JButton btnScenario_2_1 = new JButton("Scenario 2 - Low minimum production run");
        JButton btnScenario_2_2 = new JButton("Scenario 2 - High minimum production run");
        JButton btnScenario_3_1 = new JButton("Scenario 3 - Safety stock as safety strategy");
        JButton btnScenario_3_2 = new JButton("Scenario 3 - Safety time as safety strategy");
        JButton btnScenario_4_1 = new JButton("Scenario 4 - Production damaged due to natural disaster");
        JButton btnScenario_4_2 = new JButton("Scenario 4 - Production line breakdown");

        miMaterialMaster.setActionCommand("browseMaterialMaster");
        miImportForecast.setActionCommand("importForecast");
        miImportMaterialMaster.setActionCommand("importMaterialMaster");
        miImportCustomerOrders.setActionCommand("importCustomerOrders");
        miGenerateDummyData.setActionCommand("generateDummyData");
        miGenerateDummyStocks.setActionCommand("generateDummyStocks");
        miTruncateMrpTables.setActionCommand("truncateMrpTables");
        miTestScenario.setActionCommand("runTestScenario");
        btnScenario_1_1.setActionCommand("runScenario_1_1");
        btnScenario_1_2.setActionCommand("runScenario_1_2");
        btnScenario_1_3.setActionCommand("runScenario_1_3");
        btnScenario_2_1.setActionCommand("runScenario_2_1");
        btnScenario_2_2.setActionCommand("runScenario_2_2");
        btnScenario_3_1.setActionCommand("runScenario_3_1");
        btnScenario_3_2.setActionCommand("runScenario_3_2");
        btnScenario_4_1.setActionCommand("runScenario_4_1");
        btnScenario_4_2.setActionCommand("runScenario_4_2");
        btnMrpList.setActionCommand("browseMrpList");


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
        btnScenario_1_3.addActionListener(actionEvent);
        btnMrpList.addActionListener(actionEvent);
        btnScenario_1_1.addActionListener(actionEvent);
        btnScenario_1_2.addActionListener(actionEvent);
        btnScenario_1_3.addActionListener(actionEvent);
        btnScenario_2_1.addActionListener(actionEvent);
        btnScenario_2_2.addActionListener(actionEvent);
        btnScenario_3_1.addActionListener(actionEvent);
        btnScenario_3_2.addActionListener(actionEvent);
        btnScenario_4_1.addActionListener(actionEvent);
        btnScenario_4_2.addActionListener(actionEvent);

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        btnMrpList.setPreferredSize(new Dimension(500,25));
        btnMrpList.setBackground(new Color(128,255,165));
        this.add(btnMrpList, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        btnScenario_1_1.setPreferredSize(new Dimension(500,25));
        this.add(btnScenario_1_1, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        btnScenario_1_2.setPreferredSize(new Dimension(500,25));
        this.add(btnScenario_1_2, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        btnScenario_1_3.setPreferredSize(new Dimension(500,25));
        this.add(btnScenario_1_3, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        btnScenario_2_1.setPreferredSize(new Dimension(500,25));
        this.add(btnScenario_2_1, constraints);
        constraints.gridx = 0;
        constraints.gridy = 5;
        btnScenario_2_2.setPreferredSize(new Dimension(500,25));
        this.add(btnScenario_2_2, constraints);
        constraints.gridx = 0;
        constraints.gridy = 6;
        btnScenario_3_1.setPreferredSize(new Dimension(500,25));
        this.add(btnScenario_3_1, constraints);
        constraints.gridx = 0;
        constraints.gridy = 7;
        btnScenario_3_2.setPreferredSize(new Dimension(500,25));
        this.add(btnScenario_3_2, constraints);
        constraints.gridx = 0;
        constraints.gridy = 8;
        btnScenario_4_1.setPreferredSize(new Dimension(500,25));
        this.add(btnScenario_4_1, constraints);
        constraints.gridx = 0;
        constraints.gridy = 9;
        btnScenario_4_2.setPreferredSize(new Dimension(500,25));
        this.add(btnScenario_4_2, constraints);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MainMenu ex = new MainMenu();
            ex.setVisible(true);
        });

        PropertyConfigurator.configure("libs/log4j.properties");
        new InitParameters();

    }
}
