package org.example.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class FamilyTreeNodeTest {

    @Test
    public void addChildTest() {

        FamilyTreeNode parentNode = new FamilyTreeNode("Parent");
        FamilyTreeNode firstChild = new FamilyTreeNode("First Child");
        String secondChildName = "Second Child";
        int expectedNumberOfChildren = 2;

        parentNode.addChild(firstChild);
        parentNode.addChild(secondChildName);

        assertEquals(true, parentNode.hasChildren());
        int actualNumberOfChildren = parentNode.getChildren().size();
        assertEquals(expectedNumberOfChildren, actualNumberOfChildren);

    }

    @Test
    public void addChildrenTest() {

        FamilyTreeNode parentNode = new FamilyTreeNode("Parent");
        Set<FamilyTreeNode> children =
                new HashSet<>(Arrays.asList(
                        new FamilyTreeNode("Monday"),
                        new FamilyTreeNode("Tuesday"),
                        new FamilyTreeNode("Wednesday"),
                        new FamilyTreeNode("Thursday"),
                        new FamilyTreeNode("Friday"),
                        new FamilyTreeNode("Saturday"),
                        new FamilyTreeNode("Sunday")
                ));
        int expectedNumberOfChildren = 7;


        parentNode.addChildren(children);

        assertTrue(parentNode.hasChildren());
        int actualNumberOfChildren = parentNode.getChildren().size();
        assertEquals(expectedNumberOfChildren, actualNumberOfChildren);

    }

    @Test
    public void hasChildrenTest() {

        FamilyTreeNode parentNode1 = new FamilyTreeNode("Parent 1");
        FamilyTreeNode parentNode2 = new FamilyTreeNode("Parent 2");

        parentNode1.addChild("A");

        assertTrue(parentNode1.hasChildren());
        assertFalse(parentNode2.hasChildren());

    }

}
