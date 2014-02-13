package pl.edu.agh.tgmg.itext.generators.styles.parser;

import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.Paragraph;

@Deprecated
public class ParagraphStyleParser implements StyleElementParser<Paragraph, ParagraphStyle>  {

    public void applyStyle(StyleFormatter<Paragraph, ParagraphStyle> formatter, ParagraphStyle style) {
        
    }
}
