package pl.edu.pw.ee;

import org.junit.Test;

import java.io.FileNotFoundException;

public class HeapSortTimeTest {

	@Test
	public void test_time() throws FileNotFoundException {
		DataGenerator.generateTimeFor("heap_sort", "reversed_sorted", "random", "pessimistic_heap_sort", new HeapSort());
	}


}
