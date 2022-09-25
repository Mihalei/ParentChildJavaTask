package org.example.util;

import org.example.exceptions.CyclicTreeException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FamilyForest {

    // root is like a common ancestor so that all family trees can be united in one tree
    FamilyTreeNode root;

    public FamilyForest() {
        root = new FamilyTreeNode(null);
    }

    private void checkCyclicRelationships() {
        List<String> dfsCurrentNames = new ArrayList<>();
        for (FamilyTreeNode topLevelChild: root.getChildren()) {
            checkCyclicRelationshipsRecursive(topLevelChild, dfsCurrentNames);
        }
    }

    private void checkCyclicRelationshipsRecursive(FamilyTreeNode currentNode, List<String> currentNames) {
        if (currentNames.contains(currentNode.getName())) {
            throw new CyclicTreeException(
                    getErrorMessage(currentNode, currentNames));
        } else {
            if (!currentNode.hasChildren()) return;

            currentNames.add(currentNode.getName());
            for (FamilyTreeNode child: currentNode.getChildren()) {
                checkCyclicRelationshipsRecursive(child, currentNames);
            }
            currentNames.remove(currentNode.getName());
        }
    }

    private static String getErrorMessage(FamilyTreeNode lastNode, List<String> previousNames) {
        StringBuilder sb = new StringBuilder();
        for (String name: previousNames) {
            sb.append(name);
            sb.append(" -> ");
        }
        sb.append(lastNode.getName());
        String message = sb.toString();
        return message;
    }

    public void addChild(String parentName, String childName) {

        if (!containsNode(parentName)) {
            root.addChild(parentName);
        }

        if (containsNode(childName)) {
            for (FamilyTreeNode parent: getAllNodesWithValue(parentName)) {
                parent.addChild(getNode(childName));
            }
        } else {
            for (FamilyTreeNode parent: getAllNodesWithValue(parentName)) {
                parent.addChild(childName);
            }
        }

        checkCyclicRelationships();
        removeRedundantRootConnections();
    }

    private void removeRedundantRootConnections() {

        // check for repeating top level children and remove them
        List<String> topLevelChildrenNamesToRemove = new ArrayList<>();
        for (FamilyTreeNode topLevelChild: root.getChildren()) {
            if (countNodesWithValue(topLevelChild.getName()) > 1)
                topLevelChildrenNamesToRemove.add(topLevelChild.getName());
        }

        root.removeChildren(topLevelChildrenNamesToRemove);
    }

    public boolean containsNode(String name) {
        if (!root.hasChildren()) return false;
        LinkedList<FamilyTreeNode> BFSList = new LinkedList<>(root.getChildren());
        while(!BFSList.isEmpty()) {
            FamilyTreeNode nextNode = BFSList.remove(0);
            if (name.equals(nextNode.getName())) return true;
            if (nextNode.hasChildren()) BFSList.addAll(nextNode.getChildren());
        }
        return false;
    }

    public int countNodesWithValue(String name) {
        if (!root.hasChildren()) return 0;

        int count = 0;
        LinkedList<FamilyTreeNode> BFSList = new LinkedList<>(root.getChildren());
        while(!BFSList.isEmpty()) {
            FamilyTreeNode nextNode = BFSList.remove(0);
            if (name.equals(nextNode.getName())) count++;
            if (nextNode.hasChildren()) BFSList.addAll(nextNode.getChildren());
        }
        return count;
    }

    public List<FamilyTreeNode> getAllNodesWithValue(String name) {
        if (!root.hasChildren()) return new ArrayList<FamilyTreeNode>();

        List<FamilyTreeNode> allNodes = new ArrayList<>();
        LinkedList<FamilyTreeNode> BFSList = new LinkedList<>(root.getChildren());
        while(!BFSList.isEmpty()) {
            FamilyTreeNode nextNode = BFSList.remove(0);
            if (name.equals(nextNode.getName())) allNodes.add(nextNode);
            if (nextNode.hasChildren()) BFSList.addAll(nextNode.getChildren());
        }
        return allNodes;
    }

    public FamilyTreeNode getNode(String name) {
        if (!root.hasChildren()) return null;
        LinkedList<FamilyTreeNode> BFSList = new LinkedList<>(root.getChildren());
        while(!BFSList.isEmpty()) {
            FamilyTreeNode nextNode = BFSList.remove(0);
            if (name.equals(nextNode.getName())) return nextNode;
            if (nextNode.hasChildren()) BFSList.addAll(nextNode.getChildren());
        }
        return null;
    }

    @Override
    public String toString() {
        if (!root.hasChildren()) return "";
        StringBuilder sb = new StringBuilder();
        Integer depth = 0;
        for (FamilyTreeNode child: root.getChildren()) {
            toStringRecursive(child, sb, depth);
        }
        return sb.toString();
    }

    private void toStringRecursive(FamilyTreeNode current, StringBuilder sb, Integer depth) {
        addNodeNameToString(sb, depth, current);
        if (!current.hasChildren()) return;
        depth++;
        for (FamilyTreeNode child: current.getChildren()) {
            toStringRecursive(child, sb, depth);
        }
        depth--;
    }

    private static void addNodeNameToString(StringBuilder sb, Integer depth, FamilyTreeNode child) {
        for (int i = 0; i < depth; i++) {
            sb.append("\t");
        }
        sb.append(child.getName());
        sb.append('\n');
    }
}
