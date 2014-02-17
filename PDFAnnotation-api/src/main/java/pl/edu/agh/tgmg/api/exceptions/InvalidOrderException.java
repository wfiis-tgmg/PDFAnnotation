package pl.edu.agh.tgmg.api.exceptions;

public class InvalidOrderException extends InvalidAnnotationException {

    public InvalidOrderException() {
        super();
    }

    public InvalidOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOrderException(String message) {
        super(message);
    }

    public InvalidOrderException(Throwable cause) {
        super(cause);
    }
    
}
