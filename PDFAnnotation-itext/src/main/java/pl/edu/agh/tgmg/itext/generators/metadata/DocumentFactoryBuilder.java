package pl.edu.agh.tgmg.itext.generators.metadata;

import pl.edu.agh.tgmg.itext.generators.styles.BoxValues;

public class DocumentFactoryBuilder {
    private boolean createDate = false;
    private String author = "";
    private BoxValues<Integer> margins = new BoxValues<Integer>(20);
    private String subject = "";

    public DocumentFactoryBuilder setCreateDate(boolean createDate) {
        this.createDate = createDate;
        return this;
    }

    public DocumentFactoryBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public DocumentFactoryBuilder setMargins(BoxValues<Integer> margins) {
        this.margins = margins;
        return this;
    }

    public DocumentFactoryBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public DefaultITextDocumentFactory create() {
        return new DefaultITextDocumentFactory(createDate, author, margins, subject);
    }
}