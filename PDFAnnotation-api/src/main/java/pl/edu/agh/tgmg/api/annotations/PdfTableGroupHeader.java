package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfTableGroupHeader {
    String name() default "";
    CellHeaderStyle style() default @CellHeaderStyle;
}
