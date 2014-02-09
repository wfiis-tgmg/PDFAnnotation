package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;

import java.util.Collections;
import java.util.List;

public class PdfTableHeader {

    int column;

    List<TableHeaderColumn> headerColumns;

    public PdfTableHeader(int column) {
        this(column, Collections.<TableHeaderColumn>emptyList());
    }

    public PdfTableHeader(int column,List<TableHeaderColumn> headerColumns) {
        this.column = column;
        this.headerColumns = headerColumns;
    }

    public PdfPTable createPdfTable() {
        PdfPTable pdfPTable = new PdfPTable(column);
        for (TableHeaderColumn h : headerColumns) {
            PdfPCell cell = new PdfPCell(new Phrase(h.getText()));
            cell.setColspan(h.getColSpan());
            cell.setRowspan(h.getRowSpan());
            pdfPTable.addCell(cell);
        }
        return pdfPTable;
    }
}
