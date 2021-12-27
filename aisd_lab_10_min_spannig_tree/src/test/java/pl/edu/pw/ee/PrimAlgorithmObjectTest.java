package pl.edu.pw.ee;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimAlgorithmObjectTest {


	private PrimAlgorithmObject<String, String> primAlgorithmObject;

	private static final String v1 = "vertex1";
	private static final String v2 = "vertex2";
	private static final String value = "value";
	private static final String value2 = "value2";
	private static final String expected = "vertex1_value_vertex2";

	@Test
	public void algorithm_object_test() {
		primAlgorithmObject = new PrimAlgorithmObject<>(v1, v2, value);
		assertEquals(v1, primAlgorithmObject.getVertex1());
		assertEquals(v2, primAlgorithmObject.getVertex2());
	}

	@Test
	public void algorithm_objects_compare_test() {
		primAlgorithmObject = new PrimAlgorithmObject<>(v1, v2, value);
		PrimAlgorithmObject<String, String> primAlgorithmObject2 = new PrimAlgorithmObject<>(v2, v1, value2);
		assertEquals(-1 * value.compareTo(value2), primAlgorithmObject.compareTo(primAlgorithmObject2));
		assertEquals(-1 * value2.compareTo(value), primAlgorithmObject2.compareTo(primAlgorithmObject));
	}

	@Test
	public void algorithm_object_to_string_test() {
		primAlgorithmObject = new PrimAlgorithmObject<>(v1, v2, value);
		assertEquals(expected, primAlgorithmObject.toString());
	}

	@Test(expected = NullPointerException.class)
	public void algorithm_object_null_vertex1_test() {
		primAlgorithmObject = new PrimAlgorithmObject<>(null, v2, value);
	}

	@Test(expected = NullPointerException.class)
	public void algorithm_object_null_vertex2_test() {
		primAlgorithmObject = new PrimAlgorithmObject<>(v1, null, value);
	}

	@Test(expected = NullPointerException.class)
	public void algorithm_object_null_value_test() {
		primAlgorithmObject = new PrimAlgorithmObject<>(v1, v2, null);
	}
}
