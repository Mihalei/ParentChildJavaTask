package org.example;

import org.junit.Test;

import static org.junit.Assert.fail;

public class MainTest {

    private String validInputFile = "D:\\Projects\\HiringTasks\\AltimaTask\\ParentChildJavaTask\\AltimaTask\\src\\test\\resources\\InputFile.txt";
    private String outputFile = "D:\\Projects\\HiringTasks\\AltimaTask\\ParentChildJavaTask\\AltimaTask\\src\\test\\resources\\OutputFile.txt";

    @Test
    public void validInputMainTest() {

        try {
            Main.main(new String[]{validInputFile, outputFile});
        } catch(Exception e) {
            fail();
        }

    }

}
