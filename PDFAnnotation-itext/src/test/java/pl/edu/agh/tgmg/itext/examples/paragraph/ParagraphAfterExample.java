package pl.edu.agh.tgmg.itext.examples.paragraph;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.PdfAfterDocument;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.ParagraphElement;

@PdfDocument
@PdfAfterDocument(paragraphs = {
    @PdfParagraph("some text after 1"),
    @PdfParagraph("some text after 2")
})
public class ParagraphAfterExample extends ParagraphExample {
    
    @PdfParagraph("some text before")
    String str;

    @Override
    public List<? extends PdfElement> getExpectedElements() {
        return Arrays.asList(
                new ParagraphElement("some text before"),
                new ParagraphElement("some text after 1"),
                new ParagraphElement("some text after 2"));
    }
}