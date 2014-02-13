package pl.edu.agh.tgmg.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;
import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfTable {
    //CellHeaderStyle rowCellStyle() default @CellHeaderStyle;      //unused - not implemented
    //CellHeaderStyle headerCellStyle() default @CellHeaderStyle;   //unused - not implemented
    TableStyle tableStyle() default @TableStyle;
}
