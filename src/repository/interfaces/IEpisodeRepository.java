package repository.interfaces;

import model.Episodes;

import java.util.List;

public interface IEpisodeRepository {
    List<Episodes> getEpisodesBySeriesId(int seriesId);
    boolean addEpisode(Episodes episode, int seriesId);
}
