package pl.edu.agh.tgmg.itext.generators.buildingblocks.parser;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroup;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroups;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroup;
import pl.edu.agh.tgmg.api.annotations.PdfRowGroup;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableHeaderParser;
import pl.edu.agh.tgmg.api.exceptions.InvalidGroupException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;

//--------- SIMPLE COLUMNS ----------

class SimpleColumnsDTO {
    @PdfColumn
    String col1;
    @PdfColumn
    String col2;
    @PdfColumn
    String col3;
    @PdfColumn
    String col4;
}

// --------- COLUMN GROUPING ----------

@PdfColumnGroups({
    @PdfColumnGroup(id="g1", name="group1")})
class NamedColumnsDTO {
    
    @PdfColumn(name="column1")
    String col1;
    @PdfColumn(group="g1", name="column2")
    String col2;
    @PdfColumn(group="g1", name="column3")
    String col3;
}

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g2", parent="g1")})
class ColumnGroupDTO {
    @PdfColumn
    String col1;
    @PdfColumn(group="g1")
    String col2;
    @PdfColumn(group="g2")
    String col3;
    @PdfColumn(group="g2")
    String col4;
}

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g2"),
    @PdfColumnGroup(id="g3", parent="g2")})
class ColumnGroup2DTO {
    @PdfColumn(group="g1")
    String col1;
    @PdfColumn(group="g2")
    String col2;
    @PdfColumn(group="g2")
    String col3;
}

//--------- COLUMNS WITH SIMPLE NESTED TABLES ----------

class SimpleRowGroupDTO {
    @PdfColumn
    String col1;
    @PdfColumn
    String col2;
}

class ColumnWithSimpleRowGroupDTO {
    @PdfColumn
    String col3;
    @PdfRowGroup
    List<SimpleRowGroupDTO> table;
    @PdfColumn
    String col4;
}

class ColumnWithSimpleTableGroupDTO {
    @PdfTableGroup
    List<SimpleRowGroupDTO> table;
}

//--------- COLUMN GROUPING WITH COMPLEX NESTED TABLES ----------

class ColumnWithComlexTableNestingDTO {
    @PdfTableGroup
    List<ComplexNestedTableA> table;
}

class ComplexNestedTableA {
    @PdfTableGroup
    List<ComplexNestedTableB> table;
}

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g2", parent="g1")})
class ComplexNestedTableB {
    @PdfColumn
    String col1;
    @PdfRowGroup
    List<ComplexNestedTableC> table;
    @PdfColumn(group="g2")
    String col5;
}

@PdfColumnGroups({
    @PdfColumnGroup(id="g3", parent="g2")})
class ComplexNestedTableC {
    @PdfColumn(group="g1")
    String col2;
    @PdfRowGroup
    List<ComplexNestedTableD> table;
    @PdfColumn(group="g3")
    String col4;
}

class ComplexNestedTableD {
    @PdfColumn(group="g3")
    String col3;
}

//--------- ERRORS ----------

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g2", parent="invalidGroup")})
class ColumnGroupError1DTO {
    
    @PdfColumn
    String col1;
}

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g1")})
class ColumnGroupError2DTO {
    @PdfColumn
    String col1;
}

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g2")})
class ColumnGroupError3DTO {
    
    @PdfColumn
    String col1;
    @PdfColumn(group="g1")
    String col2;
    @PdfColumn(group="g2")
    String col3;
    @PdfColumn(group="g3")
    String col4;
}

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g2", parent="g3"),
    @PdfColumnGroup(id="g3", parent="g2")})
class ColumnGroupError4DTO {
    
    @PdfColumn
    String col1;
}


public class PdfTableHeaderParserTest {

    //--------- SIMPLE COLUMNS ----------
    
    @Test
    public void testSimpleColumns() {    
        Class<?> testedClass = SimpleColumnsDTO.class;
        int expectedColumns = 4;
        String[] expectedNames = {"col1", "col2", "col3", "col4" };
        checkColumns(testedClass, expectedColumns, expectedNames);
    }
    
    // --------- COLUMN GROUPING ----------
    
    @Test
    public void testNamedColumns() {
        Class<?> testedClass = NamedColumnsDTO.class;
        int expectedColumns = 3;
        String[] expectedNames = {"column1", "group1", "column2", "column3"};
        int[] expectedColSpans = { 1, 2, 1, 1 };
        int[] expectedRowSpans = { 2, 1, 1, 1 };
        checkColumns(testedClass, expectedColumns, expectedNames, expectedColSpans, expectedRowSpans);
    }
    
