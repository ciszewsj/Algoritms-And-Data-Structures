package pl.edu.pw.ee;

import pl.edu.pw.ee.data.TestDatas;
import pl.edu.pw.ee.services.HashTable;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static pl.edu.pw.ee.data.TestDatas.data4;

public class UnifiedTests {

	public void input_test_1(HashTable<String> hashObjectIInterface) {
		for (String el : TestDatas.data1) {
			hashObjectIInterface.put(el);
			assertEquals(el, hashObjectIInterface.get(el));
		}
	}

	public void input_test_2(HashTable<String> hashObjectIInterface) {
		for (String el : TestDatas.data2) {
			hashObjectIInterface.put(el);

			assertEquals(el, hashObjectIInterface.get(el));
		}
	}

	public void input_test_3(HashTable<String> hashObjectIInterface) {
		for (String el : TestDatas.data3) {
			hashObjectIInterface.put(el);

			assertEquals(el, hashObjectIInterface.get(el));
		}
	}


	public void delete_test_1(HashTable<String> hashObjectIInterface) {
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

	public void delete_test_2(HashTable<String> hashObjectIInterface) {
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

	public void delete_test_3(HashTable<String> hashObjectIInterface) {
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

	public void delete_test_not_element_in(HashTable<String> hashObjectIInterface) {
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

	public void interface_test_1(HashTable<String> hashObjectIInterface) {

		for (String el : TestDatas.data4) {
			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
			hashObjectIInterface.put(el);

			assertEquals(el, hashObjectIInterface.get(el));
		}
	}

	public void interface_test_2(HashTable<String> hashObjectIInterface) {


		for (String el : TestDatas.data4) {
			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
			hashObjectIInterface.put(el);

			assertEquals(el, hashObjectIInterface.get(el));
		}
		try {
			assertNull(hashObjectIInterface.get("not_in_data_4"));

		} catch (IllegalStateException ignored) {

		}
		for (String el : TestDatas.data4) {
			assertEquals(el, hashObjectIInterface.get(el));
			hashObjectIInterface.delete(el);

			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
		}

	}

	public void interface_test_3(HashTable<String> hashObjectIInterface) {

		for (String el : TestDatas.data3) {
			try {
				assertNull(hashObjectIInterface.get(el));

			} catch (IllegalStateException ignored) {

			}
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

	public void delete_test_find_objects(HashTable<String> hashObjectIInterface) {
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

	public void should_CorrectlyAddNewElems_WhenNotExistInHashTable(HashTable<String> emptyHash) {
		String newEleme = "nothing special";

		int nOfElemsBeforePut = getNumOfElems(emptyHash);
		emptyHash.put(newEleme);
		int nOfElemsAfterPut = getNumOfElems(emptyHash);

		assertEquals(0, nOfElemsBeforePut);
		assertEquals(1, nOfElemsAfterPut);
	}

	private int getNumOfElems(HashTable<String> hash) {
		String fieldNumOfElems = "nElems";
		try {
			System.out.println(hash.getClass().getSuperclass().getName());
			Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
			field.setAccessible(true);

			return field.getInt(hash);

		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public void countHashId_test_1(HashTable<String> hashObjectIInterface) {

		assertEquals(0, getNumOfElems(hashObjectIInterface), 0.0);

		for (String el : TestDatas.data4) {
			hashObjectIInterface.put(el);

		}
		assertTrue(getNumOfElems(hashObjectIInterface) > 0 && getNumOfElems(hashObjectIInterface) <= 1);

		for (String el : TestDatas.data4) {
			hashObjectIInterface.delete(el);

		}
		assertEquals(0, getNumOfElems(hashObjectIInterface), 0.0);
	}

	public void countHashId_test_2(HashTable<String> hashObjectIInterface) {

		assertEquals(0, getNumOfElems(hashObjectIInterface), 0.0);

		for (String el : TestDatas.data4) {
			hashObjectIInterface.put(el);
			assertTrue(getNumOfElems(hashObjectIInterface) > 0 && getNumOfElems(hashObjectIInterface) <= 1);

		}
		for (String el : TestDatas.data4) {
			assertTrue(getNumOfElems(hashObjectIInterface) > 0 && getNumOfElems(hashObjectIInterface) <= 1);
			hashObjectIInterface.delete(el);

		}
		assertEquals(0, getNumOfElems(hashObjectIInterface), 0.0);
	}

	public void countHashId_test_3(HashTable<String> hashObjectIInterface) {

		assertEquals(0, getNumOfElems(hashObjectIInterface), 0.0);
		for (String el : TestDatas.data4) {
			hashObjectIInterface.put(el);
			assertTrue(getNumOfElems(hashObjectIInterface) > 0 && getNumOfElems(hashObjectIInterface) <= 1);

		}


		for (String el : TestDatas.data4) {
			assertTrue(getNumOfElems(hashObjectIInterface) > 0 && getNumOfElems(hashObjectIInterface) <= 1);
			hashObjectIInterface.delete(el);

		}

		assertEquals(0, getNumOfElems(hashObjectIInterface), 0.0);
	}

}
