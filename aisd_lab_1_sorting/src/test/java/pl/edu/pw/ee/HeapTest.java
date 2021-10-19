package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {
    Heap heap;

    @Before
    public void initHeap() {
        heap = new Heap();
    }

    @Test
    public void test_1() {
        double[] testArray = { 1, 2, 6, 3, 4, 5 };

        for (Double d : testArray) {
            heap.put(d);
        }

        assertTrue(6.0 == heap.pop());
        assertTrue(5.0 == heap.pop());
        assertTrue(4.0 == heap.pop());
        assertTrue(3.0 == heap.pop());
        assertTrue(2.0 == heap.pop());
        assertTrue(1.0 == heap.pop());
    }
}