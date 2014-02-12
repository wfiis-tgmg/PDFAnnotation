package pl.edu.agh.tgmg.itext.generators.metadata;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.styles.BoxValues;

import java.io.OutputStream;

public class DefaultITextDocumentFactory implements ITextDocumentFactory {

    private final String title = "";
    private  String subject = "";
    private boolean createDate = true;
    private String author = "";
    private BoxValues<Integer> margins;


    public DefaultITextDocumentFactory(boolean createDate, String author, BoxValues<Integer> margins, String subject) {
        this.createDate = createDate;
        this.author = author;
        this.margins = margins;
        this.subject = subject;
    }

    public Document create(OutputStream out) throws GenDocumentException {
        return create(out, new DocumentMetaDataImpl());
    }


    @Override
    public Document create(OutputStream out, DocumentMetaData metaData) throws GenDocumentException {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, out);

            document.addAuthor(author);
            if(createDate) document.addCreationDate();
            document.addSubject(subject);
            document.setPageSize(PageSize.A4);
            document.setMargins(margins.getForLeft(), margins.getForRight(), margins.getForTop(), margins.getForTop());
            document.addTitle(title);

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

