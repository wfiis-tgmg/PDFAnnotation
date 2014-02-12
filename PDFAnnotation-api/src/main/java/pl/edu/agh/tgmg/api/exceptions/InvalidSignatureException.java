package pl.edu.agh.tgmg.api.exceptions;

public class InvalidSignatureException extends InvalidAnnotationException {

    public InvalidSignatureException() {
        super();
    }

    public InvalidSignatureException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSignatureException(String message) {
        super(message);
    }

    public InvalidSignatureException(Throwable cause) {
        super(cause);
    }

    
}
