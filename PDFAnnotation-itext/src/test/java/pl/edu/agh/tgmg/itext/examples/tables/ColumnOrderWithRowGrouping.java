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

class SimpleRowGroupWithGrouping {
    public SimpleRowGroupWithGrouping() {}
    public SimpleRowGroupWithGrouping(String col1, String col2) {
        this.col1 = col1; this.col2 = col2;
    }
    @PdfColumn(order=2)
    String col1 = "item S1";
    @PdfColumn(order=1)
    String col2 = "item S2";
}

@PdfDocument
class TableWrapperDTO14 extends TableDocumentWrapper<ColumnOrderWithRowGrouping> {
    @PdfTable
    List<ColumnOrderWithRowGrouping> table = Arrays.asList(
            new ColumnOrderWithRowGrouping(3),
            new ColumnOrderWithRowGrouping(1),
            new ColumnOrderWithRowGrouping(2));

    @Override
    public List<ColumnOrderWithRowGrouping> getTable() {
        return table;
    }

    @Override
    public String getTableFieldName() {
        return "table";
    }
}

public class ColumnOrderWithRowGrouping extends TableExample {
    public ColumnOrderWithRowGrouping() {
        this(4);
    }
    public ColumnOrderWithRowGrouping(int n) {
        table = Collections.nCopies(n, new SimpleRowGroupWithGrouping());
    }
    @PdfColumn(order=3)
    String col3 = "item 1";
    @PdfRowGroup(order=1)
    List<SimpleRowGroupWithGrouping> table;
    @PdfColumn(order=2)
    String col4 = "item 2";
    
    @Override
    public PdfTableHeader getExpectedHeader() {
        return new PdfTableHeader(4, Arrays.asList(
            new TableHeaderColumn("col2"), 
            new TableHeaderColumn("col1"),
            new TableHeaderColumn("col4"),
            new TableHeaderColumn("col3")));
    }
    @Override
    public PdfTableRow getExpectedRow() {
        List<CellRow> cellRows = new LinkedList<CellRow>();
        TableCellRow innerTable = new TableCellRow("table",
                new PdfTableElementWithStaticHeader( new PdfTableRow(
                        new StringCellRow("col2"), new StringCellRow("col1"))));
        cellRows.add(innerTable);
        cellRows.add(new StringCellRow("col4"));
        cellRows.add(new StringCellRow("col3"));
        return new PdfTableRow(cellRows);
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO14();
    }
}