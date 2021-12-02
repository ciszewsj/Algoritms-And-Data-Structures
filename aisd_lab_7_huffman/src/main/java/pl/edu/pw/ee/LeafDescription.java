package pl.edu.pw.ee;

public class LeafDescription<T extends Comparable<T>> {
	private final T value;
	private final String prefix;

	public LeafDescription(T value, String prefix) {
		this.value = value;
		this.prefix = prefix;
	}

	public T getValue() {
		return value;
	}

	public String getPrefix() {
		return prefix;
	}

	@Override
	public String toString() {
		return value.toString() + " " + prefix;
	}
}