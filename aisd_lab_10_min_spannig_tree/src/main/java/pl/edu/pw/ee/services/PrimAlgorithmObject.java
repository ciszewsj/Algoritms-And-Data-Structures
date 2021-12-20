package pl.edu.pw.ee.services;


public class PrimAlgorithmObject<K extends Comparable<K>, V extends Comparable<V>> {

	private K vertex1;
	private K vertex2;
	private V value;

	public PrimAlgorithmObject(K vertex1, K vertex2, V value) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.value = value;
	}

}
