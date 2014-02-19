package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A group of paragraphs. Allows to define multiple paragraphs at once.
 * @author Tom
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfParagraphs {
    
    /**
     * Specify the desired paragraphs.
     * @return
     */
    PdfParagraph[] value();
}
