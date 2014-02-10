package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.annotations.PdfColumn;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.PdfTableHeader;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;

public class PdfTableHeaderParser {

	public PdfTableHeader parse(Class<?> clazz) {
		
		PdfColumnGroupParser groupParser = new PdfColumnGroupParser();
		ColumnGroupNode groupTree = groupParser.parse(clazz);
		
		int order = 1;
		for(Field field: clazz.getDeclaredFields()) {
			PdfColumn column = field.getAnnotation(PdfColumn.class);
			if(column != null) {
				String name = CommonUtils.processText(column.name(), field.getName());
				ColumnGroupNode node = new ColumnGroupNode(name, column.group(),order++);
				groupTree.addLeafNode(node);
			}
		}
		
		int columns = groupTree.updateColSpan();
		int maxLevel = groupTree.getMaxLevel(0);
		groupTree.updateRowSpan(0, maxLevel);
		groupTree.updateOrder();
		
		System.out.println(groupTree.dump(0));
		
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
}
