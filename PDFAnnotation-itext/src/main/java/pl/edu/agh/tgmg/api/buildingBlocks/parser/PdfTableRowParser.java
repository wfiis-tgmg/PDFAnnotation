package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.annotations.PdfRowGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroup;
import pl.edu.agh.tgmg.api.annotations.PdfTableGroupHeader;
import pl.edu.agh.tgmg.api.exceptions.InvalidTableGroupException;
import pl.edu.agh.tgmg.api.exceptions.ReflectionException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableElementWithStaticHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableRow;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableWithDynamicHeader;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.SingleDataTable;
import pl.edu.agh.tgmg.itext.generators.dto.DynamicTableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;
import pl.edu.agh.tgmg.itext.wrapper.StringCellRow;
import pl.edu.agh.tgmg.itext.wrapper.TableCellRow;

public class PdfTableRowParser {
    
    private StyleResolver styleResolver = new StyleResolver();

    public PdfTableRowParser() {}
    
    public PdfTableRowParser(StyleResolver styleResolver) {
        this.styleResolver = styleResolver;
    }

    public PdfTableRow parse(Class<?> clazz) throws InvalidTableGroupException, ReflectionException {
        return new PdfTableRow(findCellRows(clazz));
    }
    
    private List<CellRow> findCellRows(Class<?> clazz) throws InvalidTableGroupException, ReflectionException {
        CellRow dynamicTable = findTableGroup(clazz);
        if(dynamicTable != null) {
            return Arrays.asList(dynamicTable);
        }
        return findColumns(clazz);
    }
    
    private CellRow findTableGroup(Class<?> clazz) throws ReflectionException, InvalidTableGroupException {
        List<DynamicTableHeaderColumn> headers = new LinkedList<DynamicTableHeaderColumn>();
        Field tableGroupField = null;
        boolean hasOthers = false;
        for(Field field: clazz.getDeclaredFields()) {
            PdfTableGroupHeader header = field.getAnnotation(PdfTableGroupHeader.class);
            if(header != null) {
                String text = CommonUtils.processText(header.name(), field.getName());
                DynamicTableHeaderColumn h = new DynamicTableHeaderColumn(text, field.getName());
                styleResolver.applyStyle(h, header.style(), clazz);
                headers.add(h);
            } else if(field.isAnnotationPresent(PdfTableGroup.class)) {
                if(tableGroupField != null) {
                    throw new InvalidTableGroupException("multiple table groups in class " 
                            + clazz.getName() + ". Can be only one");
                }
                tableGroupField = field;
            } else if(field.isAnnotationPresent(PdfColumn.class)) {
                hasOthers = true;
            } else if(field.isAnnotationPresent(PdfRowGroup.class)) {
                hasOthers = true;
            }
        }
        if(tableGroupField != null && hasOthers) {
            throw new InvalidTableGroupException("table group mixed with other elements in class " 
                    + clazz.getName() + ". Table group can only exists alone (with headers)");
        }
        if(headers.size() > 0 && tableGroupField == null) {
            throw new InvalidTableGroupException("table group headers in class " 
                    + clazz.getName() + " with no table group");
        }
        if(tableGroupField == null) {
            return null;
        }
        PdfTableGroup pdfTableGroup = tableGroupField.getAnnotation(PdfTableGroup.class);
        SingleDataTable dataTable = new SingleDataTable(headers.size(), headers);
        styleResolver.applyStyle(dataTable, pdfTableGroup.tableStyle(), clazz);
        List<CellRow> rows = findCellRows(CommonUtils.getTypeParamOfIterableField(tableGroupField));
        CellRow table = new PdfTableWithDynamicHeader(tableGroupField.getName(), 
                new PdfTableRow(rows), dataTable);
        styleResolver.applyStyle(table, pdfTableGroup.rowCellStyle(), clazz);
        return table;
    }
    
    private List<CellRow> findColumns(Class<?> clazz) throws ReflectionException, InvalidTableGroupException {
        List<CellRow> cellRows = new LinkedList<CellRow>();
        for(Field field : clazz.getDeclaredFields()) {
            PdfColumn column = field.getAnnotation(PdfColumn.class);
            PdfRowGroup rowGroup = field.getAnnotation(PdfRowGroup.class);
            if(column != null) {
                CellRow row = new StringCellRow(field.getName());
                styleResolver.applyStyle(row, column.rowStyle(), clazz);
                cellRows.add(row);
            } else if(rowGroup != null) {
                Class<?> innerClass = CommonUtils.getTypeParamOfIterableField(field); 
                PdfTableRow innerRows = new PdfTableRow(findCellRows(innerClass));
                TableCellRow innerTable = new TableCellRow(field.getName(),
                    new PdfTableElementWithStaticHeader( innerRows ));
                styleResolver.applyStyle(innerTable, rowGroup.rowCellStyle(), clazz);
                cellRows.add(innerTable);
            }
            
        }
        return cellRows;
    }
}
