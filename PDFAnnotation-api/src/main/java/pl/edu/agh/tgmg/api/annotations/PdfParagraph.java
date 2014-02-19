package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.MessageResolver;
import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;

/**
 * Defines a single paragraph.
 * @author Tom
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfParagraph {
    
    /**
     * Text of the paragraf [REQUIRED]. It uses {@link MessageResolver}.
     * @return
     */
    String value();
    
    /**
     * Specify names of the fields used to fill placeholders in paragraph's text.
     * @return
     */
    String[] messageFieldNames() default {};
    
    /**
     * Specify the style of this paragraph. See {@link ParagraphStyle}.
     */
    ParagraphStyle style() default @ParagraphStyle;
}
