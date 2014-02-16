package pl.edu.agh.tgmg.itext.examples.paragraph;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.api.annotations.PdfParagraphs;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.ParagraphElement;

@PdfDocument
public class MultipleParagraphExample extends ParagraphExample {
    
    @PdfParagraphs({ 
        @PdfParagraph("p1"),
        @PdfParagraph("p2")}
    )
    String str1;
    
    @PdfParagraph("Paragraph")
    
    String str2;

    @Override
    public List<? extends PdfElement> getExpectedElements() {
        return Arrays.asList(
                new ParagraphElement("p1"),
                new ParagraphElement("p2"),
                new ParagraphElement("Paragraph"));
    }
}