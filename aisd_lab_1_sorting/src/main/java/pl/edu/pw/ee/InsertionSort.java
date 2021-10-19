package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

	@Override
	public void sort(double[] nums) {
		if (nums == null) {
			throw new IllegalArgumentException("Nums array cannot be null");
		}
		if (nums.length > 1) {
			for (int i = 1; i < nums.length; i++) {
				int j = i - 1;
				Double mem = nums[i];
				while (nums[j] > mem) {
					nums[j + 1] = nums[j];
					j--;
					if (j < 0 || nums[j] < mem) {
						break;
					}
				}
				nums[j + 1] = mem;
			}
		}
	}
}
