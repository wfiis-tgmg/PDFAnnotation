package pl.edu.agh.tgmg.itext.examples.errors.paragraphs;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.api.exceptions.InvalidParagraphException;
import pl.edu.agh.tgmg.itext.examples.errors.DocumentErrorTest;

@PdfDocument
public class ParagraphErrorTest2 extends DocumentErrorTest {
    
    @PdfParagraph(value="Paragraph", messageFieldNames="str")
    String str;

    @Override
    public Class<? extends Exception> getExpectedException() {
        return InvalidParagraphException.class;
    }

    @Override
    public String getExpectedExceptionMessage() {
        return ".* Field .* from class .* is not a MessageFeed";
    }
}