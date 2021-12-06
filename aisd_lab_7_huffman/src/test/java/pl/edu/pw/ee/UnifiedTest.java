package pl.edu.pw.ee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pl.edu.pw.ee.StringAndByteOperation.length_byte;

public class UnifiedTest {


	public static void checkFileAfterDecompression(String pathName, String fileName) throws IOException {
		Huffman huffman = new Huffman(fileName + ".txt");
		checkFileAfterDecompressionWithoutArgument(huffman, pathName, fileName);
	}

	public static void checkFileAfterDecompressionWithoutArgument(Huffman huffman, String pathName, String fileName) throws IOException {
		int bytes_after_compression = huffman.huffman(pathName + fileName, true);
		int chars_after_decompression = huffman.huffman(pathName + fileName, false);
		assertEquals(Files.readString(Paths.get(pathName + fileName + "\\" + huffman.fileToCompress)),
				Files.readString(Paths.get(pathName + fileName + "\\" + huffman.decompressedFile)));
		assertTrue(bytes_after_compression < chars_after_decompression * length_byte);
	}
}
