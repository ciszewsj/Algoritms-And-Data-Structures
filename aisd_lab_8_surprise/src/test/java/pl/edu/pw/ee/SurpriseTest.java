package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SurpriseTest {


	Surprise surprise;

	static int[] normal_sit = {1, 2, 3, 4, 5};
	static int[] all_the_same_value = {1, 1, 1, 1, 1};

	static int[] with_minus = {0, -1, -2, -3, -4, -5, -9, -8};
	static int[] val = {3, -2, -1, -1, -1, -2, -4, -10, -10, -10, -8, 10, 3, -2, -1, -1, -1, -2, -4, -10, -10, -10, -8, 10};

	@Before
	public void init() {
		surprise = new Surprise();
	}

	@Test
	public void test_normal() {
		assertEquals(15, surprise.countMaxSumPoints(normal_sit));
	}

	@Test
	public void test_all_same() {
		assertEquals(5, surprise.countMaxSumPoints(all_the_same_value));
	}

	@Test
	public void with_minus_test() {
		assertEquals(-8, surprise.countMaxSumPoints(with_minus));
	}

	@Test(expected = NullPointerException.class)
	public void test_null_array() {
		surprise.countMaxSumPoints(null);
	}

	@Test(expected = IllegalStateException.class)
	public void test_free_array() {
		surprise.countMaxSumPoints(new int[]{});
	}


	@Test
	public void test() {
		System.out.println(surprise.countMaxSumPoints(val));
	}

}
