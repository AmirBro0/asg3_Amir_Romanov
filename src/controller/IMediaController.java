package controller;

import model.MediaContent;

public interface IMediaController {
    String createMedia(MediaContent media);
    String getMedia(int id);
    String getAllMedias();
    String updateMedia(MediaContent media);
    String deleteMedia(int id);
}
