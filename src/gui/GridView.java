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
        table = new JTable(buildTableModel(rs));
    }

    private void initUI(){
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // Przetwaranie nazw kolumn
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Przetwarzanie danych w tabeli
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        rs.close();

        return new DefaultTableModel(data, columnNames);

    }
}
