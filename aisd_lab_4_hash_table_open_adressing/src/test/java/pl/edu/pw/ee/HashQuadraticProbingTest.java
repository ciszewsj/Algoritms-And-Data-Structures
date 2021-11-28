package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.data.TestDatas;
import pl.edu.pw.ee.services.HashTable;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static pl.edu.pw.ee.data.TestDatas.*;
import static pl.edu.pw.ee.data.TestDatas.data4;

public class HashQuadraticProbingTest {

	HashTable<String> hashObjectIInterface;

	HashQuadraticProbing<String> hashObject;

	@Before
	public void init() {
		hashObjectIInterface = new HashQuadraticProbing<String>(1);
		hashObject = new HashQuadraticProbing<String>(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
		// given
		int initialSize = 0;

		// when
		HashTable<Double> hash = new HashQuadraticProbing<>(initialSize);

		// then
		assert false;
	}

	@Test
	public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
		// given
		HashTable<String> emptyHash = new HashQuadraticProbing<>();
		String newEleme = "nothing special";

		// when
		int nOfElemsBeforePut = getNumOfElems(emptyHash);
		emptyHash.put(newEleme);
		int nOfElemsAfterPut = getNumOfElems(emptyHash);

		// then
		assertEquals(0, nOfElemsBeforePut);
		assertEquals(1, nOfElemsAfterPut);
	}

	private int getNumOfElems(HashTable<?> hash) {
		String fieldNumOfElems = "nElems";
		try {
			Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
			field.setAccessible(true);

			int numOfElems = field.getInt(hash);

			return numOfElems;

		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void input_test_1() {
		for (String el : TestDatas.data1) {
			hashObjectIInterface.put(el);

			assertEquals(el, hashObjectIInterface.get(el));
		}
	}

	@Test
	public void input_test_2() {
		for (String el : TestDatas.data2) {
			hashObjectIInterface.put(el);

			assertEquals(el, hashObjectIInterface.get(el));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void input_test_3() {
		for (String el : TestDatas.data3) {
			hashObjectIInterface.put(el);

			assertEquals(el, hashObjectIInterface.get(el));
		}
	}

	@Test
	public void delete_test_1() {
		for (String el : TestDatas.data1) {
			hashObjectIInterface.put(el);

			assertEquals(el, hashObjectIInterface.get(el));
		}
		for (String el : TestDatas.data1) {
			assertEquals(el, hashObjectIInterface.get(el));
			hashObjectIInterface.delete(el);

			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
		}
	}

	@Test
	public void delete_test_2() {
		for (String el : TestDatas.data2) {
			hashObjectIInterface.put(el);

			assertEquals(el, hashObjectIInterface.get(el));
		}
		for (String el : TestDatas.data2) {
			assertEquals(el, hashObjectIInterface.get(el));
			hashObjectIInterface.delete(el);

			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void delete_test_3() {
		for (String el : TestDatas.data3) {
			hashObjectIInterface.put(el);

			assertEquals(el, hashObjectIInterface.get(el));
		}
		for (String el : TestDatas.data3) {
			assertEquals(el, hashObjectIInterface.get(el));
			hashObjectIInterface.delete(el);

			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
		}
	}

	@Test(expected = IllegalStateException.class)
	public void delete_test_not_element_in() {
		for (String el : TestDatas.data2) {
			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
			hashObjectIInterface.put(el);

			assertEquals(el, hashObjectIInterface.get(el));
		}
		hashObjectIInterface.delete(TestDatas.notInData2);

	}


	@Test
	public void countHashId_test_1() {

		assertEquals(0, hashObject.countLoadFactor(), 0.0);

		for (String el : TestDatas.data4) {
			hashObject.put(el);

		}
		assertTrue(hashObject.countLoadFactor() > 0 && hashObject.countLoadFactor() <= 1);

		for (String el : TestDatas.data4) {
			hashObject.delete(el);

		}
		assertEquals(0, hashObject.countLoadFactor(), 0.0);
	}

	@Test
	public void countHashId_test_2() {

		assertEquals(0, hashObject.countLoadFactor(), 0.0);

		for (String el : TestDatas.data4) {
			hashObject.put(el);
			assertTrue(hashObject.countLoadFactor() > 0 && hashObject.countLoadFactor() <= 1);

		}
		for (String el : TestDatas.data4) {
			assertTrue(hashObject.countLoadFactor() > 0 && hashObject.countLoadFactor() <= 1);
			hashObject.delete(el);

		}
		assertEquals(0, hashObject.countLoadFactor(), 0.0);
	}

	@Test
	public void countHashId_test_3() {

		assertEquals(0, hashObject.countLoadFactor(), 0.0);
		for (String el : TestDatas.data4) {
			hashObject.put(el);
			assertTrue(hashObject.countLoadFactor() > 0 && hashObject.countLoadFactor() <= 1);

		}


		for (String el : TestDatas.data4) {
			assertTrue(hashObject.countLoadFactor() > 0 && hashObject.countLoadFactor() <= 1);
			hashObject.delete(el);

		}

		assertEquals(0, hashObject.countLoadFactor(), 0.0);
	}

	@Test
	public void interface_test_1() {

		HashTable<String> hashTable = hashObjectIInterface;

		for (String el : TestDatas.data4) {
			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
			hashTable.put(el);

			assertEquals(el, hashTable.get(el));
		}
	}

	@Test
	public void interface_test_2() {

		HashTable<String> hashTable = hashObjectIInterface;


		for (String el : TestDatas.data4) {
			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
			hashTable.put(el);

			assertEquals(el, hashTable.get(el));
		}
		try {
			assertNull(hashObjectIInterface.get("not_in_data_4"));

		} catch (IllegalStateException ignored) {

		}

		for (String el : TestDatas.data4) {
			assertEquals(el, hashTable.get(el));
			hashTable.delete(el);

			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void interface_test_3() {

		HashTable<String> hashTable = hashObjectIInterface;

		for (String el : TestDatas.data3) {
			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
			hashTable.put(el);

			assertEquals(el, hashTable.get(el));
		}
		for (String el : TestDatas.data3) {
			assertEquals(el, hashTable.get(el));
			hashTable.delete(el);

			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
		}
	}

	@Test
	public void count_minus_hashid_problem_test() {
		hashObject = new HashQuadraticProbing<String>(1000);
		assertTrue(hashObject.countHashId(minusHashIdWord.hashCode()) >= 0);
		assertTrue(hashObject.countHashId(Integer.MIN_VALUE) >= 0);
	}

	@Test
	public void count_blank_hashid_problem_test() {
		hashObject = new HashQuadraticProbing<String>(1000);
		assertTrue(hashObject.countHashId(blankHashIdWord.hashCode()) >= 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void zero_hash_list_len() {
		hashObject = new HashQuadraticProbing<String>(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void a_b_is_0() {
		hashObject = new HashQuadraticProbing<String>(100, 0, 0);
	}

	@Test
	public void delete_test_find_objects() {

		hashObjectIInterface = new HashDoubleHashing<>();
		for (String el : TestDatas.data4) {
			hashObjectIInterface.put(el);
		}

		for (int i = 0; i < data4.length; i++) {
			hashObjectIInterface.delete(data4[i]);
			for (int j = i + 1; j < data4.length; j++) {
				assertEquals(data4[j], hashObjectIInterface.get(data4[j]));
			}
		}

	}
}
