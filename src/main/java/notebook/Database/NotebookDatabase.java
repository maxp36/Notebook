package notebook.Database;

import java.sql.Connection;

public class NotebookDatabase extends Database {

    public NotebookDatabase(DatabaseType dbType,
                            String dbIP,
                            Integer dbPort,
                            String dbName,
                            String dbUser,
                            String dbPassword) {

        super(dbType, dbIP, dbPort, dbName, dbUser, dbPassword);
    }

    public Connection getConnection() {
        return super.getConnection();
    }
}
