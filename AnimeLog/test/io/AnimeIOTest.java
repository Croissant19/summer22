package io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import anime.Anime;
import anime.Anime.Language;
import anime.Anime.Type;
import util.SortedList;

/**
 * Tests the input output functionality for Anime lists
 * @author Hunter Pruitt
 */
class AnimeIOTest {

	/** IO file to create three Anime objects */
	public final String THREE_WORKING_IMPORTS = "test-files/ThreeWorkingImports.txt";	
	
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
		list = AnimeIO.readFile(THREE_WORKING_IMPORTS);
		assertEquals(3, list.size());

		Anime actualGurren = list.get(0);
		Anime actualOnePiece = list.get(1);
		Anime actualNaruto = list.get(2);

		//Assert first anime is correct
		assertAll(
				() -> assertEquals("Gurren Lagann", actualGurren.getTitle()), 
				() -> assertEquals(2007, actualGurren.getYear()), 
				() -> assertEquals(26, actualGurren.getCount()), 
				() -> assertEquals(Language.SUB, actualGurren.getLanguage()), 
				() -> assertEquals(Type.SERIES, actualGurren.getType()), 
				() -> assertTrue(actualGurren.isFinished()),
				() -> assertFalse(actualGurren.isDropped()),
				() -> assertEquals("Hiroyuki Imaishi", actualGurren.getDirector()), 
				() -> assertEquals("Very good op!", actualGurren.getNotes()) 
		);
		
		//Assert second anime is correct
		assertAll(
				() -> assertEquals("One Piece", actualOnePiece.getTitle()), 
				() -> assertEquals(1999, actualOnePiece.getYear()), 
				() -> assertEquals(100, actualOnePiece.getCount()), 
				() -> assertEquals(Language.DUB, actualOnePiece.getLanguage()), 
				() -> assertEquals(Type.SERIES, actualOnePiece.getType()), 
				() -> assertFalse(actualOnePiece.isFinished()),
				() -> assertFalse(actualOnePiece.isDropped()),
				() -> assertEquals("multiple", actualOnePiece.getDirector()), 
				() -> assertEquals("Watching with brother", actualOnePiece.getNotes()) 
		);

		
		//Assert third anime is correct
		assertAll(
				() -> assertEquals("Naruto", actualNaruto.getTitle()), 
				() -> assertEquals(2002, actualNaruto.getYear()), 
				() -> assertEquals(0, actualNaruto.getCount()), 
				() -> assertEquals(Language.OTHER, actualNaruto.getLanguage()), 
				() -> assertEquals(Type.SPECIAL, actualNaruto.getType()), 
				() -> assertFalse(actualNaruto.isFinished()),
				() -> assertTrue(actualNaruto.isDropped()),
				() -> assertEquals("", actualNaruto.getDirector()), 
				() -> assertEquals("W\nO\nA\nH", actualNaruto.getNotes()) 
		);
	
	
	}

}