    @Test
    public void testColumnGrouping() {
        Class<?> testedClass = ColumnGroupDTO.class;
        int expectedColumns = 4;
        String[] expectedNames = {"col1", "g1", "col2", "g2", "col3", "col4" };
        int[] expectedColSpans = { 1, 3, 1, 2, 1, 1 };
        int[] expectedRowSpans = { 3, 1, 2, 1, 1, 1 };
        checkColumns(testedClass, expectedColumns, expectedNames, expectedColSpans, expectedRowSpans);
    }
    
    @Test
    public void testColumnGrouping2() {    
        Class<?> testedClass = ColumnGroup2DTO.class;
        int expectedColumns = 3;
        String[] expectedNames = {"g1", "g2", "col1", "col2", "col3"};
        int[] expectedColSpans = { 1, 2, 1, 1, 1 };
        int[] expectedRowSpans = { 1, 1, 1, 1, 1 };
        checkColumns(testedClass, expectedColumns, expectedNames, expectedColSpans, expectedRowSpans);
    }
    
    //--------- COLUMNS WITH SIMPLE NESTED TABLES ----------
    
    @Test
    public void testColumnWithSimpleRowGroup() {
        Class<?> testedClass = ColumnWithSimpleRowGroupDTO.class;
        int expectedColumns = 4;
        String[] expectedNames = {"col3", "col1", "col2", "col4" };
        checkColumns(testedClass, expectedColumns, expectedNames);
    }
    
    @Test
    public void testColumnWithSimpleTableGroup() {    
        Class<?> testedClass = ColumnWithSimpleTableGroupDTO.class;
        int expectedColumns = 2;
        String[] expectedNames = {"col1", "col2" };
        checkColumns(testedClass, expectedColumns, expectedNames);
    }
    
    //--------- COLUMN GROUPING WITH COMPLEX NESTED TABLES ----------
    
    @Test
    public void testColumnWithComlexTableNesting () {
        Class<?> testedClass = ColumnWithComlexTableNestingDTO.class;
        int expectedColumns = 5;
        String[] expectedNames = {"col1", "g1", "col2", "g2", "g3" , "col5", "col3", "col4"};
        int[] expectedColSpans = { 1, 4, 1, 3, 2, 1, 1, 1 };
        int[] expectedRowSpans = { 4, 1, 3, 1, 1, 2, 1, 1 };
        checkColumns(testedClass, expectedColumns, expectedNames, expectedColSpans, expectedRowSpans);
    }
    
    //--------- ERRORS ----------
    
    @Test
    public void testGroupingErrors1() {
        checkErrors(ColumnGroupError1DTO.class, "parent .* for group .* does not exist!");
    }
    
    @Test
    public void testGroupingErrors2() {
        checkErrors(ColumnGroupError2DTO.class, "group .* already exists!");
    }
    
    @Test
    public void testGroupingErrors3() {
        checkErrors(ColumnGroupError3DTO.class, ".* group not found");
    }
    
    @Test
    public void testGroupingErrors4() {
        checkErrors(ColumnGroupError4DTO.class, "cyclic dependency encountered!");
    }
    
    //--------- HELPERS ----------
    
    private void checkColumns(Class<?> clazz, int expectedColumns, 
            String[] expectedNames) {
        int[] expectedSpans = new int[expectedNames.length];
        Arrays.fill(expectedSpans, 1);
        checkColumns(clazz, expectedColumns, expectedNames, expectedSpans, expectedSpans);
    }
    
    private void checkColumns(Class<?> clazz, int expectedColumns, 
            String[] expectedNames, int[] expectedColSpans, int[] expectedRowSpans) {
        PdfTableHeaderParser headerParser = new PdfTableHeaderParser();
        PdfTableHeader header = headerParser.parse(clazz);
        Assert.assertEquals(expectedColumns, header.getColumns());
        List<TableHeaderColumn> headers = header.getHeaderColumns();
        Assert.assertEquals(expectedNames.length, headers.size());
        for(int i=0;i<expectedNames.length;i++) {
            TableHeaderColumn column = headers.get(i);
            Assert.assertEquals(expectedNames[i], column.getText());
            Assert.assertEquals(expectedColSpans[i], column.getColSpan());
            Assert.assertEquals(expectedRowSpans[i], column.getRowSpan());
        }
    }
    
    private void checkErrors(Class<?> clazz, String errorMessagePattern) {
        PdfTableHeaderParser headerParser = new PdfTableHeaderParser();
        try {
            headerParser.parse(clazz);
            Assert.fail("InvalidGroup exception should be thrown");
        } catch(InvalidGroupException e) {
            Assert.assertTrue(e.getMessage().matches(errorMessagePattern));
        }
    }
}
