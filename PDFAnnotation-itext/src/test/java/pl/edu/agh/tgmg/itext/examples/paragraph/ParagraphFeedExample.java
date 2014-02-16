package pl.edu.agh.tgmg.itext.examples.paragraph;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.PdfAfterDocument;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfMessageFeed;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.ParagraphElement;

@PdfDocument
@PdfAfterDocument(paragraphs = {
    @PdfParagraph(value="some %d text %s after", messageFieldNames={"number", "title"})
})
public class ParagraphFeedExample extends ParagraphExample {
    
    @PdfMessageFeed
    String title = "title data";
    @PdfMessageFeed
    Integer number = 23;
    
    @PdfParagraph(value="some text before %s", messageFieldNames={"str"})
    @PdfMessageFeed
    String str = "string data";

    @Override
    public List<? extends PdfElement> getExpectedElements() {
        List<String> feed1 = Arrays.asList("number", "title");
        List<String> feed2 = Arrays.asList("str");
        return Arrays.asList(
                new ParagraphElement(feed2, "some text before %s"),
                new ParagraphElement(feed1, "some %d text %s after"));
    }
}