package pl.edu.pw.ee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static pl.edu.pw.ee.HuffTree.generateHuffTreeFromString;
import static pl.edu.pw.ee.StringAndByteOperation.*;

public class Huffman {

	public String path = System.getProperty("user.dir") + "\\src\\test\\java\\pl\\edu\\pw\\ee\\datas\\";

	public String keyFile = "key.txt";

	public String compressedFile = "compressedFile.txt";

	public String decompressedFile = "decompressedFile.txt";


	public Huffman() {

	}

	public Huffman(String path) {
		this.path = path;
	}

	public Huffman(String keyFile, String compressedFile, String decompressedFile) {
		this.keyFile = keyFile;
		this.compressedFile = compressedFile;
		this.decompressedFile = decompressedFile;
	}

	public Huffman(String path, String keyFile, String compressedFile, String decompressedFile) {
		this.path = path;
		this.keyFile = keyFile;
		this.compressedFile = compressedFile;
		this.decompressedFile = decompressedFile;
	}

	public int huffman(String pathToRootDir, boolean compress) throws FileNotFoundException {

		if (compress) {
			try {
				return compress(Files.readString(Paths.get(pathToRootDir))).length();
			} catch (IOException e) {
				throw new FileNotFoundException();
			}
		}
		HuffTree huffTree;
		try {
			huffTree = generateHuffTreeFromString(bytesToString(readFile(pathToRootDir + keyFile)));
		} catch (IOException e) {
			throw new FileNotFoundException(pathToRootDir + keyFile + " file with huffTree not found");
		}
		byte[] bytes;
		try {
			bytes = readFile(pathToRootDir + compressedFile);
		} catch (IOException e) {
			throw new FileNotFoundException(pathToRootDir + compressedFile + " file with code not found");
		}
		return decompress(bytes, huffTree).length();

	}

	private String compress(String text) {
		HuffTree huffTree = buildTree(text);
		List<LeafDescription> listOfLeaf = huffTree.getLeafDescriptionList();
		StringBuilder result = new StringBuilder();
		for (char c : text.toCharArray()) {
			String code = null;
			for (LeafDescription leaf : listOfLeaf) {
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

		try {
			String huffTreeString = huffTree.treeToString();
			saveFile(path + keyFile, stringToBytes(huffTreeString), (byte) (huffTreeString.length() % 8));
			saveFile(path + compressedFile, stringToBytes(result.toString()), (byte) (result.toString().length() % 8));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	private HuffTree buildTree(String text) {
		List<HuffTree> treeList = new ArrayList<>();
		for (char c : text.toCharArray()) {
			if (c < min_chr || c > max_chr) {
				throw new IllegalStateException();
			}
			boolean isIn = false;
			for (HuffTree tree : treeList) {
				if (tree.getValue() == c) {
					tree.increase();
					isIn = true;
					break;
				}
			}
			if (!isIn) {
				treeList.add(new HuffTree(c));
			}
		}
		if (treeList.size() == 0) {
			throw new IllegalStateException("treeList size is 0");
		}
		while (treeList.size() > 1) {
			Collections.sort(treeList);
			HuffTree min1 = treeList.get(0);
			treeList.remove(min1);
			HuffTree min2 = treeList.get(0);
			treeList.remove(min2);
			treeList.add(new HuffTree(min1, min2));
		}
		return treeList.get(0);
	}

	private String decompress(byte[] bytes, HuffTree huffTree) {
		if (huffTree == null) {
			throw new IllegalStateException("huffTree");
		} else if (bytes == null) {
			throw new IllegalStateException("bytes array could not be null");
		}
		String text = bytesToString(bytes);
		StringBuilder decompressedText = new StringBuilder();
		int index = 0;
		for (int i = 0; i <= text.length(); i++) {
			String prefix = text.substring(index, i);
			try {
				decompressedText.append(huffTree.getCharByIndex(prefix));
				index = i;
			} catch (IllegalStateException ignored) {

			}
		}
		try {
			saveFile(path + decompressedFile, decompressedText.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return decompressedText.toString();
	}
}
