package pl.edu.pw.ee.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class ReadStringsFromFile {

	public static String path = System.getProperty("user.dir") + "\\src\\test\\java\\pl\\edu\\pw\\ee\\data\\";

	public static int[] sizes = new int[]{512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144};


	public static String[] readWordsFromFile(String fileName, int size) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(fileName));
		String[] strings = new String[size];
		for (int i = 0; i < size; i++) {
			strings[i] = sc.next();
		}
		return strings;
	}

	public static long averageValue(long[] times) {
		long value = 0;
		for (long time : times) {
			value += time;
		}
		return value / times.length;
	}

	public static void saveToFile(String fileName, List<AverageData> averageData, int firstIndex) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path + "\\results\\" + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int i = firstIndex;
		for (AverageData result : averageData) {
			i++;
			writer.println(i + "," + result.size + "," + result.averageInputTime + result.averageGetTime);
		}
		writer.close();
	}

	public static class AverageData {
		private String size;
		private String averageInputTime;
		private String averageGetTime;

		public AverageData(int size) {
			this.size = String.valueOf(size);
			this.averageInputTime = "";
			this.averageGetTime = "";
		}

		public AverageData(String columnName) {
			this.size = columnName;
			this.averageInputTime = "";
			this.averageGetTime = "";
		}

		public void addAverageInputTime(String name) {
			averageInputTime += name + ",";
		}

		public void addAverageGetTime(String name) {
			averageGetTime += name + ",";
		}

		public void addAverageInputTime(long average) {
			averageInputTime += average + ",";
		}

		public void addAverageGetTime(long average) {
			averageGetTime += average + ",";
		}

		@Override
		public String toString() {
			return size + " " + averageInputTime + " " + averageGetTime;
		}
	}

}
