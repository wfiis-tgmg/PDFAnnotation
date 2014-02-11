package pl.edu.agh.tgmg.itext.generators.buildingblocks;

import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellRow;
import pl.edu.agh.tgmg.itext.generators.dto.DynamicTableHeaderColumn;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;
import pl.edu.agh.tgmg.itext.wrapper.TableCellRow;

public class TestHelper {
    public static String dump(PdfTableRow row) {
        StringBuilder result = new StringBuilder();
        dump(result, row, 0);
        System.out.println("<dump>\n" + result.toString() + "\n</dump>");
        return result.toString();
    }
    
    public static void dump(StringBuilder result, PdfTableRow row, int level) {
        for(CellRow cellRow : row.getCellRows()) {
            if(cellRow instanceof StringCellRow) {
                addValue(result,((StringCellRow) cellRow).getName(), level);
            } else if(cellRow instanceof TableCellRow) {
                TableCellRow tableRow = (TableCellRow) cellRow;
                PdfTableElementWithStaticHeader inner = (PdfTableElementWithStaticHeader) tableRow.getTableElement();
                addValue(result, tableRow.getName(), level);
                dump(result, inner.getPdfTableRow(), level+1);
            } else if(cellRow instanceof PdfTableWithDynamicHeader) {
                PdfTableWithDynamicHeader inner = (PdfTableWithDynamicHeader) cellRow;
                addValue(result, inner.getName(), level);
                dump(result, inner.getSingleDataTable(), level);
                dump(result, inner.getPdfTableRow(), level +1);
            }
        }
    }
    
    public static void dump(StringBuilder result, SingleDataTable table, int level) {
        for(DynamicTableHeaderColumn column : table.getHeaderColumns()) {
            addValue(result, "header: " + column.getFieldName() + ", " 
                    + ", " + column.getText() + ", " + column.getColSpan() + ", " + column.getRowSpan(), level);
        }
    }
    
    public static void addValue(StringBuilder string, String value, int level) {
        for(int i=0;i<level;i++) {
            string.append("--");
        }
        string.append(value);
        string.append("\n");
    }
}
