package pl.edu.agh.tgmg.itext;

import pl.edu.agh.tgmg.api.PdfContainer;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.io.OutputStream;
import java.util.List;

public interface PdfGeneratorFacede {
    void generate(OutputStream out, List<PdfContainer> dto) throws GenDocumentException;
}
