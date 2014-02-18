package pl.edu.agh.tgmg.api.annotations.styles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.elements.FontFamily;
import pl.edu.agh.tgmg.api.annotations.styles.elements.FontStyle;
import pl.edu.agh.tgmg.api.annotations.styles.elements.HorizontalAlignment;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfBoxValuesB;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfBoxValuesF;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfColor;
import pl.edu.agh.tgmg.api.annotations.styles.elements.VerticalAlignment;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CellHeaderStyle {
    Class<?> styleClass() default Class.class;
    PdfBoxValuesB[] border() default {};
    PdfBoxValuesF[] borderWidth() default {};
    PdfBoxValuesF[] padding() default {};
    int[] fontSize() default {};
    FontStyle[] fontStyle() default {};
    FontFamily[] fontFamily() default {};
    PdfColor[] fontColor() default {};
    PdfColor[] borderColor() default {};
    PdfColor[] backgroundColor() default {};
    HorizontalAlignment[] horizontalAlignment() default {};
    VerticalAlignment[] verticalAlignment() default {};
}
