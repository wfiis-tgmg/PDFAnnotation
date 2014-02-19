package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.MessageResolver;
import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;

/**
 * Defines a header for the nested tables group.
 * @author Tom
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfTableGroupHeader {
    /**
     * Specify the name of the header. It uses {@link MessageResolver}.
     */
    String name() default "";
    
    /**
     * Specify the style of this column. See {@link CellHeaderStyle}.
     */
    CellHeaderStyle style() default @CellHeaderStyle;
}