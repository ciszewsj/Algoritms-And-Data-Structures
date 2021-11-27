package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.MapInterface;

import static org.junit.Assert.assertEquals;

public class RbtMapTest {

	MapInterface<Integer, String> map;

	@Before
	public void init() {
		map = new RbtMap<>();
	}

	@Test
	public void put_test() {
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			map.setValue(keyIntValueString.key, keyIntValueString.value);
		}
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			assertEquals(keyIntValueString.value, map.getValue(keyIntValueString.key));
		}
	}

	@Test(expected = IllegalStateException.class)
	public void put_2_times_one_key_test() {
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			map.setValue(keyIntValueString.key, keyIntValueString.value);
		}
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			map.setValue(keyIntValueString.key, keyIntValueString.value);
		}
	}

	@Test(expected = IllegalStateException.class)
	public void not_in_map_test() {
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			map.setValue(keyIntValueString.key, keyIntValueString.value);
		}
		map.getValue(Datas.not_in_data_1.key);
	}

	@Test(expected = IllegalArgumentException.class)
	public void value_with_null_put_test() {
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_with_null) {
			map.setValue(keyIntValueString.key, keyIntValueString.value);
		}
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_with_null) {
			assertEquals(keyIntValueString.value, map.getValue(keyIntValueString.key));
		}
	}


}
