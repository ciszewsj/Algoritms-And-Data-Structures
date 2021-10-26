package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

	private final Elem<T>[] hashElems;
	private int nElem;

	private class Elem<S> {
		private final S value;
		private Elem<S> next;

		Elem(S value, Elem<S> nextElem) {
			this.value = value;
			this.next = nextElem;
		}
	}

	public HashListChaining(int size) {
		hashElems = new Elem[size];
		initializeHash();
	}

	@Override
	public void add(T value) {

		if (value == null) {
			throw new IllegalArgumentException("Element could not be null");
		}

		int hashCode = value.hashCode();
		int hashId = countHashId(hashCode);


		Elem<T> oldElem = hashElems[hashId];

		if (oldElem != null) {
			while (oldElem.next != null && !oldElem.value.equals(value)) {
				oldElem = oldElem.next;
			}
			if (!oldElem.value.equals(value)) {
				oldElem.next = new Elem<>(value, null);
			}
		} else {
			hashElems[hashId] = new Elem<T>(value, null);
			nElem++;
		}
	}

	@Override
	public T get(T value) {

		if (value == null) {
			throw new IllegalArgumentException("Element could not be null");
		}

		int hashCode = value.hashCode();
		int hashId = countHashId(hashCode);

		Elem<T> elem = hashElems[hashId];

		while (elem != null && !elem.value.equals(value)) {
			elem = elem.next;
		}

		return elem != null ? elem.value : null;
	}

	@Override
	public void delete(T value) {

		if (value == null) {
			throw new IllegalArgumentException("Element could not be null");
		}

		int hashCode = value.hashCode();
		int hashId = countHashId(hashCode);
		Elem<T> elem = hashElems[hashId];
		Elem<T> previouselem = null;
		while (elem != null && !elem.value.equals(value)) {
			previouselem = elem;
			elem = elem.next;
		}

		if (elem != null && elem.value.equals(value)) {
			if (previouselem != null) {
				if (elem.next != null) {
					previouselem.next = elem.next;
				} else {
					previouselem.next = null;
				}
			} else {
				if (elem.next != null) {
					hashElems[hashId] = elem.next;
				} else {
					hashElems[hashId] = null;
					nElem--;
				}
			}
		}
	}

	public double countLoadFactor() {
		double size = hashElems.length;
		return nElem / size;
	}

	private void initializeHash() {
		int n = hashElems.length;

		for (int i = 0; i < n; i++) {
			hashElems[i] = null;
		}
	}

	private int countHashId(int hashCode) {
		int n = hashElems.length;
		return Math.abs(hashCode) % n;
	}

}