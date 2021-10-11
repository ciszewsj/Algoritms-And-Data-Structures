package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        if (nums.length > 1) {
            for (int i = 0; i < nums.length - 1; i++) {
                int min = i;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[min] > nums[j]) {
                        min = j;
                    }
                }
                double mem = nums[min];
                nums[min] = nums[i];
                nums[i] = mem;
            }
        }
    }

}
