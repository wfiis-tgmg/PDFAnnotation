package pl.edu.agh.tgmg.api;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.io.OutputStream;

public interface PdfGeneratorFacede {
    void generate(OutputStream out, PdfDocument dto ) throws GenDocumentException;
}
