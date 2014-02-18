package pl.edu.agh.tgmg.itext.examples;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;

import com.itextpdf.text.DocumentException;

import static org.testng.AssertJUnit.fail;

public abstract class BuildingBlocksExample <E extends PdfElement> {
    
    PdfGenerator pdfGenerator = new PdfGenerator(this.getClass());
    
    public abstract void checkElementParsing();
    public abstract E getExpectedElement(); 
    public abstract  Class<?> getDocumentWithThisExample();
    
    public Object getExampleDTO() {
        return this;
    }
    
    @Test
    public void checkElementParsingWrapper() {
        checkElementParsing();
    }
    
    @Test(dependsOnMethods = "checkElementParsingWrapper")
    public void generatePdf() throws DocumentException, GenDocumentException, FileNotFoundException {
        pdfGenerator.generatePdf(getExpectedElement(), getExampleDTO());
    }
}
