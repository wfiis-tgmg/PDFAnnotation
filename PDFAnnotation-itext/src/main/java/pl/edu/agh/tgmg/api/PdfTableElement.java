package pl.edu.agh.tgmg.api;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

public interface PdfTableElement extends PdfElement {
    PdfPTable print(Object data) throws DocumentException;
    
    @Override
    public boolean equals(Object o);
    
    @Override
    public int hashCode();
}
