package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.MessageResolver;
import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;

/**
 * Defines a column group.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfColumnGroup {
    /**
     * Specify the id of the group [REQUIRED]. It has to be unique within 
     * the given class. 
     */
	String id();
	
    /**
     * Specify the name of the group header. It uses {@link MessageResolver}.
     */
	String name() default "";
	
    /**
     * Specify the id of a parent group. 
     */
	String parent() default "";
	
    /**
     * Specify the style of the header of this group. See {@link CellHeaderStyle}.
     */
	CellHeaderStyle style() default @CellHeaderStyle;
}
