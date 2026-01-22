package controller;

public interface IMediaController {
    String createMedia(int id,String title, String type, int duration);
    String getMedia(int id);
    String getAllMedias();
    String updateMedia(int id, String title, String type, int duration);
    String deleteMedia(int id);
}
