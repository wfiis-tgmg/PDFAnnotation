package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.testng.annotations.Test;
import pl.edu.agh.tgmg.TestGroup;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.examples.EmbeddedTableRow;
import pl.edu.agh.tgmg.examples.SimpleRow;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;
import pl.edu.agh.tgmg.itext.wrapper.TableCellRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@Test(groups = TestGroup.GEN_DOC)
public class PdfTableRowTest {

    DefaultITextDocumentFactory factory = new DefaultITextDocumentFactory();



    @Test
    public void testSimpleRow() throws Exception {
        gen("simplerow.pdf", SimpleRow.feed(), Arrays.<CellRow>asList(
                new StringCellRow("row1"),
                new StringCellRow("row2"),
                new StringCellRow("row3")
        ));
    }

    @Test
    public void testTableInSingleCell() throws Exception {

        TableCellRow innerTable = new TableCellRow("row3",
                new PdfTableElement(
                        new PdfTableHeader(3), new PdfTableRow(new StringCellRow("name"))
                )

        );

        Document document = factory.create(new FileOutputStream(new File("tableInCell.pdf")), new DocumentMetaDataImpl());
        PdfTableRow pdfTableRow = new PdfTableRow(innerTable);

        List<EmbeddedTableRow> feed = EmbeddedTableRow.feed();
        PdfPTable element = genTable(feed, 1, pdfTableRow);
        document.add(element);
        factory.close(document);


    }

    @Test
    public void testTableInTableCell() throws Exception {


        TableCellRow innerTable = new TableCellRow("row3",
                new PdfTableElement(
                        new PdfTableHeader(3), new PdfTableRow(new StringCellRow("name"))
                )

        );

        List<EmbeddedTableRow> feed = EmbeddedTableRow.feed();
        gen("tableInTable.pdf", feed, Arrays.<CellRow>asList(
                new StringCellRow("row1"),
                new StringCellRow("row2"),
                innerTable));

    }

    private void gen(String pathname, List data, List<CellRow> rows) throws FileNotFoundException, DocumentException {
        int numColumns = rows.size();
        Document document = factory.create(new FileOutputStream(new File(pathname)), new DocumentMetaDataImpl());
        PdfTableRow pdfTableRow = new PdfTableRow(rows);

        PdfPTable element = genTable(data, numColumns, pdfTableRow);
        document.add(element);
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
