import connection.DatabaseConnection;
import connection.IDB;
import controller.IMediaController;
import controller.MediaController;
import model.MediaContent;
import model.Movie;
import model.Series;
import repository.IMediaRepository;
import repository.MediaRepository;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String dbName = System.getenv("DB_NAME");

        IDB db = new DatabaseConnection(url, user, password, dbName);
        IMediaRepository repo = new MediaRepository(db);
        db.getConnection();


        MediaContent Ar = new Movie(1 , "Harry Poter" ,"MOVIE",120 );
        MediaContent Ab = new Movie(2 , "500 days of summer" ,"MOVIE",90 );
        MediaContent As = new Movie(1 ,"The child of the internet" , "MOVIE" , 110);
        IMediaRepository repp1 = new MediaRepository(db);
        IMediaController conrepp1 = new MediaController(repp1);
//        System.out.println(
//                conrepp1.createMedia(Ab)
//        );
//        System.out.println(
//                conrepp1.createMedia(Ar)
//        );
        System.out.println(conrepp1.getMedia(1));
//        System.out.println(conrepp1.getAllMedias() + "\n\n\n\n\n\n\n\n\n");
//
//
//        conrepp1.updateMedia(As);
//
//        System.out.println(conrepp1.getAllMedias() + "\n\n\n\n\n\n\n\n\n");
//        conrepp1.deleteMedia(1);
//        System.out.println(conrepp1.getAllMedias() + "\n\n\n\n\n\n\n\n\n");
    }
}