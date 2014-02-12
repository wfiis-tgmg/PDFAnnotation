package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.Paragraph;

public interface CreatesParagraphElement {
    
    public StyleFormatter<Paragraph> getParagraphFormatter();
}
