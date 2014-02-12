package pl.edu.agh.tgmg.itext.generators.styles;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

public class ParagraphFormatter implements StyleFormatter<Paragraph> {


    @Override
    public void addStyle(Paragraph p) {
        p.getFont().setSize(Font.DEFAULTSIZE);
        p.getFont().setColor(BaseColor.BLACK);
        p.getFont().setFamily(Font.FontFamily.HELVETICA.name());

        p.setExtraParagraphSpace(2);
//        p.setSpacingAfter();
//        p.setSpacingBefore();
//        p.setAlignment();
    }
}
