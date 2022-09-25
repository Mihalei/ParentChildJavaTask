package org.example.util;

import org.example.exceptions.InvalidInputException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Util {

    public static FamilyForest populateForest(String fileName) throws InvalidInputException {

        List<String> childParentLines;
        try {
            childParentLines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Specified file with file name '%s' was not found.", fileName),
                    e);
        }

        FamilyForest familyForest = new FamilyForest();
        for (String childParentString: childParentLines) {

            String[] childAndParent = splitChildParentString(childParentString);
            String child = childAndParent[0];
            String parent = childAndParent[1];

            familyForest.addChild(parent, child);
        }

        return familyForest;
    }

    // returns 0 if save was successful, and 1 when error occurs
    public static int saveFamilyForestToFile(FamilyForest familyForest, String fileName) {

        try (FileWriter fw = new FileWriter(fileName)){
            fw.write(familyForest.toString());
        } catch (IOException e) {
            System.out.println(
                    String.format("An error occurred while writing to a specified file: %s.", fileName));
            return 1;
        }

        return 0;
    }

    private static String[] splitChildParentString(String childParentString) throws InvalidInputException {

        String[] childAndParent = childParentString.split(" ");
        if(childAndParent.length != 2) {
            throw new InvalidInputException(
                    "Each line in input file should have two strings (names of child and its parent).");
        }

        return childAndParent;
    }

}
