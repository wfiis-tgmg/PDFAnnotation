package pl.edu.agh.tgmg.itext.generators.styles.parser;

import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.pdf.PdfPCell;

@Deprecated
public class RowCellStyleParser implements StyleElementParser<PdfPCell, CellRowStyle>{

    @Override
    public void applyStyle(StyleFormatter<PdfPCell, CellRowStyle> formatter,
            CellRowStyle style) {
        // TODO Auto-generated method stub
        
    }

}
