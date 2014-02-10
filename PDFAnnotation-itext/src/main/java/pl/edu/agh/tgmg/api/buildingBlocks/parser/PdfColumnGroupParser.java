package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroup;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroups;
import pl.edu.agh.tgmg.api.exceptions.InvalidGroupException;

public class PdfColumnGroupParser {
	
	ColumnGroupNode rootNode = new ColumnGroupNode(null, null, null);
	List<ColumnGroupNode> nodes = new LinkedList<ColumnGroupNode>();
	
	//@Override
	public ColumnGroupNode parse(Class<?> clazz) {
		findGroups(clazz);
		findParents();
		checkForCyclicDependenies();
		return rootNode;
	}
	
	private void findGroups(Class<?> clazz) {
		PdfColumnGroups groups = (PdfColumnGroups) clazz.getAnnotation(PdfColumnGroups.class);	
		if(groups != null) {
			for(PdfColumnGroup group : groups.value()) {
				String name = CommonUtils.processText(group.name(), group.id());
				checkGroupExists(group.id());
				nodes.add(new ColumnGroupNode(group.id(), name, group.parent()));
			}
		}
	}
	
	private void checkGroupExists(String id) {
		for(ColumnGroupNode node : nodes) {
			if(node.getId().equals(id)) {
				throw new InvalidGroupException("group '" + id + "' already exists!");
			}
		}
	}
	
	private void findParents() {
		for(ColumnGroupNode node : nodes) {
			ColumnGroupNode parent = findParent(node);
			parent.addNode(node);
		}
	}
	
	private ColumnGroupNode findParent(ColumnGroupNode node) {
		if(node.getParentId().isEmpty()) {
			return rootNode;
		} 
		
		for(ColumnGroupNode nnode : nodes) {
			if(nnode.getId().equals(node.getParentId())) {
				return nnode;
			}
		}
		
		throw new InvalidGroupException("parent '" + node.getParentId() + 
				"' for group '" + node.getId() + "' does not exist!");
	}
	
	private void checkForCyclicDependenies() {
		if(rootNode.getAllDescendantCount() != nodes.size()) {
			throw new InvalidGroupException("cyclic dependency encountered!");
		}
	}
}
