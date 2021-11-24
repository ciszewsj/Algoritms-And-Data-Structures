package pl.edu.pw.ee.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class ReadStringsFromFile {

	public static String path = System.getProperty("user.dir") + "\\src\\test\\java\\pl\\edu\\pw\\ee\\data\\";

	public static String[] sufixs = new String[]{"jeden", "dziekan", "rektor", "klocuch", "karolak", "andrzejek", "ksienciuniu", "harnas", "barmanska", "piwo"};

	public static int size = 100000;

	public static String[] readWordsFromFile(String fileName) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(path + fileName));
		String[] strings = new String[size * sufixs.length];
		int k = 0;
		for (int i = 0; i < size; i++) {
			String read = sc.next();
			for (String suf : sufixs) {
				strings[k] = read + suf;
				k = k + 1;
			}
		}
		return strings;
	}

	public static void saveToFile(String fileName, List<AverageData> averageData) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path + "\\results\\" + fileName);
			writer.println(AverageData.Description());
			for (AverageData result : averageData) {
				writer.println(result);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static class AverageData {
		private static int i = 1;
		private final int n;
		private final int deep;

		public AverageData(int deep) {
			this.n = i;
			i += 1;
			this.deep = deep;
		}

		@Override
		public String toString() {
			return n + " " + deep;
		}

		public static String Description() {
			return "NumberOfElements  Deep";
		}
	}

}
