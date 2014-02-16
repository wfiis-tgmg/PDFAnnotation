package pl.edu.agh.tgmg.itext.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;

public abstract class BuildingBlocksExample <E extends PdfElement> {
    
    DefaultITextDocumentFactory factory = new DefaultITextDocumentFactory();
    
    public abstract void checkElementParsing();
    public abstract Object getExampleDTO();
    public abstract E getExpectedElement(); 
    public abstract  Class<?> getDocumentWithThisExample();
    
    @Test
    public void checkElementParsingWrapper() {
        checkElementParsing();
    }
    
    @Test(dependsOnMethods="checkElementParsingWrapper")
    public void generatePdf() throws DocumentException, GenDocumentException, FileNotFoundException {
        PdfElement pdfElement = getExpectedElement();
        Element element = pdfElement.print(getExampleDTO());
        String pathName = getPathName();
        Document document = createNewDocument(pathName);
        document.add(element);
        factory.close(document);
    }
       
    private Document createNewDocument(String pathName) throws GenDocumentException, FileNotFoundException {
        File file = new File(pathName);
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return factory.create(new FileOutputStream(file), new DocumentMetaDataImpl());
    }

    private String getPathName() {
        String name = this.getClass().getPackage().getName();
        name = name.replace("pl.edu.agh.tgmg.itext", "target");
        return name.replace('.', '/') + "/" + this.getClass().getSimpleName() + ".pdf";
    }
}
