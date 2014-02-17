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
class TableWrapperDTO6 extends TableDocumentWrapper<TableWithSimpleTableGroupExample> {
    @PdfTable
    List<TableWithSimpleTableGroupExample> table = Arrays.asList(
            new TableWithSimpleTableGroupExample("header 1", 3),
            new TableWithSimpleTableGroupExample("header 2", 1),
            new TableWithSimpleTableGroupExample("header 3", 2));

    @Override
    public List<TableWithSimpleTableGroupExample> getTable() {
        return table;
    }

    @Override
    public String getTableFieldName() {
        return "table";
    }
}

public class TableWithSimpleTableGroupExample extends TableExample {
    public TableWithSimpleTableGroupExample() {
        this("H1", 3);
    }
    public TableWithSimpleTableGroupExample(String header, int n) {
        this.header = header; 
        this.table = Collections.nCopies(n, new SimpleRowGroup());
    }
    
    @PdfTableGroupHeader
    String header;
    @PdfTableGroup
    List<SimpleRowGroup> table;
    
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
        SingleDataTable header = new SingleDataTable(1, Arrays.asList(
                new DynamicTableHeaderColumn("header", "header")));
        CellRow innerTable = new PdfTableWithDynamicHeader("table", 
                new PdfTableRow(innerCellRows), header);
        return new PdfTableRow(Arrays.asList(innerTable));
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO6();
    }
}