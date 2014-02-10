package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;

import java.util.List;

public class PdfTableHeader {

    int columns;

    List<TableHeaderColumn> headerColumns;

    public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public List<TableHeaderColumn> getHeaderColumns() {
		return headerColumns;
	}

	public void setHeaderColumns(List<TableHeaderColumn> headerColumns) {
		this.headerColumns = headerColumns;
	}

	public PdfTableHeader(int column,List<TableHeaderColumn> headerColumns) {
        this.columns = column;
        this.headerColumns = headerColumns;
    }

    public PdfPTable createPdfTable() {
        PdfPTable pdfPTable = new PdfPTable(columns);
        for (TableHeaderColumn h : headerColumns) {
            PdfPCell cell = new PdfPCell(new Phrase(h.getText()));
            cell.setColspan(h.getColSpan());
            cell.setRowspan(h.getRowSpan());
            pdfPTable.addCell(cell);
        }
        return pdfPTable;
    }
}
