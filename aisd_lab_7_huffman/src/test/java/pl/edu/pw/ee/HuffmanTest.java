package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static pl.edu.pw.ee.HuffTree.generateHuffTreeFromString;
import static pl.edu.pw.ee.datas.Datas.path;

public class HuffmanTest {
	String a = "C:\\Study\\sem3.txt";
	String b = "C:\\Study\\sem3kod.txt";

	Huffman huffman;

	@Test
	public void niemanie_create_test() throws IOException {
		huffman = new Huffman("niemanie.txt");
		huffman.huffman(path + "niemanie", true);
		huffman.huffman(path + "niemanie", false);
		assertEquals(Files.readString(Paths.get(path + "niemanie\\" + huffman.fileToCompress)),
				Files.readString(Paths.get(path + "niemanie\\" + huffman.decompressedFile)));
	}

	@Test
	public void niemanie_refren_create_test() throws IOException {
		huffman = new Huffman("niemanie_refren.txt");
		huffman.huffman(path + "niemanie_refren", true);
		huffman.huffman(path + "niemanie_refren", false);
		assertEquals(Files.readString(Paths.get(path + "niemanie_refren\\" + huffman.fileToCompress)),
				Files.readString(Paths.get(path + "niemanie_refren\\" + huffman.decompressedFile)));
	}

	@Test
	public void one_char_only_test() throws IOException {
		huffman = new Huffman("one_char_only.txt");
		huffman.huffman(path + "one_char_only", true);
		huffman.huffman(path + "one_char_only", false);
		assertEquals(Files.readString(Paths.get(path + "one_char_only\\" + huffman.fileToCompress)),
				Files.readString(Paths.get(path + "one_char_only\\" + huffman.decompressedFile)));
	}

	@Test(expected = IllegalStateException.class)
	public void empty_file_test() throws IOException {
		huffman = new Huffman("empty_file.txt");
		huffman.huffman(path + "empty_file", true);
		huffman.huffman(path + "empty_file", false);
		assertEquals(Files.readString(Paths.get(path + "empty_file\\" + huffman.fileToCompress)),
				Files.readString(Paths.get(path + "empty_file\\" + huffman.decompressedFile)));
	}

	@Test
	public void few_chars_same_test() throws IOException {
		huffman = new Huffman("few_chars_same.txt");
		huffman.huffman(path + "few_chars_same", true);
		huffman.huffman(path + "few_chars_same", false);
		assertEquals(Files.readString(Paths.get(path + "few_chars_same\\" + huffman.fileToCompress)),
				Files.readString(Paths.get(path + "few_chars_same\\" + huffman.decompressedFile)));
	}

}
