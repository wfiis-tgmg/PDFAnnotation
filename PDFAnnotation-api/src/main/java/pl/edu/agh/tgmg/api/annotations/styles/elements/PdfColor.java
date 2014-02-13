package pl.edu.agh.tgmg.api.annotations.styles.elements;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfColor {
    ColorName colorName() default ColorName.BLACK;
    int R() default -1;
    int G() default -1;
    int B() default -1;
}
