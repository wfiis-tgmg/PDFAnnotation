package pl.edu.agh.tgmg.itext.examples.tables;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;

public class SimpleRowGroupExample {
    public SimpleRowGroupExample() {}
    public SimpleRowGroupExample(String col1, String col2) {
        this.col1 = col1; this.col2 = col2;
    }
    @PdfColumn
    String col1 = "item S1";
    @PdfColumn
    String col2 = "item S2";
}