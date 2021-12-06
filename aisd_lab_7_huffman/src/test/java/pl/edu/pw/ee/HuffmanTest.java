package pl.edu.pw.ee;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static pl.edu.pw.ee.UnifiedTest.checkFileAfterDecompression;
import static pl.edu.pw.ee.UnifiedTest.checkFileAfterDecompressionWithoutArgument;
import static pl.edu.pw.ee.datas.Datas.*;

public class HuffmanTest {

	Huffman huffman;

	@Test
	public void niemanie_create_test() throws IOException {
		checkFileAfterDecompression(path, niemanie);
	}

	@Test
	public void niemanie_refren_create_test() throws IOException {
		checkFileAfterDecompression(path, niemanie_refren);
	}

	@Test
	public void one_char_only_test() throws IOException {
		checkFileAfterDecompression(path, one_char_only);
	}

	@Test(expected = IllegalStateException.class)
	public void empty_file_test() throws IOException {
		checkFileAfterDecompression(path, empty_file);
	}

	@Test
	public void few_chars_same_test() throws IOException {
		checkFileAfterDecompression(path, few_chars_same);
	}

	@Test
	public void two_chars_only_test() throws IOException {
		checkFileAfterDecompression(path, two_chars_only);
	}

	@Test(expected = IllegalStateException.class)
	public void directory_not_exist_test() throws IOException {
		huffman = new Huffman(directory_not_exist);
		huffman.huffman(path + directory_not_exist, true);
	}

	@Test
	public void unargument_constructor_test() throws IOException {
		huffman = new Huffman();
		checkFileAfterDecompressionWithoutArgument(huffman, path, unargument_constructor);
	}

	@Test
	public void full_argument_constructor_test() throws IOException {
		huffman = new Huffman(full_arguments_constructor_file,
				full_arguments_constructor_compressed_file_name,
				full_arguments_constructor_uncompressed_file_name,
				full_arguments_constructor_key_file_name);
		checkFileAfterDecompressionWithoutArgument(huffman, path, full_arguments_constructor_dir);
	}

	@Test(expected = FileNotFoundException.class)
	public void file_to_compress_not_exist_test() throws IOException {
		huffman = new Huffman();
		huffman.huffman(path + file_to_compress_not_exist, true);
	}

	@Test(expected = FileNotFoundException.class)
	public void file_to_decompress_not_exist_test() throws IOException {
		huffman = new Huffman();
		huffman.huffman(path + file_to_decompressed_not_exist, false);
	}

	@Test(expected = FileNotFoundException.class)
	public void key_file_not_exist_test() throws IOException {
		huffman = new Huffman();
		huffman.huffman(path + key_file_not_exist, false);
	}

	@Test(expected = IllegalStateException.class)
	public void wrong_key_to_compressed_file_test() throws IOException {
		huffman = new Huffman();
		huffman.huffman(path + wrong_key_to_compressed_file, false);
	}

	@Test(expected = IllegalStateException.class)
	public void wrong_file_to_decompress_test() throws IOException {
		huffman = new Huffman();
		huffman.huffman(path + wrong_file_to_decompress, false);
	}

	@Test(expected = IllegalStateException.class)
	public void empty_key_file_test() throws FileNotFoundException {
		huffman = new Huffman();
		huffman.huffman(path + empty_key_file_test, false);
	}

	@Test(expected = IllegalStateException.class)
	public void empty_file_to_decompress_test() throws FileNotFoundException {
		huffman = new Huffman();
		huffman.huffman(path + empty_file_to_decompress, false);
	}
}
