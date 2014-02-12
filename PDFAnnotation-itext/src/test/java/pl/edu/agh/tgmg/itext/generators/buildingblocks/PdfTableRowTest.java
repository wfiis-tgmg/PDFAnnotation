package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.google.common.collect.ImmutableList;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.testng.annotations.Test;
import pl.edu.agh.tgmg.TestGroup;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.examples.SecLvlTableExample;
import pl.edu.agh.tgmg.examples.SimpleRow;
import pl.edu.agh.tgmg.examples.ThirdLvlTableExample;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;
import pl.edu.agh.tgmg.itext.generators.metadata.DocumentFactoryBuilder;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;
import pl.edu.agh.tgmg.itext.wrapper.TableCellRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;


@Test(groups = TestGroup.GEN_DOC)
public class PdfTableRowTest {

    DefaultITextDocumentFactory factory = new DocumentFactoryBuilder().create();



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

        TableCellRow innerTableCell = new TableCellRow("row3",
                new PdfTableElementWithStaticHeader(
                        new PdfTableHeader(1), new PdfTableRow(new StringCellRow("name"))
                )
        );

        Document document = factory.create(new FileOutputStream(new File("tableInCell.pdf")), new DocumentMetaDataImpl());

        PdfTableRow pdfTableRow = new PdfTableRow(innerTableCell,innerTableCell);

        List<SecLvlTableExample> feed = SecLvlTableExample.feed();

        PdfPTable element1 = new PdfPTable(2);
        for (Object obj : feed) {
            List<PdfPCell> print = pdfTableRow.print(obj);
            PdfTables.addCells(element1, print);
        }

        PdfPTable element = element1;
        document.add(element);
        factory.close(document);


    }

    @Test
    public void testManual() throws Exception {
        Document document = factory.create(new FileOutputStream(new File("manual.pdf")), new DocumentMetaDataImpl());

        PdfPTable inner1 = new PdfPTable(3);
        inner1.addCell("a1");
        inner1.addCell("a2");
        inner1.addCell("a3");

        PdfPTable inner2 = new PdfPTable(2);
        inner2.addCell("a1");
        inner2.addCell("a2");
        PdfPCell a3 = new PdfPCell(new Phrase("a3"));
        a3.setColspan(2);
        inner2.addCell(a3);



        PdfPTable out = new PdfPTable(1);
        out.addCell(inner1);
        out.addCell(inner2);

        document.add(out);
        factory.close(document);

    }

    @Test
    public void testTableInTableCell() throws Exception {


        TableCellRow innerTable = new TableCellRow("row3",
                new PdfTableElementWithStaticHeader(
                        new PdfTableHeader(1), new PdfTableRow(new StringCellRow("name"))
                )

        );

        List<SecLvlTableExample> feed = SecLvlTableExample.feed();
        gen("tableInTable.pdf", feed, Arrays.<CellRow>asList(
                new StringCellRow("row1"),
                new StringCellRow("row2"),
                innerTable));

    }


    @Test
    public void test3LvlTable() throws Exception {
        PdfTableElementWithStaticHeader simpleTable = new PdfTableElementWithStaticHeader(
                new PdfTableRow(new StringCellRow("name"))
        );

        PdfTableElementWithStaticHeader secLvlTable = new PdfTableElementWithStaticHeader(
                new PdfTableRow(Arrays.<CellRow>asList(
                        new StringCellRow("row1"),
                        new StringCellRow("row2"),
                        new TableCellRow("row3", simpleTable
                        )))

        );

        PdfTableElementWithStaticHeader rootTable = new PdfTableElementWithStaticHeader(
                new PdfTableHeader(2, ImmutableList.of(new TableHeaderColumn("unique"), new TableHeaderColumn("tables"))),
                new PdfTableRow(Arrays.<CellRow>asList(
                        new StringCellRow("unique"),
                        new TableCellRow("tab", secLvlTable)
                ))

        );

        Document document = factory.create(new FileOutputStream(new File("3lvlTable.pdf")), new DocumentMetaDataImpl());
        PdfPTable table = rootTable.print(ThirdLvlTableExample.feed());
        document.add(table);
        factory.close(document);
    }

    @Test(enabled = false)
    public void testTableSecMode() throws Exception {
        PdfTableElementWithStaticHeader simpleTable = new PdfTableElementWithStaticHeader(
                new PdfTableRow(new StringCellRow("name"))
        );

        PdfTableElementWithStaticHeader secLvlTable = new PdfTableElementWithStaticHeader(
                new PdfTableHeader(1, ImmutableList.of(new TableHeaderColumn("ds"))),
                new PdfTableRow(Arrays.<CellRow>asList(
                        new StringCellRow("row1"),
                        new StringCellRow("row2"),
                        new TableCellRow("row3", simpleTable
                        )))

        );
//
//        PdfTableElement rootTable = new PdfTableElement(
//                new PdfTableHeader(2, ImmutableList.of(new TableHeaderColumn("unique"), new TableHeaderColumn("tables"))),
//                new PdfTableRow(Arrays.<CellRow>asList(
//                        new StringCellRow("unique")         ,
//                        new TableCellRow("tab", secLvlTable)
//                ))
//
//        );

        Document document = factory.create(new FileOutputStream(new File("tableSecMode.pdf")), new DocumentMetaDataImpl());
//        PdfPTable table = rootTable.print(ThirdLvlTableExample.feed());
//        document.add(table);
        factory.close(document);
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
