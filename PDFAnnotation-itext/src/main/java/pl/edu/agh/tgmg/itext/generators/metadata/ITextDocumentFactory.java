package pl.edu.agh.tgmg.itext.generators.metadata;

import com.itextpdf.text.Document;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import java.io.OutputStream;

public interface ITextDocumentFactory {

    Document create(OutputStream out, DocumentMetaData metaData) throws GenDocumentException;
}
