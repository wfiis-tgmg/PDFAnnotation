package pl.edu.agh.tgmg.itext.examples.errors.tables.header;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroup;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroups;
import pl.edu.agh.tgmg.api.exceptions.InvalidGroupException;

@PdfColumnGroups({
    @PdfColumnGroup(id="g1"),
    @PdfColumnGroup(id="g2", parent="g3"),
    @PdfColumnGroup(id="g3", parent="g2")})
class ColumnGroupErrorTest4 extends TableHeaderErrorTest {
    
    @PdfColumn
    String col1;

    @Override
    public Class<? extends Exception> getExpectedException() {
        return InvalidGroupException.class;
    }

    @Override
    public String getExpectedExceptionMessage() {
        return "cyclic dependency encountered!";
    }
}