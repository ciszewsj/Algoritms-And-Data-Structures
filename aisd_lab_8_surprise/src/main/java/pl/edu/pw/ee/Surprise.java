package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

public class Surprise {

	//Tu można by drzewo zrobić :/

	public class Node {
		int value;

		List<Node> list;

		public Node(int value) {
			this.value = value;
			this.list = new ArrayList<>();
		}

		public void putNode(Node a) {
			list.add(a);
		}
	}

	int countMaxSumPoints(int[] points) {
		if (points == null) {
			throw new NullPointerException("Points array could not be null");
		}
		if (points.length == 0) {
			throw new IllegalStateException("Not ints in array");
		}
		int i = 0;
		int result = points[i];
		while (i < points.length - 1) {
			int j = i + 1;
			int maxj = j;
			int minval = 0;
			while (j < (points.length - i > 6 ? 6 + i : points.length)) {
				if (points[j] > 0) {
					maxj = j;
					break;
				} else if (minval < points[j] / j) {
					maxj = j;
					minval = points[maxj] / j;
				}
				j++;
			}
			i = maxj;
			result += points[i];
		}
		return result;
	}


}
