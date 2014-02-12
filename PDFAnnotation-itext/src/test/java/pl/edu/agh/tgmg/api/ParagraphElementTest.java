package pl.edu.agh.tgmg.api;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Arrays;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.TestGroup;
import pl.edu.agh.tgmg.api.annotations.ToTest;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.ParagraphElement;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;

import com.itextpdf.text.Paragraph;

public class ParagraphElementTest {

    DefaultITextDocumentFactory factory = new DefaultITextDocumentFactory();

    public class Temp
    {
        String param1 = "some1";
        int param2 = 2;
    }


    @Test
    public void testFormatSimple() throws Exception {
        String text = "Some Text";
        Paragraph print = new ParagraphElement(text).print(null);
        assertNotNull(print);
        assertEquals(text,print.getContent());
    }

    @Test
    public void testFormatWithParams() throws Exception {
        String text = "Some Text %1$s %2$d %1$s";
        Paragraph print = new ParagraphElement(Arrays.asList("param1","param2"),text).print(new Temp());
        assertNotNull(print);
        assertEquals("Some Text some1 2 some1",print.getContent());

    }

    @ToTest
    @Test(groups = TestGroup.GEN_DOC)
    public void testGenerateDocWithParam() throws Exception {


    }
}
