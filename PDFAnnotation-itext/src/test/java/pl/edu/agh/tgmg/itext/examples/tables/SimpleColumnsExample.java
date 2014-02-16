package pl.edu.agh.tgmg.itext.examples.tables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfTable;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;


class TableWrapperDTO1 extends TableDocumentWrapper<SimpleColumnsExample> {
    @PdfTable
    List<SimpleColumnsExample> table = Collections.nCopies(4, new SimpleColumnsExample());
    
    @Override
    public List<SimpleColumnsExample> getTable() {
        return table;
    }
    
    @Override
    public String getTableFieldName() {
        return "table";
    }
}

@Test
public class SimpleColumnsExample extends TableExample {

    @PdfColumn
    String col1 = "item1";
    @PdfColumn
    String col2 = "item2";
    @PdfColumn
    String col3 = "item3";
    @PdfColumn
    String col4 = "item4";

    @Override
    public PdfTableHeader getExpectedHeader() {
        return new PdfTableHeader(4, Arrays.asList(
            new TableHeaderColumn("col1"), 
            new TableHeaderColumn("col2"),
            new TableHeaderColumn("col3"),
            new TableHeaderColumn("col4")));
    }
    @Override
    public PdfTableRow getExpectedRow() {
        return new PdfTableRow(
            new StringCellRow("col1"), new StringCellRow("col2"),
            new StringCellRow("col3"), new StringCellRow("col4"));
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO1();
    }
}