package pl.edu.agh.tgmg.itext.examples.errors.tables.header;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroup;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroups;
import pl.edu.agh.tgmg.api.exceptions.InvalidGroupException;

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g2", parent="invalidGroup")})
class ColumnGroupError1DTO extends TableHeaderErrorTest {
    
    @PdfColumn
    String col1;

    @Override
    public Class<? extends Exception> getExpectedException() {
        return InvalidGroupException.class;
    }

    @Override
    public String getExpectedExceptionMessage() {
        return "parent .* for group .* does not exist!";
    }
}