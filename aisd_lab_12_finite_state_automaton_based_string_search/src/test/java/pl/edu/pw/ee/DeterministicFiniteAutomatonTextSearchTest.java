package pl.edu.pw.ee;

import org.junit.Test;

import java.util.Arrays;

public class DeterministicFiniteAutomatonTextSearchTest {

	DeterministicFiniteAutomatonTextSearch dfa;

	@Test
	public void tst() {
		dfa = new DeterministicFiniteAutomatonTextSearch("abcd");

		System.out.println(dfa.findFirst("bb  aa  abcd"));
		System.out.println(Arrays.toString(dfa.findAll("abcd  aa  abcd abc")));
	}

	@Test
	public void tst2() {
		dfa = new DeterministicFiniteAutomatonTextSearch("bbbb");

		System.out.println(dfa.findFirst("bbb bbbbbb"));
		System.out.println(Arrays.toString(dfa.findAll("bbb bbbbbb")));
	}
}
