package gui;

import init.DataLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Krzysiek on 05.11.2017.
 */
public class MrpListBrowser extends JFrame {

    JTable MrpTable;

    public MrpListBrowser(){
        initUI();
        setVisible(true);
    }

    private void populateGrid(int product, int location) throws Exception {
        DataLoader dl = new DataLoader();
        ResultSet rs = dl.getMrpList(product,location);
        MrpTable = new JTable(TableModelCreator.buildTableModel(rs));
    }

    private void initUI() {
        setTitle("ReplenishMe - Browse MRP List");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        JPanel gridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();


        JComboBox<Integer> productListComboBox = new JComboBox<>();
        JComboBox<Integer> plantListComboBox = new JComboBox<>();
        JButton GenerateButton = new JButton("Generate MRP list");

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
        buttonsPanel.add(productListComboBox,constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        buttonsPanel.add(plantListComboBox,constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        buttonsPanel.add(GenerateButton,constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        gridPanel.add(new JScrollPane(MrpTable),constraints);

        this.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(buttonsPanel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(gridPanel,constraints);

        GenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedProduct = productList.get(productListComboBox.getSelectedIndex());
                int selectedPlant = plantList.get(plantListComboBox.getSelectedIndex());
                try {
                    populateGrid(selectedProduct,selectedPlant);
                    gridPanel.add(new JScrollPane(MrpTable),constraints);
                    revalidate();
                    repaint();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
