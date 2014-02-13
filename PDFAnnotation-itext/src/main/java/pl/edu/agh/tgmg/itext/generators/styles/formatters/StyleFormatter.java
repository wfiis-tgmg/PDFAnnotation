package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import java.lang.annotation.Annotation;

import com.itextpdf.text.Element;

public interface StyleFormatter <T extends Element, S extends Annotation>{
    void addStyle(T object);
    void setStyle(StyleFormatter<T, S> other);
    Class<S> getFormatterStyleClass();
}
