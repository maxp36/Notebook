package notebook;

import notebook.Database.NotebookDatabase;
import notebook.Database.DatabaseType;

public class Notebook {
    private final DatabaseType dbType = DatabaseType.MYSQL;
    private final String dbIP = "localhost";
    private final Integer dbPort = 3306;
    private final String dbName = "notebook";
    private final String dbUser = "root";
    private final String dbPassword = "root";

    private final NotebookDatabase db;

    public Notebook() {
        db = new NotebookDatabase(dbType, dbIP, dbPort, dbName, dbUser, dbPassword);
    }

    public NotebookDatabase getDb() {
        return db;
    }
}
