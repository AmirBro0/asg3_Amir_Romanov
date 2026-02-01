package repository.interfaces;

import model.MediaContent;

import java.util.List;

public interface IMediaRepository
        extends CRUD<MediaContent, Integer> {

    int getMovieWithMinDuration();
}
