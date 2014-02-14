package pl.edu.agh.tgmg.itext.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroup;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroups;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfRowGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTable;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroupHeader;
import pl.edu.agh.tgmg.api.buildingBlocks.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaDataImpl;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableHeaderParser;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableParser;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableRowParser;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableElementWithStaticHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableWithDynamicHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.SingleDataTable;
import pl.edu.agh.tgmg.itext.generators.dto.DynamicTableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.metadata.DefaultITextDocumentFactory;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;
import pl.edu.agh.tgmg.itext.wrapper.TableCellRow;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

//--------- SIMPLE COLUMNS ----------

class SimpleColumnsDTO {
    public SimpleColumnsDTO(String col1, String col2, String col3, String col4) {
        this.col1 = col1; this.col2 = col2; this.col3 = col3; this.col4 = col4;
    }
    @PdfColumn
    String col1;
    @PdfColumn
    String col2;
    @PdfColumn
    String col3;
    @PdfColumn
    String col4;
}

//--------- COLUMN GROUPING ----------

@PdfColumnGroups({
    @PdfColumnGroup(id="g1", name="group1")})
class NamedColumnsDTO {
    public NamedColumnsDTO(String col1, String col2, String col3) {
        this.col1 = col1; this.col2 = col2;this.col3 = col3;
    }
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
    public ColumnGroupDTO(String col1, String col2, String col3, String col4) {
        this.col1 = col1; this.col2 = col2; this.col3 = col3; this.col4 = col4;
    }
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
    public ColumnGroup2DTO(String col1, String col2, String col3) {
        this.col1 = col1; this.col2 = col2; this.col3 = col3;
    }
    @PdfColumn(group="g1")
    String col1;
    @PdfColumn(group="g2")
    String col2;
    @PdfColumn(group="g2")
    String col3;
}

//--------- COLUMNS WITH SIMPLE NESTED TABLES ----------

class SimpleRowGroupDTO {
    public SimpleRowGroupDTO(String col1, String col2) {
        this.col1 = col1; this.col2 = col2;
    }
    @PdfColumn
    String col1;
    @PdfColumn
    String col2;
}

class ColumnWithSimpleRowGroupDTO {
    public ColumnWithSimpleRowGroupDTO(String col3,
            List<SimpleRowGroupDTO> table, String col4) {
        this.col3 = col3; this.table = table; this.col4 = col4;
    }
    @PdfColumn
    String col3;
    @PdfRowGroup
    List<SimpleRowGroupDTO> table;
    @PdfColumn
    String col4;
}

class ColumnWithSimpleTableGroupDTO {
    public ColumnWithSimpleTableGroupDTO(String header,
            List<SimpleRowGroupDTO> table) {
        this.header = header; this.table = table;
    }
    @PdfTableGroupHeader
    String header;
    @PdfTableGroup
    List<SimpleRowGroupDTO> table;
}

//--------- COLUMN GROUPING WITH COMPLEX NESTED TABLES ----------

class ColumnWithComlexTableNestingDTO {
    public ColumnWithComlexTableNestingDTO(String h1, String h2,
            List<ComplexNestedTableA> table) {
        this.h1 = h1; this.h2 = h2; this.table = table;
    }
    @PdfTableGroupHeader(name="header1")
    String h1;
    @PdfTableGroupHeader(name="header2")
    String h2;
    @PdfTableGroup
    List<ComplexNestedTableA> table;
}

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g2", parent="g1")})
class ComplexNestedTableA {
    public ComplexNestedTableA(List<ComplexNestedTableD> table1, String col1,
            List<ComplexNestedTableB> table2, String col5) {
        this.table1 = table1; this.col1 = col1; this.table2 = table2; this.col5 = col5;
    }
    @PdfRowGroup
    List<ComplexNestedTableD> table1;
    @PdfColumn
    String col1;
    @PdfRowGroup
    List<ComplexNestedTableB> table2;
    @PdfColumn(group="g2")
    String col5;
}

@PdfColumnGroups({
    @PdfColumnGroup(id="g3", parent="g2")})
