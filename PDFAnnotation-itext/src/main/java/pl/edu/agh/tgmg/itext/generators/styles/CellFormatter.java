package pl.edu.agh.tgmg.itext.generators.styles;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public interface CellFormatter {
    void addStyles(PdfPCell p);
}
