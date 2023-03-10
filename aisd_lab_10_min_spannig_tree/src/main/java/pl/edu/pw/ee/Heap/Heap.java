package pl.edu.pw.ee.Heap;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {

	private final List<T> heapList = new ArrayList<>();

	@Override
	public void put(T item) {
		heapList.add(item);
		change(heapList.size() - 1);
	}

	@Override
	public T pop() {
		if (heapList.size() == 0) {
			throw new IllegalArgumentException("Cannot pop an element from empty Heap");
		}
		T mem = heapList.get(0);
		changeIndex(0, heapList.size() - 1);
		heapList.remove(heapList.size() - 1);
		change(0);
		return mem;
	}

	@Override
	public boolean contain(T o) {
		for (T i : heapList) {
			if (i.equals(o)) {
				return true;
			}
		}
		return false;
	}

	private void change(int firstIndex) {

		if (firstIndex >= 0) {
			int parent = firstIndex % 2 == 1 ? (firstIndex - 1) / 2 : (firstIndex - 2) / 2;
			int left = firstIndex * 2 + 1;
			int right = firstIndex * 2 + 2;

			if (left < heapList.size() && right < heapList.size()) {
				if (heapList.get(left).compareTo(heapList.get(right)) > 0) {
					if (heapList.get(firstIndex).compareTo(heapList.get(left)) < 0) {
						changeIndex(firstIndex, left);
						change(left);
					}
				} else {
					if (heapList.get(firstIndex).compareTo(heapList.get(right)) < 0) {
						changeIndex(firstIndex, right);
						change(right);
					}
				}
			} else if (left < heapList.size() && heapList.get(firstIndex).compareTo(heapList.get(left)) < 0) {
				changeIndex(firstIndex, left);
				change(left);
			} else if (right < heapList.size() && heapList.get(firstIndex).compareTo(heapList.get(right)) < 0) {
				changeIndex(firstIndex, right);
				change(right);

			}
			if (parent >= 0 && heapList.get(firstIndex).compareTo(heapList.get(parent)) >= 0) {
				changeIndex(firstIndex, parent);
				change(parent);
			}
		}
	}

	private void changeIndex(int firstIndex, int secondIndex) {
		T mem = heapList.get(secondIndex);
		heapList.set(secondIndex, heapList.get(firstIndex));
		heapList.set(firstIndex, mem);
	}
}
