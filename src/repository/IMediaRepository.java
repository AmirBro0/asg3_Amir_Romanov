package repository;

import model.MediaContent;

import java.util.List;

public interface IMediaRepository {
    boolean createMedia(MediaContent media);
    MediaContent getMedia(int id);
    List<MediaContent> getAllMedias();
    boolean updateMedia(MediaContent media);
    boolean deleteMedia(int id);
}
