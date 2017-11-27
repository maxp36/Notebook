package notebook.Database;

import java.util.ArrayList;

public class NBDBHandler extends DBHandler {

    private final int NUM_COL = 5;

    public NBDBHandler(Database db) {
        super(db);
        createTable(); //Important!
    }

    private void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS PHONE ("
                + "ID INT NOT NULL AUTO_INCREMENT, "
                + "PHONE VARCHAR(12) NOT NULL, "
                + "NAME VARCHAR(20) NOT NULL, "
                + "EMAIL VARCHAR(20) NOT NULL, "
                + "NOTE VARCHAR(100) NOT NULL, "
                + "PRIMARY KEY (ID), "
                + "UNIQUE (PHONE) "
                + ");";
        super.createTable(createTableSQL);
    }

    public void insertRow(String phone, String name, String email, String note) {
        String insertRowSQL = "INSERT INTO PHONE "
                + "(PHONE, NAME, EMAIL, NOTE) "
                + "VALUES ("
                + "'" + phone + "', "
                + "'" + name + "', "
                + "'" + email + "', "
                + "'" + note + "'"
                + ");";
        super.insertRow(insertRowSQL);
    }

    public ArrayList<ArrayList<String>> selectAll() {
        return super.selectAll(NUM_COL);
    }

    public void removeRow(String id) {
        String removeRowSQL = "DELETE FROM PHONE "
                + "WHERE ID = '" + id + "';";
        super.removeRow(removeRowSQL);
    }

    public void updateRow(String id, String phone, String name, String email, String note) {
        String updateRowSQL = "UPDATE PHONE "
                + "SET "
                + "PHONE = '" + phone + "', "
                + "NAME = '" + name + "', "
                + "EMAIL = '" + email + "', "
                + "NOTE = '" + note + "' "
                + "WHERE ID = '" + id + "';";
        super.updateRow(updateRowSQL);
    }

    public boolean checkPhoneExists (String phone, int id) {
        String checkPhoneSQL = "SELECT * FROM PHONE "
                + "WHERE PHONE = '" + phone + "';";
        return super.checkExists(checkPhoneSQL, id);
    }

    public int getIdByPhone(String phone) {
        String getIdByPhoneSQL = "SELECT ID FROM PHONE "
                + "WHERE PHONE = '" + phone + "';";
        return Integer.parseInt(super.getBy(getIdByPhoneSQL, 0));
    }

}
