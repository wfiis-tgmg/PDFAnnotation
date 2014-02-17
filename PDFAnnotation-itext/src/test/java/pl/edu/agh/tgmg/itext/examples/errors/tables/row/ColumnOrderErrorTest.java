package pl.edu.agh.tgmg.itext.examples.errors.tables.row;

import java.util.List;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfRowGroup;
import pl.edu.agh.tgmg.api.exceptions.InvalidOrderException;


class ColumnOrderErrorTest extends TableRowErrorTest {
    
    @PdfColumn
    String col1;
    @PdfRowGroup(order=5)
    List<String> col2;
    @PdfColumn
    String col3;

    @Override
    public Class<? extends Exception> getExpectedException() {
        return InvalidOrderException.class;
    }

    @Override
    public String getExpectedExceptionMessage() {
        return "Column currenOrder is only set partially";
    }
}