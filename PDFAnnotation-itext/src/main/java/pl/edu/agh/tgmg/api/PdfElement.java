package pl.edu.agh.tgmg.api;

import pl.edu.agh.tgmg.api.exceptions.ReflectionException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

public interface PdfElement  {
    Element print(Object data) throws DocumentException, ReflectionException ;
}
