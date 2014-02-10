package pl.edu.agh.tgmg.itext.generators.dto;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;

public class DynamicTableHeaderColumn extends TableHeaderColumn {

    String fieldName;

    public DynamicTableHeaderColumn(int rowspan, int colspan, String text, String fieldName) {
        super(rowspan, colspan, text);
        this.fieldName = fieldName;
    }

    public DynamicTableHeaderColumn(String text, String fieldName) {
        super(text);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
