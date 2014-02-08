package pl.edu.agh.tgmg.api.exceptions;

import java.io.Serializable;

public class GenDocumentException extends Exception {

    public GenDocumentException(String message) {
        super(message);
    }

    public GenDocumentException(Throwable cause) {
        super(cause);
    }

    public GenDocumentException() {
    }
}
