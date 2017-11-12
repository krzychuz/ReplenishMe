package gui;

import calculation.MRPElement;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Krzysiek on 12.11.2017.
 */
public class MrpTableModel extends AbstractTableModel {

    private List<MRPElement> MrpElements;
    String[] headers = {"Date","MRP Element","MRP Element Data","Receipt/Reqmnt","Available Qty","Plant"};

    public MrpTableModel(List<MRPElement> list) {
        this.MrpElements  = list;
    }

    @Override
    public int getRowCount() {
        return MrpElements.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        MRPElement e = MrpElements.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = e.getDate();
                break;
            case 1:
                value = e.getMRPType();
                break;
            case 2:
                value = e.getMRPElementData();
                break;
            case 3:
                value = e.getMRPElementQuantity();
                break;
            case 4:
                value = e.getAvailableQuantity();
                break;
            case 5:
                value = e.getPlant();
                break;
        }
        return value;
    }

    @Override
    public String getColumnName(int col) {
        return headers[col];
    }
}
