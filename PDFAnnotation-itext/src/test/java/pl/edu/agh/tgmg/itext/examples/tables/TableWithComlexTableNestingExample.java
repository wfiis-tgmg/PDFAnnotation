package pl.edu.agh.tgmg.itext.examples.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfTable;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroupHeader;
import pl.edu.agh.tgmg.api.buildingBlocks.CellRow;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableElementWithStaticHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableWithDynamicHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.SingleDataTable;
import pl.edu.agh.tgmg.itext.generators.dto.DynamicTableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;
import pl.edu.agh.tgmg.itext.wrapper.TableCellRow;

@PdfDocument
class TableWrapperDTO8 extends TableDocumentWrapper<TableWithComlexTableNestingExample>{
    @PdfTable
    List<TableWithComlexTableNestingExample> table = Arrays.asList(
            new TableWithComlexTableNestingExample("H1", "H2", 2, 4, 2, 2),
            new TableWithComlexTableNestingExample("H3", "H4", 2, 2, 1, 2));

    @Override
    public List<TableWithComlexTableNestingExample> getTable() {
        return table;
    }

    @Override
    public String getTableFieldName() {
        return "table";
    }
}

public class TableWithComlexTableNestingExample extends TableExample {
    public TableWithComlexTableNestingExample() {
        this("h1", "h2", 2, 4, 2, 2);
    }
    public TableWithComlexTableNestingExample(String h1, String h2, int n, int n1, int n2, int n2n) {
        this.h1 = h1; this.h2 = h2; 
        this.table = Collections.nCopies(2, new ComplexNestedTableAExample(n1, n2, n2n));
    }
    @PdfTableGroupHeader(name="header1")
    String h1 = "H 1";
    @PdfTableGroupHeader(name="header2")
    String h2 = "H 2";
    @PdfTableGroup
    List<ComplexNestedTableAExample> table;
    
    @Override
    public PdfTableHeader getExpectedHeader() {
        return new PdfTableHeader(6, Arrays.asList(
            new TableHeaderColumn(4, 1, "col0"),
            new TableHeaderColumn(4, 1, "col1"),
            new TableHeaderColumn(1, 4, "g1"),
            new TableHeaderColumn(3, 1, "col2"),
            new TableHeaderColumn(1, 3, "g2"),
            new TableHeaderColumn(1, 2, "g3"),
            new TableHeaderColumn(2, 1, "col5"),
            new TableHeaderColumn(1, 1, "col3"),
            new TableHeaderColumn(1, 1, "col4")));
    }
    @Override
    public PdfTableRow getExpectedRow() {
        TableCellRow innerTable4 = new TableCellRow("table1",
                new PdfTableElementWithStaticHeader( new PdfTableRow(
                        new StringCellRow("col0") )));
        TableCellRow innerTable3 = new TableCellRow("table",
                new PdfTableElementWithStaticHeader( new PdfTableRow(
                        new StringCellRow("col3") )));
        TableCellRow innerTable2 = new TableCellRow("table2",
                new PdfTableElementWithStaticHeader( new PdfTableRow(
                        new StringCellRow("col2"), innerTable3, 
                        new StringCellRow("col4"))));
        SingleDataTable header = new SingleDataTable(2, Arrays.asList(
                new DynamicTableHeaderColumn("header1", "h1"),
                new DynamicTableHeaderColumn("header2", "h2")));
        CellRow innerTable1 = new PdfTableWithDynamicHeader("table",  new PdfTableRow(
                innerTable4, new StringCellRow("col1"), 
                innerTable2, new StringCellRow("col5")), header);
        return new PdfTableRow(Arrays.asList(innerTable1));
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO8();
    }
}