package manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Anime;
import data.Anime.Language;
import data.Manga;
import data.Media.MediaType;
import data.Media.Type;
import data.Preferences.ColorMethod;
import data.Preferences.SortFocus;
import util.SortedMediaList;

/**
 * Tests the Manager class to ensure accuracy in various aspects
 * @author Hunter Pruitt
 */
class ManagerTest {
	
	/** Location of file used for testing */
	private final String TEST_FILE = "test-files/SixWorkingImports.txt";
	
	/** Pointer to current focus list, refreshed with each test */
	private SortedMediaList currentList;
	

	/**
	 * Loads the test file before each test to ensure tests do not interfere with each other
	 */
	@BeforeEach
	void setUp() {
		Manager.getInstance().processFile(TEST_FILE);
	}

	/**
	 * Ensures imported data is correct.
	 */
	@Test
	void testImport() {
		
		//Anime tests
		Manager.getInstance().setCurrentList(MediaType.ANIME);
		currentList = Manager.getInstance().getList();
		
		assertEquals(3, currentList.size());
		Anime a1 = (Anime) currentList.get(0);

		//Test each entry of the list
		//Entry 1
		assertAll(
				() -> assertEquals("Gurren Lagann", a1.getTitle()),
				() -> assertEquals(2007, a1.getYear()),
				() -> assertEquals(27, a1.getCount()),
				() -> assertEquals("Sub", a1.getLanguage()),
				() -> assertEquals("Series", a1.getType()),
				() -> assertTrue(a1.isFinished()),
				() -> assertFalse(a1.isDropped()),
				() -> assertEquals("Hiroyuki Imaishi", a1.getDirector()),
				() -> assertEquals("Gainax", a1.getStudio()),
				() -> assertEquals("Very good op!", a1.getNotes())
				);
		
		//Entry 2
		Anime a2 = (Anime) currentList.get(1);
		assertAll(
				() -> assertEquals("Naruto", a2.getTitle()),
				() -> assertEquals(2002, a2.getYear()),
				() -> assertEquals(0, a2.getCount()),
				() -> assertEquals("Other", a2.getLanguage()),
				() -> assertEquals("Special", a2.getType()),
				() -> assertFalse(a2.isFinished()),
				() -> assertTrue(a2.isDropped()),
				() -> assertEquals("", a2.getDirector()),
				() -> assertEquals("Pierrot", a2.getStudio()),
				() -> assertEquals("W\nO\nA\nH", a2.getNotes())
				);

		//Entry 3
		Anime a3 = (Anime) currentList.get(2);
		assertAll(
				() -> assertEquals("One Piece", a3.getTitle()),
				() -> assertEquals(1999, a3.getYear()),
				() -> assertEquals(100, a3.getCount()),
				() -> assertEquals("Dub", a3.getLanguage()),
				() -> assertEquals("Series", a3.getType()),
				() -> assertFalse(a3.isFinished()),
				() -> assertFalse(a3.isDropped()),
				() -> assertEquals("multiple", a3.getDirector()),
				() -> assertEquals("Toei", a3.getStudio()),
				() -> assertEquals("Watching with brother", a3.getNotes())
				);
		
		//Manga tests
		Manager.getInstance().setCurrentList(MediaType.MANGA);
		currentList = Manager.getInstance().getList();
		assertEquals(3, currentList.size());
		Manga m1 = (Manga) currentList.get(0);

		//Test each entry of the list
		//Entry 1
		assertAll(
				() -> assertEquals("Fire Punch", m1.getTitle()),
				() -> assertEquals(2016, m1.getYear()),
				() -> assertEquals(83, m1.getCount()),
				() -> assertEquals("Series", m1.getType()),
				() -> assertTrue(m1.isFinished()),
				() -> assertFalse(m1.isDropped()),
				() -> assertFalse(m1.isOngoing()),
				() -> assertEquals("Tatsuki Fujimoto", m1.getAuthor()),
				() -> assertEquals("Shonen Jump", m1.getPublisher()),
				() -> assertEquals("Worth a reread", m1.getNotes())
				);
		
		//Entry 2
		Manga m2 = (Manga) currentList.get(1);
		assertAll(
				() -> assertEquals("Chainsaw Man", m2.getTitle()),
				() -> assertEquals(2018, m2.getYear()),
				() -> assertEquals(99, m2.getCount()),
				() -> assertEquals("Series", m2.getType()),
				() -> assertFalse(m2.isFinished()),
				() -> assertFalse(m2.isDropped()),
				() -> assertTrue(m2.isOngoing()),
				() -> assertEquals("Tatsuki Fujimoto", m2.getAuthor()),
				() -> assertEquals("Shonen Jump", m2.getPublisher()),
				() -> assertEquals("", m2.getNotes())

				);

		//Entry 3
		Manga m3 = (Manga) currentList.get(2);
		assertAll(
				() -> assertEquals("Look Back", m3.getTitle()),
				() -> assertEquals(2021, m3.getYear()),
				() -> assertEquals(1, m3.getCount()),
				() -> assertEquals("Special", m3.getType()),
				() -> assertTrue(m3.isFinished()),
				() -> assertFalse(m3.isDropped()),
				() -> assertFalse(m3.isOngoing()),
				() -> assertEquals("Tatsuki Fujimoto", m3.getAuthor()),
				() -> assertEquals("Shonen Jump", m3.getPublisher()),
				() -> assertEquals("Fav oneshot so far\nmaybe should purchase?", m3.getNotes())
				);
	}

