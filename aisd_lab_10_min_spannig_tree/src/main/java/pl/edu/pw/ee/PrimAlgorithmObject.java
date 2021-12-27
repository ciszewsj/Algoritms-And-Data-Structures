package pl.edu.pw.ee;


public class PrimAlgorithmObject<K, V extends Comparable<V>> implements Comparable<PrimAlgorithmObject<K, V>> {

	private final K vertex1;
	private final K vertex2;
	private final V value;

	public static final String signSeparator = "_";
	public static final String lineSeparator = "|";


	public PrimAlgorithmObject(K vertex1, K vertex2, V value) {
		if (vertex1 == null || vertex2 == null || value == null) {
			throw new NullPointerException("Arguments could not be null");
		}
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.value = value;
	}

	public boolean contain(K vertex) {
		return vertex.equals(vertex1) || vertex.equals(vertex2);
	}

	@Override
	public int compareTo(PrimAlgorithmObject<K, V> o) {
		if (this.value.compareTo(o.value) < 0) {
			return 1;
		} else if (this.value.compareTo(o.value) > 0) {
			return -1;
		}
		return 0;
	}

	public K getVertex1() {
		return vertex1;
	}

	public K getVertex2() {
		return vertex2;
	}

	@Override
	public String toString() {
		return vertex1 + signSeparator + value + signSeparator + vertex2;

	}
}
