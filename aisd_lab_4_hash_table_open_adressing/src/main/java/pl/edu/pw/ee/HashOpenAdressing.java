package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

	private int size;
	private int nElems;
	private HashElemObject<T>[] hashElems;
	private final double correctLoadFactor;

	private class HashElemObject<T extends Comparable<T>> {
		private T value;
		private boolean free;

		public HashElemObject() {
			value = null;
			free = true;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
			free = false;
		}

		public boolean isFree() {
			return free;
		}

		public void clear() {
			this.value = null;
		}
	}

	private void setHashElemsList() {
		hashElems = new HashElemObject[size];
		for (int i = 0; i < size; i++) {
			hashElems[i] = new HashElemObject<>();
		}
	}

	public HashOpenAdressing() {
		this(2039); // initial size as random prime number
	}

	public HashOpenAdressing(int size) {
		validateHashInitSize(size);

		this.size = size;
		setHashElemsList();
		this.correctLoadFactor = 0.75;
	}

	private int findFreeHash(T objectFind) {
		int hashId;
		int i = 0;
		do {
			if (i == getSize()) {
				doubleResize();
				i = 0;
			}
			hashId = hashFunc(objectFind.hashCode(), i);
			i = i + 1;
			if (objectFind.equals(hashElems[hashId].value)) {
				return hashId;
			}
		} while (hashElems[hashId].getValue() != null);
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

			if (hashElems[hashId].isFree()) {
				throw new IllegalArgumentException("Element is not in hash");
			}

			i = i + 1;
		} while (hashElems[hashId].getValue() != objectFind);

		return hashId;
	}

	private void putElem(T newElem) {
		int hashId;
		validateInputElem(newElem);
		resizeIfNeeded();

		try {
			hashId = findFreeHash(newElem);
		} catch (OutOfMemoryError e) {
			throw new IllegalStateException("Could not insert object");
		}
		hashElems[hashId].setValue(newElem);
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

		return hashElems[hashId].getValue();
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
		hashElems[hashId].clear();
		nElems--;
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
				throw new OutOfMemoryError("Could not resize");
			}

		} else {
			throw new IllegalStateException("Hash is max size");
		}
	}

	private void reIndexHash() {
		HashElemObject<T>[] oldHash = this.hashElems;
		setHashElemsList();
		for (HashElemObject<T> elem : oldHash) {
			if (!elem.isFree()) {
				putElem(elem.getValue());
			}
		}
	}

	public int countHashId(int hashCode) {
		return hashFunc(hashCode, 0);
	}
}