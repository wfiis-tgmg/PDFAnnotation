package pl.edu.agh.tgmg.itext.generators.styles;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;

public class CellFormatterImpl implements CellFormatter {

    private final BoxValues<Float> padding;
    private final BoxValues<Float> borderWidth;
    private final BoxValues<BaseColor> borderColor;

    public CellFormatterImpl() {
        padding = new BoxValues<Float>(0.5f);
        borderWidth = new BoxValues<Float>(1f);
        borderColor = new BoxValues<BaseColor>(BaseColor.BLACK);
    }

    @Override
    public void addStyles(PdfPCell p) {


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
}


