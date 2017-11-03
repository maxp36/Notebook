package notebook;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {

    private static final String URL = "jdbc:mysql://localhost:3306/notebook";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Driver driver;
    private Connection con;
    private Statement statement;
    private ResultSet rs;

    public DBHandler() {
        try {
            driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        createTable();
    }

    private void connect() {
        //if (con == null) {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

        }
        //}
        //if (statement == null) {
        try {
            statement = con.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //}
    }

    private void disconnect() {
        //if (con != null) {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //}
        //if (statement != null) {
        try {
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //}
    }

    private void closeResultSet() {
        if (rs != null) {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void executeQuery(String query) {
        connect();

        //try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        //    Statement statement = connection.createStatement()) {
        try {
            rs = statement.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        /*if (rs == null) {
            try {
                rs = statement.executeQuery(query);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }*/

        //return rs;
    }

    private boolean execute(String query) {
        connect();

        boolean bool = false;
        //try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        //    Statement statement = connection.createStatement()) {
        try {
            bool = statement.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        /*try {
            bool = statement.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/

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
        closeResultSet();
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
        closeResultSet();
    }

    public ArrayList<ArrayList<String>> selectAll() {
        String selectTableSQL = "SELECT * FROM PHONE;";
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        ArrayList<String> temp;
        //ResultSet result = executeQuery(selectTableSQL);
        executeQuery(selectTableSQL);
        try {
            while (rs.next()) {
                temp = new ArrayList<>();
                String phone = rs.getString("PHONE");
                String name = rs.getString("NAME");
                String email = rs.getString("EMAIL");
                String note = rs.getString("NOTE");
                temp.add(phone);
                temp.add(name);
                temp.add(email);
                temp.add(note);
                list.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        disconnect();
        closeResultSet();

        return list;
    }

    public void removeRow(String phone) {
        String removeRowSQL = "DELETE FROM PHONE "
                + "WHERE PHONE = '" + phone + "';";
        execute(removeRowSQL);
        disconnect();
        closeResultSet();
    }


}
