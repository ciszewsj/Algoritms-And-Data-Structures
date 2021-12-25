package pl.edu.pw.ee.Heap;

public interface HeapInterface<T extends Comparable<T>> {
    public void put(T item);

    public T pop();
}
