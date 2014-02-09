package pl.edu.agh.tgmg.itext.wrapper;

import com.itextpdf.text.DocumentException;
import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellWrapper;
import pl.edu.agh.tgmg.api.exceptions.GenDocumentException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableElement;

public class TableCellRow implements CellRow {

    String name;

    PdfTableElement tableElement;


    public TableCellRow(String name, PdfTableElement tableElement) {
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
}
