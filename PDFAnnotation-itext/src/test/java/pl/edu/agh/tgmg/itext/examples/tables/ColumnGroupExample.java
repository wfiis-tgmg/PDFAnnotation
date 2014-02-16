package pl.edu.agh.tgmg.itext.examples.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroup;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroups;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfTable;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;

@PdfDocument
class TableWrapperDTO3 extends TableDocumentWrapper<ColumnGroupExample>{
    @PdfTable
    List<ColumnGroupExample> table = Collections.nCopies(4, new ColumnGroupExample());

    @Override
    public List<ColumnGroupExample> getTable() {
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

public class ColumnGroupExample extends TableExample {
    
    @PdfColumn
    String col1 = "item1";
    @PdfColumn(group="g1")
    String col2 = "item2";
    @PdfColumn(group="g2")
    String col3 = "item3";
    @PdfColumn(group="g2")
    String col4 = "item4";
    
    @Override
    public PdfTableHeader getExpectedHeader() {
        return new PdfTableHeader(4, Arrays.asList(
            new TableHeaderColumn(3, 1, "col1"),
            new TableHeaderColumn(1, 3, "g1"),
            new TableHeaderColumn(2, 1, "col2"),
            new TableHeaderColumn(1, 2, "g2"),
            new TableHeaderColumn(1, 1, "col3"),
            new TableHeaderColumn(1, 1, "col4")));
    }
    @Override
    public PdfTableRow getExpectedRow() {
        return new PdfTableRow(
            new StringCellRow("col1"), new StringCellRow("col2"),
            new StringCellRow("col3"), new StringCellRow("col4"));
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO3();
    }
}