package pl.edu.agh.tgmg.api.annotations.styles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.elements.HorizontalAlignment;

/**
 * Specifies the styles for a table
 * @author Tom
 *
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TableStyle {
    /**
     * Set styles from an external class with style annotations.
     * @return
     */
    Class<?> styleClass() default Class.class;
    /**
     * Specify the horizontal alignment of the table
     * @return
     */
    HorizontalAlignment[] horizontalAlignment() default {};
    /**
     * Specify the spacing after the table.
     * @return
     */
    float[] spacingAfter() default {};
    
    /**
     * Specify the spacing before the table.
     * @return
     */
    float[] spacingBefore() default {};
    /**
     * Specify the width of the table.
     * @return
     */
    float[] widthPercentage() default {};
}