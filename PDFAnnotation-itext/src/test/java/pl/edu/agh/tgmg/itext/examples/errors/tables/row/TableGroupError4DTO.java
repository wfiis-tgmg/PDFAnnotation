package pl.edu.agh.tgmg.itext.examples.errors.tables.row;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroupHeader;
import pl.edu.agh.tgmg.api.exceptions.InvalidTableGroupException;

class TableGroupError4DTO extends TableRowErrorTest {
    @PdfTableGroupHeader
    String header;
    @PdfColumn
    String col3;
    
    @Override
    public Class<? extends Exception> getExpectedException() {
        return InvalidTableGroupException.class;
    }
    @Override
    public String getExpectedExceptionMessage() {
        return "table group headers in class .* with no table group";
    }
}