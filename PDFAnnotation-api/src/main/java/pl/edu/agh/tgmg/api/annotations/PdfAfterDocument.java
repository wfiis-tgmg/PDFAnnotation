package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Allows to put static elements at the end of the document.
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfAfterDocument {
    /**
     * Defines paragraphs that should be added at the end of the document
     */
    PdfParagraph[] paragraphs() default {};
    
    /**
     * Defines signatures that should be added at the end of the document
     */
    PdfSignature[] signatures() default {};
}
