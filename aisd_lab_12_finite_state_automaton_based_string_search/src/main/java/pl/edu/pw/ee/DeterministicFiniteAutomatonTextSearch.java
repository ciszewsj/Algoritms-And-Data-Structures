package pl.edu.pw.ee;

import static java.lang.Math.min;
import static java.util.stream.Collectors.toList;

import java.util.*;

import pl.edu.pw.ee.services.PatternSearch;

public class DeterministicFiniteAutomatonTextSearch implements PatternSearch {

	private static class Key {
		private final int state;
		private final char sign;

		public Key(int state, char sign) {
			this.state = state;
			this.sign = sign;
		}

		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;
			if (!(o instanceof Key)) {
				return false;
			}
			Key key = (Key) o;
			return state == key.state && sign == key.sign;
		}

		@Override
		public int hashCode() {
			return Objects.hash(state, sign);
		}
	}

	private final String pattern;
	private Map<Key, Integer> transMap;

	public DeterministicFiniteAutomatonTextSearch(String pattern) {
		validateInput(pattern);

		this.pattern = pattern;
		buildTransitionMatrix();
	}

	@Override
	public int findFirst(String text) {
		validateInput(text);
		return findFirstFrom(text, 0);
	}

	@Override
	public int[] findAll(String text) {
		validateInput(text);
		List<Integer> results = new ArrayList<>();
		int curr_index = 0;
		while (true) {
			curr_index = findFirstFrom(text, curr_index);
			if (curr_index == -1) {
				break;
			}
			results.add(curr_index);
			curr_index += 1;
		}
		return results.stream().mapToInt(i -> i).toArray();
	}

	private int findFirstFrom(String text, int firstIndex) {
		int result = -1;
		int n = text.length();
		int acceptedState = pattern.length();
		int state = 0;

		for (int i = firstIndex; i < n; i++) {
			try {
				state = transMap.get(new Key(state, text.charAt(i)));
			} catch (NullPointerException e) {
				state = 0;
			}
			if (state == acceptedState) {
				result = i - acceptedState + 1;
				break;
			}
		}
		return result;
	}

	private void validateInput(String txt) {
		if (txt == null) {
			throw new IllegalArgumentException("I`nput text cannot be null!");
		} else if (txt.equals("")) {
			throw new IllegalArgumentException("Input text cannot be empty!");
		}
	}

	private void buildTransitionMatrix() {
		transMap = new HashMap<>();

		int m = pattern.length();
		List<Character> alphabet = getAlphabetOfPattern();

		for (int q = 0; q <= m; q++) {
			for (char sign : alphabet) {

				int k = min(m + 1, q + 2);
				k--;

				while (k > 0 && !isSuffixOfPattern(k, q, sign)) {
					k--;
				}
				transMap.put(new Key(q, sign), k);
			}
		}
	}

	private List<Character> getAlphabetOfPattern() {

		return pattern.chars()
				.distinct()
				.mapToObj(c -> (char) c)
				.collect(toList());
	}

	private boolean isSuffixOfPattern(int kIndex, int qIndex, char sign) {
		boolean isSuffix = false;

		if (pattern.charAt(--kIndex) == sign) {
			isSuffix = true;

			while (kIndex > 0) {
				kIndex--;
				qIndex--;

				if (pattern.charAt(kIndex) != pattern.charAt(qIndex)) {
					isSuffix = false;
					break;
				}
			}
		}

		return isSuffix;
	}

}
