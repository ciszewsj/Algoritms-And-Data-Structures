package pl.edu.pw.ee;

import java.lang.ProcessBuilder.Redirect.Type;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T> implements HashTable<T> {

    private final Elem<T> nil = null;
    private Elem<T>[] hashElems;
    private int nElem;

    private class Elem<S> {
        private S value;
        private Elem<S> next;

        Elem(S value, Elem<S> nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        hashElems = (Elem<T>[]) java.lang.reflect.Array.newInstance(T, size);
        initializeHash();
    }

    @Override
    public void add(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> oldElem = hashElems[hashId];
        while (oldElem != nil && !oldElem.equals(value)) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems[hashId] = new Elem<T>(value, hashElems[hashId]);
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : nil.value;
    }

    public double countLoadFactor() {
        double size = hashElems.length;
        return nElem / size;
    }

    private void initializeHash() {
        int n = hashElems.length;

        for (int i = 0; i < n; i++) {
            hashElems[i] = nil;
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.length;
        return Math.abs(hashCode) % n;
    }

}