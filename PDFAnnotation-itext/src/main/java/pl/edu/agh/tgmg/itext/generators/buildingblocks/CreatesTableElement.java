package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import pl.edu.agh.tgmg.itext.generators.styles.StyleFormatter;

import com.itextpdf.text.pdf.PdfPTable;

public interface CreatesTableElement {
    
    public void setTableFormatter(StyleFormatter<PdfPTable> style);
}

