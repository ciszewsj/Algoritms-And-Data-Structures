package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringAndByteOperation {

	public static final char bit0 = '0';
	public static final char bit1 = '1';
	public static final int length_byte = 8;

	public static int min_chr = 0;
	public static int max_chr = 255;

	private static final int diff_chr = 128;

	private static final String delimiter_value = "\n";

	public static String bytesToString(byte[] bytes) {
		byte read = (byte) ((int) bytes[0] - diff_chr);
		if (read <= 0 || read > length_byte) {
			throw new IllegalStateException("Problem with input file");
		}
		StringBuilder result = new StringBuilder();
		for (int j = 1; j < bytes.length; j++) {
			for (int i = 0; i < (j + 1 != bytes.length ? length_byte : read); i++) {
				result.append((bytes[j] >> (length_byte - (i + 1)) & 0x0001));
			}
		}
		return result.toString();
	}


	public static byte[] stringToBytes(String bytes) {
		byte[] result;
		if (bytes.length() % 8 == 0) {
			result = new byte[bytes.length() / length_byte];
		} else {
			result = new byte[bytes.length() / length_byte + 1];
		}
		int m = 0;
		int index = 0;
		int i;
		while (index < bytes.length()) {
			if (index + length_byte <= bytes.length()) {
				i = index + length_byte;
			} else {
				i = bytes.length();
			}
			result[m] = stringToByte(bytes.substring(index, i));
			m += 1;
			index = i;
		}
		return result;
	}

	private static byte stringToByte(String bytes) {
		if (bytes.length() > length_byte) {
			throw new IllegalArgumentException("Length of input string is longer than 8");
		}
		int result = convertStringToByte(bytes);
		return (byte) result;
	}

	public static byte[] readFile(String path) throws FileNotFoundException {
		Scanner in = new Scanner(new File(path));
		in.useDelimiter(delimiter_value);
		List<Byte> byteList = new ArrayList<>();
		while (in.hasNext()) {
			String a = in.next();
			if (in.hasNextLine()) {
				a = a + delimiter_value;
			}
			for (char c : a.toCharArray()) {
				byteList.add((byte) ((int) (c) - diff_chr));
			}
		}
		byte[] bytes = new byte[byteList.size()];
		for (int i = 0; i < byteList.size(); i++) {
			bytes[i] = byteList.get(i);
		}
		return bytes;
	}

	public static void saveFile(String path, byte[] bytes, byte lastBytesToRead) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(path);
		if (lastBytesToRead == 0) {
			lastBytesToRead = length_byte;
		}
		writer.print((char) ((int) lastBytesToRead));
		for (byte b : bytes) {
			writer.print((char) ((int) b + diff_chr));
		}
		writer.close();
	}

	public static String charToString(char c) {
		byte b = (byte) (((int) (c)) - 128);
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length_byte; i++) {
			result.append((b >> (length_byte - (i + 1)) & 0x0001));
		}
		return result.toString();
	}

	public static char stringToChar(String s) {
		int result = convertStringToByte(s);
		return (char) (result + diff_chr);
	}

	private static int convertStringToByte(String s) {
		int result = 0;
		char[] c = s.toCharArray();
		for (int i = 0; i < length_byte; i++) {
			try {
				if (c[i] == bit0) {
					result = (byte) (result * 2);
				} else if (c[i] == bit1) {
					result = (byte) (result * 2 + 1);
				} else {
					throw new IllegalArgumentException("String has char that are other than " + bit0 + " and " + bit1);
				}
			} catch (IndexOutOfBoundsException e) {
				result = (byte) (result * 2);
			}
		}
		return result;
	}

	public static void saveFile(String path, String text) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(path);
		writer.print(text);
		writer.close();
	}

	public static String validatePath(String pathToRootDir) {
		if (pathToRootDir.charAt(pathToRootDir.length() - 1) != '\\') {
			pathToRootDir += '\\';
		}
		File f = new File(pathToRootDir);
		if (f.isDirectory() && !f.isFile()) {
			return pathToRootDir;
		}
		throw new IllegalStateException("Path is not a directory or not exist : " + pathToRootDir);
	}
}
