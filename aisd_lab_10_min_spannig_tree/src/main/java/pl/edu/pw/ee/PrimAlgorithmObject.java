package pl.edu.pw.ee;


public class PrimAlgorithmObject<K, V extends Comparable<V>> implements Comparable<PrimAlgorithmObject<K, V>> {

	private final K vertex1;
	private final K vertex2;
	private final V value;

	private static final String signSeparator = "_";
	private static final String lineSeparator = "\n";


	public PrimAlgorithmObject(K vertex1, K vertex2, V value) {
		if (vertex1 == null || vertex2 == null || value == null) {
			throw new NullPointerException("Arguments could not be null");
		}
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.value = value;
	}

	public boolean contain(K vertex) {
		if (vertex.equals(vertex1) || vertex.equals(vertex2)) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(PrimAlgorithmObject<K, V> o) {
		return -1 * this.value.compareTo(o.value);
	}

	public K getVertex1() {
		return vertex1;
	}

	public K getVertex2() {
		return vertex2;
	}

	public V getValue() {
		return value;
	}

	@Override
	public String toString() {
		return vertex1 + signSeparator + value + signSeparator + vertex2 + lineSeparator;

	}
}
