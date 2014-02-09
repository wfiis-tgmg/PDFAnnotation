package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import org.testng.annotations.Test;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

public class PdfTableRowTest {

    DefaultITextDocumentFactory factory = new DefaultITextDocumentFactory();



    @Test
    public void testSimpleRow() throws Exception {
        Document document = factory.create(new FileOutputStream(new File("simplerow.pdf")), new DocumentMetaDataImpl());

        PdfPTable element = new PdfPTable(3);
        document.add(element);

        PdfTableRow pdfTableRow = new PdfTableRow(Arrays.<CellRow>asList(
                new StringCellRow("row1"),
                new StringCellRow("row2"),
                new StringCellRow("row3")
        ));

//        pdfTableRow.print();

        factory.close(document);
    }
}
