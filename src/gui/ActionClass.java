package gui;

import init.DataImporter;
import init.DummyDataGenerator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Krzysiek on 05.11.2017.
 */
public class ActionClass implements ActionListener  {

    DataImporter dataImporter = new DataImporter();

    @Override
    public void actionPerformed(ActionEvent e) {
                String action = (e.getActionCommand());
        DummyDataGenerator dummyDataGenerator = new DummyDataGenerator();
        switch (action) {
            case "browseMaterialMaster":
                try{
                    GridView gv = new GridView();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "browseMrpList":
                MrpListBrowser mrp = new MrpListBrowser();
                break;
            case "importForecast":
                dataImporter.loadForecast();
                break;
            case "importMaterialMaster":
                dataImporter.loadMaterialMaster();
                break;
            case "generateDummyData":
                dummyDataGenerator.GenerateDummyShipments(5053,2751,83732410,10);
                dummyDataGenerator.GenerateDummyShipments(2751,2621,83732410,10);
                dummyDataGenerator.GenerateDummyDeliveries(4853,2751,83732410,10);
                dummyDataGenerator.GenerateDummyDeliveries(2725,2621,83732410,10);
                break;
            case "truncateMrpTables":
                dummyDataGenerator.TruncateMrpTables();
                break;
            default:
                break;
        }
    }

}
