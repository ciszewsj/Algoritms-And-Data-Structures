package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.data.TestDatas;
import pl.edu.pw.ee.services.HashTable;

import static org.junit.Assert.*;
import static pl.edu.pw.ee.data.TestDatas.blankHashIdWord;
import static pl.edu.pw.ee.data.TestDatas.minusHashIdWord;

public class HashListChainingTest {

	HashTable<String> hashListChaining;

	@Test
	public void input_test_1() {
		hashListChaining = new HashListChaining<String>(1);
		for (String el : TestDatas.data1) {
			assertNull(hashListChaining.get(el));
			hashListChaining.add(el);

			assertEquals(el, hashListChaining.get(el));
		}
	}

	@Test
	public void input_test_2() {
		hashListChaining = new HashListChaining<String>(1);
		for (String el : TestDatas.data2) {
			assertNull(hashListChaining.get(el));
			hashListChaining.add(el);

			assertEquals(el, hashListChaining.get(el));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void input_test_3() {
		hashListChaining = new HashListChaining<String>(1);
		for (String el : TestDatas.data3) {
			assertNull(hashListChaining.get(el));
			hashListChaining.add(el);

			assertEquals(el, hashListChaining.get(el));
		}
	}

	@Test
	public void delete_test_1() {
		hashListChaining = new HashListChaining<String>(1);
		for (String el : TestDatas.data1) {
			assertNull(hashListChaining.get(el));
			hashListChaining.add(el);

			assertEquals(el, hashListChaining.get(el));
		}
		for (String el : TestDatas.data1) {
			assertEquals(el, hashListChaining.get(el));
			hashListChaining.delete(el);

			assertNull(hashListChaining.get(el));
		}
	}

	@Test
	public void delete_test_2() {
		hashListChaining = new HashListChaining<String>(1);
		for (String el : TestDatas.data2) {
			assertNull(hashListChaining.get(el));
			hashListChaining.add(el);

			assertEquals(el, hashListChaining.get(el));
		}
		for (String el : TestDatas.data2) {
			assertEquals(el, hashListChaining.get(el));
			hashListChaining.delete(el);

			assertNull(hashListChaining.get(el));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void delete_test_3() {
		hashListChaining = new HashListChaining<String>(1);
		for (String el : TestDatas.data3) {
			assertNull(hashListChaining.get(el));
			hashListChaining.add(el);

			assertEquals(el, hashListChaining.get(el));
		}
		for (String el : TestDatas.data3) {
			assertEquals(el, hashListChaining.get(el));
			hashListChaining.delete(el);

			assertNull(hashListChaining.get(el));
		}
	}

	@Test(expected = IllegalStateException.class)
	public void delete_test_not_element_in() {
		hashListChaining = new HashListChaining<String>(1);
		for (String el : TestDatas.data2) {
			assertNull(hashListChaining.get(el));
			hashListChaining.add(el);

			assertEquals(el, hashListChaining.get(el));
		}
		hashListChaining.delete(TestDatas.notInData2);

	}


	@Test
	public void countHashId_test_1() {
		HashListChaining<String> hashListChaining = new HashListChaining<String>(1);

		assertEquals(0, hashListChaining.countLoadFactor(), 0.0);

		for (String el : TestDatas.data4) {
			hashListChaining.add(el);

		}
		assertEquals(1, hashListChaining.countLoadFactor(), 0.0);
		for (String el : TestDatas.data4) {
			hashListChaining.delete(el);

		}
		assertEquals(0, hashListChaining.countLoadFactor(), 0.0);
	}

	@Test
	public void countHashId_test_2() {
		HashListChaining<String> hashListChaining = new HashListChaining<String>(5);

		assertEquals(0, hashListChaining.countLoadFactor(), 0.0);

		for (String el : TestDatas.data4) {
			hashListChaining.add(el);
			assertTrue(hashListChaining.countLoadFactor() > 0 && hashListChaining.countLoadFactor() <= 1);

		}
		for (String el : TestDatas.data4) {
			assertTrue(hashListChaining.countLoadFactor() > 0 && hashListChaining.countLoadFactor() <= 1);
			hashListChaining.delete(el);

		}
		assertEquals(0, hashListChaining.countLoadFactor(), 0.0);
	}

	@Test
	public void countHashId_test_3() {
		HashListChaining<String> hashListChaining = new HashListChaining<String>(20);

		assertEquals(0, hashListChaining.countLoadFactor(), 0.0);

		for (String el : TestDatas.data4) {
			hashListChaining.add(el);
			assertTrue(hashListChaining.countLoadFactor() > 0 && hashListChaining.countLoadFactor() <= 1);

		}
		for (String el : TestDatas.data4) {
			assertTrue(hashListChaining.countLoadFactor() > 0 && hashListChaining.countLoadFactor() <= 1);
			hashListChaining.delete(el);

		}
		assertEquals(0, hashListChaining.countLoadFactor(), 0.0);
	}

	@Test
	public void interface_test_1() {
		hashListChaining = new HashListChaining<String>(5);

		HashTable<String> hashTable = hashListChaining;

		for (String el : TestDatas.data4) {
			assertNull(hashTable.get(el));
			hashTable.add(el);

			assertEquals(el, hashTable.get(el));
		}
	}

	@Test
	public void interface_test_2() {
		hashListChaining = new HashListChaining<String>(5);

		HashTable<String> hashTable = hashListChaining;


		for (String el : TestDatas.data4) {
			assertNull(hashTable.get(el));
			hashTable.add(el);

			assertEquals(el, hashTable.get(el));
		}

		assertNull(hashTable.get("not_in_data_4"));

		for (String el : TestDatas.data4) {
			assertEquals(el, hashTable.get(el));
			hashTable.delete(el);

			assertNull(hashTable.get(el));
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void interface_test_3() {
		hashListChaining = new HashListChaining<String>(5);

		HashTable<String> hashTable = hashListChaining;

		for (String el : TestDatas.data3) {
			assertNull(hashTable.get(el));
			hashTable.add(el);

			assertEquals(el, hashTable.get(el));
		}
		for (String el : TestDatas.data3) {
			assertEquals(el, hashTable.get(el));
			hashTable.delete(el);

			assertNull(hashTable.get(el));
		}
	}

	@Test
	public void count_minus_hashid_problem_test() {
		HashListChaining<String> hashListChaining = new HashListChaining<String>(1000);
		assertTrue(hashListChaining.countHashIdHelp(minusHashIdWord) >= 0);
	}

	@Test
	public void count_blank_hashid_problem_test() {
		HashListChaining<String> hashListChaining = new HashListChaining<String>(1000);
		assertTrue(hashListChaining.countHashIdHelp(blankHashIdWord) >= 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void zero_hash_list_len() {
		HashListChaining<String> hashListChaining = new HashListChaining<String>(0);
	}
}
