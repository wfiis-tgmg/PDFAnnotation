package pl.edu.agh.tgmg.api.exceptions;

public class InvalidParagraphException extends InvalidAnnotationException {

    public InvalidParagraphException() {
        super();
    }

    public InvalidParagraphException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParagraphException(String message) {
        super(message);
    }

    public InvalidParagraphException(Throwable cause) {
        super(cause);
    }
    
}
