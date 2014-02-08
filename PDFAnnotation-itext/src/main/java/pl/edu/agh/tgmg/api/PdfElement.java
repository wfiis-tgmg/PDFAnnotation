package pl.edu.agh.tgmg.api;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;

public interface PdfElement  {
    void print(PdfDocument structure, Document document) throws DocumentException;
}
