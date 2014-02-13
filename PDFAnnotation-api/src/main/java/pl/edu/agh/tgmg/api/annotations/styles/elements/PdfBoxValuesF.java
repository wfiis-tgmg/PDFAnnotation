package pl.edu.agh.tgmg.api.annotations.styles.elements;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfBoxValuesF {
    float forAll() default -1;
    float forBottom() default -1;
    float forTop() default -1;
    float forLeft() default -1;
    float forRight() default -1;
}
