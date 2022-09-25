package org.example.util;

import org.example.exceptions.CyclicTreeException;
import org.example.exceptions.InvalidInputException;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class FamilyForestTest {

    private String validInputFile = "D:\\Projects\\HiringTasks\\AltimaTask\\ParentChildJavaTask\\AltimaTask\\src\\test\\resources\\InputFile.txt";
    private String outputFileExpectedResult = "D:\\Projects\\HiringTasks\\AltimaTask\\ParentChildJavaTask\\AltimaTask\\src\\test\\resources\\OutputFileExpectedResult.txt";
    private String cyclicInputFile = "D:\\Projects\\HiringTasks\\AltimaTask\\ParentChildJavaTask\\AltimaTask\\src\\test\\resources\\CyclicInputFile.txt";

    private String expectedCyclicInputErrorMessageFile = "D:\\Projects\\HiringTasks\\AltimaTask\\ParentChildJavaTask\\AltimaTask\\src\\test\\resources\\ExpectedCyclicInputErrorMessage.txt";

    @Test
    public void checkCyclicRelationshipsTest() {

        String expectedErrorMessage = "";
        try {
            expectedErrorMessage = Files.readAllLines(
                    Paths.get(expectedCyclicInputErrorMessageFile)).get(0);
            Util.populateForest(cyclicInputFile);
        } catch(Exception e) {
            if (e instanceof CyclicTreeException) {
                assertEquals(expectedErrorMessage, e.getMessage());
            } else {
                fail();
            }
        }

    }

    @Test
    public void addChildTest() {

        FamilyForest familyForest = new FamilyForest();
        String parentName = "Domo";
        String firstChildName = "Mislav";
        String secondChildName = "Karlo";

        boolean expectedContainsParent = true;
        int expectedParentNameCount = 1;
        boolean expectedContainsFirstChild = true;
        boolean expectedContainsSecondChild = true;

        familyForest.addChild(parentName, firstChildName);
        familyForest.addChild(parentName, secondChildName);

        boolean actualContainsParent = familyForest.containsNode(parentName);
        int actualParentNameCount = familyForest.countNodesWithValue(parentName);
        boolean actualContainsFirstChild = familyForest.containsNode(firstChildName);
        boolean actualContainsSecondChild = familyForest.containsNode(secondChildName);

        assertEquals(expectedContainsParent, actualContainsParent);
        assertEquals(expectedParentNameCount, actualParentNameCount);
        assertEquals(expectedContainsFirstChild, actualContainsFirstChild);
        assertEquals(expectedContainsSecondChild, actualContainsSecondChild);

    }

    @Test
    public void containsNodeTest() {

        FamilyForest familyForest = new FamilyForest();
        String parentName = "Domo";
        String childName = "Gina";

        boolean expectedContainsParentName = true;
        boolean expectedContainsChildName = true;

        familyForest.addChild(parentName, childName);

        boolean actualContainsParentName = familyForest.containsNode(parentName);
        boolean actualContainsChildName = familyForest.containsNode(childName);

        assertEquals(expectedContainsChildName, actualContainsChildName);
        assertEquals(expectedContainsParentName, actualContainsParentName);

    }

    @Test
    public void countNodesWithValueTest() {

        FamilyForest familyForest = new FamilyForest();
        String parentName = "Domo";
        String[] children = new String[]{"Gina", "Dina", "Rona", "Anon"};

        int expectedParentNameCount = 1;

        for (String childName: children) {
            familyForest.addChild(parentName, childName);
        }

        int actualParentNameCount = familyForest.countNodesWithValue(parentName);

        assertEquals(expectedParentNameCount, actualParentNameCount);

    }

    @Test
    public void getNodeTest() {

        FamilyForest familyForest = new FamilyForest();
        String parentName = "Domo";
        String childName = "Mislav";

        familyForest.addChild(parentName, childName);

        FamilyTreeNode parentNode = familyForest.getNode(parentName);
        FamilyTreeNode childNode = familyForest.getNode(childName);

        assertNotNull(parentNode);
        assertNotNull(childNode);

        assertEquals(parentName, parentNode.getName());
        assertEquals(childName, childNode.getName());

    }

    @Test
    public void getAllNodesWithValueTest() {

        FamilyForest familyForest = new FamilyForest();
        String firstParentName = "Domo";
        String secondParentName = "Karlo";
        String childName = "Mislav";
        int expectedNumberOfChildNodes = 2;

        familyForest.addChild(firstParentName, childName);
        familyForest.addChild(secondParentName, childName);

        List<FamilyTreeNode> nodes = familyForest.getAllNodesWithValue(childName);
        int actualNumberOfChildNodes = familyForest.countNodesWithValue(childName);

        assertNotNull(nodes);
        assertEquals(expectedNumberOfChildNodes, actualNumberOfChildNodes);

    }

    @Test
    public void toStringTest() {

        FamilyForest familyForest;
        try {
            familyForest = Util.populateForest(validInputFile);

            StringBuilder sb = new StringBuilder();
            for (String line: Files.readAllLines(Paths.get(outputFileExpectedResult))) {
                sb.append(line);
                sb.append('\n');
            }

            String expectedFamilyForestString = sb.toString();
            String actualFamilyForestString = familyForest.toString();
            assertEquals(expectedFamilyForestString, actualFamilyForestString);

        } catch(InvalidInputException | IOException e) {
            System.out.println("Invalid input test file.");
        }

    }
}
