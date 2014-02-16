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
class TableWrapperDTO2 extends TableDocumentWrapper<NamedColumnsExample> {
    @PdfTable
    List<NamedColumnsExample> table = Collections.nCopies(4, new NamedColumnsExample()); 

    @Override
    public List<NamedColumnsExample> getTable() {
        return table;
    }

    @Override
    public String getTableFieldName() {
        return "table";
    }
}

@PdfColumnGroups({
    @PdfColumnGroup(id="g1", name="group1")})
public class NamedColumnsExample extends TableExample {

    @PdfColumn(name="column1")
    String col1 = "item1";
    @PdfColumn(group="g1", name="column2")
    String col2 = "item2";
    @PdfColumn(group="g1", name="column3")
    String col3 = "item3";
    
    @Override
    public PdfTableHeader getExpectedHeader() {
        return new PdfTableHeader(3, Arrays.asList(
            new TableHeaderColumn(2, 1, "column1"),
            new TableHeaderColumn(1, 2, "group1"),
            new TableHeaderColumn(1, 1, "column2"),
            new TableHeaderColumn(1, 1, "column3")));
    }
    @Override
    public PdfTableRow getExpectedRow() {
        return new PdfTableRow(
            new StringCellRow("col1"), new StringCellRow("col2"),
            new StringCellRow("col3"));
    }
    @Override
    public TableDocumentWrapper<? extends TableExample> getDocummentWrapper() {
        return new TableWrapperDTO2();
    }
}