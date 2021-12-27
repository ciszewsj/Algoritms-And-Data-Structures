package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.Heap.Heap;
import pl.edu.pw.ee.Heap.HeapInterface;

import static org.junit.Assert.assertEquals;

public class HeapTest {
	HeapInterface<Double> heap;

	@Before
	public void initHeap() {
		heap = new Heap<>();
	}

	@Test(expected = IllegalArgumentException.class)
	public void heap_usage_test() {
		double[] testArray = {0.0, 0.0, 0.0, -1.5, 1.5, 0.5};

		for (Double d : testArray) {
			heap.put(d);
		}
		assertEquals(1.5, heap.pop(), 0.0);
		assertEquals(0.5, heap.pop(), 0.0);
		assertEquals(0.0, heap.pop(), 0.0);
		assertEquals(0.0, heap.pop(), 0.0);
		assertEquals(0.0, heap.pop(), 0.0);
		assertEquals(-1.5, heap.pop(), 0.0);
		heap.pop();
	}

	@Test(expected = IllegalArgumentException.class)
	public void heap_empty_pop_test() {
		heap.pop();
	}

	@Test(expected = IllegalArgumentException.class)
	public void same_value_few_times_test() {
		double[] testArray = {0.0, 0.0, 0.0};

		for (Double d : testArray) {
			heap.put(d);
		}
		assertEquals(0.0, heap.pop(), 0.0);
		assertEquals(0.0, heap.pop(), 0.0);
		assertEquals(0.0, heap.pop(), 0.0);
		heap.pop();
	}
}
