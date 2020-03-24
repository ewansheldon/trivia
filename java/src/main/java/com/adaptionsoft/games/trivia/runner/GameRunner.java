
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.GameNew;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;


public class GameRunner {

    private static boolean notAWinner;
	private static ArrayList<Integer> randomNumbers;

	public static void main(String[] args) {

        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random();

        do {

			aGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }

        } while (notAWinner);

    }

    public static void mainTest() throws FileNotFoundException {
		playGoldenMasterGame();
		playTestGame();
	}

	private static void playGoldenMasterGame() throws FileNotFoundException {
		Game aGame = new Game();
		randomNumbers = new ArrayList<Integer>();
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
		setWriteOutput("sample.txt");
		GameNew aGame = new GameNew();

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
