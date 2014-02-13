package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import pl.edu.agh.tgmg.itext.generators.styles.BoxValues;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;

public class CellFormatter {

    private  BoxValues<Float> padding;
    private  BoxValues<Float> borderWidth;
    private  BoxValues<BaseColor> borderColor;

    public CellFormatter() {
        padding = new BoxValues<Float>(0.5f);
        borderWidth = new BoxValues<Float>(1f);
        borderColor = new BoxValues<BaseColor>(BaseColor.BLACK);
    }

    public CellFormatter(BoxValues<BaseColor> borderColor, BoxValues<Float> borderWidth, BoxValues<Float> padding) {
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.padding = padding;
    }

    public void addStyle(PdfPCell p) {
        p.setBorder(Rectangle.BOX);

        p.setPadding(padding.getForAll());
        p.setPaddingBottom(padding.getForBottom());
        p.setPaddingTop(padding.getForTop());
        p.setPaddingLeft(padding.getForLeft());
        p.setPaddingRight(padding.getForRight());

        p.setBorderColor(borderColor.getForAll());
        p.setBorderColorBottom(borderColor.getForBottom());
        p.setBorderColorTop(borderColor.getForTop());
        p.setBorderColorLeft(borderColor.getForLeft());
        p.setBorderColorRight(borderColor.getForRight());


        p.setBorderWidth(borderWidth.getForAll());
        p.setBorderWidthBottom(borderWidth.getForBottom());
        p.setBorderWidthTop(borderWidth.getForTop());
        p.setBorderWidthLeft(borderWidth.getForLeft());
        p.setBorderWidthRight(borderWidth.getForRight());

        p.setVerticalAlignment(Element.ALIGN_CENTER);
        p.setHorizontalAlignment(Element.ALIGN_CENTER);
    }

    public void setBorderColor(BoxValues<BaseColor> borderColor) {
        this.borderColor = borderColor;
    }

    public void setBorderWidth(BoxValues<Float> borderWidth) {
        this.borderWidth = borderWidth;
    }

    public void setPadding(BoxValues<Float> padding) {
        this.padding = padding;
    }
    
    public void setStyle(CellFormatter other) {
        
    }
}


