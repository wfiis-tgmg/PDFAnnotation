package pl.edu.agh.tgmg.itext.generators.styles.formatters;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

public class ParagraphFormatter implements StyleFormatter<Paragraph> {


    private  int defaultsize = Font.DEFAULTSIZE;
    private  BaseColor color = BaseColor.BLACK;
    private  Font.FontFamily fontFamily = Font.FontFamily.HELVETICA;
    private  int extraSpace = 0;
    private  float spacingAfter = 0.5f;
    private  float spacingBefore = 0.5f;
    private  int alignCenter = Element.ALIGN_CENTER;


    public ParagraphFormatter(int alignCenter, BaseColor color, int defaultsize, int extraSpace, Font.FontFamily fontFamily, float spacingAfter, float spacingBefore) {
        this.alignCenter = alignCenter;
        this.color = color;
        this.defaultsize = defaultsize;
        this.extraSpace = extraSpace;
        this.fontFamily = fontFamily;
        this.spacingAfter = spacingAfter;
        this.spacingBefore = spacingBefore;
    }

    public ParagraphFormatter() {
    }

    @Override
    public void addStyle(Paragraph p) {
        p.getFont().setSize(defaultsize);
        p.getFont().setColor(color);
        p.getFont().setFamily(fontFamily.name());

        p.setExtraParagraphSpace(extraSpace);
        p.setSpacingAfter(spacingAfter);
        p.setSpacingBefore(spacingBefore);
        p.setAlignment(alignCenter);
    }

    public void setAlignCenter(int alignCenter) {
        this.alignCenter = alignCenter;
    }

    public void setColor(BaseColor color) {
        this.color = color;
    }

    public void setDefaultsize(int defaultsize) {
        this.defaultsize = defaultsize;
    }

    public void setExtraSpace(int extraSpace) {
        this.extraSpace = extraSpace;
    }

    public void setFontFamily(Font.FontFamily fontFamily) {
        this.fontFamily = fontFamily;
    }

    public void setSpacingAfter(float spacingAfter) {
        this.spacingAfter = spacingAfter;
    }

    public void setSpacingBefore(float spacingBefore) {
        this.spacingBefore = spacingBefore;
    }
}
