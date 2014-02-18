package pl.edu.agh.tgmg.itext.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

public class PdfGenerator {
    
    DefaultITextDocumentFactory factory = new DefaultITextDocumentFactory();
    Class<?> invokingClass;
    
    public PdfGenerator(Class<?> invokingClass) {
        this.invokingClass = invokingClass;
    }

    public void generatePdf(PdfElement element, Object dto) throws DocumentException, 
            GenDocumentException, FileNotFoundException {
        generatePdf(Arrays.asList(element.print(dto)));
    }
    
    public void generatePdf(List<? extends PdfElement> elements, Object dto) throws DocumentException, 
            GenDocumentException, FileNotFoundException {
        for(PdfElement element : elements) {
            generatePdf(element, dto);
        }
    }
    
    public void generatePdf(Element element) throws DocumentException, GenDocumentException, FileNotFoundException {
        generatePdf(Arrays.asList(element));
    }
    
    public void generatePdf(List<Element> elements) throws DocumentException, GenDocumentException, FileNotFoundException {
        Document document = createNewDocument();
        for(Element element : elements) {
            document.add(element);
        }
        factory.close(document);
    }
       
    private Document createNewDocument() throws GenDocumentException, FileNotFoundException {
        OutputStream out = getOutputStream();
        return factory.create(out, new DocumentMetaDataImpl());
    }
    
    public OutputStream getOutputStream() throws FileNotFoundException {
        String pathName = getPathName();
        File file = new File(pathName);
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return new FileOutputStream(file);
    }

    private String getPathName() {
        String name = invokingClass.getPackage().getName();
        name = name.replace("pl.edu.agh.tgmg.itext", "target");
        return name.replace('.', '/') + "/" + invokingClass.getSimpleName() + ".pdf";
    }
}
