package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;
import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfTableGroup {
    CellRowStyle rowCellStyle() default @CellRowStyle;
    //CellHeaderStyle headerCellStyle() default @CellHeaderStyle;   //unused - not implemented
    TableStyle tableStyle() default @TableStyle;
}
