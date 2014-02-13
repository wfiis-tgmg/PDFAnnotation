package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.elements.PageSize;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfBoxValuesF;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfDocument {

    String title() default "";
    String subject() default "";
    boolean createDate() default true;
    String author() default "";
    PdfBoxValuesF margins() default @PdfBoxValuesF(forAll=20);
    PageSize pageSize() default PageSize.A4;
}