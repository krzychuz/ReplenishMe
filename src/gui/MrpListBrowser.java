package gui;

import calculation.MRPElement;
import calculation.MRPList;
import db.DataLoader;
import simulation.GlobalParameters;
import simulation.SimulationExecutor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Krzysiek on 05.11.2017.
 */
public class MrpListBrowser extends JFrame {

    JTable MrpTable = new JTable();
    private int selectedProduct;
    private int selectedPlant;
    private List<Integer> productList;
    private List<Integer> plantList;
    private DataLoader dl;
    private SimulationExecutor se;
    private JLabel CurrentSimulationTime;
    private JComboBox<Integer> productListComboBox;
    private JComboBox<Integer> plantListComboBox;
    private MRPList mrpList;
    private JPanel gridPanel;
    GridBagConstraints constraints;
    private JScrollPane gridPane;

    public MrpListBrowser() throws SQLException {
        dl = new DataLoader();
        initUI();
        setVisible(true);
        se = new SimulationExecutor();
        mrpList = new MRPList();
    }

    private void populateGrid(List<MRPElement> mrpElements) throws Exception {
        MrpTableModel model = new MrpTableModel(mrpElements);
        MrpTable.setModel(model);
    }

    private void getProductList(){
        productList = dl.getProductList();
    }

    private void  getPlantList(){
        plantList = dl.getPlantList();
    }


    private void initUI() {
        setTitle("ReplenishMe - Browse MRP List");
        setSize(650, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel upperButtonsPanel = new JPanel(new GridBagLayout());
        JPanel lowerButtonsPanel = new JPanel(new GridBagLayout());
        JPanel simulationTimePanel = new JPanel(new GridBagLayout());
        gridPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        productListComboBox = new JComboBox<>();
        plantListComboBox = new JComboBox<>();
        JButton GenerateButton = new JButton("Generate MRP list");

        JButton AdHocMrpButton = new JButton("Ad-hoc MRP run");
        JButton SingleSimulationStep = new JButton("Tick++");
        JButton MultipleSimulationSteps = new JButton("Day++");
        CurrentSimulationTime = new JLabel("Current time: ");

        getPlantList();
        getProductList();

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
        constraints.gridx = 1;
        constraints.gridy = 0;
        lowerButtonsPanel.add(SingleSimulationStep,constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        lowerButtonsPanel.add(MultipleSimulationSteps,constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(lowerButtonsPanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        simulationTimePanel.add(CurrentSimulationTime);
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(simulationTimePanel,constraints);

        UpdateSimulationTimeLabel();


        GenerateButton.addActionListener(e ->  {
            try {
                UpdateMrpGrid();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        AdHocMrpButton.addActionListener(e -> {
            try {
                mrpList.runMRP();
                UpdateMrpGrid();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        SingleSimulationStep.addActionListener(e -> {
            UpdateMrpGrid();
            try {
                se.Tick();
                UpdateMrpGrid();
                UpdateSimulationTimeLabel();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        MultipleSimulationSteps.addActionListener(e -> {
            try {
                int TicksPerDay = 24 / GlobalParameters.Tick;
                for(int i = 0; i < TicksPerDay; i++) {
                    se.Tick();
                }
                UpdateMrpGrid();
                UpdateSimulationTimeLabel();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

    }

    private void UpdateSimulationTimeLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        CurrentSimulationTime.setText("Current time: " + sdf.format(GlobalParameters.currentTime));
    }

    private void UpdateMrpGrid() {
        selectedProduct = productList.get(productListComboBox.getSelectedIndex());
        selectedPlant = plantList.get(plantListComboBox.getSelectedIndex());
        mrpList.setMRPList(selectedProduct,selectedPlant);
        try {
            populateGrid(mrpList.getMRPList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        gridPanel.add(gridPane,constraints);
        revalidate();
        repaint();
    }
}
