package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import pl.edu.agh.tgmg.api.BlankI18nResolverImpl;
import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.I18nResolver;
import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.api.exceptions.InvalidGroupException;
import pl.edu.agh.tgmg.api.exceptions.InvalidOrderException;
import pl.edu.agh.tgmg.api.exceptions.ReflectionException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolverImpl;

public class PdfTableHeaderParser {

    private I18nResolver i18nResolver ;
    private StyleResolver styleResolver = new StyleResolverImpl();

    public PdfTableHeaderParser() {}


    public PdfTableHeaderParser(StyleResolver styleResolver) {
        this(styleResolver, new BlankI18nResolverImpl());
    }

    public PdfTableHeaderParser(StyleResolver styleResolver,I18nResolver i18nResolver ) {
        this.styleResolver = styleResolver;
        this.i18nResolver = i18nResolver;
    }

    ColumnGroupNode groupTree;
    int currenOrder;
    boolean useCustomOrder;
    
    public PdfTableHeader parse(Class<?> clazz) throws ReflectionException, InvalidGroupException  {
        
        PdfColumnGroupParser groupParser = new PdfColumnGroupParser(styleResolver, i18nResolver);
        groupTree = groupParser.parse(clazz);
        currenOrder = 1;
        findColumns(clazz);
        
        int columns = groupTree.updateColSpan();
        int maxLevel = groupTree.getMaxLevel(0);
        groupTree.updateRowSpan(0, maxLevel);
        groupTree.updateOrder();
        
        List<TableHeaderColumn> headerColumns = orderColumns(groupTree);
        PdfTableHeader header = new PdfTableHeader(columns, headerColumns);
        return header;
    }
    
    private List<TableHeaderColumn> orderColumns(ColumnGroupNode rootNode) {
        List<TableHeaderColumn> sortedColumns = new ArrayList<TableHeaderColumn>();
        Queue<ColumnGroupNode> queue = new LinkedList<ColumnGroupNode>();
        queue.add(rootNode);
        while(!queue.isEmpty()) {
            ColumnGroupNode node = queue.remove();
            Collections.sort(node.children);
            for(ColumnGroupNode child : node.children) {
                if(child.leafCount < 1) {
                    continue;
                }
                sortedColumns.add(child);
                queue.add(child);
            }
        }
        return sortedColumns;
    }
    
    private void findColumns(Class<?> clazz) throws ReflectionException {
        for(Field field : clazz.getDeclaredFields()) {
            PdfColumn column = field.getAnnotation(PdfColumn.class);
            if(column != null) {
                String name = i18nResolver.translate(column.name(), field.getName());
                int order = getOrder(column);
                ColumnGroupNode node = new ColumnGroupNode(name, column.group(), order);
                styleResolver.applyStyle(node, column.headerStyle(), clazz);
                groupTree.addLeafNode(node);
            } else if(CommonUtils.isFieldANestedTable(field)) {
                Class<?> nestedClass = CommonUtils.getTypeParamOfIterableField(field);
                findColumns(nestedClass);
            } 
        }
    }
    
    private int getOrder(PdfColumn column) {
        int customOrder = column.order();
        boolean columnHasCustomOrder = customOrder >= 0;
        if(currenOrder == 1) {
            useCustomOrder = columnHasCustomOrder;
        } else if(useCustomOrder != columnHasCustomOrder) {
            throw new InvalidOrderException("Column currenOrder is only set partially"); 
        }
        if(useCustomOrder) {
            return customOrder;
        } else {
            return currenOrder++;
        }
    }
}
