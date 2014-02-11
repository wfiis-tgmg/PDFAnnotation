package pl.edu.agh.tgmg.api.exceptions;

public class InvalidTableGroupException extends InvalidAnnotationException{

    public InvalidTableGroupException() {
        super();
    }

    public InvalidTableGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTableGroupException(String message) {
        super(message);
    }

    public InvalidTableGroupException(Throwable cause) {
        super(cause);
    }

    
}
