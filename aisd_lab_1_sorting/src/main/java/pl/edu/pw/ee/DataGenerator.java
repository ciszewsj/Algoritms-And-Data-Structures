package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class DataGenerator {


	public static String path;

	public static void saveFileFor(String name, String optimisticFile, String randomFile, String unoptimisticFile, Sorting sorter) {
		try {
			int[] sizes = {2, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000};
			Map<Integer, Data> timeMap = new TreeMap<>();
			for (int size : sizes) {


				long startTime;
				long endTime;

				double[] optimisticData = readFile(path + "data_" + optimisticFile + "_" + size + ".txt");
				double[] randomData = readFile(path + "data_" + randomFile + "_" + size + ".txt");
				double[] unoptimisticData = readFile(path + "data_" + unoptimisticFile + "_" + size + ".txt");

				startTime = System.nanoTime();
				sorter.sort(optimisticData);
				endTime = System.nanoTime();
				long optimisticTime = endTime - startTime;

				startTime = System.nanoTime();
				sorter.sort(randomData);
				endTime = System.nanoTime();
				long randomTime = endTime - startTime;

				startTime = System.nanoTime();
				sorter.sort(unoptimisticData);
				endTime = System.nanoTime();
				long unoptimisticTime = endTime - startTime;

				Data data = new Data(optimisticTime, randomTime, unoptimisticTime);

				timeMap.put(size, data);
			}

			saveFile(name, timeMap);
		} catch (FileNotFoundException e) {
			System.out.println("Could not read file");
		}
	}

	public static void main(String[] args) {
		path = System.getProperty("user.dir") + "\\src\\main\\java\\pl\\edu\\pw\\ee\\dataFiles\\";
		saveFileFor("insertion_sort", "sorted", "random", "reversed_sorted", new InsertionSort());
		saveFileFor("selection_sort", "sorted", "random", "reversed_sorted", new SelectionSort());
		saveFileFor("quick_sort", "optimistic_quick_sort", "random", "sorted", new QuickSort());
		saveFileFor("heap_sort", "reversed_sorted", "random", "sorted", new HeapSort());

	}

	public static double[] readFile(String fileName) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName));
		sc.useLocale(Locale.US);
		List<Double> listOfNums = new ArrayList<>();
		while (sc.hasNextDouble()) {
			listOfNums.add(sc.nextDouble());
		}

		double[] numArray = new double[listOfNums.size()];
		for (int i = 0; i < listOfNums.size(); i++) {
			numArray[i] = listOfNums.get(i);
		}
		return numArray;
	}

	public static void saveFile(String fileName, Map<Integer, Data> map) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path + "results\\results_" + fileName + ".txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (Integer l : map.keySet()) {
			writer.println(l + " " + map.get(l));
		}
		writer.close();
	}

}
