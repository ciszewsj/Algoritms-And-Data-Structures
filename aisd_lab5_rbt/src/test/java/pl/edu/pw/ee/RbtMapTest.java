package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.MapInterface;

import static org.junit.Assert.*;
import static pl.edu.pw.ee.Datas.*;

public class RbtMapTest {

	MapInterface<Integer, String> map;

	@Before
	public void init() {
		map = new RbtMap<>();
	}

	@Test
	public void put_test() {
		for (Datas.KeyIntValueString keyIntValueString : data_1) {
			map.setValue(keyIntValueString.key, keyIntValueString.value);
		}
		for (Datas.KeyIntValueString keyIntValueString : data_1) {
			assertEquals(keyIntValueString.value, map.getValue(keyIntValueString.key));
		}
	}

	@Test
	public void put_2_times_one_key_test() {
		for (Datas.KeyIntValueString keyIntValueString : data_1) {
			map.setValue(keyIntValueString.key, keyIntValueString.value);
		}
		assertEquals(data_1[0].value, map.getValue(data_1[0].key));
		assertEquals(data_1[0].key, in_data_1_another_value.key);
		map.setValue(in_data_1_another_value.key, in_data_1_another_value.value);
		assertEquals(in_data_1_another_value.value, map.getValue(in_data_1_another_value.key));
		assertNotSame(data_1[0].value, map.getValue(data_1[0].key));
	}

	@Test(expected = IllegalStateException.class)
	public void not_in_map_test() {
		for (Datas.KeyIntValueString keyIntValueString : data_1) {
			map.setValue(keyIntValueString.key, keyIntValueString.value);
		}
		map.getValue(Datas.not_in_data_1.key);
	}

	@Test(expected = IllegalArgumentException.class)
	public void value_with_null_put_test() {
		for (Datas.KeyIntValueString keyIntValueString : data_with_null) {
			map.setValue(keyIntValueString.key, keyIntValueString.value);
		}
		for (Datas.KeyIntValueString keyIntValueString : data_with_null) {
			assertEquals(keyIntValueString.value, map.getValue(keyIntValueString.key));
		}
	}


}
