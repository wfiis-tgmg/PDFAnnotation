package pl.edu.agh.tgmg.itext.examples.errors.tables.header;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroup;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroups;
import pl.edu.agh.tgmg.api.exceptions.InvalidGroupException;

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g2")})
class ColumnGroupError3DTO extends TableHeaderErrorTest {
    
    @PdfColumn
    String col1;
    @PdfColumn(group="g1")
    String col2;
    @PdfColumn(group="g2")
    String col3;
    @PdfColumn(group="g3")
    String col4;
    
    @Override
    public Class<? extends Exception> getExpectedException() {
        return InvalidGroupException.class;
    }
    
    @Override
    public String getExpectedExceptionMessage() {
        return ".* group not found";
    }
}