package pl.edu.agh.tgmg.api;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.edu.agh.tgmg.api.buildingBlocks.generator.PdfDocumentGenerator;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.examples.TwoColumnTable;
import pl.edu.agh.tgmg.itext.generators.ITextDocumentGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Collections;

@Test(groups = "docGen")
public class UCSimpleColumnGeneratorTest {

    DocumentStructreBuilder builder = new DocumentStructreBuilder();

    PdfDocumentGenerator gen = new ITextDocumentGenerator();

    @BeforeMethod
    public void setUp() throws Exception {
        builder.clear();
    }

    //TODO: wrap itext exception
    @Test(expectedExceptions = GenDocumentException.class)
    public void testBlank() throws Exception {
        File file = new File("blank.pdf");
        Files.deleteIfExists(file.toPath());
        gen.generate(new FileOutputStream(file), Collections.<PdfDocument>emptyList(), new DocumentStructreBuilder().create());
    }

    @Test
    public void testTwoColumns() throws Exception {

        File file = new File("twoColumns.pdf");
        Files.deleteIfExists(file.toPath());
        DocumentStructreImpl documentStructre = builder.
                setColumnRowString("name", "surname").
                setHeadersString("title for name", "title for surname").create();
        gen.generate(new FileOutputStream(file), TwoColumnTable.feed(), documentStructre);
    }
}
