package pl.edu.agh.tgmg.itext.generators.styles;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;

public interface ParagraphFormatter {
    void addStyles(Paragraph p);
}
