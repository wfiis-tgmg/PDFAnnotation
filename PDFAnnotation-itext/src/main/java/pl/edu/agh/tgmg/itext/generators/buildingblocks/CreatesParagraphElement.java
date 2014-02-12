package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import pl.edu.agh.tgmg.itext.generators.styles.StyleFormatter;

import com.itextpdf.text.Paragraph;

public interface CreatesParagraphElement {
    
    public void setParagraphFormatter(StyleFormatter<Paragraph> style);
}
