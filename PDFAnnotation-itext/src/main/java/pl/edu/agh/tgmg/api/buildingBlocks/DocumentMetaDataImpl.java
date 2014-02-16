package pl.edu.agh.tgmg.api.buildingBlocks;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

import pl.edu.agh.tgmg.itext.generators.styles.elements.BoxValues;

public class DocumentMetaDataImpl implements DocumentMetaData {

    private String title = "";
    private String subject = "";
    private boolean createDate = true;
    private String author = "";
    private BoxValues<Float> margins = new BoxValues<Float>(20.0f);
    Rectangle p = PageSize.A4 ;

    public DocumentMetaDataImpl() {
    }

    public DocumentMetaDataImpl(String author, boolean createDate, BoxValues<Float> margins,
            String subject, Rectangle p, String title) {
        this.author = author;
        this.createDate = createDate;
        this.margins.setValue(margins);
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
    public BoxValues<Float> getMargins() {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + (createDate ? 1231 : 1237);
        result = prime * result + ((margins == null) ? 0 : margins.hashCode());
        result = (int) (prime * result + p.getTop());
        result = (int) (prime * result + p.getRight());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DocumentMetaDataImpl other = (DocumentMetaDataImpl) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (createDate != other.createDate)
            return false;
        if (margins == null) {
            if (other.margins != null)
                return false;
        } else if (!margins.equals(other.margins))
            return false;
        if (p == null) {
            if (other.p != null)
                return false;
        } else if (p.getTop() != other.p.getTop() || p.getRight() != other.p.getRight())
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }
    
    
    
    
}
