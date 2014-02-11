package pl.edu.agh.tgmg.itext.generators.buildingblocks.parser;

import junit.framework.Assert;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableRowParser;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;

public class PdfTableRowParserTest {

    @Test
    public void testSimpleRows() {
        PdfTableRow expectedRows = new PdfTableRow(
                new StringCellRow("col1"), new StringCellRow("col2"),
                new StringCellRow("col3"), new StringCellRow("col4"));
        checkRows(SimpleColumnsDTO.class, expectedRows);
    }
    
    @Test
    public void testSimpleRowsWithNamedColumns() {
        PdfTableRow expectedRows = new PdfTableRow(
                new StringCellRow("col1"), new StringCellRow("col2"),
                new StringCellRow("col3"));
        checkRows(NamedColumnsDTO.class, expectedRows);
    }
    
    private void checkRows(Class<?> testedClass, PdfTableRow expectedRows) {
        PdfTableRowParser rowParser = new PdfTableRowParser();
        PdfTableRow actualRows = rowParser.parse(testedClass);
        Assert.assertEquals(expectedRows, actualRows);
    }
}
