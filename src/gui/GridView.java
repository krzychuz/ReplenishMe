package gui;

import init.DataLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Krzysiek on 29.10.2017.
 */
public class GridView extends JFrame {

    JTable table = null;

    public GridView() throws Exception {
        populateGrid();
        initUI();
    }

    private void populateGrid() throws Exception {
        DataLoader dl = new DataLoader();
        ResultSet rs = dl.getMaterialMaster();
        table = new JTable(TableModelCreator.buildTableModel(rs));
    }

    private void initUI(){
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
    }

}
