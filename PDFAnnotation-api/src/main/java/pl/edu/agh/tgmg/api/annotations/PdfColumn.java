package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.MessageResolver;
import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;
import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;

/**
 * Defines a column of a table. 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfColumn {
    /**
     * Specify order of the column in the table <br/>
     * <p>Note: if you want to specify order, all columns in the 
     * class must be given their order</p>
     */
    int order() default -1;
    
    /**
     * Specify the id of the group that the column is supposed to belong to.<br/>
     * <p>Note: it has to be an existing group. Groups are defined by {@link PdfColumnGroup}</p>
     */
    String group() default "";
    
    /**
     * Specify the name of the column header. It uses {@link MessageResolver}.
     */
    String name() default "";
    
    /**
     * Specify the style of the rows of this column. See {@link CellRowStyle}.
     */
    CellRowStyle rowStyle() default @CellRowStyle;
    
    /**
     * Specify the style of the header of this column. See {@link CellHeaderStyle}.
     */
    CellHeaderStyle headerStyle() default @CellHeaderStyle;
}
