package pl.edu.agh.tgmg.itext.examples.tables;

import java.util.Collections;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroup;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroups;
import pl.edu.agh.tgmg.api.annotations.PdfRowGroup;

@PdfColumnGroups({
    @PdfColumnGroup(id="g3", parent="g2")})
public class ComplexNestedTableB {
    public ComplexNestedTableB(int n) {
        table = Collections.nCopies(n, new ComplexNestedTableC());
    }
    @PdfColumn(group="g1")
    String col2 = "col B1";
    @PdfRowGroup
    List<ComplexNestedTableC> table;
    @PdfColumn(group="g3")
    String col4 = "col B2";
}