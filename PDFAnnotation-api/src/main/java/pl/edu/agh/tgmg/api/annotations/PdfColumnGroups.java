package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allows to specify multiple column groups at once.
 * @author Tom
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfColumnGroups {
    /**
     * Specify the desired {@link PdfColumnGroup column groups}.
     * @return
     */
	PdfColumnGroup[] value();
}
