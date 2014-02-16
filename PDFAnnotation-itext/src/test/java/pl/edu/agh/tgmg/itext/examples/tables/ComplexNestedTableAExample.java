package pl.edu.agh.tgmg.itext.examples.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroup;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroups;
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
class TableWrapperDTO7 extends TableDocumentWrapper<ComplexNestedTableAExample> {
    @PdfTable
    List<ComplexNestedTableAExample> table = Arrays.asList(
            new ComplexNestedTableAExample(4, 1, 4),
            new ComplexNestedTableAExample(4, 2, 2),
            new ComplexNestedTableAExample(2, 1, 2),
            new ComplexNestedTableAExample(2, 1, 2));

    @Override
    public List<ComplexNestedTableAExample> getTable() {
        return table;
    }

    @Override
    public String getTableFieldName() {
        return "table";
    }
}

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g2", parent="g1")})
public class ComplexNestedTableAExample extends TableExample {
    public ComplexNestedTableAExample() {
        this(4, 2, 2);
    }
    public ComplexNestedTableAExample(int n1, int n2, int n2n) {
        table1 = Collections.nCopies(n1, new ComplexNestedTableD());
        table2 = Collections.nCopies(n2, new ComplexNestedTableB(n2n));
    }
    @PdfRowGroup
    List<ComplexNestedTableD> table1;
    @PdfColumn
    String col1 = "col A1";
    @PdfRowGroup
    List<ComplexNestedTableB> table2;
    @PdfColumn(group="g2")
    String col5 = "col A2";
    
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
        List<CellRow> innerTable1 = Arrays.asList(
                innerTable4, new StringCellRow("col1"), 
                innerTable2, new StringCellRow("col5"));
        return new PdfTableRow(innerTable1);
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO7();
    }
}