package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import java.io.File;
import java.io.FileOutputStream;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.TestGroup;
import pl.edu.agh.tgmg.api.buildingBlocks.CellRow;
import pl.edu.agh.tgmg.examples.SecLvlTableExample;
import pl.edu.agh.tgmg.itext.generators.dto.DynamicTableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;

import com.google.common.collect.ImmutableList;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;

@Test(groups = TestGroup.GEN_DOC)
public class PdfTableWithDynamicHeaderTest {

    DefaultITextDocumentFactory factory = new DefaultITextDocumentFactory();



    @Test
        public void testGen() throws Exception {
            Document document = factory.create(new FileOutputStream(new File("tableWithDynamicHeader.pdf")));

            PdfTableWithDynamicHeader table = new PdfTableWithDynamicHeader(
                    "row3",
                    new PdfTableRow(new StringCellRow("name"), new StringCellRow("col")),
                    new SingleDataTable(2, ImmutableList.of(new DynamicTableHeaderColumn(1, 2, "Title", "row1"))));

            document.add(table.print(SecLvlTableExample.feed().get(3)));
        factory.close(document);
    }

    @Test
    public void testGen2() throws Exception {
        Document document = factory.create(new FileOutputStream(new File("tableWithDynamicHeaderInTable1.pdf")));
        CellRow tableCell = new PdfTableWithDynamicHeader(
                "row3",
                new PdfTableRow(new StringCellRow("name"), new StringCellRow("col")),
                new SingleDataTable(2, ImmutableList.of(new DynamicTableHeaderColumn(1, 2, "Title", "row1"))));


        PdfTableElementWithStaticHeader rootTable = new PdfTableElementWithStaticHeader(
                new PdfTableHeader(2, ImmutableList.of(new TableHeaderColumn("Static Col1"),new TableHeaderColumn("Static Col2"))),
                new PdfTableRow(tableCell)
        );


        PdfPTable print = rootTable.print(SecLvlTableExample.feed());

        document.add(print);
        factory.close(document);
    }

    @Test
    public void testGen3() throws Exception {
        Document document = factory.create(new FileOutputStream(new File("tableWithDynamicHeaderInTable2.pdf")));
        CellRow tableCell = new PdfTableWithDynamicHeader(
                "row3",
                new PdfTableRow(new StringCellRow("name"), new StringCellRow("col")),
                new SingleDataTable(3, ImmutableList.of(new DynamicTableHeaderColumn(1, 1, "Title", "row1"),new DynamicTableHeaderColumn(1, 1, "Title", "row1"),new DynamicTableHeaderColumn(1, 1, "Unique Name", "row1"))));


        PdfTableElementWithStaticHeader rootTable = new PdfTableElementWithStaticHeader(
                new PdfTableHeader(2, ImmutableList.of(new TableHeaderColumn("Static Col1"),new TableHeaderColumn("Static Col2"))),
                new PdfTableRow(tableCell)
        );


        PdfPTable print = rootTable.print(SecLvlTableExample.feed());

        document.add(print);
        factory.close(document);
    }

}
