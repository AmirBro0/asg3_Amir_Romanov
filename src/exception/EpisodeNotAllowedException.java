package exception;

public class EpisodeNotAllowedException extends RuntimeException {
    public EpisodeNotAllowedException() {
        super("Episodes can be added only to SERIES");
    }
}
