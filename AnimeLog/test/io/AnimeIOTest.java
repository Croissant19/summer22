package io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import anime.Anime;
import util.SortedList;

/**
 * Tests the input output functionality for Anime lists
 * @author Hunter Pruitt
 */
class AnimeIOTest {

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
	
	/** Sorted collection of Anime returned and passed to file IO methods */
	private SortedList<Anime> list; 
	
	/**
	 * SetUp method to prepare variables before each test
	 */
	@BeforeEach
	public void setUp() {
		list = new SortedList<Anime>();
	}
	
	/**
	 * Test for successful imports with 3 generic anime
	 */
	@Test
	void testThreeWorkingImports() {
		list = AnimeIO.readFile(TEST_FILE_ONE);
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
	void testBadLanguageImport() {
		//Test import with invalid language
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> AnimeIO.readFile(TEST_FILE_TWO));
		assertEquals("Bad file data", e1.getMessage());
		
		//Test import with invalid leading delimiter
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> AnimeIO.readFile(TEST_FILE_THREE));
		assertEquals("Bad file data", e2.getMessage());

		//Test import with missing data
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> AnimeIO.readFile(TEST_FILE_FOUR));
		assertEquals("Bad file data", e3.getMessage());
	}
	
	/**
	 * Test an import file with acceptable extra whitespace
	 */
	@Test
	void testExtraWhitespace() {
		list = AnimeIO.readFile(TEST_FILE_FIVE);
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
	
}
