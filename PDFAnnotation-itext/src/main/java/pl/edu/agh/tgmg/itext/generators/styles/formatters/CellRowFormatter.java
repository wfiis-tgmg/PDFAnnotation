package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import java.util.List;

import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;
import pl.edu.agh.tgmg.itext.generators.styles.elements.BoxValues;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;

public class CellRowFormatter extends CellFormatter implements StyleFormatter<PdfPCell, CellRowStyle> {
    
    public CellRowFormatter() {
        super(new BoxValues<Float>(0.5f), 
                new BoxValues<Float>(1f), 
                BaseColor.BLACK, 
                BaseColor.WHITE, 
                new BoxValues<Boolean>(Boolean.TRUE), 
                Element.ALIGN_CENTER, 
                Element.ALIGN_MIDDLE,  
                Font.DEFAULTSIZE, 
                BaseColor.BLACK, 
                Font.FontFamily.HELVETICA, 
                Font.NORMAL);
    }

    public CellRowFormatter(BoxValues<Float> padding,
            BoxValues<Float> borderWidth, BaseColor borderColor,
            BaseColor backgroundColor, BoxValues<Boolean> borders,
            int horizontalAlignment, int verticalAlignment, int fontSize,
            BaseColor fontColor, FontFamily fontFamily, int fontStyle) {
        super(padding, borderWidth, borderColor, backgroundColor, borders,
                horizontalAlignment, verticalAlignment, fontSize, fontColor,
                fontFamily, fontStyle);
    }
    
    @Override
    public void addStyle(PdfPCell p) {
        super.addStyle(p);
    }

    @Override
    public Class<CellRowStyle> getFormatterStyleClass() {
        return CellRowStyle.class;
    }
    
    @Override
    public List<String> getValueNames() {
        return super.getValueNames();
    }
}
