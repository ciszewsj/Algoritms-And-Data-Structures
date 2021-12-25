package pl.edu.pw.ee;

import org.junit.Test;

public class PrimAlgorithmTest {


	@Test
	public void test() {
		System.out.println(new PrimAlgorithm().findMST(System.getProperty("user.dir") + "\\src\\test\\java\\pl\\edu\\pw\\ee\\" + "small_data.txt"));
	}
}
