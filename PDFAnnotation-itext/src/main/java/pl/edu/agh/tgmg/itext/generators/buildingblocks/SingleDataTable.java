package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Collections;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.styles.TableStyle;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters.CreatesTableElement;
import pl.edu.agh.tgmg.itext.generators.dto.DynamicTableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.TableFormatter;
import pl.edu.agh.tgmg.utlis.CommonUtils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class SingleDataTable implements PdfTableElement, CreatesTableElement {

    int column;

    List<DynamicTableHeaderColumn> headerColumns;
    StyleFormatter<PdfPTable, TableStyle> tableFormatter = new TableFormatter();

    public SingleDataTable(int column) {
        this(column, Collections.<DynamicTableHeaderColumn>emptyList());
    }

    public SingleDataTable(int column, List<DynamicTableHeaderColumn> headerColumns) {
        this.column = column;
        this.headerColumns = headerColumns;
    }

    @Override
    public StyleFormatter<PdfPTable, TableStyle> getFormatter() {
        return tableFormatter;
    }
    
    @Override
    public int getColumnCount() {
        return column;
    }

    public List<DynamicTableHeaderColumn> getHeaderColumns() {
        return headerColumns;
    }

    public PdfPTable createPdfTable(Object obj) {
        PdfPTable pdfPTable = new PdfPTable(column);
        tableFormatter.addStyle(pdfPTable);
        for (DynamicTableHeaderColumn header : headerColumns) {
            PdfPCell cell = new PdfPCell(new Phrase(parseToString(obj, header)));
            cell.setColspan(header.getColSpan());
            cell.setRowspan(header.getRowSpan());
            header.getFormatter().addStyle(cell);
            pdfPTable.addCell(cell);
        }
        return pdfPTable;
    }

    private String parseToString(Object o, DynamicTableHeaderColumn h) {
        StringBuffer buffer = new StringBuffer();
        if(!isNullOrEmpty(h.getText())) buffer.append(h.getText());
        if(!isNullOrEmpty(h.getText()) && !isNullOrEmpty(h.getFieldName())) buffer.append(" : ");
        if(!isNullOrEmpty(h.getFieldName())) buffer.append(CommonUtils.getValue(o, h.getFieldName()));


        return buffer.toString();
    }

    @Override
    public PdfPTable print(Object data) throws DocumentException {
        return createPdfTable(data);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
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
        SingleDataTable other = (SingleDataTable) obj;
        if (column != other.column)
            return false;
        if (headerColumns == null) {
            if (other.headerColumns != null)
                return false;
        } else if (!headerColumns.equals(other.headerColumns))
            return false;
        return true;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setHeaderColumns(List<DynamicTableHeaderColumn> headerColumns) {
        this.headerColumns = headerColumns;
    }

    public void setStyleFormatter(StyleFormatter<PdfPTable, TableStyle> styleFormatter) {
        this.tableFormatter = styleFormatter;
    }

    @Override
    public void setFormatter(StyleFormatter<PdfPTable, TableStyle> formatter) {
        tableFormatter = formatter;
    }
}
