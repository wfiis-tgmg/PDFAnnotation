package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;
import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfColumn {
    int order() default -1;
    String group() default "";
    String name() default "";
    CellRowStyle rowStyle() default @CellRowStyle;
    CellHeaderStyle headerStyle() default @CellHeaderStyle;
}
