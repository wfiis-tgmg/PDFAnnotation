package pl.edu.agh.tgmg.api;

import com.itextpdf.text.Document;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.api.buildingBlocks.generator.PdfDocumentGenerator;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.examples.TwoColumnTable;
import pl.edu.agh.tgmg.itext.generators.ITextDocumentGenerator;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;
import pl.edu.agh.tgmg.itext.generators.metadata.ITextDocumentFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Collections;

@Test(groups = "docGen")
public class UCParagraphGeneratorTest {

    DocumentStructreBuilder builder = new DocumentStructreBuilder();
    ITextDocumentFactory documentFactory = new DefaultITextDocumentFactory();


    //TODO: wrap itext exception
    @Test(expectedExceptions = GenDocumentException.class)
    public void testBlank() throws Exception {
        File file = new File("blank.pdf");
        Files.deleteIfExists(file.toPath());
        Document document = documentFactory.create(new FileOutputStream(file), new DocumentMetaDataImpl());

//        new ParagraphElement().print(document);
        documentFactory.close(document);
    }

    @Test
    public void testTwoColumns() throws Exception {
//
//        File file = new File("twoColumns.pdf");
//        Files.deleteIfExists(file.toPath());
//        DocumentStructreImpl documentStructre = builder.
//                setColumnRowString("name", "surname").
//                setHeadersString("title for name", "title for surname").create();
//        gen.generate(new FileOutputStream(file), TwoColumnTable.feed(), documentStructre);
    }
}