class ComplexNestedTableB {
    public ComplexNestedTableB(String col2, List<ComplexNestedTableC> table, String col4) {
        this.col2 = col2; this.table = table;this.col4 = col4;
    }
    @PdfColumn(group="g1")
    String col2;
    @PdfRowGroup
    List<ComplexNestedTableC> table;
    @PdfColumn(group="g3")
    String col4;
}

class ComplexNestedTableC {
    public ComplexNestedTableC(String col3) {
        this.col3 = col3;
    }
    @PdfColumn(group="g3")
    String col3;
}

class ComplexNestedTableD {
    public ComplexNestedTableD(String col0) {
        this.col0 = col0;
    }
    @PdfColumn
    String col0;
}

//--------- DOCUMNET WRAPPERS ----------

@PdfDocument
class TableWrapperDTO1 {
    public TableWrapperDTO1(List<SimpleColumnsDTO> table) {
        this.table = table;
    }
    @PdfTable
    List<SimpleColumnsDTO> table;
}

@PdfDocument
class TableWrapperDTO2 {
    @PdfTable
    List<NamedColumnsDTO> table;
}

@PdfDocument
class TableWrapperDTO3 {
    @PdfTable
    List<ColumnGroupDTO> table;
}

@PdfDocument
class TableWrapperDTO4 {
    @PdfTable
    List<ColumnGroup2DTO> table;
}

@PdfDocument
class TableWrapperDTO5 {
    @PdfTable
    List<ColumnWithSimpleRowGroupDTO> table;
}

@PdfDocument
class TableWrapperDTO6 {
    @PdfTable
    List<ColumnWithSimpleTableGroupDTO> table;
}

@PdfDocument
class TableWrapperDTO7 {
    @PdfTable
    List<ColumnWithComlexTableNestingDTO> table;
}

public class TableExamples implements BuildingBlocksExamples<PdfTableElementWithStaticHeader> {
    
    @Override
    public Class<?> getExampleClass(int i) {
        switch(i) {
        case 0:
            return SimpleColumnsDTO.class;
        case 1:
            return NamedColumnsDTO.class;
        case 2:
            return ColumnGroupDTO.class;
        case 3:
            return ColumnGroup2DTO.class;
        case 4:
            return ColumnWithSimpleRowGroupDTO.class;
        case 5:
            return ColumnWithSimpleTableGroupDTO.class;
        case 6:
            return ColumnWithComlexTableNestingDTO.class;
        }
        return null;
    }

