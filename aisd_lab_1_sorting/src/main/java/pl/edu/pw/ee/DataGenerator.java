package pl.edu.pw.ee;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class DataGenerator {

	public static String path;
	public static void main(String[] args) {
		path = System.getProperty("user.dir") + "\\src\\main\\java\\pl\\edu\\pw\\ee\\dataFiles\\";
		try {
			String[] types = {"optimistic", "unoptimistic", "random"};
			int[] sizes = {2, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000};
			for (String type : types) {
				Map<Integer, Long> insertionSortMap = new TreeMap<>();
				Map<Integer, Long> selectionSortMap = new TreeMap<>();
				Map<Integer, Long> quickSortMap = new TreeMap<>();
				Map<Integer, Long> heapSortMap = new TreeMap<>();
				for (int size : sizes) {

					double[] data = readFile(path + "data_" + type + "_" + size + ".txt");

					long startTime;
					long endTime;

					QuickSort quickSort = new QuickSort();
					startTime = System.nanoTime();
					quickSort.sort(data);
					endTime = System.nanoTime();
					quickSortMap.put(size, endTime - startTime);

					SelectionSort selectionSort = new SelectionSort();
					startTime = System.nanoTime();
					selectionSort.sort(data);
					endTime = System.nanoTime();
					selectionSortMap.put(size, endTime - startTime);

					InsertionSort insertionSort = new InsertionSort();
					startTime = System.nanoTime();
					insertionSort.sort(data);
					endTime = System.nanoTime();
					insertionSortMap.put(size, endTime - startTime);

					HeapSort heapSort = new HeapSort();
					startTime = System.nanoTime();
					heapSort.sort(data);
					endTime = System.nanoTime();
					heapSortMap.put(size, endTime - startTime);

				}
				saveFile(path + "results//insertion_sort_" + type + ".txt", insertionSortMap);
				saveFile(path + "results//selection_sort_" + type + ".txt", selectionSortMap);
				saveFile(path + "results//quick_sort_" + type + ".txt", quickSortMap);
				saveFile(path + "results//heap_sort_" + type + ".txt", heapSortMap);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read file");
		}
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

	public static void saveFile(String fileName, Map<Integer, Long> map) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
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
