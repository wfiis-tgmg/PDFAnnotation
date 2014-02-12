package pl.edu.agh.tgmg.api.buildingBlocks;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import pl.edu.agh.tgmg.itext.generators.styles.BoxValues;

public class DocumentMetaDataImpl implements DocumentMetaData {

    private String title = "";
    private String subject = "";
    private boolean createDate = true;
    private String author = "";
    private BoxValues<Integer> margins = new BoxValues<Integer>(20);
    Rectangle p = PageSize.A4 ;


    public DocumentMetaDataImpl() {
    }

    public DocumentMetaDataImpl(String author, boolean createDate, BoxValues<Integer> margins,
            String subject, Rectangle p, String title) {
        this.author = author;
        this.createDate = createDate;
        this.margins = margins;
        this.subject = subject;
        this.p = p;
        this.title = title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public boolean isCreateDate() {
        return createDate;
    }

    @Override
    public Rectangle getPageSize() {
        return p;
    }

    @Override
    public BoxValues<Integer> getMargins() {
        return margins;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getTitle() {
        return title;
    }
    
    
}
