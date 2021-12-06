package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static pl.edu.pw.ee.StringAndByteOperation.*;
import static pl.edu.pw.ee.datas.Datas.*;

public class StringAndByteOperationTest {
	@Test
	public void validate_path_test() {

		validatePath(path);
		validatePath(path + niemanie);
		try {
			validatePath(path + niemanie + "\\" + niemanie + ".txt");
			fail();
		} catch (IllegalStateException ignored) {

		}
		try {
			validatePath(path + directory_not_exist + directory_not_exist);
			fail();
		} catch (IllegalStateException ignored) {

		}
	}
}
