package io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.Anime;
import util.SortedMediaList;

/**
 * Tests the Anime Import functionality of AnimeIO.
 * @author Hunter Pruitt
 */
class AnimeIOTest {
	
	/**
	 * Excerpt from test file with information to construct three anime.
	 */
	private final String THREE_WORKING_ANIME = "<|>Gurren Lagann,_2007,_27,_Sub,_Series,_true,_false,_Hiroyuki Imaishi,_Gainax,_Very good op!\r\n"
			+ "<|>Naruto,_2002,_13,_Dub,_Series,_false,_true,_,_Pierrot,_W\r\n"
			+ "O\r\n"
			+ "A\r\n"
			+ "H\r\n"
			+ "<|>One Piece,_1999,_100,_Dub,_Series,_false,_false,_,_Toei,_Watching with brother";

	/**
	 * Tests reading data from 3 anime and ensures data is correct.
	 */
	@Test
	void testGetAnimeFromString() {
		SortedMediaList list = AnimeIO.getAnimeFromString(THREE_WORKING_ANIME);

		//Test that anime are added
		assertEquals(3, list.size());
		Anime a1 = (Anime) list.get(0);
		Anime a2 = (Anime) list.get(1);
		Anime a3 = (Anime) list.get(2);
		
		//Test anime 1
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

		//Test anime 2
		assertAll(
	            () -> assertEquals("Naruto", a2.getTitle()),
	            () -> assertEquals(2002, a2.getYear()),
	            () -> assertEquals(13, a2.getCount()),
	            () -> assertEquals("Dub", a2.getLanguage()),
	            () -> assertEquals("Series", a2.getType()),
	            () -> assertFalse(a2.isFinished()),
	            () -> assertTrue(a2.isDropped()),
	            () -> assertEquals("", a2.getDirector()),
	            () -> assertEquals("Pierrot", a2.getStudio()),
	            () -> assertEquals("W\r\nO\r\nA\r\nH", a2.getNotes())
	    );
		
		//Test anime 3
		assertAll(
	            () -> assertEquals("One Piece", a3.getTitle()),
	            () -> assertEquals(1999, a3.getYear()),
	            () -> assertEquals(100, a3.getCount()),
	            () -> assertEquals("Dub", a3.getLanguage()),
	            () -> assertEquals("Series", a3.getType()),
	            () -> assertFalse(a3.isFinished()),
	            () -> assertFalse(a3.isDropped()),
	            () -> assertEquals("", a3.getDirector()),
	            () -> assertEquals("Toei", a3.getStudio()),
	            () -> assertEquals("Watching with brother", a3.getNotes())
	    );		
	}

	/**
	 * Ensures empty data does not throw exceptions in case user does not have data after preferences
	 */
	@Test
	void testReadEmptyData() {
		SortedMediaList list = AnimeIO.getAnimeFromString("");
		assertEquals(0, list.size());
	}

}