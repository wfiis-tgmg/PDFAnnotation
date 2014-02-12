package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import java.io.File;
import java.io.FileOutputStream;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.TestGroup;
import pl.edu.agh.tgmg.examples.SimpleRow;
import pl.edu.agh.tgmg.itext.generators.dto.DynamicTableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;

import com.google.common.collect.Lists;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;

@Test(groups = TestGroup.GEN_DOC)
public class SingleDataTableTest {


    DefaultITextDocumentFactory factory = new DefaultITextDocumentFactory();

    @Test
    public void testGenerate() throws Exception {
        Document document = factory.create(new FileOutputStream(new File("dynamicHeaders.pdf")));

        PdfPTable pdfTable = new SingleDataTable(3, Lists.newArrayList(
                new DynamicTableHeaderColumn(1, 2, "header 1", "row1"),
                new DynamicTableHeaderColumn(1, 1, "header 2", "row2")
        )).createPdfTable(SimpleRow.feed().get(0));

        document.add(pdfTable);
        factory.close(document);

    }
}
