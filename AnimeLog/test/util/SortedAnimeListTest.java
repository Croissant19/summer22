package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import anime.Anime;
import anime.Anime.Language;
import anime.Anime.Type;
import util.SortedAnimeList.SortFocus;

/**
 * Test class for SortedAnimeList, contains, get, and size tested implicitly.
 * @author Hunter Pruitt
 */
class SortedAnimeListTest {

	private static final Anime ANIME_A = new Anime("A", 2005, 0, Language.DUB, Type.SERIES, false, false, "", null);
	private static final Anime ANIME_B = new Anime("B", 2004, 0, Language.DUB, Type.SERIES, false, false, "", null);
	private static final Anime ANIME_M = new Anime("M", 2003, 0, Language.DUB, Type.SERIES, false, false, "", null);
	private static final Anime ANIME_Y = new Anime("Y", 2002, 0, Language.DUB, Type.SERIES, false, false, "", null);
	private static final Anime ANIME_Z = new Anime("Z", 2001, 0, Language.DUB, Type.SERIES, false, false, "", null);
	
	
	
	/**	Alphabetically sorted list reference for use in testing */
	private SortedAnimeList listAlphabetic;
	
	/**	Year-based sorted list reference for use in testing */
	private SortedAnimeList listNumeric;
	
	/**
	 * Resets the list field before each test.
	 */
	@BeforeEach
	void setUp() {
		listAlphabetic = new SortedAnimeList(SortFocus.ALPHABETICAL);
		listNumeric = new SortedAnimeList(SortFocus.NUMERICAL);
	}

	/**
	 * Asserts that the list is constructed properly
	 */
	@Test
	void testSortedAnimeListConstruction() {
		assertEquals(0, listAlphabetic.size());
		assertEquals(0, listNumeric.size());
	}

	/**
	 * Asserts that add and remove functions work properly.
	 */
	@Test
	void testAddRemove() {
		//Add null
		assertThrows(NullPointerException.class, () -> listAlphabetic.add(null));
		
		//Add duplicate
		listAlphabetic.add(ANIME_M);
		assertEquals(1, listAlphabetic.size());
		assertThrows(IllegalArgumentException.class, () -> listAlphabetic.add(ANIME_M));

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
		for (Anime a : listAlphabetic) {
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
		assertEquals(-1, listAlphabetic.indexOf(new Anime("!", 1999, 0, Language.DUB, Type.SPECIAL, false, false, "", "")));

		//Test after removal
		listAlphabetic.remove(3);
		assertEquals(3, listAlphabetic.indexOf(ANIME_Z));
		
		listAlphabetic.remove(0);
		assertEquals(0, listAlphabetic.indexOf(ANIME_B));	
	}
}