	/**
	 * Ensures returned array is organized as expected, and correct in title based retrieval
	 */
	@Test
	void testGetAllMediaAsArrayTitleBased() {

		//Test with anime
		Manager.getInstance().setAnimeList(SortFocus.ALPHABETICAL);
		Object[][] animeArray = Manager.getInstance().getAllAnimeAsArray();
		
		//Test each column, one at a time
		assertAll(
				() -> assertEquals(2007, animeArray[0][0]),
				() -> assertEquals(2002, animeArray[1][0]),
				() -> assertEquals(1999, animeArray[2][0]),

				() -> assertEquals("Gurren Lagann", animeArray[0][1]),
				() -> assertEquals("Naruto", animeArray[1][1]),
				() -> assertEquals("One Piece", animeArray[2][1]),

				() -> assertEquals(27, animeArray[0][2]),
				() -> assertEquals(0, animeArray[1][2]),
				() -> assertEquals(100, animeArray[2][2])
		);
		
		//Test with manga
		Manager.getInstance().setMangaList(SortFocus.ALPHABETICAL);
		Object[][] mangaArray = Manager.getInstance().getAllMangaAsArray();

		//Test each column, one at a time
		assertAll(
				() -> assertEquals(2018, mangaArray[0][0]),
				() -> assertEquals(2016, mangaArray[1][0]),
				() -> assertEquals(2021, mangaArray[2][0]),

				() -> assertEquals("Chainsaw Man", mangaArray[0][1]),
				() -> assertEquals("Fire Punch", mangaArray[1][1]),
				() -> assertEquals("Look Back", mangaArray[2][1]),

				() -> assertEquals(99, mangaArray[0][2]),
				() -> assertEquals(83, mangaArray[1][2]),
				() -> assertEquals(1, mangaArray[2][2])
		);
	}
	
