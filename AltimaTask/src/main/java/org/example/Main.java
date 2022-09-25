package org.example;

import org.example.exceptions.CyclicTreeException;
import org.example.exceptions.InvalidInputException;
import org.example.util.FamilyForest;
import org.example.util.Util;

public class Main {
    public static void main(String[] args) {

        String inputFileName = args[0];
        String outputFileName = args[1];

        FamilyForest familyForest;

        try {
            familyForest = Util.populateForest(inputFileName);
            Util.saveFamilyForestToFile(familyForest, outputFileName);
        } catch(InvalidInputException | CyclicTreeException e) {
            throw new RuntimeException(e);
        }

        System.out.println(familyForest);

    }


}