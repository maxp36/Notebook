package notebook.UI;

import notebook.Contact;
import notebook.Database.DBHandler;
import notebook.NotebookController;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class NotesTableModel extends AbstractTableModel {

    private NotebookController controller;

    private ArrayList<Contact> contacts;

    public NotesTableModel(NotebookController controller) {
        this.controller = controller;
        updateTable();
    }

    @Override
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    public int getColumnCount() {
        if (contacts.isEmpty()) {
            return 0;
        } else {
            return contacts.get(0).getNumCol();
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact c = contacts.get(rowIndex);
        switch (columnIndex) {
            case 0 : return c.getId();
            case 1 : return c.getPhone();
            case 2 : return c.getName();
            case 3 : return c.getEmail();
            case 4 : return c.getNote();
            default: return "";
        }
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

    /*public String getIdByPhone(String phone) {
        return controller.getIdByPhone(phone);
    }*/

    public void updateTable() {
        contacts = controller.getContacts();
        this.fireTableDataChanged();
    }
}
