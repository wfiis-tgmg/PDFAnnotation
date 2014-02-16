package pl.edu.agh.tgmg.itext.examples.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfTable;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroupHeader;
import pl.edu.agh.tgmg.api.buildingBlocks.CellRow;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableWithDynamicHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.SingleDataTable;
import pl.edu.agh.tgmg.itext.generators.dto.DynamicTableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;

@PdfDocument
class TableWrapperDTO10 extends TableDocumentWrapper<TableWithSimpleTableGroup2Example> {
    @PdfTable
    List<TableWithSimpleTableGroup2Example> table = Arrays.asList(
            new TableWithSimpleTableGroup2Example(3),
            new TableWithSimpleTableGroup2Example(2),
            new TableWithSimpleTableGroup2Example(4));

    @Override
    public List<TableWithSimpleTableGroup2Example> getTable() {
        return table;
    }

    @Override
    public String getTableFieldName() {
        return "table";
    }
}

public class TableWithSimpleTableGroup2Example extends TableExample {
    public TableWithSimpleTableGroup2Example() {
        this(4);
    }
    public TableWithSimpleTableGroup2Example(int n) {
        table = Collections.nCopies(n, new SimpleRowGroupExample());
    }
    @PdfTableGroupHeader
    String header1 = "h1";
    @PdfTableGroupHeader
    String header2 = "h2";
    @PdfTableGroupHeader
    String header3 = "h3";
    @PdfTableGroup
    List<SimpleRowGroupExample> table;
    
    @Override
    public PdfTableHeader getExpectedHeader() {
        return new PdfTableHeader(2, Arrays.asList(
            new TableHeaderColumn("col1"), 
            new TableHeaderColumn("col2")));
    }
    @Override
    public PdfTableRow getExpectedRow() {
        List<CellRow> innerCellRows = new LinkedList<CellRow>();
        innerCellRows.add(new StringCellRow("col1"));
        innerCellRows.add(new StringCellRow("col2"));
        SingleDataTable header = new SingleDataTable(3, Arrays.asList(
                new DynamicTableHeaderColumn("header1", "header1"),
                new DynamicTableHeaderColumn("header2", "header2"),
                new DynamicTableHeaderColumn("header3", "header3")));
        CellRow innerTable = new PdfTableWithDynamicHeader("table", 
                new PdfTableRow(innerCellRows), header);
        return new PdfTableRow(Arrays.asList(innerTable));
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO10();
    }
}