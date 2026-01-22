package connection;

import java.sql.Connection;

public interface IDB {
    Connection getConnection();
    void close();
}
