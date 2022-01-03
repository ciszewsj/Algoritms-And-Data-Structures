package pl.edu.pw.ee;


import pl.edu.pw.ee.Heap.Heap;
import pl.edu.pw.ee.Heap.HeapInterface;
import pl.edu.pw.ee.services.MinSpanningTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static pl.edu.pw.ee.PrimAlgorithmObject.lineSeparator;

public class PrimAlgorithm implements MinSpanningTree {

	private static final String incorrectDataError = "Incorrect data in File";

	public String findMST(String pathToFile) {
		List<String> vertexMap = new ArrayList<>();
		List<PrimAlgorithmObject<String, Integer>> primAlgorithmObjectList = new ArrayList<>();
		readFile(pathToFile, vertexMap, primAlgorithmObjectList);
		return findMST(vertexMap, primAlgorithmObjectList);
	}

	private String findMST(List<String> vertexMap, List<PrimAlgorithmObject<String, Integer>> primAlgorithmObjectList) {
		HeapInterface<PrimAlgorithmObject<String, Integer>> priorityQueue = new Heap<>();

		String result = "";

		String currentVertex = vertexMap.get(0);
		vertexMap.remove(currentVertex);

		while (vertexMap.size() > 0) {
			List<PrimAlgorithmObject<String, Integer>> primAlgorithmObjectListClone = new ArrayList<>(primAlgorithmObjectList);
			for (PrimAlgorithmObject<String, Integer> primAlgorithmObject : primAlgorithmObjectListClone) {
				if (primAlgorithmObject.contain(currentVertex)) {
					priorityQueue.put(primAlgorithmObject);
					primAlgorithmObjectList.remove(primAlgorithmObject);
				}
			}
			while (true) {
				try {
					PrimAlgorithmObject<String, Integer> primAlgorithmObject2 = priorityQueue.pop();
					if (vertexMap.contains(primAlgorithmObject2.getVertex1())) {
						currentVertex = primAlgorithmObject2.getVertex1();
						vertexMap.remove(currentVertex);
						result = addText(result, primAlgorithmObject2.toString());
						break;
					} else if (vertexMap.contains(primAlgorithmObject2.getVertex2())) {
						currentVertex = primAlgorithmObject2.getVertex2();
						vertexMap.remove(currentVertex);
						result = addText(result, primAlgorithmObject2.toString());
						break;
					}
				} catch (IllegalArgumentException e) {
					throw new IllegalStateException("Could not find MST");
				}
			}
		}

		return result;
	}


	private void readFile(String pathToFile, List<String> vertexMap, List<PrimAlgorithmObject<String, Integer>> primAlgorithmObjectList) {
		Scanner in;
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
			if (inputLine.hasNext()) {
				throw new IllegalArgumentException(incorrectDataError);
			}
			if (value < 0) {
				throw new IllegalArgumentException(incorrectDataError + " : Value could not be less than 0");
			}
			if (vertex1.equals(vertex2)){
				throw new IllegalArgumentException(incorrectDataError + " : Path between the same vertex is illegal");
			}
			addValueToVertexMapIfNotIn(vertex1, vertexMap);
			addValueToVertexMapIfNotIn(vertex2, vertexMap);
			PrimAlgorithmObject<String, Integer> primAlgorithmObject = new PrimAlgorithmObject<>(vertex1, vertex2, value);
			primAlgorithmObjectList.add(primAlgorithmObject);
		}
		if (vertexMap.size() == 0) {
			throw new IllegalArgumentException(incorrectDataError + " : Not find any vertex in file");
		}
	}

	private void addValueToVertexMapIfNotIn(String vertex, List<String> vertexMap) {
		if (!vertexMap.contains(vertex)) {
			vertexMap.add(vertex);
		}
	}

	private static String addText(String first, String second) {
		if (first == null || first.equals("")) {
			return second;
		}
		return first + lineSeparator + second;
	}
}
