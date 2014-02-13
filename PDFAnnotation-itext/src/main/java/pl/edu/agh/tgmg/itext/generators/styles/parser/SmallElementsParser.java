package pl.edu.agh.tgmg.itext.generators.styles.parser;

import pl.edu.agh.tgmg.api.annotations.styles.elements.BooleanEnum;
import pl.edu.agh.tgmg.api.annotations.styles.elements.ColorName;
import pl.edu.agh.tgmg.api.annotations.styles.elements.FontFamily;
import pl.edu.agh.tgmg.api.annotations.styles.elements.FontStyle;
import pl.edu.agh.tgmg.api.annotations.styles.elements.HorizontalAlignment;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PageSize;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfBoxValuesB;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfBoxValuesF;
import pl.edu.agh.tgmg.api.annotations.styles.elements.PdfColor;
import pl.edu.agh.tgmg.api.annotations.styles.elements.TextAlignment;
import pl.edu.agh.tgmg.api.annotations.styles.elements.VerticalAlignment;
import pl.edu.agh.tgmg.itext.generators.styles.elements.BoxValues;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;

public class SmallElementsParser {

    public static BaseColor parse(PdfColor color) {
        if(color.R() >= 0 && color.G() >= 0 && color.B() >= 0) {
            return new BaseColor(color.R(), color.G(), color.B());
        }
        return parse(color.colorName());
    }
    
    public static BaseColor parse(ColorName color) {
        return new BaseColor(color.getR(), color.getG(), color.getB());
    }
    
    public static BoxValues<Float> parse(PdfBoxValuesF boxValue) {
        Float a = boxValue.forAll();
        Float b = boxValue.forBottom(); 
        Float l = boxValue.forLeft();
        Float r = boxValue.forRight();
        Float t = boxValue.forTop();
        if(b < 0) b = null;
        if(l < 0) l = null;
        if(r < 0) r = null;
        if(t < 0) t = null;
        if(a >= 0) {
            return new BoxValues<Float>(a);
        } else {
            return new BoxValues<Float>(b, l, r, t);
        }
    }
    
    public static BoxValues<Boolean> parse(PdfBoxValuesB boxValue) {
        BooleanEnum a = boxValue.forAll();
        BooleanEnum b = boxValue.forBottom();
        BooleanEnum l = boxValue.forLeft();
        BooleanEnum r = boxValue.forRight();
        BooleanEnum t = boxValue.forTop();
        if(a != BooleanEnum.NONE) {
            return new BoxValues<Boolean>(parse(a));
        } else {
            return new BoxValues<Boolean>(parse(b), parse(l), parse(r), parse(t));
        }
    }
    
    public static Boolean parse(BooleanEnum b) {
        switch(b) {
        case TRUE:
            return true;
        case FALSE:
            return false;
        default:
            return null;
        }
    }
    
    public static Font.FontFamily parse(FontFamily family) {
        return Font.FontFamily.valueOf(family.name());
    }
    
    public static int parse(FontStyle style) {
        return style.getId();
    }
    
    public static int parse(VerticalAlignment alignment) {
        return alignment.getId();
    }
    
    public static int parse(HorizontalAlignment alignment) {
        return alignment.getId();
    }
    
    public static int parse(TextAlignment alignment) {
        return alignment.getId();
    }
    
    public static Rectangle parse(PageSize pageSize) {
        return new RectangleReadOnly(pageSize.getUrx(),pageSize.getUry());
    }
}
