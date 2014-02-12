package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.pdf.PdfPCell;

public interface CreatesCellElement {
    
    public StyleFormatter<PdfPCell> getCellFormatter();
}
