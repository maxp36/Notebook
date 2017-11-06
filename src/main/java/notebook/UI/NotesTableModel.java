package notebook.UI;

import notebook.Database.DBHandler;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class NotesTableModel extends AbstractTableModel {

    private DBHandler handler;

    private ArrayList<ArrayList<String>> notes;

    public NotesTableModel() {
        handler = DBHandler.getInstance();
        updateTable();
    }

    @Override
    public int getRowCount() {
        return notes.size();
    }

    @Override
    public int getColumnCount() {
        if (notes.isEmpty()) {
            return 0;
        } else {
            return notes.get(0).size();
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return notes.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID";
            case 1: return "PHONE";
            case 2: return "NAME";
            case 3: return "EMAIL";
            case 4: return "NOTE";
            default: return "";
        }
    }

    public String getIdByPhone(String phone) {
        return handler.getIdByPhone(phone);
    }

    public void updateTable() {
        notes = handler.selectAll();
        this.fireTableDataChanged();
    }
}
