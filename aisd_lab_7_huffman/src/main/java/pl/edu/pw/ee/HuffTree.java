package pl.edu.pw.ee;

import java.util.*;

import static pl.edu.pw.ee.StringAndByteOperation.*;

public class HuffTree implements Comparable<HuffTree> {
	private char value;
	private int numberOfElements;
	private boolean isLeaf;
	private HuffTree left;
	private HuffTree right;


	public HuffTree(char value) {
		this.value = value;
		this.isLeaf = true;
		this.numberOfElements = 1;
	}

	public HuffTree(HuffTree left, HuffTree right) {
		this.value = 0;
		this.isLeaf = false;
		this.numberOfElements = left.getNumberOfElements() + right.getNumberOfElements();
		this.left = left;
		this.right = right;
	}

	private HuffTree() {
		this.value = 0;
		this.isLeaf = false;
		this.numberOfElements = 0;
	}

	public void increase() {
		numberOfElements += 1;
	}

	public String treeToString() {
		String generatedTreeString = generate();
		if (generatedTreeString.charAt(0) == bit1) {
			generatedTreeString = bit0 + generatedTreeString;
		}
		return generatedTreeString;
	}

	private String generate() {
		if (isLeaf) {
			return bit1 + charToString(value);
		}
		if (right == null) {
			return bit0 + left.generate();
		} else {
			return bit0 + left.generate() + right.generate();
		}
	}

	public List<LeafDescription> getLeafDescriptionList() {
		List<LeafDescription> descriptionList = new ArrayList<>();
		generateLeafDescription(descriptionList, "");
		return descriptionList;
	}

	private void generateLeafDescription(List<LeafDescription> descriptionList, String prefix) {
		if (isLeaf && Objects.equals(prefix, "")) {
			descriptionList.add(new LeafDescription(value, String.valueOf(bit0)));
		} else if (!isLeaf) {
			left.generateLeafDescription(descriptionList, prefix + bit0);
			right.generateLeafDescription(descriptionList, prefix + bit1);
		} else {
			descriptionList.add(new LeafDescription(value, prefix));
		}
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public char getCharByIndex(String index) {
		return charByIndex(index);
	}

	private char charByIndex(String index) {
		String prefix;
		try {
			prefix = index.substring(0, 1);
		} catch (IndexOutOfBoundsException e) {
			prefix = "";
		}
		if (Objects.equals(index, "") && isLeaf) {
			return value;
		} else if (prefix.equals(String.valueOf(bit0))) {
			if (left != null) {
				return left.charByIndex(index.substring(1));
			}
			throw new IllegalStateException("Not char in tree");
		} else if (prefix.equals(String.valueOf(bit1))) {
			if (right != null) {
				return right.charByIndex(index.substring(1));
			}
			throw new IllegalStateException("Not char in tree");
		}
		throw new IllegalStateException("Not char in tree");
	}

	public char getValue() {
		return value;
	}

	@Override
	public int compareTo(HuffTree o) {
		if (numberOfElements > o.numberOfElements) {
			return 1;
		} else if (numberOfElements < o.numberOfElements) {
			return -1;
		}
		return 0;
	}

	private String generateHuff(String huffInfo) {
		if (Objects.equals(huffInfo, "")) {
			return huffInfo;
		}
		char operation = huffInfo.charAt(0);
		huffInfo = huffInfo.substring(1);
		if (operation == bit0) {
			left = new HuffTree();
			huffInfo = left.generateHuff(huffInfo);
		} else if (operation == bit1) {
			isLeaf = true;
			value = stringToChar(huffInfo.substring(0, 8));
			huffInfo = huffInfo.substring(8);
			return huffInfo;
		} else {
			throw new IllegalStateException();
		}
		right = new HuffTree();
		return right.generateHuff(huffInfo);

	}

	public static HuffTree generateHuffTreeFromString(String huffInfo) {
		HuffTree newHuffTree = new HuffTree();
		try {
			String returnString = newHuffTree.generateHuff(huffInfo);
			if (returnString.length() > 0) {
				throw new IllegalArgumentException("Wrong huffInfoFile");
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Wrong huffInfoFile");
		}
		return newHuffTree;
	}
}