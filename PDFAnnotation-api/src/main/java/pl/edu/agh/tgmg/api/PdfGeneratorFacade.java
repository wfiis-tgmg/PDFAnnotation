package pl.edu.agh.tgmg.api;

import java.io.OutputStream;

import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

public interface PdfGeneratorFacade {
    void generate(OutputStream out, Object dto ) throws GenDocumentException;
}
