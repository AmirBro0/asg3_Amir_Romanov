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

    @Override
    public String createMedia(MediaContent media) {

        if (media == null) {
            return "Media is null!";
        }

        if (media.getTitle() == null || media.getTitle().isEmpty()) {
            return "Invalid title!";
        }

        if (media.getType() == null) {
            return "Invalid media type!";
        }

        boolean created = repo.createMedia(media);

        return created ? "Media was created!" : "Media creation failed!";
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
    public String updateMedia(MediaContent media) {

        if (media == null) {
            return "Media is null!";
        }

        MediaContent existing = repo.getMedia(media.getId());
        if (existing == null) {
            return "Media not found!";
        }

        if (media.getTitle() == null || media.getTitle().isEmpty()) {
            return "Invalid title!";
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
