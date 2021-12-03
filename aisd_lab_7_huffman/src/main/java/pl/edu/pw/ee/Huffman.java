package pl.edu.pw.ee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static pl.edu.pw.ee.StringAndByteOperation.*;

public class Huffman {

	public static String path = System.getProperty("user.dir") + "\\src\\test\\java\\pl\\edu\\pw\\ee\\datas\\wynik.txt";

	public int huffman(String pathToRootDir, boolean compress) throws FileNotFoundException {

		if (compress) {
			try {
				return compress(Files.readString(Paths.get(pathToRootDir))).length();
			} catch (IOException e) {
				throw new FileNotFoundException();
			}
		}
		try {
			List<LeafDescription<Character>> leafDescriptionList = new ArrayList<>();
			StringAndByteOperation.ReturnObject returnObject = readFile(path, leafDescriptionList);
			Tree<Character> huffTree = new Tree<>(leafDescriptionList);
			byte[] bytes = returnObject.getBytes();
			return decompress(bytes, returnObject.getRead(), huffTree).length();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
	}

	private String compress(String text) {
		Tree<Character> huffTree = buildTree(text);
		List<LeafDescription<Character>> listOfLeaf = huffTree.getIndexList();
		StringBuilder result = new StringBuilder();
		for (char c : text.toCharArray()) {
			String code = null;
			for (LeafDescription<Character> leaf : listOfLeaf) {
				if (leaf.getValue() == c) {
					code = leaf.getPrefix();
					break;
				}
			}
			if (code == null) {
				throw new IllegalStateException("Element not in generated map");
			}
			result.append(code);
		}
		byte[] bytes = stringToBytes(result.toString());
		try {
			saveFile(System.getProperty("user.dir") + "\\src\\test\\java\\pl\\edu\\pw\\ee\\datas\\wynik.txt", huffTree, bytes, (byte) (result.toString().length() % length_byte));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	private Tree<Character> buildTree(String text) {
		List<Tree<Character>> treeList = new ArrayList<>();
		for (char c : text.toCharArray()) {
			boolean isIn = false;
			for (Tree<Character> tree : treeList) {
				if (tree.getValue() == null) {
					throw new IllegalStateException("Character Could not be null");
				} else if (tree.getValue().equals(c)) {
					tree.increase();
					isIn = true;
					break;
				}
			}
			if (!isIn) {
				treeList.add(new Tree<>(c));
			}
		}
		if (treeList.size() == 0) {
			throw new IllegalStateException("treeList size is 0");
		}
		while (treeList.size() > 1) {
			Collections.sort(treeList);
			Tree<Character> min1 = treeList.get(0);
			treeList.remove(min1);
			Tree<Character> min2 = treeList.get(0);
			treeList.remove(min2);
			treeList.add(new Tree<>(min1, min2));
		}
		return treeList.get(0);
	}

	public String decompress(byte[] bytes, byte read, Tree<Character> huffTree) {
		if (huffTree == null) {
			throw new IllegalStateException("Character map is not init already");
		}
		String text = bytesToString(bytes, read);
		StringBuilder decompressedText = new StringBuilder();
		int index = 0;
		for (int i = 0; i <= text.length(); i++) {
			String prefix = text.substring(index, i);
			if (huffTree.getCharByIndex(prefix) != null) {
				decompressedText.append(huffTree.getCharByIndex(prefix));
				index = i;
			}
		}
		return decompressedText.toString();
	}
}
