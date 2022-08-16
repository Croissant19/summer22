package io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Anime;
import data.Anime.Language;
import data.Media.Type;
import data.Data;
import data.Manga;
import data.Preferences;
import data.Preferences.ColorMethod;
import data.Preferences.SortFocus;
import util.SortedMediaList;

/**
 * Tests the input output functionality for user data
 * @author Hunter Pruitt
 */
class DataIOTest {

	/** IO file to create three Anime objects and three Manga objects */
	private static final String TEST_FILE_ONE = "test-files/SixWorkingImports.txt";
	
	/** Invalid IO file due to bad language */
	private static final String TEST_FILE_TWO = "test-files/SecondAnimeBadLang.txt";
	
	/** Invalid IO file due to wrong first delimiter */
	private static final String TEST_FILE_THREE = "test-files/MissingLeadingDelimit.txt";
	
	/** Invalid IO file due to unacceptable missing data */
	private static final String TEST_FILE_FOUR = "test-files/BrokenFirstAnime.txt";
	
	/** Valid IO file with extra whitespace */
	private static final String TEST_FILE_FIVE = "test-files/AcceptableExtraWhiteSpace.txt";
	
	/** Valid IO file non-default preferences */
	private static final String TEST_FILE_SIX = "test-files/NonDefaultPreferences.txt";
	
	/** Valid IO with no notes */
	private static final String TEST_FILE_SEVEN = "test-files/WorkingImportNoNotes.txt";
	
	/** Invalid IO file due to Manga color Preferences */
	private static final String TEST_FILE_EIGHT = "test-files/MangaInvalidColorMethod.txt";

	/** Valid IO file with only Manga Preferences */
	private static final String TEST_FILE_NINE = "test-files/MangaPreferencesOnly.txt";
	
	/** Valid IO file, blank which indicates that everything is null */
	private static final String TEST_FILE_TEN = "test-files/BlankFile.txt";
	
	/** File containing expected information about an anime log*/
	private static final File EXPECTED_OUT = new File("test-files/SixWorkingImports.txt");
	
	/** File containing actual information about an anime log*/
	private static final File ACTUAL_OUT = new File("test-files/actual_out.txt");

	/** Reference pointer to default preference settings */
	private static final Preferences DEFAULT_PREFERENCES = new Preferences();
	
	/** Reference pointer to alternative preference settings used in TEST_FILE_ONE */
	private static final Preferences TEST_PREFERENCES = new Preferences(SortFocus.NUMERICAL, ColorMethod.NO_COLOR, -16711936,-16711681);

	/** Sorted collection of Anime returned and passed to file IO methods */
	private SortedMediaList animeList; 
	
	/** Sorted collection of Manga returned and passed to file IO methods */
	private SortedMediaList mangaList; 
	
	/** Data object storing the SortedAnimeLists and Preferences */
	private Data data;
	
	
	
	/**
	 * SetUp method to prepare variables before each test
	 */
	@BeforeEach
	public void setUp() {
		data = new Data();
		animeList = data.getAlphabeticalAnimeList();
		mangaList = data.getAlphabeticalMangaList();
	}
	
