package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.data.ReadStringsFromFile;
import pl.edu.pw.ee.services.HashTable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.edu.pw.ee.data.ReadStringsFromFile.*;

public class HashLinearProbingSpeedTest {

	HashTable<String> hash;
	String[] strings;

	@Before
	public void init() throws FileNotFoundException {
		strings = ReadStringsFromFile.readWordsFromFile(path + "test_data_words.txt", 100000);
	}

	@Test
	public void test() {
		List<ReadStringsFromFile.AverageData> data = new ArrayList<>();
		{
			AverageData averageDataTitle = new AverageData("Początkowy rozmiar hasza");
			averageDataTitle.addAverageInputTime("Średni czas wstawiania 100000 elementów");
			averageDataTitle.addAverageInputTime("Średni czas wyszukania 100000 elementów");
			data.add(averageDataTitle);
		}
		for (int size : sizes) {
			long[] putTimes = new long[30];
			long[] getTimes = new long[30];
			for (int i = 0; i < 30; i++) {
				hash = new HashLinearProbing<>(size);
				{
					long startPut = System.nanoTime();
					for (String string : strings) {
						hash.put(string);
					}
					long endPut = System.nanoTime();
					putTimes[i] = endPut - startPut;
				}
				{
					long startGet = System.nanoTime();
					for (String string : strings) {
						hash.get(string);
					}
					long endGet = System.nanoTime();
					getTimes[i] = endGet - startGet;
				}
			}
			Arrays.sort(putTimes);
			Arrays.sort(getTimes);
			ReadStringsFromFile.AverageData averageData = new ReadStringsFromFile.AverageData(size);
			averageData.addAverageInputTime(ReadStringsFromFile.averageValue(Arrays.copyOfRange(putTimes, 10, 20)));
			averageData.addAverageGetTime(ReadStringsFromFile.averageValue(Arrays.copyOfRange(getTimes, 10, 20)));
			data.add(averageData);
		}

		saveToFile("HashLinearProbingSpeed.txt", data, -1);
	}
}
