package controller.interfaces;

import model.MediaContent;

import java.util.List;

public interface IMediaController {

    int createMedia(MediaContent media);
    String getMedia(int id);
    String getAllMedias();
    boolean updateMedia(MediaContent media);
    boolean deleteMedia(int id);
    MediaContent getMediaObject(int id);
    List<MediaContent> getAllMediaObjects();
}
