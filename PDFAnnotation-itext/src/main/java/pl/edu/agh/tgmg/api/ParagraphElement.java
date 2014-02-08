package pl.edu.agh.tgmg.api;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

public class ParagraphElement implements PdfElement {

    @Override
    public void print(PdfDocument structure, Document document) throws DocumentException {
        document.add(new Paragraph("Test"));
    }
}
