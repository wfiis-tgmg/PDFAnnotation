package pl.edu.agh.tgmg.itext.generators;

import pl.edu.agh.tgmg.api.PdfDocument;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.io.OutputStream;
import java.util.List;

public interface PdfGeneratorFacede {
    void generate(OutputStream out, PdfDocument dto ) throws GenDocumentException;
}
