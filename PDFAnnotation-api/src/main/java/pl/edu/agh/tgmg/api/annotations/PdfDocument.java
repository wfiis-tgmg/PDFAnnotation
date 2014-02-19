package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.MessageResolver;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PageSize;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfBoxValuesF;

/**
 * Represents the pdf document template. It allows to set document metadata.
 * @author Tom
 *
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfDocument {

    /**
     * Specify the document's title. It uses {@link MessageResolver}.
     * @return
     */
    String title() default "";
    
    /**
     * Specify the document's subject. It uses {@link MessageResolver}.
     * @return
     */
    String subject() default "";
    
    /**
     * Specify if the document creation date shuold be added.
     * @return
     */
    boolean createDate() default true;
    
    /**
     * Specify the document's author. It uses {@link MessageResolver}.
     * @return
     */
    String author() default "";
    
    /**
     * Specify the document margins. 
     */
    PdfBoxValuesF margins() default @PdfBoxValuesF(forAll=20);
    
    /**
     * Specify the document size. See {@link PageSize}.
     * @return
     */
    PageSize pageSize() default PageSize.A4;
}