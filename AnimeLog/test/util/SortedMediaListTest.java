package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Anime;
import data.Anime.Language;
import data.Media;
import data.Media.Type;
import data.Preferences.SortFocus;

/**
 * Test class for SortedMediaList, contains, get, and size tested implicitly.
 * @author Hunter Pruitt
 */
class SortedMediaListTest {

	private static final Anime ANIME_A = new Anime("A", 2005, 0, Language.DUB, Type.SERIES, false, false, "", "", null);
	private static final Anime ANIME_B = new Anime("B", 2004, 0, Language.DUB, Type.SERIES, false, false, "", "", null);
	private static final Anime ANIME_M = new Anime("M", 2003, 0, Language.DUB, Type.SERIES, false, false, "", "", null);
	private static final Anime ANIME_Y = new Anime("Y", 2002, 0, Language.DUB, Type.SERIES, false, false, "", "", null);
	private static final Anime ANIME_Z = new Anime("Z", 2001, 0, Language.DUB, Type.SERIES, false, false, "", "", null);
	
	private static final Anime ANIME_M2 = new Anime("M", 2002, 0, Language.DUB, Type.SERIES, false, false, "", "", null);
	private static final Anime ANIME_OTHER_2003 = new Anime("C", 2003, 0, Language.DUB, Type.SERIES, false, false, "", "", null);
	
	/**	Alphabetically sorted list reference for use in testing */
	private SortedMediaList listAlphabetic;
	
	/**	Year-based sorted list reference for use in testing */
	private SortedMediaList listNumeric;
	
	/**
	 * Resets the list field before each test.
	 */
	@BeforeEach
	void setUp() {
		listAlphabetic = new SortedMediaList(SortFocus.ALPHABETICAL);
		listNumeric = new SortedMediaList(SortFocus.NUMERICAL);
	}

	/**
	 * Asserts that the list is constructed properly
	 */
	@Test
	void testSortedMediaListConstruction() {
		assertEquals(0, listAlphabetic.size());
		assertEquals(0, listNumeric.size());
	}

	/**
	 * Asserts that add and remove functions work properly.
	 */
	@Test
	void testAddRemove() {
		//Add null
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> listAlphabetic.add(null));
		assertEquals("Cannot add null element.", e1.getMessage());

		//Add duplicate
		listAlphabetic.add(ANIME_M);
		assertEquals(1, listAlphabetic.size());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> listAlphabetic.add(ANIME_M));
		assertEquals("Cannot add duplicate element.", e2.getMessage());

		//Add out of order and ensure list is sorted correctly
		listAlphabetic.add(ANIME_B);
		listAlphabetic.add(ANIME_A);
		listAlphabetic.add(ANIME_Z);
		assertEquals(4, listAlphabetic.size());

		assertEquals(ANIME_A, listAlphabetic.get(0));
		assertEquals(ANIME_B, listAlphabetic.get(1));
		assertEquals(ANIME_M, listAlphabetic.get(2));
		assertEquals(ANIME_Z, listAlphabetic.get(3));

		
		//Remove and check that list is maintained correctly
		assertThrows(IndexOutOfBoundsException.class, () -> listAlphabetic.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> listAlphabetic.remove(20));
		
		assertEquals(ANIME_A, listAlphabetic.remove(0));
		assertEquals(ANIME_B, listAlphabetic.get(0));
		assertEquals(ANIME_M, listAlphabetic.remove(1));
		assertEquals(ANIME_Z, listAlphabetic.get(1));
		assertEquals(2, listAlphabetic.size());

		assertEquals(ANIME_Z, listAlphabetic.remove(1));
		assertThrows(IndexOutOfBoundsException.class, () -> listAlphabetic.get(1));

		
		//Test add and remove with Numerically sorted lists too
		//Test add
		listNumeric.add(ANIME_B);
		listNumeric.add(ANIME_A);
		listNumeric.add(ANIME_Z);
		assertEquals(ANIME_Z, listNumeric.get(0));
		assertEquals(ANIME_B, listNumeric.get(1));
		assertEquals(ANIME_A, listNumeric.get(2));

		//Test remove
		assertEquals(ANIME_Z, listNumeric.remove(0));
		assertEquals(ANIME_B, listNumeric.get(0));
		assertEquals(ANIME_A, listNumeric.remove(1));
		assertEquals(ANIME_B, listNumeric.remove(0));
	}
	
	/**
	 * Asserts that add still works in respective lists when some sorting-dependant values are shared
	 */
	@Test
	void testAddSomeMatchingFields() {
		listAlphabetic.add(ANIME_M);
		listAlphabetic.add(ANIME_M2);
		assertEquals(ANIME_M2, listAlphabetic.get(0));
		assertEquals(ANIME_M, listAlphabetic.get(1));

		
		listNumeric.add(ANIME_M);
		listNumeric.add(ANIME_OTHER_2003);
		assertEquals(ANIME_OTHER_2003, listNumeric.get(0));
		assertEquals(ANIME_M, listNumeric.get(1));

	}

	
	/**
	 * Tests the iterable interface by using a for-each loop and ensuring fields are passed as desired
	 */
	@Test
	void testIterator() {
		listAlphabetic.add(ANIME_A);
		listAlphabetic.add(ANIME_B);
		listAlphabetic.add(ANIME_M);
		listAlphabetic.add(ANIME_Y);
		listAlphabetic.add(ANIME_Z);

		String expString = "";
		for (Media a : listAlphabetic) {
			expString += a.getTitle() + " ";
		}
	
		assertEquals("A B M Y Z ", expString);
	}

	
	
	/**
	 * Tests that indexOf returns correct index
	 */
	@Test
	void testIndexOf() {
		//Test on empty list
		assertEquals(-1, listAlphabetic.indexOf(ANIME_A));

		listAlphabetic.add(ANIME_A);
		listAlphabetic.add(ANIME_M);
		listAlphabetic.add(ANIME_Z);
		listAlphabetic.add(ANIME_B);
		listAlphabetic.add(ANIME_Y);

		//Test in general
		assertEquals(0, listAlphabetic.indexOf(ANIME_A));
		assertEquals(1, listAlphabetic.indexOf(ANIME_B));
		assertEquals(2, listAlphabetic.indexOf(ANIME_M));
		assertEquals(3, listAlphabetic.indexOf(ANIME_Y));
		assertEquals(4, listAlphabetic.indexOf(ANIME_Z));
		assertEquals(-1, listAlphabetic.indexOf(new Anime("!", 1999, 0, Language.DUB, Type.SPECIAL, false, false, "", "", "")));

		//Test after removal
		listAlphabetic.remove(3);
		assertEquals(3, listAlphabetic.indexOf(ANIME_Z));
		
		listAlphabetic.remove(0);
		assertEquals(0, listAlphabetic.indexOf(ANIME_B));	
	}
}