package pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters;

import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.pdf.PdfPCell;

public interface CreatesRowCellElement extends CreatesPdfElement<PdfPCell, CellRowStyle> {
    public StyleFormatter<PdfPCell, CellRowStyle> getFormatter();

}