    @Override
    public Object getExampleDTO(Class<?> clazz) {
        if(clazz.equals(SimpleColumnsDTO.class)) {
            return Arrays.asList(
                    new SimpleColumnsDTO("item1", "item2", "Item3", "Item4"),
                    new SimpleColumnsDTO("item1", "item2", "Item3", "Item4"),
                    new SimpleColumnsDTO("item1", "item2", "Item3", "Item4"),
                    new SimpleColumnsDTO("item1", "item2", "Item3", "Item4"));
        } 
        if(clazz.equals(NamedColumnsDTO.class)) {
            return Arrays.asList(
                    new NamedColumnsDTO("item1", "item2", "Item3"),
                    new NamedColumnsDTO("item1", "item2", "Item3"),
                    new NamedColumnsDTO("item1", "item2", "Item3"),
                    new NamedColumnsDTO("item1", "item2", "Item3"));
        }
        if(clazz.equals(ColumnGroupDTO.class)) {
            return Arrays.asList(
                    new ColumnGroupDTO("item1", "item2", "Item3", "Item4"),
                    new ColumnGroupDTO("item1", "item2", "Item3", "Item4"),
                    new ColumnGroupDTO("item1", "item2", "Item3", "Item4"),
                    new ColumnGroupDTO("item1", "item2", "Item3", "Item4"));
        }
        if(clazz.equals(ColumnGroup2DTO.class)) {
            return Arrays.asList(
                    new ColumnGroup2DTO("item1", "item2", "Item3"),
                    new ColumnGroup2DTO("item1", "item2", "Item3"),
                    new ColumnGroup2DTO("item1", "item2", "Item3"),
                    new ColumnGroup2DTO("item1", "item2", "Item3"));
        }
        if(clazz.equals(ColumnWithSimpleRowGroupDTO.class)) {
            return Arrays.asList(
                    new ColumnWithSimpleRowGroupDTO("item1", Arrays.asList(
                            new SimpleRowGroupDTO("item2", "item3"),
                            new SimpleRowGroupDTO("item2", "item3"),
                            new SimpleRowGroupDTO("item2", "item3")), "item4"),
                    new ColumnWithSimpleRowGroupDTO("item1", Arrays.asList(
                            new SimpleRowGroupDTO("item2", "item3")), "item4"),
                    new ColumnWithSimpleRowGroupDTO("item1", Arrays.asList(
                            new SimpleRowGroupDTO("item2", "item3"),
                            new SimpleRowGroupDTO("item2", "item3")), "item4"));
        }
        if(clazz.equals(ColumnWithSimpleTableGroupDTO.class)) {
            return Arrays.asList(
                    new ColumnWithSimpleTableGroupDTO("some header 1", Arrays.asList(
                            new SimpleRowGroupDTO("item2", "item3"),
                            new SimpleRowGroupDTO("item2", "item3"),
                            new SimpleRowGroupDTO("item2", "item3"))),
                    new ColumnWithSimpleTableGroupDTO("some header 2", Arrays.asList(
                            new SimpleRowGroupDTO("item2", "item3"),
                            new SimpleRowGroupDTO("item2", "item3"))),
                    new ColumnWithSimpleTableGroupDTO("some header 3", Arrays.asList(
                            new SimpleRowGroupDTO("item2", "item3"),
                            new SimpleRowGroupDTO("item2", "item3"),
                            new SimpleRowGroupDTO("item2", "item3"),
                            new SimpleRowGroupDTO("item2", "item3"))));
        }
        if(clazz.equals(ColumnWithComlexTableNestingDTO.class)) {
            return Arrays.asList(
                new ColumnWithComlexTableNestingDTO("Header 1", "Header 2", Arrays.asList(
                    new ComplexNestedTableA(
                        Arrays.asList (
                           new ComplexNestedTableD("col A"), 
                           new ComplexNestedTableD("col B")), 
                        "col C", 
                        Arrays.asList(
                            new ComplexNestedTableB(
                                "Col D", 
                                Arrays.asList(
                                    new ComplexNestedTableC("Col F"),
                                    new ComplexNestedTableC("Col G")), 
                                "Col E"),
                            new ComplexNestedTableB(
                                "Col D", 
                                Arrays.asList(
                                    new ComplexNestedTableC("Col F"),
                                    new ComplexNestedTableC("Col G")), 
                                "Col E")), 
                       "col F"),
                   new ComplexNestedTableA(
                       Arrays.asList (
                          new ComplexNestedTableD("col A"), 
                          new ComplexNestedTableD("col B")), 
                       "col C", 
                       Arrays.asList(
                           new ComplexNestedTableB(
                               "Col D", 
                               Arrays.asList(
                                   new ComplexNestedTableC("Col F"),
                                   new ComplexNestedTableC("Col G")), 
                               "Col E"),
                           new ComplexNestedTableB(
                               "Col D", 
                               Arrays.asList(
                                   new ComplexNestedTableC("Col F"),
                                   new ComplexNestedTableC("Col G")), 
                               "Col E")), 
                      "col F"))),
              new ColumnWithComlexTableNestingDTO("Header 3", "Header 4", Arrays.asList(
                  new ComplexNestedTableA(
                      Arrays.asList (
                         new ComplexNestedTableD("col A"), 
                         new ComplexNestedTableD("col B")), 
                      "col C", 
                      Arrays.asList(
                          new ComplexNestedTableB(
                              "Col D", 
                              Arrays.asList(
                                  new ComplexNestedTableC("Col F"),
                                  new ComplexNestedTableC("Col G")), 
                              "Col E")), 
                     "col F"),
                 new ComplexNestedTableA(
                     Arrays.asList (
                        new ComplexNestedTableD("col A"), 
                        new ComplexNestedTableD("col B")), 
                     "col C", 
                     Arrays.asList(
                         new ComplexNestedTableB(
                             "Col D", 
                             Arrays.asList(
                                 new ComplexNestedTableC("Col F"),
                                 new ComplexNestedTableC("Col G")), 
                             "Col E")), 
                    "col F"))));
        }
        return null;
    }

