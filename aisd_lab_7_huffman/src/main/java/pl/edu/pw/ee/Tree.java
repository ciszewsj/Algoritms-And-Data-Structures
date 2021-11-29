package pl.edu.pw.ee;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Tree<T extends Comparable<T>> implements Comparable<Tree<T>> {
	private final T value;
	private int numberOfElements;

	private Tree<T> left;
	private Tree<T> right;

	public Tree(T value) {
		this.value = value;
		this.numberOfElements = 1;
	}

	public Tree(Tree<T> left, Tree<T> right) {
		value = null;
		this.numberOfElements = left.getNumberOfElements() + right.getNumberOfElements();
		this.left = left;
		this.right = right;
	}

	public void increase() {
		numberOfElements += 1;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public Map<T, String> getIndexes() {
		Map<T, String> map = new HashMap<>();
		createIndex(map, "");
		return map;
	}

	private void createIndex(Map<T, String> map, String index) {

		if (Objects.equals(index, "") && value != null) {
			map.put(value, "0");
		} else if (value != null) {
			map.put(value, index);
		} else {
			left.createIndex(map, index + "0");
			right.createIndex(map, index + "1");
		}
	}

	public T getValue() {
		return value;
	}

	@Override
	public int compareTo(Tree<T> o) {
		if (numberOfElements > o.numberOfElements) {
			return 1;
		} else if (numberOfElements < o.numberOfElements) {
			return -1;
		}
		return 0;
	}
}