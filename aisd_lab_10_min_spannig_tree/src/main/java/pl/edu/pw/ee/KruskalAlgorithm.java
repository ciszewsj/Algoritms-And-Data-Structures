package pl.edu.pw.ee;

import pl.edu.pw.ee.Heap.Heap;
import pl.edu.pw.ee.Heap.HeapInterface;
import pl.edu.pw.ee.services.MinSpanningTree;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.pw.ee.PrimAlgorithm.addText;
import static pl.edu.pw.ee.PrimAlgorithm.readFile;

public class KruskalAlgorithm implements MinSpanningTree {


	public String findMST(String pathToFile) {
		List<String> vertexMap = new ArrayList<>();
		List<PrimAlgorithmObject<String, Integer>> primAlgorithmObjectList = new ArrayList<>();
		readFile(pathToFile, vertexMap, primAlgorithmObjectList);
		HeapInterface<PrimAlgorithmObject<String, Integer>> priorityQueue = new Heap<>();
		for (PrimAlgorithmObject<String, Integer> primAlgorithmObject : primAlgorithmObjectList) {
			priorityQueue.put(primAlgorithmObject);
		}
		return findMST(vertexMap, priorityQueue);
	}

	private String findMST(List<String> vertexMap, HeapInterface<PrimAlgorithmObject<String, Integer>> priorityQueue) {
		List<PrimAlgorithmObject<String, Integer>> result = new ArrayList<>();
		PrimAlgorithmObject<String, Integer> elem = priorityQueue.pop();
		while (elem != null) {

			addToList(elem, result, vertexMap);

			try {
				elem = priorityQueue.pop();
			} catch (IllegalArgumentException e) {
				elem = null;
			}
		}
		String resultString = "";
		for (PrimAlgorithmObject<String, Integer> primAlgorithmObject : result) {
			resultString = addText(resultString, primAlgorithmObject.toString());
		}
		return resultString;
	}

	private void addToList(PrimAlgorithmObject<String, Integer> primAlgorithmObject, List<PrimAlgorithmObject<String, Integer>> result, List<String> vertexMap) {
		if (vertexMap.contains(primAlgorithmObject.getVertex2()) || vertexMap.contains(primAlgorithmObject.getVertex1())) {
			result.add(primAlgorithmObject);
			vertexMap.remove(primAlgorithmObject.getVertex1());
			vertexMap.remove(primAlgorithmObject.getVertex2());
		} else if (checkConnection(primAlgorithmObject, result)) {
			result.add(primAlgorithmObject);
		}
	}

	private boolean checkConnection(PrimAlgorithmObject<String, Integer> primAlgorithmObject, List<PrimAlgorithmObject<String, Integer>> result) {
		return checkConnection(primAlgorithmObject.getVertex1(), "", primAlgorithmObject.getVertex2(), result);
	}

	private boolean checkConnection(String s, String c, String e, List<PrimAlgorithmObject<String, Integer>> result) {
		if (s.equals(c)) {
			return false;
		} else if (c.equals(e)) {
			return true;
		}
		if (c.equals("")) {
			c = s;
		}
		for (PrimAlgorithmObject<String, Integer> primAlgorithmObject : result) {
			if (primAlgorithmObject.getVertex2().equals(c)) {
				if (checkConnection(s, primAlgorithmObject.getVertex1(), e, result)) {
					return true;
				}
			} else {
				if (checkConnection(s, primAlgorithmObject.getVertex2(), e, result)) {
					return true;
				}
			}
		}
		return false;
	}
}
