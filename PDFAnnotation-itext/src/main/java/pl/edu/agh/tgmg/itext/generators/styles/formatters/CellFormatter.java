package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.tgmg.itext.generators.styles.elements.BoxValues;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;

public class CellFormatter {

    private  BoxValues<Float> padding;
    private  BoxValues<Float> borderWidth;
    private  BaseColor borderColor;
    private  BaseColor backgroundColor;
    private  BoxValues<Boolean> border;
    private int horizontalAlignment;
    private int verticalAlignment;
    
    private  int fontSize;
    private  BaseColor fontColor;
    private  Font.FontFamily fontFamily;
    private  int fontStyle;
    
    protected CellFormatter(BoxValues<Float> padding,
            BoxValues<Float> borderWidth, BaseColor borderColor,
            BaseColor backgroundColor, BoxValues<Boolean> border,
            int horizontalAlignment, int verticalAlignment, int fontSize,
            BaseColor fontColor, FontFamily fontFamily, int fontStyle) {
        this.padding = padding;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
        this.border = border;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.fontFamily = fontFamily;
        this.fontStyle = fontStyle;
    }
    
    public void addStyle(PdfPCell p) {
        p.setPaddingBottom(padding.getForBottom());
        p.setPaddingTop(padding.getForTop());
        p.setPaddingLeft(padding.getForLeft());
        p.setPaddingRight(padding.getForRight());

        p.setBorderColor(borderColor);
        p.setBackgroundColor(backgroundColor);

        p.setBorderWidthBottom(borderWidth.getForBottom());
        p.setBorderWidthTop(borderWidth.getForTop());
        p.setBorderWidthLeft(borderWidth.getForLeft());
        p.setBorderWidthRight(borderWidth.getForRight());
        
        Phrase phrase = p.getPhrase();
        if(phrase != null) {
            phrase.getFont().setSize(fontSize);
            phrase.getFont().setColor(fontColor);
            phrase.getFont().setFamily(fontFamily.name());
            phrase.getFont().setStyle(fontStyle);
        }
        
        int brd = Rectangle.NO_BORDER;
        if(border.getForLeft()) brd |= Rectangle.LEFT;
        if(border.getForRight()) brd |= Rectangle.RIGHT;
        if(border.getForTop()) brd |= Rectangle.TOP;
        if(border.getForBottom()) brd |= Rectangle.BOTTOM;
        p.setBorder(brd);

        p.setVerticalAlignment(verticalAlignment);
        p.setHorizontalAlignment(horizontalAlignment);
    }

    public void setPadding(BoxValues<Float> padding) {
        this.padding.setValue(padding);
    }

    public void setBorderWidth(BoxValues<Float> borderWidth) {
        this.borderWidth.setValue(borderWidth);
    }

    public void setBorderColor(BaseColor borderColor) {
        this.borderColor = borderColor;
    }

    public void setBackgroundColor(BaseColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBorder(BoxValues<Boolean> border) {
        this.border.setValue(border);
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

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public void setHorizontalAlignment(int horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public void setVerticalAlignment(int verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public List<String> getValueNames() {
        return Arrays.asList(
            "padding", 
            "borderWidth", 
            "borderColor",
            "backgroundColor", 
            "border", 
            "horizontalAlignment",
            "verticalAlignment",
            "fontSize", 
            "fontColor", 
            "fontFamily", 
            "fontStyle");
    }
  
}


