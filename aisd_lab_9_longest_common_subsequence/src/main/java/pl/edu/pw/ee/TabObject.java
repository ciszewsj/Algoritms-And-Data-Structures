package pl.edu.pw.ee;

public class TabObject {
	private int value;
	private Site hasSite;


	public enum Site {
		LEFT, UP, SLANT
	}

	public TabObject() {
		value = 0;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setSite(Site site) {
		hasSite = site;
	}

	public Site hasSite() {
		return hasSite;
	}
}
