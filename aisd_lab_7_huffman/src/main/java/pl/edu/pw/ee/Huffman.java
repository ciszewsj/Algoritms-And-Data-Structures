package pl.edu.pw.ee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Huffman {

	private Map<String, Character> characterMap;

	public int huffman(String pathToRootDir, boolean compress) throws FileNotFoundException {
		String text;
		try {
			text = Files.readString(Paths.get(pathToRootDir));
		} catch (IOException e) {
			throw new FileNotFoundException();
		}
		if (compress) {
			return compress(text).length();
		}
		return decompress(text).length();
	}

	private String compress(String text) {

		Map<Character, String> characterMap = buildMap(text);
		StringBuilder result = new StringBuilder();
		for (char c : text.toCharArray()) {
			String code = characterMap.get(c);
			if (code == null) {
				throw new IllegalStateException("Element not in generated map");
			}
			result.append(characterMap.get(c));
		}
		saveInvertedMap(characterMap);
		return result.toString();
	}

	private Map<Character, String> buildMap(String text) {
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
		return treeList.get(0).getIndexes();
	}

	private void saveInvertedMap(Map<Character, String> oldMap) {
		this.characterMap = new HashMap<>();
		for (Character key : oldMap.keySet()) {
			this.characterMap.put(oldMap.get(key), key);
		}

	}

	private String decompress(String text) {
		if (characterMap == null) {
			throw new IllegalStateException("Character map is not init already");
		}
		StringBuilder decompressedText = new StringBuilder();

		int index = 0;
		for (int i = 0; i < text.length(); i++) {
			String prefix = text.substring(index, i);
			if (characterMap.get(prefix) != null) {
				decompressedText.append(characterMap.get(prefix));
				index = i;
			}
		}

		return decompressedText.toString();
	}

}
