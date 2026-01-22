package controller.interfaces;

import model.MediaContent;

public interface IMediaController {
    int createMedia(MediaContent media);
    String getMedia(int id);
    String getAllMedias();
    boolean updateMedia(MediaContent media);
    boolean deleteMedia(int id);
}
