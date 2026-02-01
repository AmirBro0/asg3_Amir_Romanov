package controller;

import controller.interfaces.IMediaController;
import model.MediaContent;
import services.interfaces.IMediaService;

public class MediaController implements IMediaController {

    private final IMediaService service;

    public MediaController(IMediaService service) {
        this.service = service;
    }

    @Override
    public int createMedia(MediaContent media) {
        return service.createMedia(media);
    }

    @Override
    public String getMedia(int id) {
        return service.getMedia(id).toString();
    }

    @Override
    public String getAllMedias() {
        StringBuilder sb = new StringBuilder();
        for (MediaContent media : service.getAllMedias()) {
            sb.append(media).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean updateMedia(MediaContent media) {
        return service.updateMedia(media);
    }

    @Override
    public boolean deleteMedia(int id) {
        return service.deleteMedia(id);
    }

    public MediaContent getMediaObject(int id) {
        return service.getMedia(id);
    }
}
