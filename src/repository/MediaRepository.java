package repository;

import connection.interfaces.IDB;
import model.MediaContent;
import model.Movie;
import model.Series;
import repository.interfaces.IEpisodeRepository;
import repository.interfaces.IMediaRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaRepository implements IMediaRepository {
    private final IDB db;

    public MediaRepository(IDB db) {
        this.db = db;
    }

    @Override
    public int createMedia(MediaContent media) {

        String sql = "INSERT INTO media_content(title, type, duration) VALUES (?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, media.getTitle());
            st.setString(2, media.getType());
            st.setInt(3, media.getDuration());

            st.executeUpdate();

            ResultSet keys = st.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1); // ← ВАЖНО
            }

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return -1;
    }


    @Override
    public MediaContent getMedia(int id) {

        String sql = "SELECT id, title, type, duration FROM media_content WHERE id=?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                String type = rs.getString("type");


                MediaContent media = null;

                if ("MOVIE".equals(type)) {
                    media = new Movie(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("duration")
                    );
                } else if ("SERIES".equals(type)) {
                    Series series = new Series(
                            rs.getInt("id"),
                            rs.getString("title")
                    );
                    IEpisodeRepository episodeRepo = new EpisodeRepository(db);
                    series.setEpisodes(
                            episodeRepo.getEpisodesBySeriesId(series.getId()));
                    media = series;
                }


                return media;
            }

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }



    @Override
    public List<MediaContent> getAllMedias() {

        List<MediaContent> medias = new ArrayList<>();
        String sql = "SELECT id, title, type, duration FROM media_content";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            IEpisodeRepository episodeRepo = new EpisodeRepository(db);

            while (rs.next()) {
                String type = rs.getString("type");

                if ("MOVIE".equals(type)) {

                    medias.add(new Movie(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("duration")
                    ));

                } else if ("SERIES".equals(type)) {

                    Series series = new Series(
                            rs.getInt("id"),
                            rs.getString("title")
                    );

                    series.setEpisodes(
                            episodeRepo.getEpisodesBySeriesId(series.getId())
                    );

                    medias.add(series);
                }
            }

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return medias;
    }


    @Override
    public boolean updateMedia(MediaContent media) {

        String sql = "UPDATE media_content SET title = ?, type = ?, duration = ? WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, media.getTitle());
            st.setString(2, media.getType());
            st.setInt(3, media.getDuration());
            st.setInt(4, media.getId());

            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }
    @Override
    public boolean deleteMedia(int id) {

        String sql = "DELETE FROM media_content WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);

            int rows = st.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

}
