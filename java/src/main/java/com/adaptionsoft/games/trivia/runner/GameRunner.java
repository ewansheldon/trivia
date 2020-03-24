
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;


public class GameRunner {

    private static boolean notAWinner;

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
		Game aGame = new Game();
		setWriteOutput("sample.txt");

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		int[] rands = {1, 1, 4, 5, 1, 3, 2, 5, 1, 1, 4, 6, 3, 5, 2, 0, 3, 0, 5, 7, 4, 0, 1, 1, 3, 3, 4, 0, 3, 1, 5, 8, 4, 3};

		for (int i = 0; i < rands.length; i++) {
			if (i % 2 == 0) {
				aGame.roll(rands[i]);
			} else {
				if (rands[i] == 7) {
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
