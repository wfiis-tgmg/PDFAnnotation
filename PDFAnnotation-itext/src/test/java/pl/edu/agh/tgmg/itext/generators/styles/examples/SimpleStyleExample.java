package pl.edu.agh.tgmg.itext.generators.styles.examples;

import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.api.annotations.styles.CellHeaderStyle;
import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;
import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;
import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;
import pl.edu.agh.tgmg.api.annotations.styles.elements.BooleanEnum;
import pl.edu.agh.tgmg.api.annotations.styles.elements.ColorName;
import pl.edu.agh.tgmg.api.annotations.styles.elements.FontFamily;
import pl.edu.agh.tgmg.api.annotations.styles.elements.HorizontalAlignment;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfBoxValuesB;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfColor;
import pl.edu.agh.tgmg.api.annotations.styles.elements.TextAlignment;
import pl.edu.agh.tgmg.api.annotations.styles.elements.VerticalAlignment;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;

@ParagraphStyle(spacingAfter=12f,indentationLeft=23f, fontFamily=FontFamily.TIMES_ROMAN, styleClass=StyleExample2.class)
@CellRowStyle(border=@PdfBoxValuesB(forLeft=BooleanEnum.FALSE, forRight=BooleanEnum.TRUE), fontSize=27, styleClass=Double.class)
@CellHeaderStyle(fontColor=@PdfColor(colorName=ColorName.RED), verticalAlignment=VerticalAlignment.ALIGN_TOP)
@TableStyle(horizontalAlignment=HorizontalAlignment.RIGHT, spacingAfter=15f, widthPercentage=20f, styleClass=StyleResolver.class)
@PdfDocument
public class SimpleStyleExample {

    @PdfParagraph(value = "", style=@ParagraphStyle( styleClass=StyleExample3.class,
            indentationLeft=13f, textAlignment=TextAlignment.ALIGN_JUSTIFIED))
    public String str;
    
}
