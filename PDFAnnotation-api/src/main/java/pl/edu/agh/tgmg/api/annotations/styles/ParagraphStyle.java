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

/**
 * Specifies the styles for a paragraph.
 * @author Tom
 *
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ParagraphStyle {
    
    /**
     * Set styles from an external class with style annotations.
     * @return
     */
    Class<?> styleClass() default Class.class;
    /**
     * Specify the size of the font.
     * @return
     */
    int[] fontSize() default {};
    
    /**
     * Specify the style of the font. See {@link FontStyle}.
     * @return
     */
    FontStyle[] fontStyle() default {};
    
    /**
     * Specify the font family.
     * @return
     */
    FontFamily[] fontFamily() default {};
    
    /** 
     * Specify the font color.
     * @return
     */
    PdfColor[] fontColor() default {};
    
    /**
     * Specify the spacing after the paragraph.
     * @return
     */
    float[] spacingAfter() default {};
    
    /**
     * Specify the spacing before the paragraph.
     * @return
     */
    float[] spacingBefore() default {};
    
    /**
     * Specify the left indentation of the paragraph.
     * @return
     */
    float[] indentationLeft() default {};
    /**
     * Specify the right indentation of the paragraph.
     * @return
     */
    float[] indentationRight() default {};
    
    /**
     * Specify some extra space to the paragraph.
     * @return
     */
    int[] extraSpace() default {};
    
    /**
     * Specify the text horizontal alignment.
     */
    TextAlignment[] textAlignment() default {};
    
}