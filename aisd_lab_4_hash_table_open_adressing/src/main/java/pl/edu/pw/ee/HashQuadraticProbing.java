package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {
	private double a = 1;
	private double b = 2;

	public HashQuadraticProbing() {
		super();
	}

	public HashQuadraticProbing(int size) {
		super(size);
	}

	public HashQuadraticProbing(int size, double a, double b) {
		super(size);
		if (a == 0 || b == 0) {
			throw new IllegalArgumentException("a and b could not be 0");
		}
		this.a = a;
		this.b = b;

	}

	@Override
	int hashFunc(int key, int i) {
		int m = getSize();
		int hash = (int) (key % m + a * i + b * i * i) % m;
		return hash < 0 ? -hash : hash;
	}
}
