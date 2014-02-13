package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;

import com.itextpdf.text.pdf.PdfPCell;

public class CellHeaderFormatter extends CellFormatter implements StyleFormatter<PdfPCell, CellHeaderStyle> {

    @Override
    public void setStyle(StyleFormatter<PdfPCell, CellHeaderStyle> other) {
        super.setStyle((CellFormatter) other);
    }

    @Override
    public Class<CellHeaderStyle> getFormatterStyleClass() {
        return CellHeaderStyle.class;
    }

}
