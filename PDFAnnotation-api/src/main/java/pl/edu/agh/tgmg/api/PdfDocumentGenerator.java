package pl.edu.agh.tgmg.api;

import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.io.OutputStream;
import java.util.List;

public interface PdfDocumentGenerator {
    void generate(OutputStream out, List<? extends PdfDocument> data, DocumentStructure documentStructure) throws GenDocumentException;


}
