package pl.edu.agh.tgmg.api.exceptions;

public class InvalidAnnotationException extends AnnotationParserException {

	public InvalidAnnotationException() {
		super();
	}

	public InvalidAnnotationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAnnotationException(String message) {
		super(message);
	}

	public InvalidAnnotationException(Throwable cause) {
		super(cause);
	}
	
}
