package services;

import exception.*;
import model.MediaContent;
import repository.interfaces.IMediaRepository;
import services.interfaces.IMediaService;

import java.util.List;

public class MediaService implements IMediaService {

    private final IMediaRepository repo;

    public MediaService(IMediaRepository repo) {
        this.repo = repo;
    }

    @Override
    public int createMedia(MediaContent media) {
        if (media == null)
            throw new InvalidMediaException("Media is null");

        if (media.getTitle() == null || media.getTitle().isBlank())
            throw new InvalidMediaException("Title is empty");

        int id = repo.createMedia(media);
        if (id == -1)
            throw new DatabaseException("Failed to create media");

        return id;
    }

    @Override
    public MediaContent getMedia(int id) {
        MediaContent media = repo.getMedia(id);
        if (media == null)
            throw new MediaNotFoundException(id);
        return media;
    }

    @Override
    public List<MediaContent> getAllMedias() {
        return repo.getAllMedias();
    }

    @Override
    public boolean updateMedia(MediaContent media) {
        if (media == null)
            throw new InvalidMediaException("Media is null");
        return repo.updateMedia(media);
    }

    @Override
    public boolean deleteMedia(int id) {
        getMedia(id);
        return repo.deleteMedia(id);
    }

    @Override
    public int getMovieWithMinDuration() {
        return repo.getMovieWithMinDuration();
    }
}

