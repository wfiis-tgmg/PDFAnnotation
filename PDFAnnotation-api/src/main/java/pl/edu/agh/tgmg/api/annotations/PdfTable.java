package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;
import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;

/**
 * Defines a table in the document. The field has to either a Iterable, or
 * an array. Can only be present in PdfDocument class.
 * @author Tom
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfTable {
    
    /**
     * Specify the style of the table. See {@link TableStyle}.
     */
    TableStyle tableStyle() default @TableStyle;
}
