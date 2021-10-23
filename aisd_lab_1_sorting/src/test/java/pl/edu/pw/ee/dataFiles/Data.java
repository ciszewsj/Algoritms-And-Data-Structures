package pl.edu.pw.ee.dataFiles;

public class Data {
	private Long optimistic;
	private Long random;
	private Long unoptimistic;

	public Data(long optimisticNum, long randomNum, long unoptimisticNum) {
		optimistic = optimisticNum;
		random = randomNum;
		unoptimistic = unoptimisticNum;
	}

	@Override
	public String toString() {
		String values = String.format("%d %d %d", optimistic, random, unoptimistic);
		return values;

	}
}