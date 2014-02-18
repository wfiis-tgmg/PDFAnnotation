package pl.edu.agh.tgmg.itext.examples;

import java.io.FileNotFoundException;
import java.util.List;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParser;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParserImpl;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;

import com.itextpdf.text.DocumentException;

public abstract class DocumentExample {

    PdfGenerator pdfGenerator = new PdfGenerator(this.getClass());
    StyleResolver styleResolver = Mockito.mock(StyleResolver.class);
    PdfAnnotationParser parser = new PdfAnnotationParserImpl(styleResolver);
    
    public abstract List<? extends PdfElement> getExpectedElements();
    
    public Object getExampleDTO() {
        return this;
    }
    
    @Test
    public void checkDocumentParsing() {
        DocumentStructure structure = parser.parse(this.getClass());
        List<? extends PdfElement> elements = structure.getPdfElements();
        List<? extends PdfElement> expected = getExpectedElements();
        Assert.assertEquals(elements, expected);
    }
    
    @Test(dependsOnMethods = "checkDocumentParsing")
    public void generatePdf() throws DocumentException, GenDocumentException, FileNotFoundException {
        pdfGenerator.generatePdf(getExpectedElements(), getExampleDTO());
    }
}
