package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import pl.edu.agh.tgmg.itext.generators.styles.StyleFormatter;

import com.itextpdf.text.pdf.PdfPCell;

public interface CreatesCellElement {
    
    public void setCellFormatter(StyleFormatter<PdfPCell> style);
}
