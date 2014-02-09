package pl.edu.agh.tgmg.api;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import pl.edu.agh.tgmg.api.annotations.ToTest;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@ToTest
public class ParagraphElement implements PdfElement {

    String text;
    List<String> paramNames = Collections.emptyList();


    public ParagraphElement(String text) {
        this.text = text;
    }

    public ParagraphElement(List<String> paramNames, String text) {
        this.paramNames = paramNames;
        this.text = text;
    }

    @Override
    public Paragraph print(Object data) throws DocumentException {

        List params = new LinkedList();
        for (String paramName : paramNames) {
            params.add(CommonUtils.getValue(data, paramName));
        }

        String format = String.format(text, params.toArray());
        Paragraph element = new Paragraph(format);
        return element;
    }
}
