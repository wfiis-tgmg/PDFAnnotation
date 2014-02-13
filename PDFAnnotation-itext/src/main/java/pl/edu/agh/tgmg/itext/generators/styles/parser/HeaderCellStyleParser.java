package pl.edu.agh.tgmg.itext.generators.styles.parser;

import java.lang.reflect.Method;

import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.CellHeaderFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.pdf.PdfPCell;

@Deprecated
public class HeaderCellStyleParser implements StyleElementParser<PdfPCell, CellHeaderStyle> {

    @Override
    public void applyStyle(StyleFormatter<PdfPCell, CellHeaderStyle> formatter,
            CellHeaderStyle style) {
        for(String valueName : formatter.getValueNames()) {
            try {
                Method getter = style.getClass().getMethod(valueName);
            } catch (NoSuchMethodException | SecurityException e) {
                
            }
        }
        
        
    }

}
