import static org.junit.Assert.*;

import org.junit.Test;


public class TwoWayLinkedListTest {

/*	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/
	
	
	
	@Test
	public void twoWayLinkedListTest(){
		TwoWayLinkedList<String> list = new TwoWayLinkedList<String>();
		list.add(0, "Norge");
		assertEquals("Norge", list.getFirst());
	}
	
	@Test
	public void flereNoderTest(){
		TwoWayLinkedList<String> list = new TwoWayLinkedList<String>();
		list.add(0, "Norge");
		list.add(1, "Tyskland");
		list.add(2, "Italia");
		list.add(3, "Østerrike");
		list.add(4, "Danmark");
		assertEquals("Østerrike", list.get(3));

	}

	@Test
	public void testingAddFirstMethod(){
		TwoWayLinkedList<String> list = new TwoWayLinkedList<String>();
		list.add(0, "Norge");
		list.add(1, "Tyskland");
		list.add(2, "Italia");
		list.add(3, "Østerrike");
		list.add(4, "Danmark");
		list.addFirst("Serbia");
		assertEquals("Serbia", list.getFirst());
	}
	
	@Test
	public void testingAddLastMethod(){
		TwoWayLinkedList<String> list = new TwoWayLinkedList<String>();
		list.add(0, "Norge");
		list.add(1, "Tyskland");
		list.add(2, "Italia");
		list.add(3, "Østerrike");
		list.add(4, "Danmark");
		list.addLast("Serbia");
		assertEquals("Serbia", list.getLast());
	}
	
	@Test
	public void testingSizeMethod(){
		TwoWayLinkedList<String> list = new TwoWayLinkedList<String>();
		list.add(0, "Norge");
		list.add(1, "Tyskland");
		list.add(2, "Italia");
		list.add(3, "Østerrike");
		list.add(4, "Danmark");
		assertEquals(5, list.size());
	}
	
	@Test
	public void testingRemove(){
		TwoWayLinkedList<String> list = new TwoWayLinkedList<String>();
		list.add(0, "Norge");
		list.add(1, "Tyskland");
		list.add(2, "Italia");
		list.add(3, "Østerrike");
		list.add(4, "Danmark");
		list.remove(3);
		assertEquals("Danmark", list.get(3));
	}
	@Test
	public void testingSet(){
		TwoWayLinkedList<String> list = new TwoWayLinkedList<String>();
		list.add(0, "Norge");
		list.add(1, "Tyskland");
		list.add(2, "Italia");
		list.add(3, "Østerrike");
		list.add(4, "Danmark");
		list.set(3, "Finland");
		assertEquals("Finland", list.get(3));
	}
	@Test
	public void testingRemoveFirst(){
		TwoWayLinkedList<String> list = new TwoWayLinkedList<String>();
		list.add(0, "Norge");
		list.add(1, "Tyskland");
		list.add(2, "Italia");
		list.add(3, "Østerrike");
		list.add(4, "Danmark");
		list.removeFirst();
		assertEquals("Tyskland", list.get(0));
	}
}
