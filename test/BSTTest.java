import static org.junit.Assert.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class BSTTest {
	
	private static BST<String> list = new BST<String>();
	private static ArrayList<String> expected = new ArrayList<String>();	
	
	
	@BeforeClass
	public static void setUp() {
		list.insert("Norge");
		list.insert("Tyskland");
		list.insert("Danmark");
		list.insert("Østerrike");
		expected.add("Østerrike");
		expected.add("Tyskland");
		expected.add("Norge");
	}


	@Test
	public void bstTest() {
		assertThat(list.getSize(), is(equalTo(4)));
	}

	@Test
	public void bstGetPathTest() {
		assertThat(list.getPath(list.root.right.right.element), is(equalTo(expected)));
	}
	
	@Test
	public void bstLeafTest(){
		assertThat(list.isLeaf(list.root.right.right.element), is(true));
	}
	
	@Test
	public void deleteTest(){
		list.delete("Tyskland");
		assertThat(list.size, is(equalTo(3)));
	}
	
	@Test
	public void getNodeTest(){
		assertThat(list.getNode("Norge").element, is(equalTo("Norge")));
	}
	
	@Test
	public void searchTest(){
		assertThat(list.search("Polen"), is(false));
		
	}
	
	@Test
	public void searchTest2(){
		assertThat(list.search("Norge"), is(true));
	}
	
	
	
}


