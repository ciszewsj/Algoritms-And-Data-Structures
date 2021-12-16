package pl.edu.pw.ee;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static pl.edu.pw.ee.Datas.*;

public class LongestCommonSubsequenceTest {

	private void test_method_compare(String[] strings, String template1, String template2) {
		LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(strings[0], strings[1]);
		assertEquals(template1, longestCommonSubsequence.findLCS());

		LongestCommonSubsequence longestCommonSubsequenceReverse = new LongestCommonSubsequence(strings[1], strings[0]);
		assertEquals(template2, longestCommonSubsequenceReverse.findLCS());
	}

	private void display_method_compare(String[] strings, String templateOfDisplay) {
		LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(strings[0], strings[1]);
		assertEquals(templateOfDisplay, longestCommonSubsequence.toString());

	}

	@Test
	public void ababab_test() {
		test_method_compare(ababab_test, ababab_reverse_result, ababab_result);
	}

	@Test
	public void politechnika_toaleta_test() {
		test_method_compare(politechnika_test, politechnika_reverse_result, politechnika_result);
	}

	@Test
	public void polish_chars_test() {
		test_method_compare(polish_chars_test, polish_chars_reverse_result, polish_chars_result);
	}

	@Test
	public void strange_chars_test() {
		test_method_compare(strange_chars_test, strange_chars_reverse_result, strange_chars_result);
	}

	@Test
	public void one_string_free_test() {
		test_method_compare(one_string_free_test, one_string_free_reverse_result, one_string_free_result);
	}

	@Test
	public void ababab_display_test() {
		display_method_compare(ababab_test, ababab_display_test);
	}

	@Test
	public void one_string_free_display_test() {
		display_method_compare(one_string_free_test, one_string_free_display_result);
	}

	@Test
	public void strange_chars_display_test() {
		display_method_compare(strange_chars_test, strange_chars_display_result);
	}


	@Test(expected = NullPointerException.class)
	public void null_value_str_1_test() {
		new LongestCommonSubsequence(null, "");
	}

	@Test(expected = NullPointerException.class)
	public void null_value_str_2_test() {
		new LongestCommonSubsequence("", null);
	}

	@Test(expected = NullPointerException.class)
	public void null_values_both_str_test() {
		new LongestCommonSubsequence(null, null);
	}
}
