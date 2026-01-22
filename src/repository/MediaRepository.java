package repository;

import connection.IDB;
import model.MediaContent;
import model.Movie;
import model.Series;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaRepository implements IMediaRepository {
    private final IDB db;

    public MediaRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createMedia(MediaContent media) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO media_content(title,type,duration) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, media.getTitle());
            st.setString(2, media.getType());
            st.setInt(3, media.getDuration());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public MediaContent getMedia(int id) {

        String sql = "SELECT id, title, type, duration FROM media WHERE id=?";

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
                    media = new Series(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("duration")
                    );
                }


                System.out.println("Media type: " + type);

                return media;
            }

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }


    @Override
    public List<MediaContent> getAllMedias() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id, title, type, duration FROM media_content";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<MediaContent> medias = new ArrayList<>();
            while (rs.next()) {
                MediaContent media;
                String type = rs.getString("type");
                if ("MOVIE".equals(type)) {
                    media = new Movie(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("type"),
                            rs.getInt("duration"));
                } else if ("SERIES".equals(type)) {
                    media = new Series(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("type"),
                            rs.getInt("duration"));
                } else {
                    continue;
                }

                medias.add(media);

            }

            return medias;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean updateMedia(MediaContent media) {

        String sql = "UPDATE media_content SET title = ?, type = ?, duration = ? WHERE id = ? ";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, media.getTitle());
            st.setString(2, media.getType());
            st.setInt(3, media.getDuration());
            st.setInt(4, media.getId());

            int rows = st.executeUpdate();

            return rows > 0;

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
