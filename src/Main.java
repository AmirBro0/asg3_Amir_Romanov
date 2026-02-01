import connection.DatabaseConnection;
import connection.interfaces.IDB;
import controller.MediaController;
import controller.interfaces.IMediaController;
import repository.EpisodeRepository;
import repository.interfaces.IEpisodeRepository;
import repository.interfaces.IMediaRepository;
import repository.MediaRepository;
import services.MediaService;
import services.MediaStreamingApp;
import services.interfaces.IMediaService;

public class Main {

    public static void main(String[] args) {

        IDB db = new DatabaseConnection(
                System.getenv("DB_HOST"),
                System.getenv("DB_USER"),
                System.getenv("DB_PASSWORD"),
                System.getenv("DB_NAME")
        );

//        IMediaRepository mediaRepo = new MediaRepository(db);
//        IEpisodeRepository episodeRepo = new EpisodeRepository(db);
//        MediaController controller = new MediaController(mediaRepo);
        IMediaRepository mediaRepo = new MediaRepository(db);
        IMediaService mediaService = new MediaService(mediaRepo);

        IMediaController controller = new MediaController(mediaService);
        IEpisodeRepository episodeRepo = new EpisodeRepository(db);


        MediaStreamingApp app =
                new MediaStreamingApp(controller, episodeRepo);

        app.open();


    }
}
