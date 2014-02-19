package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.ToTest;
import pl.edu.agh.tgmg.api.annotations.styles.ParagraphStyle;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters.CreatesParagraphElement;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.CellHeaderFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.ParagraphFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;
import pl.edu.agh.tgmg.utlis.CommonUtils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

@ToTest
public class ParagraphElement implements PdfElement, CreatesParagraphElement {

    String text;
    List<String> paramNames = Collections.emptyList();
    StyleFormatter<Paragraph, ParagraphStyle> styleFormatter = new ParagraphFormatter();

    public ParagraphElement(String text) {
        this.text = text;
    }

    public ParagraphElement(List<String> paramNames, String text) {
        this.paramNames = paramNames;
        this.text = text;
    }
    
    @Override
    public StyleFormatter<Paragraph, ParagraphStyle> getFormatter() {
        return styleFormatter;
    }
    
    @Override
    public Paragraph print(Object data) throws DocumentException {

        List params = new LinkedList();
        for (String paramName : paramNames) {
            params.add(CommonUtils.getValue(data, paramName));
        }

        String format = String.format(text, params.toArray());
        Paragraph element = new Paragraph(format);
        styleFormatter.addStyle(element);
        //ParagraphFormatter formP = (ParagraphFormatter) styleFormatter;
        //Paragraph element = new Paragraph(format, new Font(formP.getFontFamily(), formP.getFontSize()));
        return element;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((paramNames == null) ? 0 : paramNames.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
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
        ParagraphElement other = (ParagraphElement) obj;
        if (paramNames == null) {
            if (other.paramNames != null)
                return false;
        } else if (!paramNames.equals(other.paramNames))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ParagraphElement [text=" + text + ", paramNames=" + paramNames
                + "]";
    }

    @Override
    public void setFormatter(StyleFormatter<Paragraph, ParagraphStyle> formatter) {
        this.styleFormatter = formatter;
    }


}
