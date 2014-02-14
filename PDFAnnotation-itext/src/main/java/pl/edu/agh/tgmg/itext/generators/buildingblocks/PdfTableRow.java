package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;
import pl.edu.agh.tgmg.api.buildingBlocks.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.CellWrapper;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.formatters.CreatesRowCellElement;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.CellRowFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;

import com.google.common.collect.Lists;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfTableRow implements CreatesRowCellElement{

    List<CellRow> cellRows = Collections.emptyList();
    StyleFormatter<PdfPCell, CellRowStyle> cellFormatter = new CellRowFormatter();

    public PdfTableRow(List<CellRow> cellRows) {
        this.cellRows = cellRows;
    }

    public PdfTableRow(CellRow ... cellRows) {
        this.cellRows = Lists.newArrayList(cellRows);
    }
    
    @Override
    public StyleFormatter<PdfPCell, CellRowStyle> getFormatter() {
        return cellFormatter;
        
    }

    public List<CellRow> getCellRows() {
        return cellRows;
    }

    public List<PdfPCell> print(Object data) {

        List<PdfPCell> res = new LinkedList<PdfPCell>();
        for (CellRow row : cellRows) {
            PdfPCell cell = cast(row.getCell(data)); //FIXME Na pewno dwa razy new PdfPCell?
            row.getFormatter().addStyle(cell);
            res.add(cell);
        }

        return res;
    }

    private PdfPCell cast(CellWrapper cellWrapper) {
        Object value1 = cellWrapper.getValue();
        switch (cellWrapper.getType()) {
            case PHRASE:
                return new PdfPCell((Phrase) value1);
            case TABLE:
                PdfPTable table = (PdfPTable) value1;
                PdfPCell pdfPCell = new PdfPCell(table);
                pdfPCell.setColspan(table.getNumberOfColumns());
                return pdfPCell;
        }
        throw new GenDocumentException();
    }

    public int getCells() {
        return cellRows.size();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((cellRows == null) ? 0 : cellRows.hashCode());
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
        PdfTableRow other = (PdfTableRow) obj;
        if (cellRows == null) {
            if (other.cellRows != null)
                return false;
        } else if (!cellRows.equals(other.cellRows))
            return false;
        return true;
    }

    @Override
    public void setFormatter(StyleFormatter<PdfPCell, CellRowStyle> formatter) {
        cellFormatter = formatter;
    }


    
    
}
