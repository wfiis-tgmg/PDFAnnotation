package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.PdfTableElement;
import pl.edu.agh.tgmg.itext.generators.dto.DynamicTableHeaderColumn;

import java.util.Collections;
import java.util.List;

public class SingleDataTable implements PdfTableElement {

    int column;

    List<DynamicTableHeaderColumn> headerColumns;

    public SingleDataTable(int column) {
        this(column, Collections.<DynamicTableHeaderColumn>emptyList());
    }

    public SingleDataTable(int column, List<DynamicTableHeaderColumn> headerColumns) {
        this.column = column;
        this.headerColumns = headerColumns;
    }

    public PdfPTable createPdfTable(Object o) {
        PdfPTable pdfPTable = new PdfPTable(column);
        for (DynamicTableHeaderColumn h : headerColumns) {
            Object value = CommonUtils.getValue(o, h.getFieldName());
            PdfPCell cell = new PdfPCell(new Phrase(String.format("%s: %s",h.getText(),value) ));
            cell.setColspan(h.getColSpan());
            cell.setRowspan(h.getRowSpan());
            pdfPTable.addCell(cell);
        }
        return pdfPTable;
    }

    @Override
    public PdfPTable print(Object data) throws DocumentException {
        return createPdfTable(data);
    }
}
