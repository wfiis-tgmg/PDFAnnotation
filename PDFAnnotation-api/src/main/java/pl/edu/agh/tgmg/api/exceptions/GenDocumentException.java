package pl.edu.agh.tgmg.api.exceptions;


public class GenDocumentException extends RuntimeException {

    public GenDocumentException(String message) {
        super(message);
    }

    public GenDocumentException(Throwable cause) {
        super(cause);
    }
    

    public GenDocumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenDocumentException() {
    }
}
