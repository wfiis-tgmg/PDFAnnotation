package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;

public class ParagraphFormatter implements StyleFormatter<Paragraph, ParagraphStyle> {

    private  int fontSize = Font.DEFAULTSIZE;
    public  BaseColor fontColor = BaseColor.BLACK;
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
    
    public ParagraphFormatter(int fontSize, BaseColor fontColor,
            FontFamily fontFamily, int extraSpace, float spacingAfter,
            float spacingBefore, int textAlignment, int fontStyle,
            float indentationRight, float indentationLeft) {
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.fontFamily = fontFamily;
        this.extraSpace = extraSpace;
        this.spacingAfter = spacingAfter;
        this.spacingBefore = spacingBefore;
        this.textAlignment = textAlignment;
        this.fontStyle = fontStyle;
        this.indentationRight = indentationRight;
        this.indentationLeft = indentationLeft;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + extraSpace;
        result = prime * result
                + ((fontColor == null) ? 0 : fontColor.hashCode());
        result = prime * result
                + ((fontFamily == null) ? 0 : fontFamily.hashCode());
        result = prime * result + fontSize;
        result = prime * result + fontStyle;
        result = prime * result + Float.floatToIntBits(indentationLeft);
        result = prime * result + Float.floatToIntBits(indentationRight);
        result = prime * result + Float.floatToIntBits(spacingAfter);
        result = prime * result + Float.floatToIntBits(spacingBefore);
        result = prime * result + textAlignment;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParagraphFormatter other = (ParagraphFormatter) obj;
        if (extraSpace != other.extraSpace)
            return false;
        if (fontColor == null) {
            if (other.fontColor != null)
                return false;
        } else if (!fontColor.equals(other.fontColor))
            return false;
        if (fontFamily != other.fontFamily)
            return false;
        if (fontSize != other.fontSize)
            return false;
        if (fontStyle != other.fontStyle)
            return false;
        if (Float.floatToIntBits(indentationLeft) != Float
                .floatToIntBits(other.indentationLeft))
            return false;
        if (Float.floatToIntBits(indentationRight) != Float
                .floatToIntBits(other.indentationRight))
            return false;
        if (Float.floatToIntBits(spacingAfter) != Float
                .floatToIntBits(other.spacingAfter))
            return false;
        if (Float.floatToIntBits(spacingBefore) != Float
                .floatToIntBits(other.spacingBefore))
            return false;
        if (textAlignment != other.textAlignment)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ParagraphFormatter [fontSize=" + fontSize + ", fontColor="
                + fontColor + ", fontFamily=" + fontFamily + ", extraSpace="
                + extraSpace + ", spacingAfter=" + spacingAfter
                + ", spacingBefore=" + spacingBefore + ", textAlignment="
                + textAlignment + ", fontStyle=" + fontStyle
                + ", indentationRight=" + indentationRight
                + ", indentationLeft=" + indentationLeft + "]";
    }

    public int getFontSize() {
        return fontSize;
    }

    public BaseColor getFontColor() {
        return fontColor;
    }

    public Font.FontFamily getFontFamily() {
        return fontFamily;
    }

    public int getExtraSpace() {
        return extraSpace;
    }

    public float getSpacingAfter() {
        return spacingAfter;
    }

    public float getSpacingBefore() {
        return spacingBefore;
    }

    public int getTextAlignment() {
        return textAlignment;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public float getIndentationRight() {
        return indentationRight;
    }

    public float getIndentationLeft() {
        return indentationLeft;
    }
    
    

}
