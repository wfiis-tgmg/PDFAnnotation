package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfFlowTextCell {
    String name() default "";
    int rowSpan() default 1;
    boolean startNewTable() default false;
    boolean placeInTheSameLine() default false;
}
