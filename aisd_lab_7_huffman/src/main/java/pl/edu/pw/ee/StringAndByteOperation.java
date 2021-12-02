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

	public static String bytesToString(byte[] bytes, int read) {
		StringBuilder result = new StringBuilder();
		for (int j = 0; j < bytes.length; j++) {
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
		byte result = 0;
		char[] c = bytes.toCharArray();
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

	public static class ReturnObject {
		private final byte read;
		private final byte[] bytes;

		public ReturnObject(byte read, byte[] bytes) {
			this.read = read;
			this.bytes = bytes;
		}

		public byte getRead() {
			return read;
		}

		public byte[] getBytes() {
			return bytes;
		}
	}

	public static ReturnObject readFile(String path, List<LeafDescription<Character>> leaf) throws FileNotFoundException {
		Scanner in = new Scanner(new File(path));
		int nodes = in.nextInt();
		in.nextLine();
		for (int i = 0; i < nodes; i++) {
			String key = in.nextLine();
			try {
				leaf.add(new LeafDescription<>(key.toCharArray()[0], key.substring(2)));
			} catch (ArrayIndexOutOfBoundsException e) {
				leaf.add(new LeafDescription<>('\n', in.next()));
				in.nextLine();
			}
		}
		byte t = in.nextByte();
		in.nextLine();
		List<Byte> byteList = new ArrayList<>();
		while (in.hasNextLine()) {
			String a = in.nextLine();
			if (in.hasNextLine()) {
				a = a + "\n";
			}
			for (char c : a.toCharArray()) {
				byteList.add((byte) ((int) c));
			}
		}
		byte[] bytes = new byte[byteList.size()];
		for (int i = 0; i < byteList.size(); i++) {
			bytes[i] = byteList.get(i);
		}
		return new ReturnObject(t, bytes);
	}

	public static void saveFile(String path, Tree<Character> leaf, byte[] bytes, byte read) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(path);
		writer.println(leaf.nOfElementsInTree());
		for (LeafDescription<Character> c : leaf.getIndexList()) {
			writer.println(c);
		}
		writer.println(read);
		for (byte b : bytes) {
			writer.print((char) (b));
		}
		writer.close();
	}
}
