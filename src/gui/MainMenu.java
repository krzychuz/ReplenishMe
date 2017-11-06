package gui;

import calculation.Forecast;
import init.DataLoader;
import init.DummyDataGenerator;

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
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();

        JMenu DbRecords = new JMenu("DB Records");
        DbRecords.setMnemonic(KeyEvent.VK_F);
        menuBar.add(DbRecords);

        JMenuItem newMenuItem = new JMenuItem("Material master", KeyEvent.VK_N);
        DbRecords.add(newMenuItem);

        JButton MrpListButton = new JButton("Browse MRP lists");

        newMenuItem.setActionCommand("browseMaterialMaster");
        MrpListButton.setActionCommand("browseMrpList");

        ActionClass actionEvent = new ActionClass();
        newMenuItem.addActionListener(actionEvent);
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
        DataLoader.loadMaterialMaster();
        DummyDataGenerator.GenerateDummyShipments();
        DummyDataGenerator.GenerateDummyDeliveries();
        DummyDataGenerator.GenerateDummyForecast();
        DataLoader dl = new DataLoader();
    }
}
