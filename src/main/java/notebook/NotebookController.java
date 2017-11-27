package notebook;

import notebook.Database.DBHandler;
import notebook.Database.NBDBHandler;

import java.util.ArrayList;

public class NotebookController {

    private DBHandler dbHandler;

    public NotebookController(Notebook notebook) {
        dbHandler = new NBDBHandler(notebook.getDb());
    }

    public void addContact(Contact contact) {
        ((NBDBHandler)dbHandler).insertRow(contact.getPhone(),
                                            contact.getName(),
                                            contact.getEmail(),
                                            contact.getNote());
    }

    public ArrayList<Contact> getContacts() {
        ArrayList<ArrayList<String>> list = ((NBDBHandler)dbHandler).selectAll();
        ArrayList<Contact> contacts = new ArrayList<>();
        for (ArrayList<String> c : list) {
            Contact con = new Contact((Integer.parseInt(c.get(0))), c.get(1), c.get(2), c.get(3), c.get(4));
            contacts.add(con);
        }
        return contacts;
    }

    public void removeContact(Contact contact) {
        ((NBDBHandler)dbHandler).removeRow(String.valueOf(contact.getId()));
    }

    public void updateContact(Contact contact) {
        ((NBDBHandler)dbHandler).updateRow(String.valueOf(contact.getId()),
                                                            contact.getPhone(),
                                                            contact.getName(),
                                                            contact.getEmail(),
                                                            contact.getNote());
    }

    public boolean isExists(Contact contact) {
        return ((NBDBHandler)dbHandler).checkPhoneExists(contact.getPhone(), contact.getId());
    }

    public int getIdByPhone(String phone) {
        return ((NBDBHandler)dbHandler).getIdByPhone(phone);
    }

}
