package pl.edu.agh.tgmg.api;

import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public interface PdfDocumentGenerator {
    void generate(OutputStream out, List<? extends PdfContainer> data, DocumentStructure documentStructure) throws GenDocumentException;


}
