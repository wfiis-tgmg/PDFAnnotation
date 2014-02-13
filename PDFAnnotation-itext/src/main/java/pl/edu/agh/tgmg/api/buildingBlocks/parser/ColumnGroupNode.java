package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.exceptions.InvalidGroupException;
import pl.edu.agh.tgmg.itext.generators.dto.TableHeaderColumn;

public class ColumnGroupNode extends TableHeaderColumn implements Comparable<ColumnGroupNode> {
	
	String id;
	int order;
	String parentId;
	ColumnGroupNode parent;
	List<ColumnGroupNode> children = new LinkedList<ColumnGroupNode>();
	
	int leafCount;
	int distanceFrom;
	
	
	int level;
	int maxLevel;
	Integer treeHeight;
	
	public ColumnGroupNode(String text, String parentId, int order) {
		super(text);
		this.id = null;
		this.order = order;
		this.level = 0;
		this.parent = null;
		this.parentId = parentId;
	}
	
	public ColumnGroupNode(String id, String text, String parentId) {
		super(text);
		this.id = id;
		this.level = 0;
		this.parent = null;
		this.parentId = parentId;
		this.order = 0;
	}

	public String getId() {
		return id;
	}

	public int getOrder() {
		return order;
	}

	public ColumnGroupNode getParent() {
		return parent;
	}

	public String getParentId() {
		return parentId;
	}

	public void addNode(ColumnGroupNode node) {
		if(!isRoot() && !node.getParentId().equals(id)) {
			throw new InvalidGroupException("cannot add node '" + node.getId() +
					"' - wrong parent ('" + id + "' instead of '" + node.getParentId() + "')");
		}
		children.add(node);
		node.parent = this;
	}
	
	public boolean addLeafNode(ColumnGroupNode node) {
		if((isRoot() && node.parentId.isEmpty()) || (id != null && id.equals(node.parentId))) {
			addNode(node);
			return true;
		} 
		for(ColumnGroupNode child : children) {
			if(child.addLeafNode(node) == true) {
				return true;
			}
		}
		if(parent == null) {
			throw new InvalidGroupException("Cannot assign a column '" + 
				node.getText() +"' to group '" + parentId + "' - group not found");
		}
		return false;
	}
	
	public List<ColumnGroupNode> getAllDescendants() {
		List<ColumnGroupNode> result = new LinkedList<ColumnGroupNode>(children);
		for(ColumnGroupNode node : children) {
			result.addAll(node.getAllDescendants());
		}
		return result;
	}
	
	public List<ColumnGroupNode> getAllHeaders() {
		List<ColumnGroupNode> result = new LinkedList<ColumnGroupNode>();
		for(ColumnGroupNode node : children) {
			if(node.leafCount > 0) {
				result.add(node);
			} else {
				System.out.println("No colums belong to the group '" + node.id + "' - the group and its children will be ignored");
				//TODO logger.warn("No colums belong to the group '' - the group and its children will be ignored")
				//TODO or throw exception ?
			}
		}
		return result;
	}
	
	public int getAllDescendantCount() {
		int result = children.size();
		for(ColumnGroupNode node : children) {
			result += node.getAllDescendantCount();
		}
		return result;
	}
	
	public int getMaxLevel(int level) {
		if(isLeaf() && !isGroup()) {
			return level;
		}
		int maxLevel = 0;
		for(ColumnGroupNode child : children) {
			int tmp = child.getMaxLevel(level+1);
			if(tmp > maxLevel) {
				maxLevel = tmp;
			}
		}
		return maxLevel;
	}
	
	public int updateOrder() {
		if(order == 0 && leafCount > 0) {
			for(ColumnGroupNode child : children) {
				if(child.leafCount > 0) {
					this.order = child.updateOrder();
				}
			}
		}
		return order;
	}

	public int updateColSpan() {
		this.leafCount = 0;
		if(children.isEmpty() && !isGroup()) {
			this.leafCount = 1;
		}
		for(ColumnGroupNode child : children) {
			this.leafCount += child.updateColSpan();
		}
		this.setColumn(leafCount);
		return leafCount;
	}
	
	public void updateRowSpan(int level, int maxLevel) {
		int rowSpan = isGroup() ? 1 : maxLevel - level + 1;
		this.setRow(rowSpan);
		for(ColumnGroupNode child : children) {
			child.updateRowSpan(level + 1, maxLevel);
		}
	}
	
	public boolean isGroup() {
		return id != null || parent == null;
	}
	
	public boolean isLeaf() {
		return children.isEmpty();
	}
	
	public boolean isRoot() {
		return id == null && parent == null;
	}
	
	/*public String toString() {
		return "g["+getText() + ", " + id+", "+parentId+",l:" + isLeaf() + 
				",g:" + isGroup() + ",r:" + isRoot() + ",lc: " + leafCount + ",or:" + order + "]";
	}*/
	
	public String dump(int level) {
		StringBuilder result = new StringBuilder();
		for(int i=0;i<level;i++) {
			result.append('-');
		}
		result.append(this.toString());
		result.append("\n");
		for(ColumnGroupNode child : children) {
			result.append(child.dump(level+1));
		}
		return result.toString();
	}

	@Override
	public int compareTo(ColumnGroupNode o) {
		return this.order - o.order;
	}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
	
	
	
	
}
