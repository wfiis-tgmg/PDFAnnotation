package pl.edu.agh.tgmg.itext.generators.buildingblocks.parser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfRowGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroupHeader;
import pl.edu.agh.tgmg.api.buildingBlocks.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableRowParser;
import pl.edu.agh.tgmg.api.exceptions.InvalidTableGroupException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableElementWithStaticHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableWithDynamicHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.SingleDataTable;
import pl.edu.agh.tgmg.itext.generators.dto.DynamicTableHeaderColumn;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;
import pl.edu.agh.tgmg.itext.wrapper.TableCellRow;

class TableGroupError1DTO {
    @PdfTableGroupHeader
    String header;
    @PdfTableGroup
    List<SimpleRowGroupDTO> table;
    @PdfRowGroup
    List<SimpleRowGroupDTO> table2;
}

class TableGroupError2DTO {
    @PdfTableGroupHeader
    String header;
    @PdfTableGroup
    List<SimpleRowGroupDTO> table;
    @PdfColumn
    String col3;
}

class TableGroupError3DTO {
    @PdfTableGroup
    List<SimpleRowGroupDTO> table1;
    @PdfTableGroup
    List<SimpleRowGroupDTO> table2;
}

class TableGroupError4DTO {
    @PdfTableGroupHeader
    String header;
    @PdfColumn
    String col3;
}

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
    
    @Test
    public void testSimpleRowGroup() {
        List<CellRow> cellRows = new LinkedList<CellRow>();
        TableCellRow innerTable = new TableCellRow("table",
                new PdfTableElementWithStaticHeader( new PdfTableRow(
                        new StringCellRow("col1"), new StringCellRow("col2"))));
        cellRows.add(new StringCellRow("col3"));
        cellRows.add(innerTable);
        cellRows.add(new StringCellRow("col4"));
        PdfTableRow expectedRows = new PdfTableRow(cellRows);
        checkRows(ColumnWithSimpleRowGroupDTO.class, expectedRows);
    }
    
    @Test
    public void testSimpleTableGroup() {
        List<CellRow> innerCellRows = new LinkedList<CellRow>();
        innerCellRows.add(new StringCellRow("col1"));
        innerCellRows.add(new StringCellRow("col2"));
        SingleDataTable header = new SingleDataTable(1, Arrays.asList(
                new DynamicTableHeaderColumn("header", "header")));
        CellRow innerTable = new PdfTableWithDynamicHeader("table", 
                new PdfTableRow(innerCellRows), header);
        PdfTableRow expectedRows = new PdfTableRow(Arrays.asList(innerTable));
        checkRows(ColumnWithSimpleTableGroupDTO.class, expectedRows);
    }
    
    @Test
    public void testComplexNestedTable() {
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
        SingleDataTable header = new SingleDataTable(2, Arrays.asList(
                new DynamicTableHeaderColumn("header1", "h1"),
                new DynamicTableHeaderColumn("header2", "h2")));
        CellRow innerTable1 = new PdfTableWithDynamicHeader("table",  new PdfTableRow(
                innerTable4, new StringCellRow("col1"), 
                innerTable2, new StringCellRow("col5")), header);
        PdfTableRow expectedRows = new PdfTableRow(Arrays.asList(innerTable1));
        checkRows(ColumnWithComlexTableNestingDTO.class, expectedRows);
    }
    
    @Test(expectedExceptions=InvalidTableGroupException.class, 
            expectedExceptionsMessageRegExp="table group mixed with other elements in class .*")
    public void testTableGroupErrors1() {
        PdfTableRowParser rowParser = new PdfTableRowParser();
        rowParser.parse(TableGroupError1DTO.class);
    }
    
    @Test(expectedExceptions=InvalidTableGroupException.class,
            expectedExceptionsMessageRegExp="table group mixed with other elements in class .*")
    public void testTableGroupErrors2() {
        PdfTableRowParser rowParser = new PdfTableRowParser();
        rowParser.parse(TableGroupError2DTO.class);
    }
    
    @Test(expectedExceptions=InvalidTableGroupException.class,
            expectedExceptionsMessageRegExp="multiple table groups in class .*")
    public void testTableGroupErrors3() {
        PdfTableRowParser rowParser = new PdfTableRowParser();
        rowParser.parse(TableGroupError3DTO.class);
    }
    
    @Test(expectedExceptions=InvalidTableGroupException.class,
    expectedExceptionsMessageRegExp="table group headers in class .* with no table group")
    public void testTableGroupErrors4() {
        PdfTableRowParser rowParser = new PdfTableRowParser();
        rowParser.parse(TableGroupError4DTO.class);
    }
    
    private void checkRows(Class<?> testedClass, PdfTableRow expectedRows) {
        PdfTableRowParser rowParser = new PdfTableRowParser();
        PdfTableRow actualRows = rowParser.parse(testedClass);
        Assert.assertEquals(expectedRows, actualRows);
    }
}
