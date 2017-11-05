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

        newMenuItem.addActionListener( e -> {
            try{
                GridView gv = new GridView();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

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

        //DataLoader dl = new DataLoader();
        //List<Forecast> forecastList = new ArrayList<>();
        //forecastList = dl.getForecastsPerProductLocation(83732531,2751);
    }
}
