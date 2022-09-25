package org.example.util;

import org.example.exceptions.InvalidInputException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    private String validInputFile = "D:\\Projects\\HiringTasks\\AltimaTask\\ParentChildJavaTask\\AltimaTask\\src\\test\\resources\\InputFile.txt";
    private String outputFile = "D:\\Projects\\HiringTasks\\AltimaTask\\ParentChildJavaTask\\AltimaTask\\src\\test\\resources\\OutputFile.txt";

    @Test
    public void populateForestTest() {

        FamilyForest familyForest;
        try {
            familyForest = Util.populateForest(validInputFile);
            assertNotNull(familyForest);
            assertTrue(familyForest.root.hasChildren());
        } catch(InvalidInputException e) {
            System.out.println("Invalid input test file.");
        }

    }

    @Test
    public void saveFamilyForestToFileTest() {

        FamilyForest familyForest = new FamilyForest();
        String parentName = "Domo";
        String firstChildName = "Mislav";
        String secondChildName = "Karlo";
        int expectedSaveStatus = 0;

        familyForest.addChild(parentName, firstChildName);
        familyForest.addChild(parentName, secondChildName);

        try {
            int actualSaveStatus = Util.saveFamilyForestToFile(familyForest, outputFile);
            assertEquals(expectedSaveStatus, actualSaveStatus);
        } catch (Exception e) {
            fail();
        };

    }

}
