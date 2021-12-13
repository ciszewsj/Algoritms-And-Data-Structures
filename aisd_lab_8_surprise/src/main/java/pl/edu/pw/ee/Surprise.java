package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

public class Surprise {

	int countMaxSumPoints(int[] points) {
		return count(points, 0);
	}

	private int count(int[] points, int n) {
		if (n == points.length - 1) {
			return points[n];
		}
		int a;
		int b = Integer.MIN_VALUE;
		if (n + 1 < points.length) {
			b = points[n] + count(points, n + 1);
		}
		if (n + 2 < points.length) {
			a = points[n] + count(points, n + 2);
			if (a > b) {
				b = a;
			}
		}
		if (n + 3 < points.length) {
			a = points[n] + count(points, n + 3);
			if (a > b) {
				b = a;
			}
		}
		if (n + 4 < points.length) {
			a = points[n] + count(points, n + 4);
			if (a > b) {
				b = a;
			}
		}
		if (n + 5 < points.length) {
			a = points[n] + count(points, n + 5);
			if (a > b) {
				b = a;
			}
		}
		if (n + 6 < points.length) {
			a = points[n] + count(points, n + 6);
			if (a > b) {
				b = a;
			}
		}
		return b;
	}


}
