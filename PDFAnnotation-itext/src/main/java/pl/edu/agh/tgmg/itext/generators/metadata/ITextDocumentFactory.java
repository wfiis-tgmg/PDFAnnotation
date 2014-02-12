package pl.edu.agh.tgmg.itext.generators.metadata;

import java.io.OutputStream;

import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import com.itextpdf.text.Document;

public interface ITextDocumentFactory {

    Document create(OutputStream out, DocumentMetaData metaData) throws GenDocumentException;

    void close(Document document);
}
