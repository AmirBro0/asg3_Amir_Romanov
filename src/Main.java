import connection.DatabaseConnection;
import connection.IDB;
import repository.IMediaRepository;
import repository.MediaRepository;

public class Main {
    public static void main(String[] args) {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String dbName = System.getenv("DB_NAME");

        IDB db = new DatabaseConnection(url, user, password, dbName);
        IMediaRepository repo = new MediaRepository(db);
        db.getConnection();
    }
}