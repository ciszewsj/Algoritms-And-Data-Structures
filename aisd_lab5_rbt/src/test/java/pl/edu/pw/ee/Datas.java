package pl.edu.pw.ee;

import java.util.List;

public class Datas {
	public static class KeyIntValueString {
		public int key;
		public String value;

		public KeyIntValueString(int key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	public static KeyIntValueString not_in_data_1 = new KeyIntValueString(-100, "fdd");

	public static KeyIntValueString[] data_1 = {
			new KeyIntValueString(1, "a"),
			new KeyIntValueString(2, "b"),
			new KeyIntValueString(3, "c"),
			new KeyIntValueString(4, "d"),
			new KeyIntValueString(5, "e"),
			new KeyIntValueString(6, "f"),
			new KeyIntValueString(7, "g"),
			new KeyIntValueString(8, "h"),
			new KeyIntValueString(9, "i"),
			new KeyIntValueString(10, "j")
	};

	public static KeyIntValueString[] data_with_null = {
			new KeyIntValueString(1, "a"),
			new KeyIntValueString(100, null),
			new KeyIntValueString(2, "b"),
			new KeyIntValueString(3, "c"),
			new KeyIntValueString(4, "d"),
			new KeyIntValueString(5, "e"),
			new KeyIntValueString(6, "f"),
			new KeyIntValueString(7, "g"),
			new KeyIntValueString(8, "h"),
			new KeyIntValueString(9, "i"),
			new KeyIntValueString(10, "j")
	};
}
