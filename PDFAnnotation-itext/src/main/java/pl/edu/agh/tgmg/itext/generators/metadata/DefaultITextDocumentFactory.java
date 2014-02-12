package pl.edu.agh.tgmg.itext.generators.metadata;

import java.io.OutputStream;

import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.styles.BoxValues;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class DefaultITextDocumentFactory implements ITextDocumentFactory {





    public Document create(OutputStream out) throws GenDocumentException {
        return create(out, new DocumentMetaDataImpl());
    }


    @Override
    public Document create(OutputStream out, DocumentMetaData metaData) throws GenDocumentException {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, out);

            document.addAuthor(metaData.getAuthor());
            if(metaData.isCreateDate()) document.addCreationDate();
            document.addSubject(metaData.getSubject());
            document.setPageSize(metaData.getPageSize());
            document.setMargins(metaData.getMargins().getForLeft(), metaData.getMargins().getForRight(), metaData.getMargins().getForTop(), metaData.getMargins().getForTop());
            document.addTitle(metaData.getTitle());

            document.open();

            return document;
        }catch (Exception e)
        {
            throw new GenDocumentException(e);
        }
     }

    @Override
    public void close(Document document)
    {
        document.close();
    }
}

