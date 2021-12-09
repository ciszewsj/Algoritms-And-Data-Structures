package pl.edu.pw.ee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.*;

import static pl.edu.pw.ee.HuffTree.generateHuffTreeFromString;
import static pl.edu.pw.ee.StringAndByteOperation.*;

public class Huffman {

	public String fileToCompress = "fileToCompress.txt";

	public String keyFile = "key.txt";

	public String compressedFile = "compressedFile.txt";

	public String decompressedFile = "decompressedFile.txt";


	public Huffman() {

	}

	public Huffman(String fileToCompress) {
		this.fileToCompress = fileToCompress;
	}


	public Huffman(String fileToCompress, String keyFile, String compressedFile, String decompressedFile) {
		this.fileToCompress = fileToCompress;
		this.keyFile = keyFile;
		this.compressedFile = compressedFile;
		this.decompressedFile = decompressedFile;
	}

	public int huffman(String pathToRootDir, boolean compress) throws FileNotFoundException {

		pathToRootDir = validatePath(pathToRootDir);

		if (compress) {
			try {
				return compress(pathToRootDir, Files.readString(Paths.get(pathToRootDir + fileToCompress))).length();
			} catch (InvalidPathException | IOException e) {
				throw new FileNotFoundException("Could not find file : " + pathToRootDir + fileToCompress);
			}
		}
		HuffTree huffTree;
		try {
			huffTree = generateHuffTreeFromString(bytesToString(readFile(pathToRootDir + keyFile)));
		} catch (InvalidPathException | IOException e) {
			throw new FileNotFoundException(pathToRootDir + keyFile + " file with huffTree not found");
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalStateException("File is empty");
		}
		byte[] bytes;
		try {
			bytes = readFile(pathToRootDir + compressedFile);
		} catch (InvalidPathException | IOException e) {
			throw new FileNotFoundException(pathToRootDir + compressedFile + " file with code not found");
		}
		return decompress(pathToRootDir, bytes, huffTree).length();

	}

	private String compress(String path, String text) {
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
		} catch (IOException e) {
			throw new IllegalStateException("Could not save file");
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

	private String decompress(String path, byte[] bytes, HuffTree huffTree) {
		if (huffTree == null) {
			throw new IllegalStateException("huffTree is null");
		} else if (bytes == null) {
			throw new IllegalStateException("bytes array could not be null");
		}
		String text;
		try {
			text = bytesToString(bytes);
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalStateException("File is empty");
		}
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
		if (index != text.length()) {
			throw new IllegalStateException("Could not decompressed File");
		}
		try {
			saveFile(path + decompressedFile, decompressedText.toString());
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("Could not save file");
		}
		return decompressedText.toString();
	}
}
