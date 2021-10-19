package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {
    Heap heap;

    @Before
    public void initHeap() {
        heap = new Heap();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_1() {
        double[] testArray = { 1, 2, 6, 3, 4, 5 };

        for (Double d : testArray) {
            heap.put(d);
        }

        assertEquals(6.0, heap.pop(), 0.0);
        assertEquals(5.0, heap.pop(), 0.0);
        assertEquals(4.0, heap.pop(), 0.0);
        assertEquals(3.0, heap.pop(), 0.0);
        assertEquals(2.0, heap.pop(), 0.0);
        assertEquals(1.0, heap.pop(), 0.0);
        heap.pop();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_2() {
        double[] testArray = { 0.0, 0.0, 0.0, -1.5, 1.5, 0.5 };

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
    public void test_3() {
        heap.pop();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_4() {
        double[] testArray = { 5.0 };

        for (Double d : testArray) {
            heap.put(d);
        }
        assertEquals(5.0, heap.pop(), 0.0);
        heap.pop();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_6() {
        double[] testArray = { 0.0, 0.0, 0.0 };

        for (Double d : testArray) {
            heap.put(d);
        }
        assertEquals(0.0, heap.pop(), 0.0);
        assertEquals(0.0, heap.pop(), 0.0);
        assertEquals(0.0, heap.pop(), 0.0);
        heap.pop();
    }
}