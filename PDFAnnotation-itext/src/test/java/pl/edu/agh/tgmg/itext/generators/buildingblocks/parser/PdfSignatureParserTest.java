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
import pl.edu.agh.tgmg.api.annotations.PdfSignature;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParser;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParserImpl;
import pl.edu.agh.tgmg.api.exceptions.InvalidSignatureException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfSignatureElement;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;

@PdfDocument
class SimpleSignatureDTO {
    
    @PdfSignature(title="title")
    
    String str;
}

@PdfDocument
@PdfAfterDocument(signatures = 
    @PdfSignature(title="some title", description="signature", 
        staticSignature="Mr Anderson"))
class SignatureAfterDTO {
    
    @PdfSignature(title="title")
    String str;
}

@PdfDocument
class SignatureFeedDTO {
    
    @PdfSignature(title="title", dataFieldName="signature", description="desc")
    @PdfMessageFeed
    String signature;
}

@PdfDocument
class SignatureError1DTO {
    
    @PdfSignature(dataFieldName="invalidField")
    String str;
}

@PdfDocument
class SignatureError2DTO {
    
    @PdfSignature(dataFieldName="str")
    String str;
}

public class PdfSignatureParserTest {
    
    @Test
    public void testSimpleParagraph() {
        PdfSignatureElement expected = new PdfSignatureElement("title", "( signature )", "" , "");
        checkParagraphs(SimpleSignatureDTO.class, expected);
    }

    @Test
    public void testParagraphAfter() {
        List<PdfSignatureElement> expected = Arrays.asList(
                new PdfSignatureElement("title", "( signature )", "", ""),
                new PdfSignatureElement("some title", "signature", "Mr Anderson", ""));
        checkParagraphs(SignatureAfterDTO.class, expected);
    }
    
    @Test
    public void testParagraphFeed() {
        PdfSignatureElement expected = new PdfSignatureElement("title", "desc", "", "signature");
        checkParagraphs(SignatureFeedDTO.class, expected);
    }
    
    @Test(expectedExceptions=InvalidSignatureException.class, expectedExceptionsMessageRegExp=
            ".* Message feed .* does not exists in class .*")
    public void testErrors1() {
        checkParagraphs(SignatureError1DTO.class, new ArrayList<PdfSignatureElement>());
    }
    
    @Test(expectedExceptions=InvalidSignatureException.class, expectedExceptionsMessageRegExp=
            ".* Field .* from class .* is not a MessageFeed")
    public void testErrors2() {
        checkParagraphs(SignatureError2DTO.class, new ArrayList<PdfSignatureElement>());
    }
    
    private void checkParagraphs(Class<?> clazz, PdfSignatureElement expected) {
        checkParagraphs(clazz, Arrays.asList(expected));
    }
    
    private void checkParagraphs(Class<?> clazz, List<PdfSignatureElement> expected) {
        StyleResolver styleResolver = Mockito.mock(StyleResolver.class);
        PdfAnnotationParser parser = new PdfAnnotationParserImpl(styleResolver);
        DocumentStructure structure = parser.parse(clazz);
        List<? extends PdfElement> elements = structure.getPdfElements();
        Assert.assertNotNull(elements);
        Assert.assertEquals(expected.size(), elements.size());
        for(int i=0;i<expected.size();i++) {
            Assert.assertTrue(elements.get(i) instanceof PdfSignatureElement);
            PdfSignatureElement actual = (PdfSignatureElement) elements.get(i);
            Assert.assertEquals(actual, expected.get(i));
        }
    }
}
