package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class SomeTest {

    @Test
    public void golden_master_test() throws Exception {
        for (int i = 0; i < 10; i++) {
            GameRunner.mainTest();
            String golden = getStringBuilder("goldenMaster.txt").toString();
            String sample = getStringBuilder("sample.txt").toString();

            assertEquals(golden, sample);
        }
    }

    private static StringBuilder getStringBuilder(String path) throws FileNotFoundException {
        StringBuilder file = new StringBuilder();
        File goldenMaster = new File(path);
        Scanner myReader = new Scanner(goldenMaster);
        while (myReader.hasNextLine()) {
            file.append(myReader.nextLine());
        }
        myReader.close();
        return file;
    }
}
