package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for SortedList, contains, get, and size tested implicitly.
 * @author Hunter Pruitt
 */
class SortedListTest {

	/**	List reference for use in testing */
	private SortedList<String> list;
	
	/**
	 * Resets the list field before each test.
	 */
	@BeforeEach
	void setUp() {
		list = new SortedList<String>();
	}

	/**
	 * Asserts that the list is constructed properly
	 */
	@Test
	void testSortedList() {
		assertEquals(0, list.size());
	}

	/**
	 * Asserts that add and remove functions work properly.
	 */
	@Test
	void testAddRemove() {
		//Add null
		assertThrows(NullPointerException.class, () -> list.add(null));
		
		//Add duplicate
		list.add("M");
		assertEquals(1, list.size());
		assertThrows(IllegalArgumentException.class, () -> list.add("M"));
		
		//Add out of order and ensure list is sorted correctly
		list.add("B");
		list.add("A");
		list.add("Z");
		assertEquals(4, list.size());

		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("M", list.get(2));
		assertEquals("Z", list.get(3));

		
		//Remove and check that list is maintained correctly
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(20));
		
		assertEquals("A", list.remove(0));
		assertEquals("B", list.get(0));
		assertEquals("M", list.remove(1));
		assertEquals("Z", list.get(1));
		assertEquals(2, list.size());

		assertEquals("Z", list.remove(1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));

	}
	


}