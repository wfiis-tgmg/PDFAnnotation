package pl.edu.agh.tgmg.api.annotations.styles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfBoxValues {
    int forAll() default -1;
    int forBottom() default -1;
    int forTop() default -1;
    int forLeft() default -1;
    int forRight() default -1;
}
