package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting {

    @Override
    public void sort(double[] nums) {

        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        Heap heap = new Heap();
        for (double number : nums) {
            heap.put(number);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[nums.length - 1 - i] = heap.pop();
        }
    }

}
