package pl.edu.agh.tgmg.itext.examples.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
class TableWrapperDTO9 extends TableDocumentWrapper<DoubleRowGroupsExample> {
    @PdfTable
    List<DoubleRowGroupsExample> table = Arrays.asList(
            new DoubleRowGroupsExample(2, 4),
            new DoubleRowGroupsExample(4, 4),
            new DoubleRowGroupsExample(3, 2));

    @Override
    public List<DoubleRowGroupsExample> getTable() {
        return table;
    }

    @Override
    public String getTableFieldName() {
        return "table";
    }
}

public class DoubleRowGroupsExample extends TableExample {
    public DoubleRowGroupsExample() {
        this(2, 3);
    }
    public DoubleRowGroupsExample(int n1, int n2) {
        table1 = Collections.nCopies(n1, new ComplexNestedTableD());
        table2 = Collections.nCopies(n2, new ComplexNestedTableD());
    }
    @PdfRowGroup
    List<ComplexNestedTableD> table1;
    @PdfRowGroup
    List<ComplexNestedTableD> table2;
    @Override
    
    public PdfTableHeader getExpectedHeader() {
        return new PdfTableHeader(2, Arrays.asList(
            new TableHeaderColumn("col0"), 
            new TableHeaderColumn("col0")));
    }
    @Override
    public PdfTableRow getExpectedRow() {
        TableCellRow innerTable1 = new TableCellRow("table1",
                new PdfTableElementWithStaticHeader( new PdfTableRow(
                        new StringCellRow("col0"))));
        TableCellRow innerTable2 = new TableCellRow("table2",
                new PdfTableElementWithStaticHeader( new PdfTableRow(
                        new StringCellRow("col0"))));
        List<CellRow> list = Arrays.asList((CellRow) innerTable1,(CellRow) innerTable2);
        return new PdfTableRow(list);
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO9();
    }
}