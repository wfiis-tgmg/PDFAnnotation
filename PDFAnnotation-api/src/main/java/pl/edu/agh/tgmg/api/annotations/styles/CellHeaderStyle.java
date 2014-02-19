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

/**
 * Specifies styles for headers of a table.
 * @author Tom
 *
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CellHeaderStyle {
    /**
     * Set styles from an external class with style annotations.
     * @return
     */
    Class<?> styleClass() default Class.class;
    
    /**
     * Specify which (if any) borders should be used
     * @return
     */
    PdfBoxValuesB[] border() default {};
    
    /**
     * Specify the border width in all directions
     * @return
     */
    PdfBoxValuesF[] borderWidth() default {};
    
    /**
     * Specify the cell padding in all directions
     * @return
     */
    PdfBoxValuesF[] padding() default {};
    
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
     * Specify the color of the borders.
     * @return
     */
    PdfColor[] borderColor() default {};
    
    /**
     * Specify the color of the background.
     * @return
     */
    PdfColor[] backgroundColor() default {};
    
    /**
     * Specify the horizontal alignment of the cell
     * @return
     */
    HorizontalAlignment[] horizontalAlignment() default {};
    
    /**
     * Specify the vertical alignment of the cell
     * @return
     */
    VerticalAlignment[] verticalAlignment() default {};
}
