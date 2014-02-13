package pl.edu.agh.tgmg.itext.generators.styles;

import java.lang.annotation.Annotation;

import pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters.CreatesPdfElement;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.Element;


public interface StyleResolver {
    <E extends Element, S extends Annotation> void setDefaltStyle(
            Class<S> annotationType, StyleFormatter<E, S> formatter);
    <E extends Element, S extends Annotation> void applyStyle(CreatesPdfElement<E, S> element, 
            S styleAnnotation, Class<?> declaringClass);
}
