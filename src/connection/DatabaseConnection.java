package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements IDB {
    private String host;
    private String username;
    private String password;
    private String dbName;

    private Connection connection;

    public DatabaseConnection(String host, String username, String password, String dbName) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
    }

    @Override
    public Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(host + "/" + dbName, username, password);
            return connection;
        } catch (Exception e) {
            System.out.println("failed to connect to postgres: " + e.getMessage());

            return null;
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection close error: " + e.getMessage());
            }
        }
        else {
            System.out.println("Connection is already closed");
        }
    }
}