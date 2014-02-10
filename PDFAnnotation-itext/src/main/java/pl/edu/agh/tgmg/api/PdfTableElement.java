package pl.edu.agh.tgmg.api;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;

public interface PdfTableElement extends PdfElement {
    PdfPTable print(Object data) throws DocumentException;
}
