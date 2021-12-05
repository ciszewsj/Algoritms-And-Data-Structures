package pl.edu.pw.ee;

public class LeafDescription {
	private final char value;
	private final String prefix;

	public LeafDescription(char value, String prefix) {
		this.value = value;
		this.prefix = prefix;
	}

	public char getValue() {
		return value;
	}

	public String getPrefix() {
		return prefix;
	}
}