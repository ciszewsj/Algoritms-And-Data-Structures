package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RedBlackTreeTest {

	RedBlackTree<Integer, String> tree;

	@Before
	public void init() {
		tree = new RedBlackTree<>();
	}

	@Test
	public void get_post_order_test() {
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			tree.put(keyIntValueString.key, keyIntValueString.value);
		}
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			assertEquals(keyIntValueString.value, tree.get(keyIntValueString.key));
		}
	}

	@Test(expected = IllegalStateException.class)
	public void delete_test() {
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			tree.put(keyIntValueString.key, keyIntValueString.value);
		}
		System.out.println(tree.getInOrder());
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			tree.deleteMax();
			System.out.println(tree.getInOrder());
		}
		System.out.println(tree.getInOrder());
	}

	@Test(expected = IllegalStateException.class)
	public void empty_delete_test() {
		tree.deleteMax();

	}
}
