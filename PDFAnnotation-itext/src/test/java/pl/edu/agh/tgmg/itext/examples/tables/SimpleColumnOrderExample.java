package pl.edu.agh.tgmg.itext.examples.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfTable;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;

@PdfDocument
class TableWrapperDTO12 extends TableDocumentWrapper<SimpleColumnOrderExample> {
    @PdfTable
    List<SimpleColumnOrderExample> table = Collections.nCopies(4, new SimpleColumnOrderExample());

    @Override
    public List<SimpleColumnOrderExample> getTable() {
        return table;
    }

    @Override
    public String getTableFieldName() {
        return "table";
    }
}

public class SimpleColumnOrderExample extends TableExample {

    @PdfColumn(order = 3)
    String col1 = "item1";
    @PdfColumn(order = 4)
    String col2 = "item2";
    @PdfColumn(order = 1)
    String col3 = "item3";
    @PdfColumn(order = 2)
    String col4 = "item4";
    
    @Override
    public PdfTableHeader getExpectedHeader() {
        return new PdfTableHeader(4, Arrays.asList(
            new TableHeaderColumn("col3"), 
            new TableHeaderColumn("col4"),
            new TableHeaderColumn("col1"),
            new TableHeaderColumn("col2")));
    }
    @Override
    public PdfTableRow getExpectedRow() {
        return new PdfTableRow(
            new StringCellRow("col3"), new StringCellRow("col4"),
            new StringCellRow("col1"), new StringCellRow("col2"));
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO12();
    }

}
