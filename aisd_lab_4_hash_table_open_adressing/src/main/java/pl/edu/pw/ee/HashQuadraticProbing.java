package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {
	private static int a = 1;
	private static int b = 2;

	@Override
	int hashFunc(int key, int i) {
		int hash = hashFunc(key, 0) + a * i + b * i * i;
		if (hash == Integer.MIN_VALUE) {
			hash += 1;
		}
		if (hash < 0) {
			hash *= -1;
		}
		return hash;
	}
}
