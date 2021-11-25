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

	public static String in_order_data_1 = "1:a 2:b 3:c 4:d 5:e 6:f 7:g 8:h 9:i 10:j";

	public static String post_order_data_1 = "4:d 2:b 1:a 3:c 8:h 6:f 5:e 7:g 10:j 9:i";

	public static String pre_order_data_1 = "1:a 3:c 2:b 5:e 7:g 6:f 9:i 10:j 8:h 4:d";


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
