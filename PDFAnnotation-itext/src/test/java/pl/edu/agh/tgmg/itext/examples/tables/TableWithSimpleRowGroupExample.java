package pl.edu.agh.tgmg.itext.examples.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfRowGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTable;
import pl.edu.agh.tgmg.api.buildingBlocks.CellRow;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableElementWithStaticHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;
import pl.edu.agh.tgmg.itext.wrapper.TableCellRow;

@PdfDocument
class TableWrapperDTO5 extends TableDocumentWrapper<TableWithSimpleRowGroupExample> {
    @PdfTable
    List<TableWithSimpleRowGroupExample> table = Arrays.asList(
            new TableWithSimpleRowGroupExample(3),
            new TableWithSimpleRowGroupExample(1),
            new TableWithSimpleRowGroupExample(2));

    @Override
    public List<TableWithSimpleRowGroupExample> getTable() {
        return table;
    }

    @Override
    public String getTableFieldName() {
        return "table";
    }
}

public class TableWithSimpleRowGroupExample extends TableExample {
    public TableWithSimpleRowGroupExample() {
        this(4);
    }
    public TableWithSimpleRowGroupExample(int n) {
        table = Collections.nCopies(n, new SimpleRowGroupExample());
    }
    @PdfColumn
    String col3 = "item 1";
    @PdfRowGroup
    List<SimpleRowGroupExample> table;
    @PdfColumn
    String col4 = "item 2";
    
    @Override
    public PdfTableHeader getExpectedHeader() {
        return new PdfTableHeader(4, Arrays.asList(
            new TableHeaderColumn("col3"), 
            new TableHeaderColumn("col1"),
            new TableHeaderColumn("col2"),
            new TableHeaderColumn("col4")));
    }
    @Override
    public PdfTableRow getExpectedRow() {
        List<CellRow> cellRows = new LinkedList<CellRow>();
        TableCellRow innerTable = new TableCellRow("table",
                new PdfTableElementWithStaticHeader( new PdfTableRow(
                        new StringCellRow("col1"), new StringCellRow("col2"))));
        cellRows.add(new StringCellRow("col3"));
        cellRows.add(innerTable);
        cellRows.add(new StringCellRow("col4"));
        return new PdfTableRow(cellRows);
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO5();
    }
}