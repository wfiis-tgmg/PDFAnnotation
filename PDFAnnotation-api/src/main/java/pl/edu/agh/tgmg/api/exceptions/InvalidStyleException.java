package pl.edu.agh.tgmg.api.exceptions;

public class InvalidStyleException extends InvalidAnnotationException {

    public InvalidStyleException() {
        super();
    }

    public InvalidStyleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStyleException(String message) {
        super(message);
    }

    public InvalidStyleException(Throwable cause) {
        super(cause);
    }

}
