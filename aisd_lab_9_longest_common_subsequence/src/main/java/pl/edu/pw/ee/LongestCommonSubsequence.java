package pl.edu.pw.ee;

import java.util.Arrays;

import static pl.edu.pw.ee.TabObject.Site.*;

class LongestCommonSubsequence {

	private TabObject[][] commonArray;
	private final char[] topString;
	private final char[] leftString;

	private static final char hasLeftChar = '<';
	private static final char hasUpChar = '^';
	private static final char hasSlantChar = '\\';
	private static final char freeChar = ' ';

	private static final String newLineDescription = "\\n";
	private static final String carriageReturnDescription = "\\r";
	private static final String tabDescription = "\\t";

	private static final char verticalSeparatorTemplate = '-';
	private static final char horizontalSeparatorTemplate = '|';
	private static final char crossSeparatorTemplate = '+';

	private String lineNumberOne;
	private String lineNumberTwo;
	private String lineNumberThree;
	private String lineNumberFour;

	public LongestCommonSubsequence(String firstStr, String secondStr) {
		validateArguments(firstStr, secondStr);
		this.leftString = secondStr.toCharArray();
		this.topString = firstStr.toCharArray();
		buildCommonArray();
	}

	private void validateArguments(String firstStr, String secondStr) {
		if (firstStr == null || secondStr == null) {
			throw new NullPointerException("Strings could not be null");
		}
	}

	private void buildCommonArray() {
		commonArray = new TabObject[leftString.length + 1][topString.length + 1];
		for (int i = 0; i < commonArray[0].length; i++) {
			commonArray[0][i] = new TabObject();
		}
		for (int i = 0; i < commonArray.length; i++) {
			commonArray[i][0] = new TabObject();
		}
		for (int j = 1; j < commonArray[0].length; j++) {
			for (int i = 1; i < commonArray.length; i++) {
				commonArray[i][j] = new TabObject();

				if (topString[j - 1] == leftString[i - 1]) {
					commonArray[i][j].setValue(commonArray[i - 1][j - 1].getValue() + 1);
					commonArray[i][j].setSite(SLANT);
				} else {
					if (commonArray[i - 1][j].getValue() > commonArray[i][j - 1].getValue()) {
						commonArray[i][j].setValue(commonArray[i - 1][j].getValue());
						commonArray[i][j].setSite(UP);
					} else {
						commonArray[i][j].setValue(commonArray[i][j - 1].getValue());
						commonArray[i][j].setSite(LEFT);
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
			if (commonArray[i][j].getSite() == SLANT) {
				result.insert(0, topString[j - 1]);
				i -= 1;
				j -= 1;
			} else if (commonArray[i][j].getSite() == UP) {
				i--;
			} else {
				j--;
			}
		}
		return result.toString();
	}

	public void display() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		buildTemplates();
		StringBuilder toDisplay = new StringBuilder();
		int x = commonArray.length - 1;
		int y = commonArray[0].length - 1;
		for (int i = commonArray.length; i >= 0; i--) {
			String[] lines = new String[4];
			Arrays.fill(lines, "\n");
			for (int j = commonArray[0].length; j >= 0; j--) {
				boolean isInPath = false;
				if (i - 1 == x && j - 1 == y) {
					isInPath = true;
					if (commonArray[x][y].getSite() == LEFT) {
						y--;
					} else if (commonArray[x][y].getSite() == UP) {
						x--;
					} else if (commonArray[x][y].getSite() == SLANT) {
						x--;
						y--;
					}
				}
				addOneToRowStringList(lines, i, j, isInPath);
			}
			for (int n = lines.length - 1; n >= 0; n--) {
				toDisplay.insert(0, lines[n]);
			}
		}
		return toDisplay.toString();
	}

	private void addOneToRowStringList(String[] lines, int i, int j, boolean isInPath) {
		boolean isLeftBorder = j <= 0;
		boolean isUpBorder = i <= 0;
		lines[3] = (isLeftBorder ? crossSeparatorTemplate : "") + lineNumberFour + crossSeparatorTemplate + lines[3];
		lines[2] = (isLeftBorder ? horizontalSeparatorTemplate : "") + lineNumberThree + horizontalSeparatorTemplate + lines[2];
		lines[1] = (isLeftBorder ? horizontalSeparatorTemplate : "")
				+ String.format(lineNumberTwo, isInPath ? commonArray[i - 1][j - 1].getSite() == LEFT ? hasLeftChar : freeChar : freeChar,
				j - 2 >= 0 && isUpBorder ? charToDescription(topString[j - 2]) :
						i - 2 >= 0 && isLeftBorder ? charToDescription(leftString[i - 2]) : isLeftBorder || isUpBorder ? charToDescription(freeChar) :
								commonArray[i - 1][j - 1].getValue())
				+ horizontalSeparatorTemplate + lines[1];
		lines[0] = (isLeftBorder ? horizontalSeparatorTemplate : "")
				+ String.format(lineNumberOne, isUpBorder ? freeChar : isInPath ? commonArray[i - 1][j - 1].getSite() == SLANT ?
						hasSlantChar : freeChar : freeChar,
				isUpBorder ? freeChar : isInPath ? commonArray[i - 1][j - 1].getSite() == UP ? hasUpChar : freeChar : freeChar)
				+ horizontalSeparatorTemplate + lines[0];
		if (isLeftBorder && isUpBorder) {
			lines[0] = lines[3] + lines[0];
		}
	}

	private void buildTemplates() {
		int maxSizeInt = String.valueOf(commonArray[commonArray.length - 1][commonArray[0].length - 1].getValue()).length();
		if (maxSizeInt < 2) {
			maxSizeInt = 2;
		}
		int size = maxSizeInt + 2;
		StringBuilder newLineNumberOne = new StringBuilder("%c");
		int spaces = size / 2;
		newLineNumberOne.append(" ".repeat(Math.max(0, spaces)));
		newLineNumberOne.append("%c");
		int lineSize = newLineNumberOne.length();
		newLineNumberOne.append(" ".repeat(Math.max(0, size - lineSize)));
		lineNumberOne = newLineNumberOne.toString();
		lineNumberTwo = "%c %" + maxSizeInt + "s";
		lineNumberThree = " ".repeat(Math.max(0, size));
		lineNumberFour = String.valueOf(verticalSeparatorTemplate).repeat(Math.max(0, size));
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
