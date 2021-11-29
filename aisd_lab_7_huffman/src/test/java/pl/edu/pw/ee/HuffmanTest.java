package pl.edu.pw.ee;

import org.junit.Test;

import java.io.FileNotFoundException;

public class HuffmanTest {
	String a = "C:\\Study\\sem3.txt";
	String b = "C:\\Study\\sem3kod.txt";

	@Test
	public void test() throws FileNotFoundException {
		Huffman huffman = new Huffman();
		huffman.huffman(a, true);
	}
}
