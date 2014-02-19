package pl.edu.agh.tgmg.itext.examples.errors.tables.header;

import org.testng.annotations.Test;
import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.exceptions.InvalidOrderException;


public class ColumnOrderErrorTest extends TableHeaderErrorTest {
    
    @PdfColumn
    String col1;
    @PdfColumn(order=5)
    String col2;
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