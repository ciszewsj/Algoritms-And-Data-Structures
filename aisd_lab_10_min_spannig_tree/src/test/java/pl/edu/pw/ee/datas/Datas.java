package pl.edu.pw.ee.datas;

public class Datas {
	private static final String path = System.getProperty("user.dir") + "\\src\\test\\java\\pl\\edu\\pw\\ee\\datas\\";

	public static final String small_data_file = path + "small_data.txt";
	public static final String small_data_result = "0_12_1|1_13_2";

	public static final String not_existing_file = path + "file_not_exists";

	public static final String doubled_line_file = path + "doubled_line.txt";
	public static final String doubled_line_result = "0_12_1|1_13_2";

	public static final String blank_line_in_middle_of_file_file = path + "blank_line_in_middle_of_file.txt";

	public static final String both_side_file = path + "both_side_text.txt";
	public static final String both_side_result = "b_10_a";

	public static final String lab_graph_file = path + "lab_graph.txt";
	public static final String lab_graph_result = "A_2_E|A_4_H|A_5_C|B_6_H|B_7_D";

	public static final String to_few_argument_in_line_file = path + "to_few_argument_in_line.txt";

	public static final String to_much_argument_in_line_file = path + "to_much_argument_in_line.txt";

	public static final String spliced_graph_file = path + "spliced_graph.txt";

	public static final String minus_values_file = path + "minus_values.txt";


}
