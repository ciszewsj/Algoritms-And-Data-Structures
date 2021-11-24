package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.data.ReadStringsFromFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static pl.edu.pw.ee.data.ReadStringsFromFile.readWordsFromFile;
import static pl.edu.pw.ee.data.ReadStringsFromFile.saveToFile;

public class RbtDeepInsertTest {
	RedBlackTree<String, String> rbt;

	String[] strings;

	@Before
	public void init() throws FileNotFoundException {
		rbt = new RedBlackTree();
		strings = readWordsFromFile("test_data_words.txt");
	}

	@Test
	public void deepRbtTest() {
		List<ReadStringsFromFile.AverageData> averageDataList = new ArrayList<>();
		for (String word : strings) {
			if (word == null) {
				System.out.println(word);
			}
			averageDataList.add(new ReadStringsFromFile.AverageData(rbt.put(word, word)));
		}
		saveToFile("Result.txt", averageDataList);
	}
}
