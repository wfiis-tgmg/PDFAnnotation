package pl.edu.agh.tgmg.itext.generators.styles;

import java.lang.annotation.Annotation;

import pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters.CreatesPdfElement;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.itextpdf.text.Element;

/**
 * Class that manages, applies and resolves styles during the annotation parsing process.
 * @author Tom
 *
 */
public interface StyleResolver {
    /**
     * Sets custom default style for a given style (formatter)
     */
    <E extends Element, S extends Annotation> void setDefaltStyle(
            Class<S> annotationType, StyleFormatter<E, S> formatter);
    
    /**
     * Apply a given style (formatter) to a element. It takes in to account 
     * the given values, the default values, the values of the declaring class and the
     * values of the root class (document class).
     */
    <E extends Element, S extends Annotation> void applyStyle(CreatesPdfElement<E, S> element, 
            S styleAnnotation, Class<?> declaringClass);
    
    /**
     * Sets a root class that should be used when applying styles.
     * @param root
     */
    void setRootClass(Class<?> root);
}
