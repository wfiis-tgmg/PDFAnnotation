package pl.edu.agh.tgmg.itext.examples.errors.tables.row;

import java.util.List;

import pl.edu.agh.tgmg.api.annotations.PdfTableGroup;
import pl.edu.agh.tgmg.api.exceptions.InvalidTableGroupException;
import pl.edu.agh.tgmg.itext.examples.tables.SimpleRowGroupExample;

class TableGroupErrorTest3 extends TableRowErrorTest {
    @PdfTableGroup
    List<SimpleRowGroupExample> table1;
    @PdfTableGroup
    List<SimpleRowGroupExample> table2;
    
    @Override
    public Class<? extends Exception> getExpectedException() {
        return InvalidTableGroupException.class;
    }
    @Override
    public String getExpectedExceptionMessage() {
        return "multiple table groups in class .*";
    }
}