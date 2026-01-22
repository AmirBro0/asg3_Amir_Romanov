package exception;

public class MediaNotFoundException extends RuntimeException {
    public MediaNotFoundException(int id) {
        super("Media with id " + id + " was not found");
    }
}
