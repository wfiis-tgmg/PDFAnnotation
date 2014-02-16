package pl.edu.agh.tgmg.itext.examples.errors.tables.row;

import java.util.List;

import pl.edu.agh.tgmg.api.annotations.PdfRowGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroupHeader;
import pl.edu.agh.tgmg.api.exceptions.InvalidTableGroupException;
import pl.edu.agh.tgmg.itext.examples.tables.SimpleRowGroupExample;

class TableGroupErrorTest1 extends TableRowErrorTest {
    @PdfTableGroupHeader
    String header;
    @PdfTableGroup
    List<SimpleRowGroupExample> table;
    @PdfRowGroup
    List<SimpleRowGroupExample> table2;
    
    @Override
    public Class<? extends Exception> getExpectedException() {
        return InvalidTableGroupException.class;
    }
    @Override
    public String getExpectedExceptionMessage() {
        return "table group mixed with other elements in class .*";
    }
}