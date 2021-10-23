package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class SelectionSortTest {
	SelectionSort sorter;

	@Before
	public void initSort() {
		sorter = new SelectionSort();
	}

	@Test
	public void test_1() {
		double[] testArray = TestDatas.test_1;
		sorter.sort(testArray);
		assertTrue(Arrays.equals(testArray, TestDatas.test_1_result));
	}

	@Test
	public void test_2() {
		double[] testArray = TestDatas.test_2;
		sorter.sort(testArray);
		assertTrue(Arrays.equals(testArray, TestDatas.test_2_result));
	}

	@Test
	public void test_3() {
		double[] testArray = TestDatas.test_3;
		sorter.sort(testArray);
		assertTrue(Arrays.equals(testArray, TestDatas.test_3_result));
	}

	@Test
	public void test_4() {
		double[] testArray = TestDatas.test_4;
		sorter.sort(testArray);
		assertTrue(Arrays.equals(testArray, TestDatas.test_4_result));
	}

	@Test
	public void test_5() {
		double[] testArray = TestDatas.test_5;
		sorter.sort(testArray);
		assertTrue(Arrays.equals(testArray, TestDatas.test_5_result));
	}

	@Test
	public void test_6() {
		double[] testArray = TestDatas.test_6;
		sorter.sort(testArray);
		assertTrue(Arrays.equals(testArray, TestDatas.test_6_result));
	}

	@Test
	public void test_7() {
		double[] testArray = TestDatas.test_7;
		sorter.sort(testArray);
		assertTrue(Arrays.equals(testArray, TestDatas.test_7_result));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_8() {
		double[] testArray = TestDatas.test_8;
		sorter.sort(testArray);
	}

	@Test
	public void test_9() {
		double[] testArray = TestDatas.test_9;
		sorter.sort(testArray);
		assertTrue(Arrays.equals(testArray, TestDatas.test_9));
	}

	@Test
	public void test_10() {
		double[] testArray = TestDatas.test_10;
		sorter.sort(testArray);
		assertTrue(Arrays.equals(testArray, TestDatas.test_10));
	}

	@Test
	public void test_11() {
		double[] testArray = TestDatas.test_11;
		sorter.sort(testArray);
		assertTrue(Arrays.equals(testArray, TestDatas.test_11_result));
	}
}
