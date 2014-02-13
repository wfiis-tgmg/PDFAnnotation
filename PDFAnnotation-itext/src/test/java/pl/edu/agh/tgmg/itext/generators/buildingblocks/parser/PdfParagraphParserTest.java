package pl.edu.agh.tgmg.itext.generators.buildingblocks.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.PdfAfterDocument;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfMessageFeed;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.api.annotations.PdfParagraphs;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParser;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParserImpl;
import pl.edu.agh.tgmg.api.exceptions.InvalidParagraphException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.ParagraphElement;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;

@PdfDocument
class SimpleParagraphDTO {
    
    @PdfParagraph("Paragraph")
    
    String str;
}

@PdfDocument
class MultipleParagraphDTO {
    
    @PdfParagraphs({ 
        @PdfParagraph("p1"),
        @PdfParagraph("p2")}
    )
    String str1;
    
    @PdfParagraph("Paragraph")
    
    String str2;
}

@PdfDocument
@PdfAfterDocument(paragraphs = {
    @PdfParagraph("some text after 1"),
    @PdfParagraph("some text after 2")
})
class ParagraphAfterDTO {
    
    @PdfParagraph("some text before")
    String str;
}

@PdfDocument
@PdfAfterDocument(paragraphs = {
    @PdfParagraph(value="some %d text %s after", messageFieldNames={"number", "title"})
})
class ParagraphFeedDTO {
    
    @PdfMessageFeed
    String title;
    @PdfMessageFeed
    Integer number;
    
    @PdfParagraph(value="some text before %s", messageFieldNames={"str"})
    @PdfMessageFeed
    String str;
}

@PdfDocument
class ParagraphError1DTO {
    
    @PdfParagraph(value="Paragraph", messageFieldNames="invalidField")
    String str;
}

@PdfDocument
class ParagraphError2DTO {
    
    @PdfParagraph(value="Paragraph", messageFieldNames="str")
    String str;
}

public class PdfParagraphParserTest {
    
    @Test
    public void testSimpleParagraph() {
        List<ParagraphElement> expected = Arrays.asList(new ParagraphElement("Paragraph"));
        checkParagraphs(SimpleParagraphDTO.class, expected);
    }
    
    @Test
    public void testMultipleParagraph() {
        List<ParagraphElement> expected = Arrays.asList(
                new ParagraphElement("p1"),
                new ParagraphElement("p2"),
                new ParagraphElement("Paragraph"));
        checkParagraphs(MultipleParagraphDTO.class, expected);
    }

    @Test
    public void testParagraphAfter() {
        List<ParagraphElement> expected = Arrays.asList(
                new ParagraphElement("some text before"),
                new ParagraphElement("some text after 1"),
                new ParagraphElement("some text after 2"));
        checkParagraphs(ParagraphAfterDTO.class, expected);
    }
    
    @Test
    public void testParagraphFeed() {
        List<String> feed1 = Arrays.asList("number", "title");
        List<String> feed2 = Arrays.asList("str");
        List<ParagraphElement> expected = Arrays.asList(
                new ParagraphElement(feed2, "some text before %s"),
                new ParagraphElement(feed1, "some %d text %s after"));
        checkParagraphs(ParagraphFeedDTO.class, expected);
    }
    
    @Test(expectedExceptions=InvalidParagraphException.class, expectedExceptionsMessageRegExp=
            ".* Message feed .* does not exists in class .*")
    public void testErrors1() {
        checkParagraphs(ParagraphError1DTO.class, new ArrayList<ParagraphElement>());
    }
    
    @Test(expectedExceptions=InvalidParagraphException.class, expectedExceptionsMessageRegExp=
            ".* Field .* from class .* is not a MessageFeed")
    public void testErrors2() {
        checkParagraphs(ParagraphError2DTO.class, new ArrayList<ParagraphElement>());
    }
    
    private void checkParagraphs(Class<?> clazz, List<ParagraphElement> expected) {
        StyleResolver styleResolver = Mockito.mock(StyleResolver.class);
        PdfAnnotationParser parser = new PdfAnnotationParserImpl(styleResolver);
        DocumentStructure structure = parser.parse(clazz);
        List<? extends PdfElement> elements = structure.getPdfElements();
        Assert.assertNotNull(elements);
        Assert.assertEquals(expected.size(), elements.size());
        for(int i=0;i<expected.size();i++) {
            Assert.assertTrue(elements.get(i) instanceof ParagraphElement);
            ParagraphElement actual = (ParagraphElement) elements.get(i);
            Assert.assertEquals(actual, expected.get(i));
        }
    }
}
