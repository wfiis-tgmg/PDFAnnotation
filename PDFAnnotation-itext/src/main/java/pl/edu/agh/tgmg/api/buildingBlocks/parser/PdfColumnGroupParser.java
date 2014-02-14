package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.I18nResolver;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroup;
import pl.edu.agh.tgmg.api.annotations.PdfColumnGroups;
import pl.edu.agh.tgmg.api.exceptions.InvalidGroupException;
import pl.edu.agh.tgmg.api.exceptions.ReflectionException;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolverImpl;

public class PdfColumnGroupParser {
    
    private StyleResolver styleResolver = new StyleResolverImpl();

    ColumnGroupNode rootNode;
    List<ColumnGroupNode> nodes;

    I18nResolver i18nResolver;

    public PdfColumnGroupParser(StyleResolver styleResolver,I18nResolver i18nResolver) {
        this.styleResolver = styleResolver;
        this.i18nResolver = i18nResolver;
    }

    public ColumnGroupNode parse(Class<?> clazz) throws InvalidGroupException, ReflectionException {
        rootNode = new ColumnGroupNode(null, null, null);
        nodes = new LinkedList<ColumnGroupNode>();

        findGroups(clazz);
        findParents();
        checkForCyclicDependenies();
        return rootNode;
    }

    private void findGroups(Class<?> clazz) throws ReflectionException, InvalidGroupException {
        PdfColumnGroups groups = (PdfColumnGroups) clazz.getAnnotation(PdfColumnGroups.class);    
        if(groups != null) {
            for(PdfColumnGroup group : groups.value()) {
                String name = i18nResolver.translate(group.name(), group.id());
                checkGroupExists(group.id());
                ColumnGroupNode node = new ColumnGroupNode(group.id(), name, group.parent());
                styleResolver.applyStyle(node, group.style(), clazz);
                nodes.add(node);
            }
        }
        
        for(Field field : clazz.getDeclaredFields()) {
            if(CommonUtils.isFieldANestedTable(field)) {
                Class<?> nestedClass = CommonUtils.getTypeParamOfIterableField(field);
                findGroups(nestedClass);
            }
        }
    }
    
    private void checkGroupExists(String id) throws InvalidGroupException {
        for(ColumnGroupNode node : nodes) {
            if(node.getId().equals(id)) {
                throw new InvalidGroupException("group '" + id + "' already exists!");
            }
        }
    }
    
    private void findParents() throws InvalidGroupException {
        for(ColumnGroupNode node : nodes) {
            ColumnGroupNode parent = findParent(node);
            parent.addNode(node);
        }
    }
    
    private ColumnGroupNode findParent(ColumnGroupNode node) throws InvalidGroupException {
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
    
    private void checkForCyclicDependenies() throws InvalidGroupException {
        if(rootNode.getAllDescendantCount() != nodes.size()) {
            throw new InvalidGroupException("cyclic dependency encountered!");
        }
    }
}
