package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.testng.annotations.Test;
import pl.edu.agh.tgmg.TestGroup;
import pl.edu.agh.tgmg.api.buildingBlocks.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.examples.SimpleRow;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;


@Test(groups = TestGroup.GEN_DOC)
public class PdfTableTest {

    DefaultITextDocumentFactory factory = new DefaultITextDocumentFactory();



    @Test
    public void testSimpleRow() throws Exception {

        List<CellRow> rows = Arrays.<CellRow>asList(
                new StringCellRow("row1"),
                new StringCellRow("row2"),
                new StringCellRow("row3")
        );


        int numColumns = rows.size();
        Document document = factory.create(new FileOutputStream(new File("simpletable.pdf")), new DocumentMetaDataImpl());


        PdfTableRow pdfTableRow = new PdfTableRow(rows);

        List<TableHeaderColumn> headerColumns = Arrays.asList(new TableHeaderColumn("col1"), new TableHeaderColumn("col1"));
        PdfTableElementWithStaticHeader withStaticHeader = new PdfTableElementWithStaticHeader(new PdfTableHeader(2, headerColumns),pdfTableRow);
        document.add(withStaticHeader.print(SimpleRow.feed()));
        factory.close(document);
    }


    private PdfPTable genTable(List data, int numColumns, PdfTableRow pdfTableRow) {
        PdfPTable element = new PdfPTable(numColumns);
        for (Object obj : data) {
            List<PdfPCell> print = pdfTableRow.print(obj);
            PdfTables.addCells(element, print);
        }

        return element;
    }



}
