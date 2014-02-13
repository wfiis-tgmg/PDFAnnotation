package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import java.lang.annotation.Annotation;
import java.util.List;

import com.itextpdf.text.Element;

public interface StyleFormatter <T extends Element, S extends Annotation> {
    void addStyle(T object);
    Class<S> getFormatterStyleClass();
    List<String> getValueNames();
    
    @Override
    public boolean equals(Object obj);
    @Override
    public int hashCode();
    @Override
    public String toString();
    
}
