package com.adaptionsoft.games.trivia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.GameNew;
import org.junit.jupiter.api.Test;

public class SomeTest {
    private static ArrayList<Integer> randomNumbers;
    private static boolean notAWinner;

    @Test
    public void golden_master_test() throws Exception {
        for (int i = 0; i < 10; i++) {
            mainTest(i);
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

    public static void mainTest(int i) throws FileNotFoundException {
        playGoldenMasterGame();
        playTestGame();
    }

    private static void playGoldenMasterGame() throws FileNotFoundException {
        Game aGame = new Game();
        randomNumbers = new ArrayList<>();
        setWriteOutput("goldenMaster.txt");

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random();
        do {
            int roll = rand.nextInt(5) + 1;
            aGame.roll(roll);

            int correctNumber = rand.nextInt(9);
            if (correctNumber == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
            captureNumbers(roll, correctNumber);
        } while (notAWinner);
    }

    private static void captureNumbers(int roll, int correctNumber) {
        randomNumbers.add(roll);
        randomNumbers.add(correctNumber);
    }

    private static void playTestGame() throws FileNotFoundException {
        GameNew aGame = new GameNew();

        setWriteOutput("sample.txt");
        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        for (int i = 0; i < randomNumbers.size(); i++) {
            if (i % 2 == 0) {
                aGame.roll(randomNumbers.get(i));
            } else {
                if (randomNumbers.get(i) == 7) {
                    notAWinner = aGame.wrongAnswer();
                } else {
                    notAWinner = aGame.wasCorrectlyAnswered();
                }
            }
        }
    }


    private static void setWriteOutput(String path) throws FileNotFoundException {
        File file = new File(path);
        PrintStream stream = new PrintStream(file);
        System.setOut(stream);
    }
}
