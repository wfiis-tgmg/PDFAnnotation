package pl.edu.agh.tgmg.itext.generators.buildingblocks.small;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.draw.LineSeparator;
import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.exceptions.ReflectionException;

public class Line implements PdfElement{

    @Override
    public Element print(Object data) throws DocumentException, ReflectionException {
        return new LineSeparator(1,100, BaseColor.BLACK, Element.ALIGN_CENTER, -2);
    }
}
