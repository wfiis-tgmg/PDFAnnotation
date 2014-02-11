package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;

public class PdfTableRowParser {

    public PdfTableRow parse(Class<?> clazz) {
        List<CellRow> cellRows = new LinkedList<CellRow>();
        for(Field field: clazz.getDeclaredFields()) {
            if(field.isAnnotationPresent(PdfColumn.class)) {
                cellRows.add(new StringCellRow(field.getName()));
            }
        }
        return new PdfTableRow(cellRows);
    }
}
