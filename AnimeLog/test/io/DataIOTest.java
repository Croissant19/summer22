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
import data.Anime.Type;
import data.Data;
import data.Preferences;
import data.Preferences.ColorMethod;
import data.Preferences.SortFocus;
import util.SortedAnimeList;

/**
 * Tests the input output functionality for user data
 * @author Hunter Pruitt
 */
class DataIOTest {

	/** IO file to create three Anime objects */
	private static final String TEST_FILE_ONE = "test-files/ThreeWorkingImports.txt";
	
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
	
	/** File containing expected information about an anime log*/
	private static final File EXPECTED_OUT = new File("test-files/ThreeWorkingImports.txt");
	
	/** File containing actual information about an anime log*/
	private static final File ACTUAL_OUT = new File("test-files/actual_out.txt");

	/** Reference pointer to default preference settings */
	private static final Preferences DEFAULT_PREFERENCES = new Preferences();

	/** Sorted collection of Anime returned and passed to file IO methods */
	private SortedAnimeList list; 
	
	/** Data object storing the SortedAnimeLists and Preferences */
	private Data data;
	
	
	
	/**
	 * SetUp method to prepare variables before each test
	 */
	@BeforeEach
	public void setUp() {
		data = new Data();
		list = data.getAlphabeticalAnimeList();
	}
	
	/**
	 * Test for successful imports with 3 generic anime
	 */
	@Test
	void testThreeWorkingImports() {
		data = DataIO.readFile(TEST_FILE_ONE);
		list = data.getAlphabeticalAnimeList();
		assertEquals(3, list.size());
		Anime actualGurren = list.get(0);
		Anime actualNaruto = list.get(1);
		Anime actualOnePiece = list.get(2);

		//Assert first anime is correct
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
	}
	
	/**
	 * Test an import file with acceptable extra whitespace
	 */
	@Test
	void testExtraWhitespace() {
		list = DataIO.readFile(TEST_FILE_FIVE).getAlphabeticalAnimeList();
		assertEquals(1, list.size());

		Anime actualGurren = list.get(0);

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
		Preferences p = data.getPreferences();
		
		assertEquals(SortFocus.NUMERICAL, p.getSortMethod());
		assertEquals(ColorMethod.SUB_DUB, p.getColorMethod());
		assertEquals(-10001936, p.getColor1().getRGB());
		assertEquals(-16710081, p.getColor2().getRGB());
	}
	
	/**
	 * Tests importing an Anime with no notes attached
	 */
	@Test
	void testImportNoNotes() {
		Anime actual = DataIO.readFile(TEST_FILE_SEVEN).getAlphabeticalAnimeList().get(0);

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
	 * Tests the export functionality of the AnimeIO with working file info
	 */
	@Test
	void testExport() {
		//Create and add the expected anime
		Anime a = new Anime("Gurren Lagann", 2007, 26, Language.SUB, Type.SERIES, true, false, 
				"Hiroyuki Imaishi", "Very good op!");
		Anime b = new Anime("One Piece", 1999, 100, Language.DUB, Type.SERIES, false, false, 
				"multiple", "Watching with brother");
		Anime c = new Anime("Naruto", 2002, 0, Language.OTHER, Type.SPECIAL, false, true, 
				"", "W\nO\nA\nH");
		
		list.add(a);
		list.add(b);
		list.add(c);
		
		//Export the list and check file is as expected
		data = new Data(list, DEFAULT_PREFERENCES);
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
