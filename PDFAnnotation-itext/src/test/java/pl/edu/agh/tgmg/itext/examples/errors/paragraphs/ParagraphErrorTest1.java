package pl.edu.agh.tgmg.itext.examples.errors.paragraphs;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.api.exceptions.InvalidParagraphException;
import pl.edu.agh.tgmg.itext.examples.errors.DocumentErrorTest;

@PdfDocument
public class ParagraphErrorTest1 extends DocumentErrorTest {
    
    @PdfParagraph(value="Paragraph", messageFieldNames="invalidField")
    String str;

    @Override
    public Class<? extends Exception> getExpectedException() {
        return InvalidParagraphException.class;
    }

    @Override
    public String getExpectedExceptionMessage() {
        return ".* Message feed .* does not exists in class .*";
    }
}