	/**
	 * Test for successful imports with 3 generic anime and 3 generic manga
	 */
	@Test
	void testSixWorkingImports() {
		data = DataIO.readFile(TEST_FILE_ONE);
		animeList = data.getAlphabeticalAnimeList();
		mangaList = data.getAlphabeticalMangaList();
		
		//Check Anime
		//////////////////

		assertEquals(3, animeList.size());
		Anime actualGurren = (Anime) animeList.get(0);
		Anime actualNaruto = (Anime) animeList.get(1);
		Anime actualOnePiece = (Anime) animeList.get(2);

		//Assert first anime is correct
		assertAll(
				() -> assertEquals("Gurren Lagann", actualGurren.getTitle()), 
				() -> assertEquals(2007, actualGurren.getYear()), 
				() -> assertEquals(27, actualGurren.getCount()), 
				() -> assertEquals("Sub", actualGurren.getLanguage()), 
				() -> assertEquals("Series", actualGurren.getType()), 
				() -> assertTrue(actualGurren.isFinished()),
				() -> assertFalse(actualGurren.isDropped()),
				() -> assertEquals("Hiroyuki Imaishi", actualGurren.getDirector()), 
				() -> assertEquals("Very good op!", actualGurren.getNotes()) 
		);
		
		//Assert second anime is correct
		assertAll(
				() -> assertEquals("Naruto", actualNaruto.getTitle()), 
				() -> assertEquals(2002, actualNaruto.getYear()), 
				() -> assertEquals(0, actualNaruto.getCount()), 
				() -> assertEquals("Other", actualNaruto.getLanguage()), 
				() -> assertEquals("Special", actualNaruto.getType()), 
				() -> assertFalse(actualNaruto.isFinished()),
				() -> assertTrue(actualNaruto.isDropped()),
				() -> assertEquals("", actualNaruto.getDirector()), 
				() -> assertEquals("W\nO\nA\nH", actualNaruto.getNotes()) 
		);

		
		//Assert third anime is correct
		assertAll(
				() -> assertEquals("One Piece", actualOnePiece.getTitle()), 
				() -> assertEquals(1999, actualOnePiece.getYear()), 
				() -> assertEquals(100, actualOnePiece.getCount()), 
				() -> assertEquals("Dub", actualOnePiece.getLanguage()), 
				() -> assertEquals("Series", actualOnePiece.getType()), 
				() -> assertFalse(actualOnePiece.isFinished()),
				() -> assertFalse(actualOnePiece.isDropped()),
				() -> assertEquals("multiple", actualOnePiece.getDirector()), 
				() -> assertEquals("Watching with brother", actualOnePiece.getNotes())
		);
		
		//Check Manga
		//////////////////

		assertEquals(3, mangaList.size());
		Manga actualChainsawMan = (Manga) mangaList.get(0);
		Manga actualFirePunch = (Manga) mangaList.get(1);
		Manga actualLookBack = (Manga) mangaList.get(2);

		//Assert first Manga is correct
		assertAll(
				() -> assertEquals("Chainsaw Man", actualChainsawMan.getTitle()), 
				() -> assertEquals(2018, actualChainsawMan.getYear()), 
				() -> assertEquals(99, actualChainsawMan.getCount()), 
				() -> assertEquals("Tatsuki Fujimoto", actualChainsawMan.getAuthor()),
				() -> assertEquals("Shonen Jump", actualChainsawMan.getPublisher()),
				() -> assertEquals("Series", actualChainsawMan.getType()), 
				() -> assertFalse(actualChainsawMan.isFinished()),
				() -> assertFalse(actualChainsawMan.isDropped()),
				() -> assertTrue(actualChainsawMan.isOngoing()),
				() -> assertEquals("", actualChainsawMan.getNotes())
		);
		
		//Assert second Manga is correct
		assertAll(
				() -> assertEquals("Fire Punch", actualFirePunch.getTitle()), 
				() -> assertEquals(2016, actualFirePunch.getYear()), 
				() -> assertEquals(83, actualFirePunch.getCount()), 
				() -> assertEquals("Tatsuki Fujimoto", actualFirePunch.getAuthor()),
				() -> assertEquals("Shonen Jump", actualFirePunch.getPublisher()),
				() -> assertEquals("Series", actualFirePunch.getType()), 
				() -> assertTrue(actualFirePunch.isFinished()),
				() -> assertFalse(actualFirePunch.isDropped()),
				() -> assertFalse(actualFirePunch.isOngoing()),
				() -> assertEquals("Worth a reread", actualFirePunch.getNotes())
		);

		//Assert third Manga is correct
		assertAll(
				() -> assertEquals("Look Back", actualLookBack.getTitle()), 
				() -> assertEquals(2021, actualLookBack.getYear()), 
				() -> assertEquals(1, actualLookBack.getCount()), 
				() -> assertEquals("Tatsuki Fujimoto", actualLookBack.getAuthor()),
				() -> assertEquals("Shonen Jump", actualLookBack.getPublisher()),
				() -> assertEquals("Special", actualLookBack.getType()), 
				() -> assertTrue(actualLookBack.isFinished()),
				() -> assertFalse(actualLookBack.isDropped()),
				() -> assertFalse(actualLookBack.isOngoing()),
				() -> assertEquals("Fav oneshot so far\nmaybe should purchase?", actualLookBack.getNotes()) 
		);
	}

	
	/**
	 * Test various bad imports
	 */
	@Test
	void testBadImport() {
		//Test import with invalid language
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> DataIO.readFile(TEST_FILE_TWO));
		assertEquals("Bad file data", e1.getMessage());
		
