package pl.edu.agh.tgmg.itext.wrapper;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellWrapper;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableElement;

import com.itextpdf.text.DocumentException;

public class TableCellRow implements CellRow {

    String name;

    PdfTableElement tableElement;


    public TableCellRow(String name, PdfTableElement tableElement) {
        this.name = name;
        this.tableElement = tableElement;
    }

    public PdfTableElement getTableElement() {
        return tableElement;
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
        TableCellRow other = (TableCellRow) obj;
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
    
    
}
