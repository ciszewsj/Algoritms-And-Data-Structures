package pl.edu.pw.ee;

public class Datas {


	public static final String[] ababab_test = {"BABBAB", "ABBAABA"};

	public static final String ababab_result = "ABBAB";
	public static final String ababab_reverse_result = "ABBAB";

	public static final String[] politechnika_test = {"POLITECHNIKA", "TOALETA"};

	public static final String politechnika_result = "OLTA";
	public static final String politechnika_reverse_result = "OLEA";

	public static final String[] polish_chars_test = {"Brzęczyszczykiewicz", "Chrząszczyrzewoszyce"};

	public static final String polish_chars_result = "rzszczyewc";
	public static final String polish_chars_reverse_result = "rzszczyewz";

	public static final String[] strange_chars_test = {"\t\n\t\r\\k...l", "\t\t\n\t\\k\\l\r"};

	public static final String strange_chars_result = "\t\n\t\\kl";
	public static final String strange_chars_reverse_result = "\t\n\t\\kl";

	public static final String[] one_string_free_test = {"", "ABABABBA"};

	public static final String one_string_free_result = "";
	public static final String one_string_free_reverse_result = "";

	public static final String ababab_display_test =
			"        #      A      B      B      A      A      B      A\n" +
					"                                                          \n" +
					"\n" +
					" #      0      0      0      0      0      0      0      0\n" +
					"               ^   \\      \\                    \\          \n" +
					"\n" +
					" B      0      0      1      1   <  1   <  1      1   <  1\n" +
					"            \\         ^      ^   \\      \\             \\   \n" +
					"\n" +
					" A      0      1      1      1      2      2   <  2      2\n" +
					"               ^   \\      \\         ^      ^   \\          \n" +
					"\n" +
					" B      0      1      2      2      2      2      3   <  3\n" +
					"               ^   \\      \\                    \\         ^\n" +
					"\n" +
					" B      0      1      2      3   <  3   <  3      3      3\n" +
					"            \\         ^      ^   \\      \\             \\   \n" +
					"\n" +
					" A      0      1      2      3      4      4   <  4      4\n" +
					"               ^   \\      \\         ^      ^   \\          \n" +
					"\n" +
					" B      0      1      2      3      4      4      5   <  5\n";

	public static final String one_string_free_display_result =
			"        #      A      B      A      B      A      B      B      A\n" +
					"                                                                 \n" +
					"\n" +
					" #      0      0      0      0      0      0      0      0      0\n";

	public static final String strange_chars_display_result =
			"        #     \\t     \\t     \\n     \\t      \\      k      \\      l     \\r\n" +
					"                                                                        \n" +
					"\n" +
					" #      0      0      0      0      0      0      0      0      0      0\n" +
					"            \\      \\             \\                                      \n" +
					"\n" +
					"\\t      0      1      1   <  1      1   <  1   <  1   <  1   <  1   <  1\n" +
					"               ^      ^   \\                                             \n" +
					"\n" +
					"\\n      0      1      1      2   <  2   <  2   <  2   <  2   <  2   <  2\n" +
					"            \\      \\         ^   \\                                      \n" +
					"\n" +
					"\\t      0      1      2      2      3   <  3   <  3   <  3   <  3   <  3\n" +
					"               ^      ^      ^      ^      ^      ^      ^      ^   \\   \n" +
					"\n" +
					"\\r      0      1      2      2      3      3      3      3      3      4\n" +
					"               ^      ^      ^      ^   \\             \\                ^\n" +
					"\n" +
					" \\      0      1      2      2      3      4   <  4      4   <  4      4\n" +
					"               ^      ^      ^      ^      ^   \\                        \n" +
					"\n" +
					" k      0      1      2      2      3      4      5   <  5   <  5   <  5\n" +
					"               ^      ^      ^      ^      ^      ^      ^      ^      ^\n" +
					"\n" +
					" .      0      1      2      2      3      4      5      5      5      5\n" +
					"               ^      ^      ^      ^      ^      ^      ^      ^      ^\n" +
					"\n" +
					" .      0      1      2      2      3      4      5      5      5      5\n" +
					"               ^      ^      ^      ^      ^      ^      ^      ^      ^\n" +
					"\n" +
					" .      0      1      2      2      3      4      5      5      5      5\n" +
					"               ^      ^      ^      ^      ^      ^      ^   \\          \n" +
					"\n" +
					" l      0      1      2      2      3      4      5      5      6   <  6\n";
}
