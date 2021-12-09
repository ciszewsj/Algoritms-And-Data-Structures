package pl.edu.pw.ee;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static pl.edu.pw.ee.HuffTree.generateHuffTreeFromString;

public class HuffTreeTest {

	HuffTree huffTree;

	@Test
	public void increase_test() {
		char c = 'c';
		huffTree = new HuffTree(c);
		assertEquals(1, huffTree.getNumberOfElements());
		huffTree.increase();
		assertEquals(2, huffTree.getNumberOfElements());
		assertEquals(c, huffTree.getValue());
	}

	@Test
	public void build_tree_from_string_test() {
		List<HuffTree> huffList = new ArrayList<>();
		for (int i = 0; i < 128; i++) {
			huffList.add(new HuffTree((char) (i)));
		}
		while (huffList.size() > 1) {
			Collections.sort(huffList);
			HuffTree min1 = huffList.get(0);
			huffList.remove(min1);
			HuffTree min2 = huffList.get(0);
			huffList.remove(min2);
			huffList.add(new HuffTree(min1, min2));
		}
		HuffTree huffTree = huffList.get(0);
		String huffDescription = huffTree.treeToString();
		HuffTree newHuffTree = generateHuffTreeFromString(huffDescription);
		assertEquals(huffTree.treeToString(), newHuffTree.treeToString());
	}

	@Test
	public void build_tree_from_1_element_string_test() {
		char c = 'a';
		huffTree = new HuffTree(c);
		HuffTree newHuffTree = generateHuffTreeFromString(huffTree.treeToString());
		assertEquals(c, newHuffTree.getCharByIndex("0"));
		assertEquals(huffTree.treeToString(), newHuffTree.treeToString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void generate_huff_null_test() {
		generateHuffTreeFromString(null);
	}
}
