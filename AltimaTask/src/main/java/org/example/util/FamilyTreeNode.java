package org.example.util;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FamilyTreeNode {

    private String name;
    private Set<FamilyTreeNode> children;

    FamilyTreeNode(String name) {

        this.name = name;
        this.children = new LinkedHashSet<>();

    }

    public void addChild(String childName) {
        children.add(new FamilyTreeNode(childName));
    }

    public void addChild(FamilyTreeNode child) {
        children.add(child);
    }

    public void addChildren(Set<FamilyTreeNode> children) {
        if (children == null || children.isEmpty()) return;
        this.children.addAll(children);
    }

    public void removeChild(String childName) {
        if (children.isEmpty()) return;
        children.remove(new FamilyTreeNode(childName));
    }

    public void removeChildren(List<String> children) {
        if (children == null || children.isEmpty()) return;
        if (this.children.isEmpty()) return;
        for (String childName: children) {
            removeChild(childName);
        }
    }

    public String getName() {
        return name;
    }

    public Set<FamilyTreeNode> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        if (children == null || children.isEmpty()) return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamilyTreeNode)) return false;

        FamilyTreeNode that = (FamilyTreeNode) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
