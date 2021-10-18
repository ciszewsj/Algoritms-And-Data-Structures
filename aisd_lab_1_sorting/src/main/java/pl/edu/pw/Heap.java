package pl.edu.pw;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap implements HeapInterface {

    List<Comparable> heapList = new ArrayList<>();

    @Override
    public void put(Comparable item) {
        heapList.add(item);
        int i = heapList.size() - 1;
        if (i > 0) {

        }
    }

    @Override
    public Comparable pop() {
        // TODO Auto-generated method stub
        return null;
    }

    private void change(int firstIndex) {
        if (firstIndex > 0) {
            int parent = firstIndex % 2 == 1 ? (firstIndex - 1) / 2 : (firstIndex - 2) / 2;
            int left = firstIndex * 2 + 1;
            int right = firstIndex * 2 + 2;
            if (parent >= 0 && heapList.get(firstIndex).compareTo(heapList.get(parent)) >= 0) {

            }
        }
    }
}
