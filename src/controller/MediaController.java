package controller;

import model.MediaContent;
import model.Movie;
import model.Series;
import repository.IMediaRepository;

import java.util.List;

public class MediaController implements IMediaController {
    private final IMediaRepository repo;

    public MediaController(IMediaRepository repo) {
        this.repo = repo;
    }

    public String createMedia(int id,String title, String type, int duration) {

        MediaContent media = null;

        if ("MOVIE".equals(type)) {
            media = new Movie(id, title, type, duration);
        }


        boolean created = repo.createMedia(media);

        return (created ? "User was created!" : "User creation was failed!");
    }

    public String getMedia(int id) {
        model.MediaContent media = repo.getMedia(id);

        return (media == null ? "User was not found!" : media.toString());
    }

    public String getAllMedias() {
        List<MediaContent> medias = repo.getAllMedias();

        StringBuilder response = new StringBuilder();
        for (MediaContent media : medias) {
            response.append(media.toString()).append("\n");
        }

        return response.toString();
    }

    @Override
    public String updateMedia(int id, String title, String type, int duration) {

        MediaContent existing = repo.getMedia(id);
        if (existing == null) {
            return "Media not found!";
        }

        MediaContent media = null;

        if ("MOVIE".equals(type)) {
            media = new Movie(id, title, type, duration);
        } else if ("SERIES".equals(type)) {
            media = new Series(id, title, type, duration);
        }


        if (media == null) {
            return "Invalid media type!";
        }

        boolean updated = repo.updateMedia(media);

        return updated ? "Media was updated!" : "Media update failed!";
    }

    @Override
    public String deleteMedia(int id) {

        MediaContent media = repo.getMedia(id);
        if (media == null) {
            return "Media not found!";
        }

        boolean deleted = repo.deleteMedia(id);

        return deleted ? "Media was deleted!" : "Media deletion failed!";
    }


}
