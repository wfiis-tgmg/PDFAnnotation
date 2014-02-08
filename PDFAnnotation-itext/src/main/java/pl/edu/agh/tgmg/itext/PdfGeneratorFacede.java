package pl.edu.agh.tgmg.itext;

import pl.edu.agh.tgmg.api.PdfDocument;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.io.OutputStream;
import java.util.List;

public interface PdfGeneratorFacede {
    void generate(OutputStream out, List<PdfDocument> dto) throws GenDocumentException;
}
