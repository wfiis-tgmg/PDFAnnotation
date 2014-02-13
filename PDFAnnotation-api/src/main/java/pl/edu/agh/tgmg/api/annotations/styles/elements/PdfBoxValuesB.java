package pl.edu.agh.tgmg.api.annotations.styles.elements;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfBoxValuesB {
    BooleanEnum forAll() default BooleanEnum.NONE;
    BooleanEnum forBottom() default BooleanEnum.NONE;
    BooleanEnum forTop() default BooleanEnum.NONE;
    BooleanEnum forLeft() default BooleanEnum.NONE;
    BooleanEnum forRight() default BooleanEnum.NONE;
}
