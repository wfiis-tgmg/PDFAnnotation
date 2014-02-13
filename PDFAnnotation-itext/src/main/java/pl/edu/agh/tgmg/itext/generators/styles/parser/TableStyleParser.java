package pl.edu.agh.tgmg.itext.generators.styles.parser;

import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.pdf.PdfPTable;

@Deprecated
public class TableStyleParser implements StyleElementParser<PdfPTable, TableStyle>  {
    
    public void applyStyle(StyleFormatter<PdfPTable, TableStyle> formatter, TableStyle style) {
        
    }

}
