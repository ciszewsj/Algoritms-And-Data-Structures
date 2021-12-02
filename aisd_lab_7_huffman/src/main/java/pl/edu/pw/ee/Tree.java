package pl.edu.pw.ee;

import java.util.*;

import static pl.edu.pw.ee.StringAndByteOperation.bit0;
import static pl.edu.pw.ee.StringAndByteOperation.bit1;

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

	private Tree() {
		value = null;
		this.numberOfElements = 0;
	}

	public Tree(List<LeafDescription<T>> treeList) {
		value = null;
		for (LeafDescription<T> leafDescription : treeList) {
			insertTree(leafDescription.getPrefix(), leafDescription.getValue());
		}
	}

	private void insertTree(String prefix, T value) {
		String newPrefix = prefix.substring(1);
		char side = prefix.toCharArray()[0];
		if (value != null) {
			if (side == '0') {
				if (left == null && newPrefix.length() == 0) {
					left = new Tree<>();
					left.insertTree(newPrefix, value);
				} else if (left == null) {
					left = new Tree<>(value);
				} else if (newPrefix.length() == 0) {
					throw new IllegalStateException("Prefixes are wrong");
				}
			} else if (side == bit1) {
				if (right == null && newPrefix.length() == 0) {
					right = new Tree<>();
					right.insertTree(newPrefix, value);
				} else if (right == null) {
					right = new Tree<>(value);
				} else if (newPrefix.length() == 0) {
					throw new IllegalStateException("Prefixes are wrong");
				}
			} else {
				throw new IllegalStateException("Prefixes are wrong");
			}
		} else {
			throw new IllegalStateException("Prefixes are wrong");
		}
	}


	public void increase() {
		numberOfElements += 1;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public T getCharByIndex(String index) {
		return charByIndex(index);
	}

	private T charByIndex(String index) {
		if (Objects.equals(index, "") || index == null) {
			return value;
		} else if (index.substring(0, 1).equals(String.valueOf(bit0))) {
			if (left != null) {
				return left.charByIndex(index.substring(1));
			}
			return null;
		} else if (index.substring(0, 1).equals(String.valueOf(bit1))) {
			if (right != null) {
				return right.charByIndex(index.substring(1));
			}
			return null;
		}
		return null;
	}

	public List<LeafDescription<T>> getIndexList() {
		List<LeafDescription<T>> listOfIndexes = new ArrayList<>();
		createIndexList(listOfIndexes, "");
		return listOfIndexes;
	}

	private void createIndexList(List<LeafDescription<T>> listOfIndexes, String index) {
		if (index.equals("") && value != null) {
			listOfIndexes.add(new LeafDescription<>(value, index + bit0));
		} else if (value != null) {
			listOfIndexes.add(new LeafDescription<>(value, index));
		} else {
			left.createIndexList(listOfIndexes, index + bit0);
			right.createIndexList(listOfIndexes, index + bit1);
		}
	}

	public T getValue() {
		return value;
	}

	public int nOfElementsInTree() {
		int n = 0;
		if (value != null) {
			return 1;
		} else {
			n += left.nOfElementsInTree();
			n += right.nOfElementsInTree();
		}
		return n;
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