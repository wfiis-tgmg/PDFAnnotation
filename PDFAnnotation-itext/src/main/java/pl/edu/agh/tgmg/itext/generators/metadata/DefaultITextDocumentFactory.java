package pl.edu.agh.tgmg.itext.generators.metadata;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.metadata.ITextDocumentFactory;

import java.io.OutputStream;

public class DefaultITextDocumentFactory implements ITextDocumentFactory {
    @Override
    public Document create(OutputStream out, DocumentMetaData metaData) throws GenDocumentException {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, out);

            document.addAuthor("");
            document.addCreationDate();
            document.addSubject("Przywieszki");
            document.setPageSize(PageSize.A4);
            document.setMargins(20, 20, 40, 60);
            document.open();

            return document;
        }catch (Exception e)
        {
            throw new GenDocumentException(e);
        }
     }
}