	/**
	 * Ensures returned array is organized as expected, and correct in year based retrieval
	 */
	@Test
	void testGetAllMediaAsArrayYearBased() {
		
		//Test with Anime
		Manager.getInstance().setAnimeList(SortFocus.NUMERICAL);		
		Object[][] animeArray = Manager.getInstance().getAllAnimeAsArray();
		
		//Test each column, one at a time
		assertAll(
				() -> assertEquals(1999, animeArray[0][0]),
				() -> assertEquals(2002, animeArray[1][0]),
				() -> assertEquals(2007, animeArray[2][0]),

				() -> assertEquals("One Piece", animeArray[0][1]),
				() -> assertEquals("Naruto", animeArray[1][1]),
				() -> assertEquals("Gurren Lagann", animeArray[2][1]),

				() -> assertEquals(100, animeArray[0][2]),
				() -> assertEquals(0, animeArray[1][2]),
				() -> assertEquals(27, animeArray[2][2])
				);
		
		//Test with Manga
		Manager.getInstance().setMangaList(SortFocus.NUMERICAL);		
		Object[][] mangaArray = Manager.getInstance().getAllMangaAsArray();
		
		//Test each column, one at a time
		assertAll(
				() -> assertEquals(2016, mangaArray[0][0]),
				() -> assertEquals(2018, mangaArray[1][0]),
				() -> assertEquals(2021, mangaArray[2][0]),

				() -> assertEquals("Fire Punch", mangaArray[0][1]),
				() -> assertEquals("Chainsaw Man", mangaArray[1][1]),
				() -> assertEquals("Look Back", mangaArray[2][1]),

				() -> assertEquals(83, mangaArray[0][2]),
				() -> assertEquals(99, mangaArray[1][2]),
				() -> assertEquals(1, mangaArray[2][2])
				);
	}

	/** 
	 * Tests changes made to the anime list and that they are reflected properly
	 */
	@Test
	void testEditAnimeList() {
		Manager.getInstance().setCurrentList(MediaType.ANIME);
		currentList = Manager.getInstance().getList();
		
		//Test exceptions for adding
		Exception e1 = assertThrows(IllegalArgumentException.class, ()-> Manager.getInstance().addAnime(null));
		assertEquals("Tried to add non-Anime object to Anime list.", e1.getMessage());
		
		Anime duplicate = new Anime("Gurren Lagann", 2007, 0, Language.DUB, Type.SPECIAL, false, false, "", "Gainax", "");
		Exception e2 = assertThrows(IllegalArgumentException.class, ()-> Manager.getInstance().addAnime(duplicate));
		assertEquals("Cannot add duplicate element.", e2.getMessage());

		//Test exception for removing
		assertThrows(IndexOutOfBoundsException.class, ()-> Manager.getInstance().removeAnime(10));
		
		//Test add
		Anime testAnime = new Anime("a", 1999, 0, Language.DUB, Type.SERIES, false, false, null, null, null);
		Manager.getInstance().addAnime(testAnime);
		assertEquals(4, currentList.size());
		assertEquals(testAnime, currentList.get(0));

		//Test remove
		Manager.getInstance().removeAnime(0);
		assertEquals(3, currentList.size());
		assertEquals(new Anime("Gurren Lagann", 2007, 26, Language.SUB, Type.SPECIAL, true, false, "Hiroyuki Imaishi", "Gainax", "Very good op!"), 
				currentList.get(0));
	}
	
	/** 
	 * Tests changes made to the manga list and that they are reflected properly
	 */
	@Test
	void testEditMangaList() {
		Manager.getInstance().setCurrentList(MediaType.MANGA);
		currentList = Manager.getInstance().getList();
		
		//Test exceptions for adding
		Exception e1 = assertThrows(IllegalArgumentException.class, ()-> Manager.getInstance().addManga(null));
		assertEquals("Tried to add non-Manga object to Manga list.", e1.getMessage());
				
		Manga duplicate = new Manga("Look Back", 2021, 1, "Tatsuki Fujimoto", "Shonen Jump", Type.SPECIAL, true, false, false, "");
		Exception e2 = assertThrows(IllegalArgumentException.class, ()-> Manager.getInstance().addManga(duplicate));
		assertEquals("Cannot add duplicate element.", e2.getMessage());
		
		//Test exception for removing
		assertThrows(IndexOutOfBoundsException.class, ()-> Manager.getInstance().removeManga(10));
		
		//Test add
		Manga testManga = new Manga("a", 2000, 0, "B.A. Author", "Magazine Weekly", Type.SERIES, false, true, false, "");
		Manager.getInstance().addManga(testManga);
		assertEquals(4, currentList.size());
		assertEquals(testManga, currentList.get(0));

		//Test remove
		Manager.getInstance().removeManga(0);
		assertEquals(3, currentList.size());
		assertEquals(new Manga("Fire Punch", 2016, 83, "Tatsuki Fujimoto", "Shonen Jump", Type.SERIES, true, false, false, ""), 
				currentList.get(0));
	}

