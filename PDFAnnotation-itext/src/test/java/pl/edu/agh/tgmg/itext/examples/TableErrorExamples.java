package pl.edu.agh.tgmg.itext.examples;

import java.util.List;

import org.mockito.Mockito;
import org.testng.Assert;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroup;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroups;
import pl.edu.agh.tgmg.api.annotations.PdfRowGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroupHeader;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableHeaderParser;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.PdfTableRowParser;
import pl.edu.agh.tgmg.api.exceptions.InvalidGroupException;
import pl.edu.agh.tgmg.api.exceptions.InvalidTableGroupException;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;

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


public class TableErrorExamples {
    
    public static Class<?> getTableHeaderErrorExample(int i) {
        switch(i) {
        case 0:
            return ColumnGroupError1DTO.class;
        case 1:
            return ColumnGroupError2DTO.class;
        case 2:
            return ColumnGroupError3DTO.class;
        case 3:
            return ColumnGroupError4DTO.class;
        case 4:
        }
        return null;
    }
    
    public static Class<?> getTableRowErrorExample(int i) {
        switch(i) {
        case 0:
            return TableGroupError1DTO.class;
        case 1:
            return TableGroupError2DTO.class;
        case 2:
            return TableGroupError3DTO.class;
        case 3:
            return TableGroupError4DTO.class;
        }
        return null;
    }
    
    public static Class<? extends Exception> getExpectedException(Class<?> clazz) {
        if(clazz.equals(ColumnGroupError1DTO.class)) {
            return InvalidGroupException.class;
        } 
        if(clazz.equals(ColumnGroupError2DTO.class)) {
            return InvalidGroupException.class;
        }
        if(clazz.equals(ColumnGroupError3DTO.class)) {
            return InvalidGroupException.class;
        }
        if(clazz.equals(ColumnGroupError4DTO.class)) {
            return InvalidGroupException.class;
        }
        if(clazz.equals(TableGroupError1DTO.class)) {
            return InvalidTableGroupException.class;
        }
        if(clazz.equals(TableGroupError2DTO.class)) {
            return InvalidTableGroupException.class;
        }
        if(clazz.equals(TableGroupError3DTO.class)) {
            return InvalidTableGroupException.class;
        }
        if(clazz.equals(TableGroupError4DTO.class)) {
            return InvalidTableGroupException.class;
        }
        return null;
    }
    
    public static String getExpectedMessagePattern(Class<?> clazz) {
        if(clazz.equals(ColumnGroupError1DTO.class)) {
            return "parent .* for group .* does not exist!";
        } 
        if(clazz.equals(ColumnGroupError2DTO.class)) {
            return "group .* already exists!";
        }
        if(clazz.equals(ColumnGroupError3DTO.class)) {
            return ".* group not found";
        }
        if(clazz.equals(ColumnGroupError4DTO.class)) {
            return "cyclic dependency encountered!";
        }
        if(clazz.equals(TableGroupError1DTO.class)) {
            return "table group mixed with other elements in class .*";
        }
        if(clazz.equals(TableGroupError2DTO.class)) {
            return "table group mixed with other elements in class .*";
        }
        if(clazz.equals(TableGroupError3DTO.class)) {
            return "multiple table groups in class .*";
        }
        if(clazz.equals(TableGroupError4DTO.class)) {
            return "table group headers in class .* with no table group";
        }
        return null;
    }
    
    StyleResolver styleResolver = Mockito.mock(StyleResolver.class);
    
    public void checkRowErrors(int i) {
        PdfTableRowParser rowParser = new PdfTableRowParser(styleResolver);
        Class<?> clazz = getTableRowErrorExample(i);
        Class<? extends Exception> exception = getExpectedException(clazz);
        String message = getExpectedMessagePattern(clazz);
        try {
            rowParser.parse(clazz);
            Assert.fail("Expected exception: "  + exception);
        } catch(Exception e) {
            Assert.assertTrue(exception.isAssignableFrom(e.getClass()), 
                    "Expected exception: "  + exception + ", but is instead: " + e);
            Assert.assertTrue(e.getMessage().matches(message), 
                    "Expected message: " + message + ", but is instead: "  + e.getMessage());
        }
    }
    
    public void checkHeaderErrors(int i) {
        PdfTableHeaderParser headerParser = new PdfTableHeaderParser(styleResolver);
        Class<?> clazz = getTableHeaderErrorExample(i);
        Class<? extends Exception> exception = getExpectedException(clazz);
        String message = getExpectedMessagePattern(clazz);
        try {
            headerParser.parse(clazz);
            Assert.fail("Expected exception: "  + exception);
        } catch(Exception e) {
            Assert.assertTrue(exception.isAssignableFrom(e.getClass()), 
                    "Expected exception: "  + exception + ", but is instead: " + e);
            Assert.assertTrue(e.getMessage().matches(message), 
                    "Expected message: " + message + ", but is instead: "  + e.getMessage());
        }
    }
}