		//Test import with invalid leading delimiter
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> DataIO.readFile(TEST_FILE_THREE));
		assertEquals("Bad file data", e2.getMessage());

		//Test import with missing data
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> DataIO.readFile(TEST_FILE_FOUR));
		assertEquals("Bad file data", e3.getMessage());
		
		//Test import with invalid manga ColorMethod
		Exception e4 = assertThrows(IllegalArgumentException.class, () -> DataIO.readFile(TEST_FILE_EIGHT));
		assertEquals("Using language-based color method with Manga, not allowed.", e4.getMessage());
	}
	
	/**
	 * Test an import file with acceptable extra whitespace
	 */
	@Test
	void testExtraWhitespace() {
		animeList = DataIO.readFile(TEST_FILE_FIVE).getAlphabeticalAnimeList();
		assertEquals(1, animeList.size());

		Anime actualGurren = (Anime) animeList.get(0);

		//Assert that anime is read and whitespace trimmed
		assertAll(
				() -> assertEquals("Gurren Lagann", actualGurren.getTitle()), 
				() -> assertEquals(2007, actualGurren.getYear()), 
				() -> assertEquals(26, actualGurren.getCount()), 
				() -> assertEquals("Sub", actualGurren.getLanguage()), 
				() -> assertEquals("Series", actualGurren.getType()), 
				() -> assertTrue(actualGurren.isFinished()),
				() -> assertFalse(actualGurren.isDropped()),
				() -> assertEquals("Hiroyuki Imaishi", actualGurren.getDirector()), 
				() -> assertEquals("Very good op!", actualGurren.getNotes()) 
		);
	}
	
	/**
	 * Tests importing data from files with non-default preferences
	 */
	@Test
	void testFileWithUniquePreferences() {
		data = DataIO.readFile(TEST_FILE_SIX);
		Preferences p = data.getAnimePreferences();

		assertEquals(SortFocus.NUMERICAL, p.getSortMethod());
		assertEquals(ColorMethod.SUB_DUB, p.getColorMethod());
		assertEquals(-10001936, p.getColor1().getRGB());
		assertEquals(-16710081, p.getColor2().getRGB());
	}

	/**
	 * Tests importing data where only Manga Preferences have been set
	 */
	@Test
	void testFileMangaPreferencesOnly() {
		data = DataIO.readFile(TEST_FILE_NINE);

		//Ensure Manga Preferences correct
		Preferences p = data.getMangaPreferences();

		assertEquals(SortFocus.NUMERICAL, p.getSortMethod());
		assertEquals(ColorMethod.NO_COLOR, p.getColorMethod());
		assertEquals(-16711936, p.getColor1().getRGB());
		assertEquals(-16711681, p.getColor2().getRGB());

		//Assert everything else null
		assertNull(data.getAlphabeticalAnimeList());
		assertNull(data.getAlphabeticalMangaList());
		assertNull(data.getAnimePreferences());
	}
	
	/**
	 * Tests importing an Anime with no notes attached
	 */
	@Test
	void testImportNoNotes() {
		Anime actual = (Anime) DataIO.readFile(TEST_FILE_SEVEN).getAlphabeticalAnimeList().get(0);

		//Assert that anime is read correctly
		assertAll(
				() -> assertEquals("Gurren Lagann", actual.getTitle()), 
				() -> assertEquals(2007, actual.getYear()), 
				() -> assertEquals(26, actual.getCount()), 
				() -> assertEquals("Sub", actual.getLanguage()), 
				() -> assertEquals("Series", actual.getType()), 
				() -> assertTrue(actual.isFinished()),
				() -> assertFalse(actual.isDropped()),
				() -> assertEquals("Hiroyuki Imaishi", actual.getDirector()), 
				() -> assertEquals("", actual.getNotes()) 
		);
	
	}

	/**
	 * Ensures that everything is null when there is no data to read and that exceptions are not thrown.
	 */
	@Test
	void testBlankFileGivesNull() {
		data = DataIO.readFile(TEST_FILE_TEN);

		//Assert everything null
		assertNull(data.getAlphabeticalAnimeList());
		assertNull(data.getAnimePreferences());
		assertNull(data.getAlphabeticalMangaList());
		assertNull(data.getMangaPreferences());
	}
	
	/**
	 * Tests the export functionality of the DataIO with working file info
	 */
	@Test
	void testExport() {
		//Create and add the expected anime
		Anime a1 = new Anime("Gurren Lagann", 2007, 27, Language.SUB, Type.SERIES, true, false, 
				"Hiroyuki Imaishi", "Gainax", "Very good op!");
		Anime a2 = new Anime("One Piece", 1999, 100, Language.DUB, Type.SERIES, false, false, 
				"multiple", "Toei", "Watching with brother");
		Anime a3 = new Anime("Naruto", 2002, 0, Language.OTHER, Type.SPECIAL, false, true, 
				"", "Pierrot", "W\nO\nA\nH");
		
		animeList.add(a1);
		animeList.add(a2);
		animeList.add(a3);
		
		//Create and add the expected anime
		Manga m1 = new Manga("Chainsaw Man", 2018, 99, "Tatsuki Fujimoto", "Shonen Jump", Type.SERIES, false, false, true, "");
		Manga m2 = new Manga("Fire Punch", 2016, 83, "Tatsuki Fujimoto", "Shonen Jump", Type.SERIES, true, false, false, "Worth a reread");
		Manga m3 = new Manga("Look Back", 2021, 1,  "Tatsuki Fujimoto", "Shonen Jump", Type.SPECIAL, true, false, false, "Fav oneshot so far\r\n"
				+ "maybe should purchase?");
		
		mangaList.add(m1);
		mangaList.add(m2);
		mangaList.add(m3);
		
		//Export the list and check file is as expected
		data = new Data(animeList, DEFAULT_PREFERENCES, mangaList, TEST_PREFERENCES);
		try {
			DataIO.writeData(data, ACTUAL_OUT);
			assertTrue(compareFiles(EXPECTED_OUT, ACTUAL_OUT));
		} catch (IOException e) {
			fail("Threw an unexpected exception");
		}	
	}
	
	/**
	 * Compares the contents of two .txt files for equality.
	 * 
	 * @param file1 the expected file from the test-files folder
	 * @param file2 the file generated by writeIssuesToFile
	 * @return boolean indicator of the two files equality
	 * @throws IOException if an Exception occurs transforming
	 * files to Strings, indicating test failure.
	 */
	private boolean compareFiles(File file1, File file2) throws IOException {
		String expContents = "";
		int expLineCt = 0;
		String actContents = "";
		int actLineCt = 0;

		//Generate string for file 1
		FileInputStream fis = new FileInputStream(file1);
		Scanner in = new Scanner(fis);

		while (in.hasNextLine()) {
			expLineCt++;
			expContents += in.nextLine();
		}
		in.close();
		
		//Generate string for file 2
		fis = new FileInputStream(file2);
		in = new Scanner(fis);
		while (in.hasNextLine()) {
			actLineCt++;
			actContents += in.nextLine();
		}
		in.close();
		fis.close();
		
		return expContents.equals(actContents) && expLineCt == actLineCt;
	}
	
}