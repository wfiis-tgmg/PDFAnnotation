package pl.edu.agh.tgmg.itext.examples.system;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.PdfGeneratorFacade;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfAnnotationParserImpl;
import pl.edu.agh.tgmg.itext.examples.DocumentExample;
import pl.edu.agh.tgmg.itext.generators.PdfGeneratorFacadeImpl;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;

public abstract class SystemTest extends DocumentExample{

    @Test(dependsOnMethods="checkDocumentParsing")
    public void systemTest() throws FileNotFoundException {
        PdfGeneratorFacade properPdfGenerator = new PdfGeneratorFacadeImpl(
                new PdfAnnotationParserImpl(), new DefaultITextDocumentFactory());
        OutputStream out = pdfGenerator.getOutputStream();
        properPdfGenerator.generate(out, this);
    }
    
    @Override
    public void generatePdf() {
        
    }
}
