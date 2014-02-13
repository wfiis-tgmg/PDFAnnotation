package pl.edu.agh.tgmg.api.annotations.styles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.elements.HorizontalAlignment;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TableStyle {
    Class<?> styleClass() default Class.class;
    HorizontalAlignment[] horizontalAlignment() default {};
    float[] spacingAfter() default {};
    float[] spacingBefore() default {};
    float[] widthPercentage() default {};
}