package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReadStringsFromFile {

	String path;
	String[] strings;
	int[] sizes;
	HashListChaining<String> hashListChaining;

	@Before
	public void init() throws FileNotFoundException {
		path = System.getProperty("user.dir") + "\\src\\test\\java\\pl\\edu\\pw\\ee\\data\\";
		strings = ReadStringsFromFile.readWordsFromFile(path + "test_data_words.txt", 100000);
		sizes = new int[]{4096, 4096 * 2, 4096 * 3, 4096 * 4, 4096 * 5, 4096 * 6, 4096 * 7};
	}

	@Test
	public void generateTimeDatas() {
		List<ReadStringsFromFile.Data> data = new ArrayList<>();

		for (int size : sizes) {

			long[] times = new long[30];

			for (int i = 0; i < 30; i++) {
				long start = System.nanoTime();
				hashListChaining = new HashListChaining<>(size);
				for (String string : strings) {
					hashListChaining.add(string);
				}
				for (String string : strings) {
					if (hashListChaining.get(string) == null) {
						throw new RuntimeException("Not find element");
					}
				}
				long end = System.nanoTime();
				times[i] = end - start;
			}
			Arrays.sort(times);
			data.add(new ReadStringsFromFile.Data(size, ReadStringsFromFile.averageValue(Arrays.copyOfRange(times, 10, 20))));
		}
		ReadStringsFromFile.saveFile(path + "\\results\\time_results.txt", data);
	}


	public static String[] readWordsFromFile(String fileName, int size) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(fileName));
		String[] strings = new String[size];
		for (int i = 0; i < size; i++) {
			strings[i] = sc.next();
		}
		return strings;
	}

	public static void saveFile(String fileName, List<Data> data) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int i = 0;
		for (Data result : data) {
			i++;
			writer.println(i + " " + result);
		}
		writer.close();
	}

	public static long averageValue(long[] times) {
		long value = 0;
		for (long time : times) {
			value += time;
		}
		return value / times.length;
	}

	static class Data {
		private int size;
		private long averageTime;

		public Data(int size, long averageTime) {
			this.size = size;
			this.averageTime = averageTime;
		}

		@Override
		public String toString() {
			return size + " " + averageTime;
		}
	}

}
