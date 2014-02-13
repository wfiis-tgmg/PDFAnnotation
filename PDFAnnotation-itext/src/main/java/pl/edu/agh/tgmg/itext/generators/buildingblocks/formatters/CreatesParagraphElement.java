package pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters;

import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.Paragraph;

public interface CreatesParagraphElement extends CreatesPdfElement<Paragraph, ParagraphStyle> {
    public StyleFormatter<Paragraph, ParagraphStyle> getFormatter();
}
