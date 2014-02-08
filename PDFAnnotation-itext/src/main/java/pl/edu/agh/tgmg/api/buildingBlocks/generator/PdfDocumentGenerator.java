package pl.edu.agh.tgmg.api.buildingBlocks.generator;

import com.itextpdf.text.Document;
import pl.edu.agh.tgmg.api.PdfDocument;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.io.OutputStream;
import java.util.List;

public interface PdfDocumentGenerator {


    void generate(Document document, List<? extends PdfDocument> data, DocumentStructure documentStructure) throws GenDocumentException;
}
