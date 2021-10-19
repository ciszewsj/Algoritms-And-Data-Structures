package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap implements HeapInterface<Double> {

    private List<Double> heapList = new ArrayList<>();

    @Override
    public void put(Double item) {
        heapList.add(item);
        change(heapList.size() - 1);
    }

    @Override
    public Double pop() {
        if (heapList.size() == 0) {
            throw new IllegalArgumentException("Cannot pop an element from empty Heap");
        }
        Double mem = heapList.get(0);
        changeIndex(0, heapList.size() - 1);
        heapList.remove(heapList.size() - 1);
        change(0);
        return mem;
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
        Double mem = heapList.get(secondIndex);
        heapList.set(secondIndex, heapList.get(firstIndex));
        heapList.set(firstIndex, mem);
    }

    @Override
    public String toString() {
        return heapList.toString();
    }
}
