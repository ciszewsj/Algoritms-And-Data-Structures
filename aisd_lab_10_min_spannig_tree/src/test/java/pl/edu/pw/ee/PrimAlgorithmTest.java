package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.MinSpanningTree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.edu.pw.ee.datas.Datas.*;

public class PrimAlgorithmTest {

	private MinSpanningTree minSpanningTree;

	@Before
	public void init() {
		minSpanningTree = new PrimAlgorithm();
	}

	@Test
	public void small_file_test() {
		assertEquals(small_data_result, minSpanningTree.findMST(small_data_file));
	}

	@Test(expected = IllegalStateException.class)
	public void file_not_exists_test() {
		minSpanningTree.findMST(not_existing_file);
	}

	@Test
	public void doubled_line_file_test() {
		assertEquals(doubled_line_result, minSpanningTree.findMST(doubled_line_file));
	}

	@Test(expected = IllegalArgumentException.class)
	public void blank_line_in_middle_of_file_test() {
		minSpanningTree.findMST(blank_line_in_middle_of_file_file);
	}

	@Test
	public void both_side_test() {
		assertEquals(both_side_result, minSpanningTree.findMST(both_side_file));
	}

	@Test
	public void lab_graph_test() {
		assertEquals(lab_graph_result, minSpanningTree.findMST(lab_graph_file));
	}

	@Test(expected = IllegalArgumentException.class)
	public void to_few_argument_in_line_test() {
		minSpanningTree.findMST(to_few_argument_in_line_file);
	}

	@Test(expected = IllegalArgumentException.class)
	public void to_much_argument_in_line_test() {
		minSpanningTree.findMST(to_much_argument_in_line_file);
	}

	@Test(expected = IllegalStateException.class)
	public void spliced_graph_test() {
		minSpanningTree.findMST(spliced_graph_file);
	}

	@Test(expected = IllegalArgumentException.class)
	public void minus_values_test() {
		minSpanningTree.findMST(minus_values_file);
	}

	@Test(expected = IllegalArgumentException.class)
	public void same_vertex_test() {
		minSpanningTree.findMST(same_vertex_file);
	}
}
