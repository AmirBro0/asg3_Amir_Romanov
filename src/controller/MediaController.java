package controller;

import controller.interfaces.IMediaController;
import exception.DatabaseException;
import exception.InvalidMediaException;
import exception.MediaNotFoundException;
import model.MediaContent;
import repository.interfaces.IMediaRepository;

import java.util.List;

public class MediaController implements IMediaController {
    private final IMediaRepository repo;

    public MediaController(IMediaRepository repo) {
        this.repo = repo;
    }

    @Override
    public int createMedia(MediaContent media) {

        if (media == null) {
            throw new InvalidMediaException("Media is null");
        }

        if (media.getTitle() == null || media.getTitle().isEmpty()) {
            throw new InvalidMediaException("Title is empty");
        }

        int id = repo.createMedia(media);

        if (id == -1) {
            throw new DatabaseException("Failed to create media");
        }

        return id;
    }

    public String getMedia(int id) {

        MediaContent media = repo.getMedia(id);

        if (media == null) {
            throw new MediaNotFoundException(id);
        }

        return media.toString();
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
    public boolean updateMedia(MediaContent media) {
        if (media == null) return false;
        return repo.updateMedia(media);
    }

    @Override
    public boolean deleteMedia(int id) {

        if (repo.getMedia(id) == null) {
            throw new MediaNotFoundException(id);
        }

        return repo.deleteMedia(id);
    }
    public MediaContent getMediaObject(int id) {
        MediaContent media = repo.getMedia(id);
        if (media == null) {
            throw new MediaNotFoundException(id);
        }
        return media;
    }


}
