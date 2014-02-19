package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.MessageResolver;

/**
 * Defines a signature element. Makes a predefined structure designed to be a place
 * for a signature.
 * @author Tom
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfSignature {
    
    /**
     * Specify the signature's title. It uses {@link MessageResolver}.
     * @return
     */
    String title() default "";
    
    /**
     * Specify the signature's description. It uses {@link MessageResolver}.
     * @return
     */
    String description() default "( signature )";
    
    /**
     * Hardcode the signature. It uses {@link MessageResolver}.
     * @return
     */
    String staticSignature() default "";
    
    /**
     * Specify a field that should hold the signature.
     * @return
     */
    String dataFieldName() default "";
}
