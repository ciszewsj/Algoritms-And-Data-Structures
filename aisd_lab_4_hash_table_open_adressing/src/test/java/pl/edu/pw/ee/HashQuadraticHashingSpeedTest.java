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

public class HashQuadraticHashingSpeedTest {
	HashTable<String> hash;
	String[] strings;
	PomAandB[] pomAandBList;

	@Before
	public void init() throws FileNotFoundException {
		strings = ReadStringsFromFile.readWordsFromFile(path + "test_data_words.txt", 100000);
		pomAandBList = new PomAandB[]{
				new PomAandB(1, 1),
				new PomAandB(-100, -2),
				new PomAandB(12, 3),
				new PomAandB(666, 666),
				new PomAandB(69, 69),
				new PomAandB(37, 21),
				new PomAandB(21, 37),
				new PomAandB(9, 8),
				new PomAandB(77, 119),
				new PomAandB(12, 10)};
	}

	@Test
	public void test() {
		List<AverageData> data = new ArrayList<>();
		{
			AverageData averageDataTitle = new AverageData("Początkowy rozmiar hasza");
			for (int i = 0; i < pomAandBList.length; i++) {
				averageDataTitle.addAverageInputTime("Średni czas wstawiania 100000 elementów");
			}
			for (int i = 0; i < pomAandBList.length; i++) {
				averageDataTitle.addAverageInputTime("Średni czas wyszukania 100000 elementów");
			}
			data.add(averageDataTitle);

			AverageData averageDataAandBtitle = new AverageData("");
			for (PomAandB pomAandB : pomAandBList) {
				averageDataAandBtitle.addAverageInputTime("a = " + pomAandB.a + "; b = " + pomAandB.b);
			}
			for (PomAandB pomAandB : pomAandBList) {
				averageDataAandBtitle.addAverageGetTime("a = " + pomAandB.a + "; b = " + pomAandB.b);
			}
			data.add(averageDataAandBtitle);
		}
		for (
				int size : sizes) {
			long[] putTimes = new long[30];
			long[] getTimes = new long[30];
			AverageData averageData = new AverageData(size);
			for (PomAandB pomAandB : pomAandBList) {
				for (int i = 0; i < 30; i++) {
					hash = new HashQuadraticProbing<>(size, pomAandB.a, pomAandB.b);
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
				averageData.addAverageInputTime(ReadStringsFromFile.averageValue(Arrays.copyOfRange(putTimes, 10, 20)));
				averageData.addAverageGetTime(ReadStringsFromFile.averageValue(Arrays.copyOfRange(getTimes, 10, 20)));
			}
			data.add(averageData);
		}

		saveToFile("HashQuadraticHashingSpeed.txt", data, -2);
	}

	class PomAandB {
		public double a;
		public double b;

		public PomAandB(double a, double b) {
			this.a = a;
			this.b = b;
		}
	}
}
