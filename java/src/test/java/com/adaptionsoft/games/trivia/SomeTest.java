package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SomeTest {

	@Test
	public void golden_master_test() throws Exception {
		String golden = getStringBuilder("goldenMaster.txt").toString();
		GameRunner.mainTest();
		String sample = getStringBuilder("sample.txt").toString();

		assertEquals(golden, sample);
	}

	private static StringBuilder getStringBuilder(String path) throws FileNotFoundException {
		StringBuilder file = new StringBuilder();
		File goldenMaster = new File(path);
		Scanner myReader = new Scanner(goldenMaster);
		while (myReader.hasNextLine()) {
			file.append(myReader.nextLine());
		}
		System.out.println(file.toString());
		myReader.close();
		return file;
	}
}
