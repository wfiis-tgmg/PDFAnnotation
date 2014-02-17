package pl.edu.agh.tgmg.itext.examples.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.testng.annotations.Test;

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
class TableWrapperDTO13 extends TableDocumentWrapper<ColumnOrderWithColumnGrouping> {
    @PdfTable
    List<ColumnOrderWithColumnGrouping> table = Collections.nCopies(4, new ColumnOrderWithColumnGrouping());

    @Override
    public List<ColumnOrderWithColumnGrouping> getTable() {
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
public class ColumnOrderWithColumnGrouping extends TableExample {
    
    @PdfColumn(order=4)
    String col1 = "item1";
    @PdfColumn(group="g1", order=3)
    String col2 = "item2";
    @PdfColumn(group="g2", order=2)
    String col3 = "item3";
    @PdfColumn(group="g2", order=1)
    String col4 = "item4";
    
    @Override
    public PdfTableHeader getExpectedHeader() {
        return new PdfTableHeader(4, Arrays.asList(
            new TableHeaderColumn(1, 3, "g1"),
            new TableHeaderColumn(3, 1, "col1"),
            new TableHeaderColumn(1, 2, "g2"),
            new TableHeaderColumn(2, 1, "col2"),
            new TableHeaderColumn(1, 1, "col4"),
            new TableHeaderColumn(1, 1, "col3")));
    }
    @Override
    public PdfTableRow getExpectedRow() {
        return new PdfTableRow(
            new StringCellRow("col4"), new StringCellRow("col3"),
            new StringCellRow("col2"), new StringCellRow("col1"));
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO13();
    }
   
}
