package pl.edu.agh.tgmg.itext.wrapper;

import pl.edu.agh.tgmg.api.annotations.styles.CellRowStyle;
import pl.edu.agh.tgmg.api.buildingBlocks.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.CellWrapper;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableElementWithStaticHeader;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.CellRowFormatter;
import pl.edu.agh.tgmg.itext.generators.styles.formatters.StyleFormatter;
import pl.edu.agh.tgmg.utlis.CommonUtils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;

public class DynamicTableCellRow implements CellRow {

    String name;
    PdfTableElementWithStaticHeader tableElement;
    
    StyleFormatter<PdfPCell, CellRowStyle> cellFormatter = new CellRowFormatter();

    public DynamicTableCellRow(String name, PdfTableElementWithStaticHeader tableElement) {
        this.name = name;
        this.tableElement = tableElement;
    }

    @Override
    public CellWrapper getCell(Object o)  {

        try {
            return new ITextTableWrapper(tableElement.print(CommonUtils.getValue(o, name)));
        } catch (DocumentException e) {
            throw new GenDocumentException();
        }
    }


    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
                + ((tableElement == null) ? 0 : tableElement.hashCode());
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
        DynamicTableCellRow other = (DynamicTableCellRow) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (tableElement == null) {
            if (other.tableElement != null)
                return false;
        } else if (!tableElement.equals(other.tableElement))
            return false;
        return true;
    }

    @Override
    public StyleFormatter<PdfPCell, CellRowStyle> getFormatter() {
        return cellFormatter;
    }

    @Override
    public void setFormatter(StyleFormatter<PdfPCell, CellRowStyle> formatter) {
        cellFormatter = formatter;
    }

    @Override
    public int getColumnCount() {
        return tableElement.getColumnCount();
    }
    
    
}
