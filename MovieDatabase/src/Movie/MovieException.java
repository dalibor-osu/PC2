package Movie;

public class MovieException extends Exception {
    public MovieException() {
        super("Movie Exception");
    }

    public MovieException(String message) {
        super(message);
    }
}
