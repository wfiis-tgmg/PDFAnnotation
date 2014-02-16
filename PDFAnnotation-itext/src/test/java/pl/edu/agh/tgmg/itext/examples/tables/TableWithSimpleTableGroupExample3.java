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
class TableWrapperDTO11 extends TableDocumentWrapper<TableWithSimpleTableGroupExample3> {
    @PdfTable
    List<TableWithSimpleTableGroupExample3> table = Arrays.asList(
            new TableWithSimpleTableGroupExample3("header 1", 3),
            new TableWithSimpleTableGroupExample3("header 2", 1),
            new TableWithSimpleTableGroupExample3("header 3", 2));

    @Override
    public List<TableWithSimpleTableGroupExample3> getTable() {
        return table;
    }

    @Override
    public String getTableFieldName() {
        return "table";
    }
}

public class TableWithSimpleTableGroupExample3 extends TableExample {
    public TableWithSimpleTableGroupExample3() {
        this("H1", 3);
    }
    public TableWithSimpleTableGroupExample3(String header, int n) {
        this.header = header; 
        this.table = Collections.nCopies(n, new TableWithSimpleRowGroupExample(2));
    }
    
    @PdfTableGroupHeader
    String header;
    @PdfTableGroup
    List<TableWithSimpleRowGroupExample> table;
    
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
        
        SingleDataTable header = new SingleDataTable(1, Arrays.asList(
                new DynamicTableHeaderColumn("header", "header")));
        CellRow innerTable2 = new PdfTableWithDynamicHeader("table", 
                new PdfTableRow(cellRows), header);
        return new PdfTableRow(Arrays.asList(innerTable2));
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO11();
    }
}