package notebook.Database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String URL; // = "jdbc:mysql://localhost:3306/notebook";
    private final String USER; // = "root";
    private final String PASSWORD; // = "root";

    private Driver driver;

    public Database(DatabaseType type,
                    String ip,
                    Integer port,
                    String nameDB,
                    String user,
                    String password) {

        String tempType = "";
        switch (type) {
            case MYSQL: {
                tempType = "mysql";
                try {
                    driver = new FabricMySQLDriver();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        URL = "jdbc:" + tempType + "://" + ip + ":" + port + "/" + nameDB;
        USER = user;
        PASSWORD = password;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}
