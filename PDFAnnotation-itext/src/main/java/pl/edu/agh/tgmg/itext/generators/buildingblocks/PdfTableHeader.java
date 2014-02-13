package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import java.util.Collections;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters.CreatesTableElement;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.TableFormatter;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfTableHeader implements CreatesTableElement {

    int columns;

    List<TableHeaderColumn> headerColumns;
    
    StyleFormatter<PdfPTable, TableStyle> tableFormatter = new TableFormatter();

    @Override
    public StyleFormatter<PdfPTable, TableStyle> getFormatter() {
        return tableFormatter;
    }

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

    public PdfTableHeader(int column) {
        this(column, Collections.<TableHeaderColumn>emptyList());
    }
    
    public PdfTableHeader(int column,List<TableHeaderColumn> headerColumns) {
        this.columns = column;
        this.headerColumns = headerColumns;
    }

    public PdfPTable createPdfTable() {
        PdfPTable pdfPTable = new PdfPTable(columns);
        tableFormatter.addStyle(pdfPTable);
        for (TableHeaderColumn h : headerColumns) {
            PdfPCell cell = new PdfPCell(new Phrase(h.getText()));
            cell.setColspan(h.getColSpan());
            cell.setRowspan(h.getRowSpan());
            h.getFormatter().addStyle(cell);
            pdfPTable.addCell(cell);
        }
        return pdfPTable;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + columns;
        result = prime * result
                + ((headerColumns == null) ? 0 : headerColumns.hashCode());
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
        PdfTableHeader other = (PdfTableHeader) obj;
        if (columns != other.columns)
            return false;
        if (headerColumns == null) {
            if (other.headerColumns != null)
                return false;
        } else if (!headerColumns.equals(other.headerColumns))
            return false;
        return true;
    }
    
    

    @Override
    public String toString() {
        return "PdfTableHeader [columns=" + columns + ", headerColumns="
                + headerColumns + "]";
    }

    @Override
    public void setFormatter(StyleFormatter<PdfPTable, TableStyle> formatter) {
        tableFormatter = formatter;
    }

}