    @Override
    public PdfTableElementWithStaticHeader getExpectedElement(Class<?> clazz) {
        return new PdfTableElementWithStaticHeader(
                getExpectedHeader(clazz),
                getExpectedRow(clazz));
    }
    
    public PdfTableHeader getExpectedHeader(Class<?> clazz) {
        if(clazz.equals(SimpleColumnsDTO.class)) {
            return new PdfTableHeader(4, Arrays.asList(
                    new TableHeaderColumn("col1"), 
                    new TableHeaderColumn("col2"),
                    new TableHeaderColumn("col3"),
                    new TableHeaderColumn("col4")));
        } 
        if(clazz.equals(NamedColumnsDTO.class)) {
            return new PdfTableHeader(3, Arrays.asList(
                    new TableHeaderColumn(2, 1, "column1"),
                    new TableHeaderColumn(1, 2, "group1"),
                    new TableHeaderColumn(1, 1, "column2"),
                    new TableHeaderColumn(1, 1, "column3")));
        }
        if(clazz.equals(ColumnGroupDTO.class)) {
            return new PdfTableHeader(4, Arrays.asList(
                    new TableHeaderColumn(3, 1, "col1"),
                    new TableHeaderColumn(1, 3, "g1"),
                    new TableHeaderColumn(2, 1, "col2"),
                    new TableHeaderColumn(1, 2, "g2"),
                    new TableHeaderColumn(1, 1, "col3"),
                    new TableHeaderColumn(1, 1, "col4")));
        }
        if(clazz.equals(ColumnGroup2DTO.class)) {
            return new PdfTableHeader(3, Arrays.asList(
                    new TableHeaderColumn(1, 1, "g1"),
                    new TableHeaderColumn(1, 2, "g2"),
                    new TableHeaderColumn(1, 1, "col1"),
                    new TableHeaderColumn(1, 1, "col2"),
                    new TableHeaderColumn(1, 1, "col3")));
        }
        if(clazz.equals(ColumnWithSimpleRowGroupDTO.class)) {
            return new PdfTableHeader(4, Arrays.asList(
                    new TableHeaderColumn("col3"), 
                    new TableHeaderColumn("col1"),
                    new TableHeaderColumn("col2"),
                    new TableHeaderColumn("col4")));
        }
        if(clazz.equals(ColumnWithSimpleTableGroupDTO.class)) {
            return new PdfTableHeader(2, Arrays.asList(
                    new TableHeaderColumn("col1"), 
                    new TableHeaderColumn("col2")));
        }
        if(clazz.equals(ColumnWithComlexTableNestingDTO.class)) {
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
        return null;
    }
    
    public PdfTableRow getExpectedRow(Class<?> clazz) {
        if(clazz.equals(SimpleColumnsDTO.class)) {
            return new PdfTableRow(
                    new StringCellRow("col1"), new StringCellRow("col2"),
                    new StringCellRow("col3"), new StringCellRow("col4"));
        } 
        if(clazz.equals(NamedColumnsDTO.class)) {
            return new PdfTableRow(
                    new StringCellRow("col1"), new StringCellRow("col2"),
                    new StringCellRow("col3"));
        }
        if(clazz.equals(ColumnGroupDTO.class)) {
            return new PdfTableRow(
                    new StringCellRow("col1"), new StringCellRow("col2"),
                    new StringCellRow("col3"), new StringCellRow("col4"));
        }
        if(clazz.equals(ColumnGroup2DTO.class)) {
            return new PdfTableRow(
                    new StringCellRow("col1"), new StringCellRow("col2"),
                    new StringCellRow("col3"));
        }
        if(clazz.equals(ColumnWithSimpleRowGroupDTO.class)) {
            List<CellRow> cellRows = new LinkedList<CellRow>();
            TableCellRow innerTable = new TableCellRow("table",
                    new PdfTableElementWithStaticHeader( new PdfTableRow(
                            new StringCellRow("col1"), new StringCellRow("col2"))));
            cellRows.add(new StringCellRow("col3"));
            cellRows.add(innerTable);
            cellRows.add(new StringCellRow("col4"));
            return new PdfTableRow(cellRows);
        }
        if(clazz.equals(ColumnWithSimpleTableGroupDTO.class)) {
            List<CellRow> innerCellRows = new LinkedList<CellRow>();
            innerCellRows.add(new StringCellRow("col1"));
            innerCellRows.add(new StringCellRow("col2"));
            SingleDataTable header = new SingleDataTable(1, Arrays.asList(
                    new DynamicTableHeaderColumn("header", "header")));
            CellRow innerTable = new PdfTableWithDynamicHeader("table", 
                    new PdfTableRow(innerCellRows), header);
            return new PdfTableRow(Arrays.asList(innerTable));
        }
        if(clazz.equals(ColumnWithComlexTableNestingDTO.class)) {
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
            return new PdfTableRow(Arrays.asList(innerTable1));
        }
        return null;
    }
    
    public Field getTableFieldForExample(Class<?> clazz) {  
        try {
        if(clazz.equals(SimpleColumnsDTO.class)) {
            return TableWrapperDTO1.class.getDeclaredField("table");
        } 
        if(clazz.equals(NamedColumnsDTO.class)) {
            return TableWrapperDTO2.class.getDeclaredField("table");
        }
        if(clazz.equals(ColumnGroupDTO.class)) {
            return TableWrapperDTO3.class.getDeclaredField("table");
        }
        if(clazz.equals(ColumnGroup2DTO.class)) {
            return TableWrapperDTO4.class.getDeclaredField("table");
        }
        if(clazz.equals(ColumnWithSimpleRowGroupDTO.class)) {
            return TableWrapperDTO5.class.getDeclaredField("table");
        }
        if(clazz.equals(ColumnWithSimpleTableGroupDTO.class)) {
            return TableWrapperDTO6.class.getDeclaredField("table");
        }
        if(clazz.equals(ColumnWithComlexTableNestingDTO.class)) {
            return TableWrapperDTO7.class.getDeclaredField("table");
        }
        } catch(NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    StyleResolver styleResolver = Mockito.mock(StyleResolver.class);
    
    public PdfTableHeader checkHeaderExamples(int i) {
        PdfTableHeaderParser headerParser = new PdfTableHeaderParser(styleResolver);
        Class<?> clazz = getExampleClass(i);
        PdfTableHeader header = headerParser.parse(clazz);
        Assert.assertEquals(header, getExpectedHeader(clazz));
        return header;
    }
    
    public PdfTableRow checkRowExamples(int i) {
        PdfTableRowParser rowParser = new PdfTableRowParser(styleResolver);
        Class<?> clazz = getExampleClass(i);
        PdfTableRow row = rowParser.parse(clazz);
        Assert.assertEquals(row, getExpectedRow(clazz));
        return row;
    }

    public PdfTableElementWithStaticHeader checkTableExamples(int i) {
        PdfTableParser tableParser = new PdfTableParser(styleResolver);
        Class<?> clazz = getExampleClass(i);
        Field field = getTableFieldForExample(clazz);
        PdfTableElementWithStaticHeader table = tableParser.parse(field);
        Assert.assertEquals(table, getExpectedElement(clazz));
        return table;
    }
    
    DefaultITextDocumentFactory factory = new DefaultITextDocumentFactory();
    
    public void generateTable(int i) throws DocumentException, GenDocumentException, FileNotFoundException {
        Class<?> clazz = getExampleClass(i);
        String pathName = "target/" + clazz.getSimpleName() + ".pdf";
        PdfElement element = checkTableExamples(i);
        Document document = factory.create(new FileOutputStream(new File(pathName)), new DocumentMetaDataImpl());
        document.add(element.print(getExampleDTO(clazz)));
        factory.close(document);
    }
    
    @Test
    public void testGenerate() throws GenDocumentException, FileNotFoundException, DocumentException {
        for(int i=0;i<7;i++) {
            generateTable(i);
        }
    }
}
