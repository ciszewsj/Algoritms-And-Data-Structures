package pl.edu.pw.ee;

import org.junit.Test;

import java.io.FileNotFoundException;

public class SelectionSortTimeTest {

	@Test
	public void test_time() throws FileNotFoundException {
		DataGenerator.generateTimeFor("selection_sort", "sorted", "random", "reversed_sorted", new SelectionSort());
	}


}
