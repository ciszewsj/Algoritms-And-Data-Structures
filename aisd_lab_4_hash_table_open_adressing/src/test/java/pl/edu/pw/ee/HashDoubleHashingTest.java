package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.HashTable;

import static org.junit.Assert.assertTrue;
import static pl.edu.pw.ee.data.TestDatas.blankHashIdWord;
import static pl.edu.pw.ee.data.TestDatas.minusHashIdWord;

public class HashDoubleHashingTest {

	HashTable<String> hashObjectIInterface;

	HashDoubleHashing<String> hashObject;

	UnifiedTests unifiedTests;

	@Before
	public void init() {
		hashObjectIInterface = new HashDoubleHashing<>(1);
		hashObject = new HashDoubleHashing<>(1);
		unifiedTests = new UnifiedTests();

	}

	@Test(expected = IllegalArgumentException.class)
	public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
		int initialSize = 0;

		new HashDoubleHashing<>(initialSize);

		assert false;
	}

	@Test
	public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
		unifiedTests.should_CorrectlyAddNewElems_WhenNotExistInHashTable(hashObjectIInterface);
	}

	@Test
	public void input_test_1() {
		unifiedTests.input_test_1(hashObjectIInterface);
	}

	@Test
	public void input_test_2() {
		unifiedTests.input_test_2(hashObjectIInterface);
	}

	@Test(expected = IllegalArgumentException.class)
	public void input_test_3() {
		unifiedTests.input_test_3(hashObjectIInterface);
	}

	@Test
	public void delete_test_1() {
		unifiedTests.delete_test_1(hashObjectIInterface);
	}

	@Test
	public void delete_test_2() {
		unifiedTests.delete_test_2(hashObjectIInterface);
	}

	@Test(expected = IllegalArgumentException.class)
	public void delete_test_3() {
		unifiedTests.delete_test_3(hashObjectIInterface);
	}

	@Test(expected = IllegalStateException.class)
	public void delete_test_not_element_in() {
		unifiedTests.delete_test_not_element_in(hashObjectIInterface);
	}

	@Test
	public void countHashId_test_1() {
		unifiedTests.countHashId_test_1(hashObjectIInterface);
	}

	@Test
	public void countHashId_test_2() {
		unifiedTests.countHashId_test_2(hashObjectIInterface);
	}

	@Test
	public void countHashId_test_3() {
		unifiedTests.countHashId_test_3(hashObjectIInterface);
	}

	@Test
	public void interface_test_1() {
		unifiedTests.interface_test_1(hashObjectIInterface);
	}

	@Test
	public void interface_test_2() {

		unifiedTests.interface_test_2(hashObjectIInterface);
	}

	@Test(expected = IllegalArgumentException.class)
	public void interface_test_3() {

		unifiedTests.interface_test_3(hashObjectIInterface);
	}

	@Test
	public void count_minus_hashid_problem_test() {
		hashObject = new HashDoubleHashing<>(1000);
		assertTrue(hashObject.countHashId(minusHashIdWord.hashCode()) >= 0);
		assertTrue(hashObject.countHashId(Integer.MIN_VALUE) >= 0);
	}

	@Test
	public void count_blank_hashid_problem_test() {
		hashObject = new HashDoubleHashing<>(1000);
		assertTrue(hashObject.countHashId(blankHashIdWord.hashCode()) >= 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void zero_hash_list_len() {
		hashObject = new HashDoubleHashing<>(0);
	}

	@Test
	public void delete_test_find_objects() {
		unifiedTests.delete_test_find_objects(hashObjectIInterface);
	}
}
