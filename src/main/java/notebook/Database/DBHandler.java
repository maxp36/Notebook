package notebook.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBHandler {

    private Database database;

    private Connection con;
    private Statement statement;
    private ResultSet rs;

    private static DBHandler instance;

    public static synchronized DBHandler getInstance() {
        if (instance == null) {
            instance = new DBHandler();
        }
        return instance;
    }

    private DBHandler() {
        database = new Database(DatabaseType.MYSQL, "localhost", 3306,
                                "notebook", "root", "root");
        createTable();
    }

    private void connect() {
        con = database.getConnection();
        try {
            if (con != null) statement = con.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void disconnect() {

        try {
            if (rs != null) rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            if (statement != null) statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void executeQuery(String query) {
        connect();

        try {
            if (statement != null) rs = statement.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private boolean execute(String query) {
        connect();

        boolean bool = false;

        try {
            if (statement != null) bool = statement.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return bool;
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
        execute(createTableSQL);
        disconnect();
    }

    public void insertRow(String phone, String name, String email, String note) {
        String insertTableSQL = "INSERT INTO PHONE "
                + "(PHONE, NAME, EMAIL, NOTE) "
                + "VALUES ("
                + "'" + phone + "', "
                + "'" + name + "', "
                + "'" + email + "', "
                + "'" + note + "'"
                + ");";
        execute(insertTableSQL);
        disconnect();
    }

    public ArrayList<ArrayList<String>> selectAll() {
        String selectTableSQL = "SELECT * FROM PHONE;";
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        ArrayList<String> temp;

        executeQuery(selectTableSQL);
        try {
            if (rs != null) {
                while (rs.next()) {
                    temp = new ArrayList<>();
                    String id = rs.getString("ID");
                    String phone = rs.getString("PHONE");
                    String name = rs.getString("NAME");
                    String email = rs.getString("EMAIL");
                    String note = rs.getString("NOTE");
                    temp.add(id);
                    temp.add(phone);
                    temp.add(name);
                    temp.add(email);
                    temp.add(note);
                    list.add(temp);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        disconnect();

        return list;
    }

    public void removeRow(String id) {
        String removeRowSQL = "DELETE FROM PHONE "
                + "WHERE ID = '" + id + "';";
        execute(removeRowSQL);
        disconnect();
    }

    public void updateRow(String id, String phone, String name, String email, String note) {
        String updateSQL = "UPDATE PHONE "
                + "SET "
                + "PHONE = '" + phone + "', "
                + "NAME = '" + name + "', "
                + "EMAIL = '" + email + "', "
                + "NOTE = '" + note + "' "
                + "WHERE ID = '" + id + "';";
        execute(updateSQL);
        disconnect();
    }

    public boolean checkPhoneExists (String phone) {
        String checkSQL = "SELECT * FROM PHONE "
                + "WHERE PHONE = '" + phone + "';";
        executeQuery(checkSQL);
        try {
            if (rs != null){
                if (rs.next()) {
                    return true;  //exists
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        disconnect();
        return false; //not exists
    }

    public String getIdByPhone(String phone) {
        String getIdSQL = "SELECT ID FROM PHONE "
                + "WHERE PHONE = '" + phone + "';";
        executeQuery(getIdSQL);
        try {
            if (rs != null){
                if (rs.next()) {
                    return rs.getString("ID");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        disconnect();
        return "";
    }

}
