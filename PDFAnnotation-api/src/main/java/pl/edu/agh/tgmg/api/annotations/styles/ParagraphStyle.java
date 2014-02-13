package pl.edu.agh.tgmg.api.annotations.styles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.elements.FontFamily;
import pl.edu.agh.tgmg.api.annotations.styles.elements.FontStyle;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfColor;
import pl.edu.agh.tgmg.api.annotations.styles.elements.TextAlignment;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ParagraphStyle {
    Class<?> styleClass() default Class.class;
    int[] fontSize() default {};
    FontStyle[] fontStyle() default {};
    FontFamily[] fontFamily() default {};
    PdfColor[] fontColor() default @PdfColor;
    float[] spacingAfter() default {};
    float[] spacingBefore() default {};
    float[] indentationLeft() default {};
    float[] indentationRight() default {};
    int[] extraSpace() default {};
    TextAlignment[] textAlignment() default {};
    
}