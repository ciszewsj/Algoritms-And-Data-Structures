package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static pl.edu.pw.ee.Datas.*;


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
		assertEquals(post_order_data_1, tree.getPostOrder());
	}

	@Test
	public void get_in_order_test() {
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			tree.put(keyIntValueString.key, keyIntValueString.value);
		}
		assertEquals(in_order_data_1, tree.getInOrder());
	}

	@Test
	public void get_pre_order_test() {
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			tree.put(keyIntValueString.key, keyIntValueString.value);
		}
		assertEquals(pre_order_data_1, tree.getPreOrder());
	}

	@Test
	public void delete_test() {
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			tree.put(keyIntValueString.key, keyIntValueString.value);
		}
		int i = data_1.length;
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			tree.deleteMax();
			i -= 1;
			for (int j = 0; j < i; j++) {


				tree.get(data_1[j].key);
			}
			for (int j = 1; j < (data_1.length - i - 1); j++) {
				try {
					tree.get(data_1[data_1.length - j].key);
					fail();
				} catch (IllegalStateException ignored) {

				}
			}
		}
	}

	@Test
	public void deleteAndAddTestOrders() {
		for (Datas.KeyIntValueString keyIntValueString : Datas.data_1) {
			tree.put(keyIntValueString.key, keyIntValueString.value);
		}
		assertEquals(post_order_data_1, tree.getPostOrder());
		assertEquals(in_order_data_1, tree.getInOrder());
		assertEquals(pre_order_data_1, tree.getPreOrder());

		tree.deleteMax();

		tree.put(data_1[data_1.length - 1].key, data_1[data_1.length - 1].value);

		assertEquals(post_order_data_1, tree.getPostOrder());
		assertEquals(in_order_data_1, tree.getInOrder());
		assertEquals(pre_order_data_1, tree.getPreOrder());


	}

	@Test
	public void oneElementDelete() {
		tree.put(data_1[0].key, data_1[0].value);
		tree.deleteMax();
		try {
			tree.get(data_1[0].key);
			fail();
		} catch (IllegalStateException ignored) {

		}
	}


	@Test(expected = IllegalStateException.class)
	public void empty_delete_test() {
		tree.deleteMax();

	}
}
