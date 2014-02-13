package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.tgmg.api.annotations.PdfFlowDataCell;
import pl.edu.agh.tgmg.api.annotations.PdfFlowTextCell;
import pl.edu.agh.tgmg.api.annotations.PdfFlowTextCells;
import pl.edu.agh.tgmg.api.exceptions.InvalidFlowCellException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.SingleDataTable;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolverImpl;

public class PdfFlowCellParser {

    private StyleResolver styleRepository = new StyleResolverImpl();
    
    List<SingleDataTable> result;
    Field startingField;
    
    public PdfFlowCellParser() {}
    
    public PdfFlowCellParser(StyleResolver styleResolver) {
        this.styleRepository = styleResolver;
    }

    public List<SingleDataTable> parse(Class<?> root, int currentField) {
        result = new ArrayList<>();
        startingField = root.getDeclaredFields()[currentField];
        Field[] fields = root.getDeclaredFields();
        for(int i=currentField;i<fields.length;i++) {
            Field field = fields[i];
            if(i > currentField && hasOtherAnnotations(field)) {
                break;
            }
            PdfFlowTextCells textCells = field.getAnnotation(PdfFlowTextCells.class);
            PdfFlowDataCell dataCell = field.getAnnotation(PdfFlowDataCell.class);
            if(textCells == null && dataCell == null) {
                break;
            }
            if(textCells != null) {
                for(PdfFlowTextCell textCell : textCells.value()) {
                    addTextCell(textCell);
                }
            }
            if(dataCell != null) {
                addDataCell(dataCell, field.getName());
            }
        }
        return result;
    }
    
    private boolean hasOtherAnnotations(Field field) {
        for(Annotation annotation : field.getAnnotations()) {
            if(!(annotation instanceof PdfFlowTextCells) || 
                    !(annotation instanceof PdfFlowDataCell)) {
                return true;
            }
        }
        return false;
    }
    
    private void addTextCell(PdfFlowTextCell cell) {
        
    }
    
    private void addDataCell(PdfFlowDataCell cell, String fieldName) {
        
    }
    
    public int cellsRetrieved() {
        if(startingField == null) {
            throw new InvalidFlowCellException("invoked when parse was not yet used");
        }
        int result = 0;
        //TODO
        if(result == 0) {
            throw new InvalidFlowCellException("No Flow Cells retrieved from "
                    + "Flow Cell annotation field: " + startingField.getName());
        }
        return result;
    }
}
