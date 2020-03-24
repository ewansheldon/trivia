package com.adaptionsoft.games.trivia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.GameNew;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class SomeTest {
    private static boolean notAWinner;

    @Test
    public void golden_master_test() throws Exception {
        for (int i = 0; i < 10; i++) {
            mainTest(new Random().nextInt());
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

    public static void mainTest(int seed) throws FileNotFoundException {
        playGoldenMasterGame(seed);
        playTestGame(seed);
    }

    private static void playGoldenMasterGame(int seed) throws FileNotFoundException {
        Game aGame = new Game();
        setWriteOutput("goldenMaster.txt");

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random(seed);
        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }

        } while (notAWinner);
    }

    private static void playTestGame(int seed) throws FileNotFoundException {
        GameNew aGame = new GameNew();

        setWriteOutput("sample.txt");
        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random(seed);
        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }

        } while (notAWinner);
    }


    private static void setWriteOutput(String path) throws FileNotFoundException {
        File file = new File(path);
        PrintStream stream = new PrintStream(file);
        System.setOut(stream);
    }
}
