package pl.edu.agh.tgmg.api;

import pl.edu.agh.tgmg.api.exceptions.ReflectionException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;

public interface PdfElement  {
    Element print(Object data) throws DocumentException, ReflectionException ;
}
