package pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters;

import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.pdf.PdfPTable;

public interface CreatesTableElement extends CreatesPdfElement<PdfPTable, TableStyle> {
    public StyleFormatter<PdfPTable, TableStyle> getFormatter();
}

