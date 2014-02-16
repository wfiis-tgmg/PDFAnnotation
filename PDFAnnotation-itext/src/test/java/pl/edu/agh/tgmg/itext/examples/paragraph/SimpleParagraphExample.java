package pl.edu.agh.tgmg.itext.examples.paragraph;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.ParagraphElement;

@PdfDocument
public class SimpleParagraphExample extends ParagraphExample {
    
    @PdfParagraph("Paragraph")
    
    String str;

    @Override
    public List<? extends PdfElement> getExpectedElements() {
        return Arrays.asList(new ParagraphElement("Paragraph"));
    }
}