package pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters;

import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.pdf.PdfPCell;

public interface CreatesHeaderCellElement extends CreatesPdfElement<PdfPCell, CellHeaderStyle> {
    public StyleFormatter<PdfPCell, CellHeaderStyle> getFormatter();
}