	/**
	 * Ensures that Manga cannot be colored by language, a field exclusive to Anime
	 */
	@Test
	void testMangaExceptionColorMethod() {
		Exception e = assertThrows(IllegalArgumentException.class, ()-> Manager.getInstance().setColorMethod(MediaType.MANGA, ColorMethod.SUB_DUB));
		assertEquals("Cannot color by language with Manga", e.getMessage());
	}
	
	/**
	 * Tests that stats are calculated correctly
	 */
	@Test
	void testGetStatistics() {
		//Test stats with anime
		assertAll(
				() -> assertEquals("3", Manager.getInstance().getEntryCount(MediaType.ANIME)),
				() -> assertEquals("127", Manager.getInstance().getCountSum(MediaType.ANIME)),
				() -> assertEquals("Tie", Manager.getInstance().getFavoredLanguageAndPercent()),
				() -> assertEquals("2", Manager.getInstance().getNumSeries(MediaType.ANIME)),
				() -> assertEquals("1", Manager.getInstance().getNumSpecial(MediaType.ANIME)),
				() -> assertEquals("33%", Manager.getInstance().getPercentDropped(MediaType.ANIME)),
				() -> assertEquals("33%", Manager.getInstance().getPercentFinished(MediaType.ANIME))
				);

		
		//Test stats after removing all anime (should be defaults)
		//Including further tests for getFavoredLanguageAndPercent()
		Manager.getInstance().removeAnime(0);
		assertEquals("Dub 100%", Manager.getInstance().getFavoredLanguageAndPercent()); //Is dub 100% here because Other languages arent counted unless some anime is watched ( count != 0)
		Manager.getInstance().removeAnime(0);
		assertEquals("Dub 100%", Manager.getInstance().getFavoredLanguageAndPercent());
		Manager.getInstance().removeAnime(0);
		
		assertAll(
				() -> assertEquals("0", Manager.getInstance().getEntryCount(MediaType.ANIME)),
				() -> assertEquals("0", Manager.getInstance().getCountSum(MediaType.ANIME)),
				() -> assertEquals("N/A", Manager.getInstance().getFavoredLanguageAndPercent()),
				() -> assertEquals("0", Manager.getInstance().getNumSeries(MediaType.ANIME)),
				() -> assertEquals("0", Manager.getInstance().getNumSpecial(MediaType.ANIME)),
				() -> assertEquals("N/A", Manager.getInstance().getPercentDropped(MediaType.ANIME)),
				() -> assertEquals("N/A", Manager.getInstance().getPercentFinished(MediaType.ANIME))
				);


		//Test stats with manga
		assertAll(
				() -> assertEquals("3", Manager.getInstance().getEntryCount(MediaType.MANGA)),
				() -> assertEquals("183", Manager.getInstance().getCountSum(MediaType.MANGA)),
				() -> assertEquals("2", Manager.getInstance().getNumSeries(MediaType.MANGA)),
				() -> assertEquals("1", Manager.getInstance().getNumSpecial(MediaType.MANGA)),
				() -> assertEquals("0%", Manager.getInstance().getPercentDropped(MediaType.MANGA)),
				() -> assertEquals("66%", Manager.getInstance().getPercentFinished(MediaType.MANGA)),
				() -> assertEquals("33%", Manager.getInstance().getPercentOngoing()),
				() -> assertEquals("Tatsuki Fujimoto", Manager.getInstance().getFavoredAuthor())
				);
		
		//Alternate test for favored author manga statistic
		Manager.getInstance().removeManga(0);
		Manager.getInstance().removeManga(0);
		Manga m1 = new Manga("Mongo", 1999, 5, "foo", "bar", Type.SERIES, false, false, false, "");
		Manga m2 = new Manga("Mungo", 1999, 15, "foo", "bar", Type.SERIES, false, false, false, "");
		Manager.getInstance().addManga(m1);
		Manager.getInstance().addManga(m2);
		assertEquals("foo", Manager.getInstance().getFavoredAuthor());
	}

}