package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LongestCommonSubsequenceTest {
	@Test
	public void tst() {
		LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence("BABBAB", "ABBAABA");

		longestCommonSubsequence.display();

		System.out.println(longestCommonSubsequence.findLCS());
	}

	@Test
	public void tst2() {
		LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence("", "");


		System.out.println(longestCommonSubsequence.findLCS());
	}

	@Test
	public void tst3() {
		LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence("POLITECHNIKA", "TOALETA");


		longestCommonSubsequence.display();

		System.out.println(longestCommonSubsequence.findLCS());
	}
}
