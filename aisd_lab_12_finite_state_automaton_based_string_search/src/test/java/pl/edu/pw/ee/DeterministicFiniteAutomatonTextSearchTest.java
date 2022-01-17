package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.services.PatternSearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeterministicFiniteAutomatonTextSearchTest {

	PatternSearch dfa;

	private final int[] ababab_all = {0, 2, 4, 6, 8};

	@Test
	public void ababab_test() {
		String ababab_pattern = "ab";
		dfa = new DeterministicFiniteAutomatonTextSearch(ababab_pattern);

		String ababab = "abababababa";
		int ababab_first = 0;
		assertEquals(ababab_first, dfa.findFirst(ababab));
		assertArrayEquals(ababab_all, dfa.findAll(ababab));
	}

	private final int[] not_in_all = {};


	@Test
	public void not_in_test() {
		String not_in_pattern = "not_in_pattern";
		dfa = new DeterministicFiniteAutomatonTextSearch(not_in_pattern);

		String not_in = "kkskkskskskskksks";
		int not_in_first = -1;
		assertEquals(not_in_first, dfa.findFirst(not_in));
		assertArrayEquals(not_in_all, dfa.findAll(not_in));
	}

	public String same_char_as_in_pattern_text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	public String same_char_as_in_pattern = "a";

	public int same_char_as_in_pattern_first = 0;
	public int[] same_char_as_in_pattern_all = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
			20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40};

	@Test
	public void same_char_as_in_pattern_test(){
		dfa = new DeterministicFiniteAutomatonTextSearch(same_char_as_in_pattern);

		assertEquals(same_char_as_in_pattern_first, dfa.findFirst(same_char_as_in_pattern_text));
		assertArrayEquals(same_char_as_in_pattern_all, dfa.findAll(same_char_as_in_pattern_text));
	}

	public String three_diff_chars_text = "aaabaaaabaaaaaaabaaaaaabbbbbaab";
	public String three_diff_chars_pattern = "aab";

	public int three_diff_chars_first = 1;
	public int[] three_diff_chars_all = {1, 6, 14, 21, 28};


	@Test
	public void three_diff_chars_test(){
		dfa = new DeterministicFiniteAutomatonTextSearch(three_diff_chars_pattern);

		assertEquals(three_diff_chars_first, dfa.findFirst(three_diff_chars_text));
		assertArrayEquals(three_diff_chars_all, dfa.findAll(three_diff_chars_text));
	}

	@Test(expected = IllegalArgumentException.class)
	public void null_test() {
		dfa = new DeterministicFiniteAutomatonTextSearch(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void empty_test() {
		dfa = new DeterministicFiniteAutomatonTextSearch("");
	}


	public final String null_and_empty_pattern = "null_pattern";

	@Test(expected = IllegalArgumentException.class)
	public void null_find_first_text_test() {
		dfa = new DeterministicFiniteAutomatonTextSearch(null_and_empty_pattern);
		dfa.findFirst(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void empty_find_first_text_test() {
		dfa = new DeterministicFiniteAutomatonTextSearch(null_and_empty_pattern);
		dfa.findFirst("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void null_find_all_text_test() {
		dfa = new DeterministicFiniteAutomatonTextSearch(null_and_empty_pattern);
		dfa.findAll(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void empty_find_all_text_test() {
		dfa = new DeterministicFiniteAutomatonTextSearch(null_and_empty_pattern);
		dfa.findAll("");
	}
}
