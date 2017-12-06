package gui;

import calculation.MRPElement;
import calculation.MRPList;
import init.DataLoader;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Krzysiek on 05.11.2017.
 */
public class MrpListBrowser extends JFrame {

    JTable MrpTable = new JTable();
    int selectedProduct;
    int selectedPlant;

    public MrpListBrowser(){
        initUI();
        setVisible(true);
    }

    private void populateGrid(int product, int location) throws Exception {
        DataLoader dl = new DataLoader();
        List<MRPElement> MRPList;
        MRPList = dl.getMrpElementsPerProductLocation(product,location);
        MrpTableModel model = new MrpTableModel(MRPList);
        MrpTable.setModel(model);
    }

    private void initUI() {
        setTitle("ReplenishMe - Browse MRP List");
        setSize(650, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel upperButtonsPanel = new JPanel(new GridBagLayout());
        JPanel lowerButtonsPanel = new JPanel(new GridBagLayout());
        JPanel gridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        JScrollPane gridPane;

        JComboBox<Integer> productListComboBox = new JComboBox<>();
        JComboBox<Integer> plantListComboBox = new JComboBox<>();
        JButton GenerateButton = new JButton("Generate MRP list");
        JButton AdHocMrpButton = new JButton("Ad-hoc MRP run");

        DataLoader dl = new DataLoader();
        List<Integer> productList = dl.getProductList();
        List<Integer> plantList = dl.getPlantList();

        for(int i : productList) {
            productListComboBox.addItem(i);
        }

        for(int i : plantList) {
            plantListComboBox.addItem(i);
        }


        constraints.insets = new Insets(10,10,10,10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        upperButtonsPanel.add(productListComboBox,constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        upperButtonsPanel.add(plantListComboBox,constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        upperButtonsPanel.add(GenerateButton,constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        gridPane = new JScrollPane(MrpTable);
        gridPanel.add(gridPane,constraints);

        this.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(upperButtonsPanel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(gridPanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        lowerButtonsPanel.add(AdHocMrpButton,constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(lowerButtonsPanel,constraints);

        GenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedProduct = productList.get(productListComboBox.getSelectedIndex());
                selectedPlant = plantList.get(plantListComboBox.getSelectedIndex());
                try {
                    populateGrid(selectedProduct,selectedPlant);
                    gridPanel.add(gridPane,constraints);
                    revalidate();
                    repaint();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        AdHocMrpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
