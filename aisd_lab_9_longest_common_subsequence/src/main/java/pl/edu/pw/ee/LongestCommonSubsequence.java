package pl.edu.pw.ee;

import static pl.edu.pw.ee.TabObject.Site.*;

class LongestCommonSubsequence {

	private TabObject[][] commonArray;
	private final char[] firstStr;
	private final char[] secondStr;

	private static final char hasLeftChar = '<';
	private static final char hasUpChar = '^';
	private static final char hasSlantChar = '\\';

	private static final String newLineDescription = "\\n";
	private static final String carriageReturnDescription = "\\r";
	private static final String tabDescription = "\\t";

	public LongestCommonSubsequence(String firstStr, String secondStr) {
		validateArguments(firstStr, secondStr);
		this.firstStr = firstStr.toCharArray();
		this.secondStr = secondStr.toCharArray();
		buildCommonArray();
	}

	private void validateArguments(String firstStr, String secondStr) {
		if (firstStr == null || secondStr == null) {
			throw new NullPointerException("Strings could not be null");
		}
	}

	private void buildCommonArray() {
		commonArray = new TabObject[secondStr.length + 1][firstStr.length + 1];
		for (int i = 0; i < commonArray[0].length; i++) {
			commonArray[0][i] = new TabObject();
		}
		for (int i = 0; i < commonArray.length; i++) {
			commonArray[i][0] = new TabObject();
		}
		for (int i = 1; i < commonArray.length; i++) {
			for (int j = 1; j < commonArray[0].length; j++) {
				commonArray[i][j] = new TabObject();

				if (firstStr[j - 1] == secondStr[i - 1]) {
					commonArray[i][j].setValue(commonArray[i - 1][j - 1].getValue() + 1);
					commonArray[i][j].setSite(SLANT);
				} else {
					if (commonArray[i - 1][j].getValue() > commonArray[i][j - 1].getValue()) {
						commonArray[i][j].setValue(commonArray[i - 1][j].getValue());
						commonArray[i][j].setSite(LEFT);
					} else {
						commonArray[i][j].setValue(commonArray[i][j - 1].getValue());
						commonArray[i][j].setSite(UP);
					}
				}
			}
		}
	}

	public String findLCS() {
		StringBuilder result = new StringBuilder();
		int j = commonArray[0].length - 1;
		int i = commonArray.length - 1;
		while (i > 0 && j > 0) {
			if (commonArray[i][j].hasSite() == SLANT) {
				result.insert(0, firstStr[j - 1]);
				i -= 1;
				j -= 1;
			} else if (commonArray[i][j].hasSite() == UP) {
				j--;
			} else {
				i--;
			}
		}
		return result.toString();
	}

	public void display() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		StringBuilder toDisplay = new StringBuilder();
		toDisplay.append("       ");
		for (int i = 0; i < commonArray.length; i++) {
			toDisplay.append(String.format("%2s", i - 1 < 0 ? "#" : charToDescription(secondStr[i - 1])));
			if (i + 1 < commonArray.length) {
				toDisplay.append("     ");
			}
		}
		toDisplay.append('\n');
		for (int i = 0; i < commonArray[0].length; i++) {
			toDisplay.append("  ");
			for (TabObject[] tabObjects : commonArray) {
				toDisplay.append(String.format("   %c  %c", tabObjects[i].hasSite() == SLANT ? hasSlantChar : ' ', tabObjects[i].hasSite() == UP ? hasUpChar : ' '));
			}
			toDisplay.append('\n');
			toDisplay.append('\n');
			toDisplay.append(String.format("%2s", i - 1 < 0 ? "#" : charToDescription(firstStr[i - 1])));
			for (TabObject[] tabObjects : commonArray) {
				toDisplay.append(String.format("  %2s  ", tabObjects[i].hasSite() == LEFT ? hasLeftChar : ' '));
				toDisplay.append(tabObjects[i].getValue());
			}
			toDisplay.append("\n");
		}
		return toDisplay.toString();
	}

	private String charToDescription(char c) {
		if (c == '\r') {
			return carriageReturnDescription;
		} else if (c == '\n') {
			return newLineDescription;
		} else if (c == '\t') {
			return tabDescription;
		}

		return String.valueOf(c);
	}
}
