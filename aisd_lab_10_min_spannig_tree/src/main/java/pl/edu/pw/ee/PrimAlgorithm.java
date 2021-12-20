package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrimAlgorithm implements MinSpanningTree {

	public static final String incorrectDataError = "Incorrect data in File";

	public String findMST(String pathToFile) {
		readFile(pathToFile);
		return null;
	}

	public void readFile(String pathToFile) {
		Scanner in = null;
		try {
			in = new Scanner(new File(pathToFile));
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("File not exist");
		}
		while (in.hasNextLine()) {
			String line = in.nextLine();
			Scanner inputLine = new Scanner(line);
			String vertex1;
			String vertex2;
			int value;
			if (inputLine.hasNext()) {
				vertex1 = inputLine.next();
			} else {
				throw new IllegalArgumentException(incorrectDataError);
			}
			if (inputLine.hasNext()) {
				vertex2 = inputLine.next();
			} else {
				throw new IllegalArgumentException(incorrectDataError);
			}
			if (inputLine.hasNextInt()) {
				value = inputLine.nextInt();
			} else {
				throw new IllegalArgumentException(incorrectDataError);
			}
		}
	}
}
