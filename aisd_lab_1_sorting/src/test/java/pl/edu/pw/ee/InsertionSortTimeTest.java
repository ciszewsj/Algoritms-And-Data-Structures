package pl.edu.pw.ee;

import org.junit.Test;

import java.io.FileNotFoundException;

public class InsertionSortTimeTest {

	@Test
	public void test_time() throws FileNotFoundException {
		DataGenerator.generateTimeFor("insertion_sort", "sorted", "random", "reversed_sorted", new InsertionSort());
	}


}
