package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import anime.Anime;
import anime.Anime.Language;
import anime.Anime.Type;
import manager.Manager.SortFocus;

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
	
	
	
	/**	List reference for use in testing */
	private SortedAnimeList list;
	
	/**
	 * Resets the list field before each test.
	 */
	@BeforeEach
	void setUp() {
		list = new SortedAnimeList(SortFocus.ALPHABETICAL);
	}

	/**
	 * Asserts that the list is constructed properly
	 */
	@Test
	void testSortedAnimeListConstruction() {
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
		list.add(ANIME_M);
		assertEquals(1, list.size());
		assertThrows(IllegalArgumentException.class, () -> list.add(ANIME_M));
		
		//Add out of order and ensure list is sorted correctly
		list.add(ANIME_B);
		list.add(ANIME_A);
		list.add(ANIME_Z);
		assertEquals(4, list.size());

		assertEquals(ANIME_A, list.get(0));
		assertEquals(ANIME_B, list.get(1));
		assertEquals(ANIME_M, list.get(2));
		assertEquals(ANIME_Z, list.get(3));

		
		//Remove and check that list is maintained correctly
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(20));
		
		assertEquals(ANIME_A, list.remove(0));
		assertEquals(ANIME_B, list.get(0));
		assertEquals(ANIME_M, list.remove(1));
		assertEquals(ANIME_Z, list.get(1));
		assertEquals(2, list.size());

		assertEquals(ANIME_Z, list.remove(1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));

	}
	
	/**
	 * Tests the iterable interface by using a for-each loop and ensuring fields are passed as desired
	 */
	@Test
	void testIterator() {
		list.add(ANIME_A);
		list.add(ANIME_B);
		list.add(ANIME_M);
		list.add(ANIME_Y);
		list.add(ANIME_Z);

		String expString = "";
		for (Anime a : list) {
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
		assertEquals(-1, list.indexOf(ANIME_A));

		list.add(ANIME_A);
		list.add(ANIME_M);
		list.add(ANIME_Z);
		list.add(ANIME_B);
		list.add(ANIME_Y);

		//Test in general
		assertEquals(0, list.indexOf(ANIME_A));
		assertEquals(1, list.indexOf(ANIME_B));
		assertEquals(2, list.indexOf(ANIME_M));
		assertEquals(3, list.indexOf(ANIME_Y));
		assertEquals(4, list.indexOf(ANIME_Z));
		assertEquals(-1, list.indexOf(new Anime("!", 1999, 0, Language.DUB, Type.SPECIAL, false, false, "", "")));

		//Test after removal
		list.remove(3);
		assertEquals(3, list.indexOf(ANIME_Z));
		
		list.remove(0);
		assertEquals(0, list.indexOf(ANIME_B));	
	}
}