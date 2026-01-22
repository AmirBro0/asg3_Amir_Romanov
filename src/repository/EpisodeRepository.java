package repository;

import connection.IDB;
import model.Episodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpisodeRepository implements IEpisodeRepository {

    private final IDB db;

    public EpisodeRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<Episodes> getEpisodesBySeriesId(int seriesId) {

        List<Episodes> episodes = new ArrayList<>();
        String sql = "SELECT episode_name, minute FROM episodes WHERE series_id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, seriesId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                episodes.add(new Episodes(
                        rs.getString("episode_name"),
                        rs.getInt("minute")
                ));
            }

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return episodes;
    }

    @Override
    public boolean addEpisode(Episodes episode, int seriesId) {

        String sql = "INSERT INTO episodes(series_id, episode_name, minute) VALUES (?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, seriesId);
            st.setString(2, episode.getEpisodeName());
            st.setInt(3, episode.getMinute());

            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }
}
