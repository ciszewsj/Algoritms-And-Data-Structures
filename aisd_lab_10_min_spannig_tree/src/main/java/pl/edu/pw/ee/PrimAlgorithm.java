package pl.edu.pw.ee;


import pl.edu.pw.ee.Heap.Heap;
import pl.edu.pw.ee.Heap.HeapInterface;
import pl.edu.pw.ee.services.MinSpanningTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimAlgorithm implements MinSpanningTree {

	private static final String incorrectDataError = "Incorrect data in File";

	private List<String> vertexMap;
	private List<PrimAlgorithmObject<String, Integer>> primAlgorithmObjectList;


	public String findMST(String pathToFile) {
		vertexMap = new ArrayList<>();
		primAlgorithmObjectList = new ArrayList<>();
		readFile(pathToFile);
		return findMST();
	}

	private String findMST() {
		HeapInterface<PrimAlgorithmObject<String, Integer>> priorityQueue = new Heap<>();

		StringBuilder result = new StringBuilder();

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
						result.append(primAlgorithmObject2);
						break;
					} else if (vertexMap.contains(primAlgorithmObject2.getVertex2())) {
						currentVertex = primAlgorithmObject2.getVertex2();
						vertexMap.remove(currentVertex);
						result.append(primAlgorithmObject2);
						break;
					}
				} catch (IllegalArgumentException e) {
					throw new IllegalStateException("Could not find MST");
				}
			}
		}

		return result.toString();
	}


	private void readFile(String pathToFile) {
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
			addValueToVertexMapIfNotIn(vertex1);
			addValueToVertexMapIfNotIn(vertex2);
			PrimAlgorithmObject<String, Integer> primAlgorithmObject = new PrimAlgorithmObject<>(vertex1, vertex2, value);
			if (primAlgorithmObjectList.contains(primAlgorithmObject)) {
				throw new IllegalStateException(incorrectDataError + " : Data line in file are doubled");
			}
			primAlgorithmObjectList.add(primAlgorithmObject);

		}
	}

	private void addValueToVertexMapIfNotIn(String vertex) {
		if (!vertexMap.contains(vertex)) {
			vertexMap.add(vertex);
		}
	}
}
