package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;

/**
 * Defines the row group for a table. It has to be at field that is either
 * Iterable or an array.
 * @author Tom
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfRowGroup {
    
    /**
     * Specify the style of this row group. See {@link CellRowStyle}.
     */
    CellRowStyle rowCellStyle() default @CellRowStyle;
    
    /**
     * Specify order of this row group in the table <br/>
     * <p>Note: if you want to specify order, all columns (and row groups) in the 
     * class must be given their order</p>
     */
    int order() default -1;
}
