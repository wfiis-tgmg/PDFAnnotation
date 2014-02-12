package pl.edu.agh.tgmg.api.exceptions;

public class ReflectionException extends AnnotationParserException {

    public ReflectionException() {
        super();
    }

    public ReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectionException(String message) {
        super(message);
    }

    public ReflectionException(Throwable cause) {
        super(cause);
    }
    
}
