package notebook.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class DBHandler {

    private final Database db;

    private Connection con;
    private Statement statement;
    private ResultSet rs;

    //private static DBHandler instance;

    /*public static synchronized DBHandler getInstance(NotebookDatabase db) {
        if (instance == null) {
            instance = new DBHandler(db);
        }
        return instance;
    }*/

    //private DBHandler(NotebookDatabase db) {
    public DBHandler(Database db) {
        this.db = db;
    }

    private void connect() {
        con = db.getConnection();
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


    protected void createTable(String query) {
        execute(query);
        disconnect();
    }

    protected void insertRow(String query) {
        execute(query);
        disconnect();
    }

    protected ArrayList<ArrayList<String>> selectAll(int colNum) {
        String selectTableSQL = "SELECT * FROM PHONE;";
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        ArrayList<String> tempList;

        executeQuery(selectTableSQL);
        try {
            if (rs != null) {
                while (rs.next()) {
                    tempList = new ArrayList<>();
                    for (int i = 1; i < colNum + 1; i++) {
                        tempList.add(rs.getString(i));
                    }
                    list.add(tempList);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        disconnect();

        return list;
    }

    protected void removeRow(String query) {
        execute(query);
        disconnect();
    }

    protected void updateRow(String query) {
        execute(query);
        disconnect();
    }

    protected boolean checkExists(String query, int id) {
        executeQuery(query);
        try {
            if (rs != null){
                while (rs.next()) {
                    if (rs.getInt(1) != id) {
                        return true;  //exists
                    }
                }
                return false;  //not exists
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        disconnect();
        return false;  //not exists
    }

    protected String getBy(String query, int index) {
        executeQuery(query);
        try {
            if (rs != null){
                if (rs.next()) {
                    return rs.getString(index + 1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        disconnect();
        return "";
    }

}
