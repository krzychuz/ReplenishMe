package gui;

import init.DataImporter;
import init.DataLoader;
import init.DummyDataGenerator;
import simulation.InitParameters;
import simulation.ParametersReader;
import simulation.ScenarioParser;

import java.awt.*;
import java.awt.event.KeyEvent;
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

        JMenuItem miMaterialMaster = new JMenuItem("Material master");
        DbRecords.add(miMaterialMaster);

        JMenuItem miImportForecast = new JMenuItem("Import forecast");
        ImportData.add(miImportForecast);

        JMenuItem miImportMaterialMaster = new JMenuItem("Import material master");
        ImportData.add(miImportMaterialMaster);

        JMenuItem miGenerateDummyData = new JMenuItem("Generate dummy data");
        ImportData.add(miGenerateDummyData);

        JMenuItem miTruncateMrpTables = new JMenuItem("Truncate MRP tables");
        ImportData.add(miTruncateMrpTables);

        JButton MrpListButton = new JButton("Browse MRP lists");

        miMaterialMaster.setActionCommand("browseMaterialMaster");
        miImportForecast.setActionCommand("importForecast");
        miImportMaterialMaster.setActionCommand("importMaterialMaster");
        miGenerateDummyData.setActionCommand("generateDummyData");
        miTruncateMrpTables.setActionCommand("truncateMrpTables");
        MrpListButton.setActionCommand("browseMrpList");


        ActionClass actionEvent = new ActionClass();
        miMaterialMaster.addActionListener(actionEvent);
        miImportForecast.addActionListener(actionEvent);
        miImportMaterialMaster.addActionListener(actionEvent);
        miGenerateDummyData.addActionListener(actionEvent);
        miTruncateMrpTables.addActionListener(actionEvent);;
        MrpListButton.addActionListener(actionEvent);

        this.setLayout(new FlowLayout());
        this.add(MrpListButton);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MainMenu ex = new MainMenu();
            ex.setVisible(true);
        });

        ScenarioParser.ParseXmlScenario();
        InitParameters i = new InitParameters();
    }
}
