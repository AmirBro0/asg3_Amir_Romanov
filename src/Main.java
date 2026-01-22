import connection.DatabaseConnection;
import connection.IDB;
import controller.IMediaController;
import controller.MediaController;
import model.Episodes;
import model.MediaContent;
import model.Movie;
import model.Series;
import repository.EpisodeRepository;
import repository.IEpisodeRepository;
import repository.IMediaRepository;
import repository.MediaRepository;
import services.MediaStreamingApp;

public class Main {

    public static void main(String[] args) {

        IDB db = new DatabaseConnection(
                System.getenv("DB_HOST"),
                System.getenv("DB_USER"),
                System.getenv("DB_PASSWORD"),
                System.getenv("DB_NAME")
        );

        IMediaRepository mediaRepo = new MediaRepository(db);
        IEpisodeRepository episodeRepo = new EpisodeRepository(db);
        MediaController controller = new MediaController(mediaRepo);

        MediaStreamingApp app =
                new MediaStreamingApp(controller, episodeRepo);

        app.open();
    }
}
