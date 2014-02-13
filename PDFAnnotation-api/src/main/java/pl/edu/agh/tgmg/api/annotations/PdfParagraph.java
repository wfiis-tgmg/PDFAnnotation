package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfParagraph {
    String value();
    String[] messageFieldNames() default {};
    ParagraphStyle style() default @ParagraphStyle;
}
