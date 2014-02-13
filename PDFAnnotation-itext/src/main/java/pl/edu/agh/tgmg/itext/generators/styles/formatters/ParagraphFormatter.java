package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

public class ParagraphFormatter implements StyleFormatter<Paragraph, ParagraphStyle> {

    private  int fontSize = Font.DEFAULTSIZE;
    private  BaseColor fontColor = BaseColor.BLACK;
    private  Font.FontFamily fontFamily = Font.FontFamily.HELVETICA;
    private  int extraSpace = 0;
    private  float spacingAfter = 0.5f;
    private  float spacingBefore = 0.5f;
    private  int textAlignment = Element.ALIGN_CENTER;
    private  int fontStyle = Font.NORMAL;
    private  float indentationRight = 0;
    private  float indentationLeft = 0;

    public ParagraphFormatter() {
    }

    @Override
    public void addStyle(Paragraph p) {
        p.getFont().setSize(fontSize);
        p.getFont().setColor(fontColor);
        p.getFont().setFamily(fontFamily.name());
        p.getFont().setStyle(fontStyle);
        p.setIndentationLeft(indentationLeft);
        p.setIndentationRight(indentationRight);

        p.setExtraParagraphSpace(extraSpace);
        p.setSpacingAfter(spacingAfter);
        p.setSpacingBefore(spacingBefore);
        p.setAlignment(textAlignment);
    }

    @Override
    public Class<ParagraphStyle> getFormatterStyleClass() {
        return ParagraphStyle.class;
    }

    @Override
    public List<String> getValueNames() {
       return Arrays.asList(
           "fontSize",
            "fontColor",
            "fontFamily",
            "extraSpace",
            "spacingAfter",
            "spacingBefore",
            "textAlignment",
            "fontStyle",
            "indentationRight",
            "indentationLeft");
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontColor(BaseColor fontColor) {
        this.fontColor = fontColor;
    }

    public void setFontFamily(Font.FontFamily fontFamily) {
        this.fontFamily = fontFamily;
    }

    public void setExtraSpace(int extraSpace) {
        this.extraSpace = extraSpace;
    }

    public void setSpacingAfter(float spacingAfter) {
        this.spacingAfter = spacingAfter;
    }

    public void setSpacingBefore(float spacingBefore) {
        this.spacingBefore = spacingBefore;
    }

    public void setTextAlignment(int textAlignment) {
        this.textAlignment = textAlignment;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public void setIndentationRight(float indentationRight) {
        this.indentationRight = indentationRight;
    }

    public void setIndentationLeft(float indentationLeft) {
        this.indentationLeft = indentationLeft;
    }
    
    

}
