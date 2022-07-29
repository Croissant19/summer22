package manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Anime;
import data.Anime.Language;
import data.Anime.Type;
import data.Preferences.SortFocus;
import util.SortedAnimeList;

/**
 * Tests the Manager class to ensure accuracy in various aspects
 * @author Hunter Pruitt
 */
class ManagerTest {
	
	/** Location of file used for testing */
	private final String TEST_FILE = "test-files/ThreeWorkingImports.txt";
	
	/** Pointer to sorted anime list, refreshed with each test */
	private SortedAnimeList list;

	/**
	 * Loads the test file before each test to ensure tests do not interfere with each other
	 */
	@BeforeEach
	void setUp() {
		Manager.getInstance().processFile(TEST_FILE);
		list = Manager.getInstance().getAnimeList();
	}

	/**
	 * Ensures imported data is correct.
	 */
	@Test
	void testImport() {
		assertEquals(3, list.size());
		Anime a1 = list.get(0);

		//Test each entry of the list
		//Entry 1
		assertAll(
				() -> assertEquals("Gurren Lagann", a1.getTitle()),
				() -> assertEquals(2007, a1.getYear()),
				() -> assertEquals(26, a1.getCount()),
				() -> assertEquals("Sub", a1.getLanguage()),
				() -> assertEquals("Series", a1.getType()),
				() -> assertTrue(a1.isFinished()),
				() -> assertFalse(a1.isDropped()),
				() -> assertEquals("Hiroyuki Imaishi", a1.getDirector()),
				() -> assertEquals("Very good op!", a1.getNotes())
				);
		
		//Entry 2
		Anime a2 = list.get(1);
		assertAll(
				() -> assertEquals("Naruto", a2.getTitle()),
				() -> assertEquals(2002, a2.getYear()),
				() -> assertEquals(0, a2.getCount()),
				() -> assertEquals("Other", a2.getLanguage()),
				() -> assertEquals("Special", a2.getType()),
				() -> assertFalse(a2.isFinished()),
				() -> assertTrue(a2.isDropped()),
				() -> assertEquals("", a2.getDirector()),
				() -> assertEquals("W\nO\nA\nH", a2.getNotes())
				);

		//Entry 3
		Anime a3 = list.get(2);
		assertAll(
				() -> assertEquals("One Piece", a3.getTitle()),
				() -> assertEquals(1999, a3.getYear()),
				() -> assertEquals(100, a3.getCount()),
				() -> assertEquals("Dub", a3.getLanguage()),
				() -> assertEquals("Series", a3.getType()),
				() -> assertFalse(a3.isFinished()),
				() -> assertFalse(a3.isDropped()),
				() -> assertEquals("multiple", a3.getDirector()),
				() -> assertEquals("Watching with brother", a3.getNotes())
				);
	}
	
	/**
	 * Ensures returned array is organized as expected, and correct in title based retrieval
	 */
	@Test
	void testGetAllAnimeAsArrayTitleBased() {
		Manager.getInstance().setAnimeList(SortFocus.ALPHABETICAL);

		Object[][] array = Manager.getInstance().getAllAnimeAsArray();
		
		//Test each column, one at a time
		assertAll(
				() -> assertEquals(2007, array[0][0]),
				() -> assertEquals(2002, array[1][0]),
				() -> assertEquals(1999, array[2][0]),

				() -> assertEquals("Gurren Lagann", array[0][1]),
				() -> assertEquals("Naruto", array[1][1]),
				() -> assertEquals("One Piece", array[2][1]),

				() -> assertEquals(26, array[0][2]),
				() -> assertEquals(0, array[1][2]),
				() -> assertEquals(100, array[2][2])
				);
	}

	/**
	 * Ensures returned array is organized as expected, and correct in year based retrieval
	 */
	@Test
	void testGetAllAnimeAsArrayYearBased() {
		Manager.getInstance().setAnimeList(SortFocus.NUMERICAL);
		
		Object[][] array = Manager.getInstance().getAllAnimeAsArray();
		
		//Test each column, one at a time
		assertAll(
				() -> assertEquals(1999, array[0][0]),
				() -> assertEquals(2002, array[1][0]),
				() -> assertEquals(2007, array[2][0]),

				() -> assertEquals("One Piece", array[0][1]),
				() -> assertEquals("Naruto", array[1][1]),
				() -> assertEquals("Gurren Lagann", array[2][1]),

				() -> assertEquals(100, array[0][2]),
				() -> assertEquals(0, array[1][2]),
				() -> assertEquals(26, array[2][2])
				);
	}
	
	/** 
	 * Tests changes made to the list and that they are reflected properly
	 */
	@Test
	void testEditAnimeList() {
		//Test exceptions for adding
		assertThrows(NullPointerException.class, ()-> Manager.getInstance().addAnime(null));
		Anime duplicate = new Anime("Gurren Lagann", 2007, 0, Language.DUB, Type.SPECIAL, false, false, "", "");
		assertThrows(IllegalArgumentException.class, ()-> Manager.getInstance().addAnime(duplicate));
		
		//Test exception for removing
		assertThrows(IndexOutOfBoundsException.class, ()-> Manager.getInstance().removeAnime(10));
		
		//Test add
		Anime testAnime = new Anime("a", 1999, 0, Language.DUB, Type.SERIES, false, false, null, null);
		Manager.getInstance().addAnime(testAnime);
		assertEquals(4, list.size());
		assertEquals(testAnime, list.get(0));

		//Test remove
		Manager.getInstance().removeAnime(0);
		assertEquals(3, list.size());
		assertEquals(new Anime("Gurren Lagann", 2007, 26, Language.SUB, Type.SPECIAL, true, false, "Hiroyuki Imaishi", "Very good op!"), 
				list.get(0));
	}

	/**
	 * Tests that stats are calculated correctly
	 */
	@Test
	void testGetStatistics() {
		//Test stats with anime
		assertAll(
				() -> assertEquals("3", Manager.getInstance().getEntryCount()),
				() -> assertEquals("126", Manager.getInstance().getCountSum()),
				() -> assertEquals("Tie", Manager.getInstance().getFavoredLanguageAndPercent()),
				() -> assertEquals("2", Manager.getInstance().getNumSeries()),
				() -> assertEquals("1", Manager.getInstance().getNumSpecial()),
				() -> assertEquals("33%", Manager.getInstance().getPercentDropped()),
				() -> assertEquals("33%", Manager.getInstance().getPercentFinished())
				);

		
		//Test stats after removing all anime (should be defaults)
		//Including further tests for getFavoredLanguageAndPercent()
		Manager.getInstance().removeAnime(0);
		assertEquals("Dub 100%", Manager.getInstance().getFavoredLanguageAndPercent()); //Is dub 100% here because Other languages arent counted unless some anime is watched ( count != 0)
		Manager.getInstance().removeAnime(0);
		assertEquals("Dub 100%", Manager.getInstance().getFavoredLanguageAndPercent());
		Manager.getInstance().removeAnime(0);
		
		assertAll(
				() -> assertEquals("0", Manager.getInstance().getEntryCount()),
				() -> assertEquals("0", Manager.getInstance().getCountSum()),
				() -> assertEquals("N/A", Manager.getInstance().getFavoredLanguageAndPercent()),
				() -> assertEquals("0", Manager.getInstance().getNumSeries()),
				() -> assertEquals("0", Manager.getInstance().getNumSpecial()),
				() -> assertEquals("N/A", Manager.getInstance().getPercentDropped()),
				() -> assertEquals("N/A", Manager.getInstance().getPercentFinished())
				);
	}

}
