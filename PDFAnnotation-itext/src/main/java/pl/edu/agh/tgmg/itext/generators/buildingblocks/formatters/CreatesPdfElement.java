package pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters;

import java.lang.annotation.Annotation;

import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.Element;

public interface CreatesPdfElement <E extends Element, S extends Annotation>{
    public StyleFormatter<E, S> getFormatter();
    public void setFormatter(StyleFormatter<E, S> formatter);
}
