package pl.edu.pw.ee;

import org.junit.Test;

import java.io.FileNotFoundException;

public class QuickSortTimeTest {

	@Test
	public void test_time() throws FileNotFoundException {
		DataGenerator.generateTimeFor("quick_sort", "optimistic_quick_sort", "random", "sorted", new QuickSort());
	}


}
