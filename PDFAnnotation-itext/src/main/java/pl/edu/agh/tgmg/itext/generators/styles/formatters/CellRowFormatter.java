package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;

import com.itextpdf.text.pdf.PdfPCell;

public class CellRowFormatter extends CellFormatter implements StyleFormatter<PdfPCell, CellRowStyle> {

    @Override
    public void setStyle(StyleFormatter<PdfPCell, CellRowStyle> other) {
        super.setStyle((CellFormatter) other);
    }

    @Override
    public Class<CellRowStyle> getFormatterStyleClass() {
        return CellRowStyle.class;
    }

}
