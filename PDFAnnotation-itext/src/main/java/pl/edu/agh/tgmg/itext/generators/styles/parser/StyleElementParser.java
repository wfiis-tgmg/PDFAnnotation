package pl.edu.agh.tgmg.itext.generators.styles.parser;

import java.lang.annotation.Annotation;

import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.Element;

public interface StyleElementParser <E extends Element, S extends Annotation> {
    
    public void applyStyle(StyleFormatter<E, S> formatter, S style);
}
