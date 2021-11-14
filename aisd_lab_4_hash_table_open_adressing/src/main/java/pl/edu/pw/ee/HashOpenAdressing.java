package pl.edu.pw.ee;

import pl.edu.pw.ee.exceptions.NotImplementedException;
import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

	private int size;
	private int nElems;
	private T[] hashElems;
	private final double correctLoadFactor;

	public HashOpenAdressing() {
		this(2039); // initial size as random prime number
	}

	public HashOpenAdressing(int size) {
		validateHashInitSize(size);

		this.size = size;
		this.hashElems = (T[]) new Comparable[this.size];
		this.correctLoadFactor = 0.75;
	}

	private int findFreeHash(T objectFind) {
		int hashId;
		int i = 0;


		do {
			if (i == getSize()) {
				throw new IllegalStateException("Could not find object");
			}
			hashId = hashFunc(objectFind.hashCode(), i);
			i = i + 1;
			if (objectFind.equals(hashElems[hashId])) {
				throw new IllegalArgumentException("Element is in hash");
			}
		} while (hashElems[hashId] != null);

		return hashId;
	}

	private int findObjectHash(T objectFind) {
		int hashId;
		int i = 0;


		do {
			if (i == getSize()) {
				throw new IllegalStateException("Could not find object");
			}
			hashId = hashFunc(objectFind.hashCode(), i);

			if (hashElems[hashId] == null) {
				throw new IllegalArgumentException("Element is not in hash");
			}

			i = i + 1;
		} while (hashElems[hashId] != objectFind);

		return hashId;
	}

	private void putElem(T newElem) {
		int hashId;
		validateInputElem(newElem);
		resizeIfNeeded();

		try {
			hashId = findFreeHash(newElem);

		} catch (IllegalStateException | IllegalArgumentException e) {
			throw new IllegalStateException("Could not insert object");
		}
		hashElems[hashId] = newElem;
	}

	@Override
	public void put(T newElem) {

		putElem(newElem);

		nElems++;

	}


	@Override
	public T get(T elem) {
		int hashId;
		validateInputElem(elem);

		try {
			hashId = findObjectHash(elem);
		} catch (IllegalStateException | IllegalArgumentException e) {
			throw new IllegalStateException("Could not find object");
		}

		return hashElems[hashId];
	}

	@Override
	public void delete(T elem) {
		int hashId;
		validateInputElem(elem);

		try {
			hashId = findObjectHash(elem);
		} catch (IllegalStateException | IllegalArgumentException e) {
			throw new IllegalStateException("Could not find object");
		}

		hashElems[hashId] = null;
		nElems--;
		reIndexHash();
	}

	private void validateHashInitSize(int initialSize) {
		if (initialSize < 1) {
			throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
		}
	}

	private void validateInputElem(T newElem) {
		if (newElem == null) {
			throw new IllegalArgumentException("Input elem cannot be null!");
		}
	}

	abstract int hashFunc(int key, int i);

	int getSize() {
		return size;
	}

	private void resizeIfNeeded() {
		double loadFactor = countLoadFactor();

		if (loadFactor >= correctLoadFactor) {
			doubleResize();
		}
	}

	public double countLoadFactor() {
		return (double) nElems / size;
	}

	private void doubleResize() {
		if (this.size != Integer.MAX_VALUE) {
			if (this.size * 2 > this.size) {
				this.size = this.size * 2;
			} else {
				this.size = Integer.MAX_VALUE;
			}

			try {
				reIndexHash();
			} catch (OutOfMemoryError e) {
				throw new OutOfMemoryError("Could not  resize ");
			}

		} else {
			throw new IllegalStateException("Hash is max size");
		}
	}

	private void reIndexHash() {
		T[] oldHash = this.hashElems;
		this.hashElems = (T[]) new Comparable[this.size];
		for (T elem : oldHash) {
			if (elem != null) {
				putElem(elem);
			}
		}
	}

	public int countHashId(int hashCode) {
		return hashFunc(hashCode, 0);
	}
}