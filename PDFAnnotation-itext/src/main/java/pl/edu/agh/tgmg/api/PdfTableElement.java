package pl.edu.agh.tgmg.api;

import pl.edu.agh.tgmg.api.exceptions.ReflectionException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

public interface PdfTableElement extends PdfElement {
    PdfPTable print(Object data) throws DocumentException, ReflectionException;
    
    @Override
    public boolean equals(Object o);
    
    @Override
    public int hashCode();
}
