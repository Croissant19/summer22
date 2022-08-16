package io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.Manga;
import util.SortedMediaList;

/**
 * Tests the Manga Import functionality of MangaIO.
 * @author Hunter Pruitt
 */
class MangaIOTest {
	
	/**
	 * Excerpt from test file with information to construct three manga.
	 */
	private final String THREE_WORKING_MANGA = "<|>Chainsaw Man,_2018,_99,_Tatsuki Fujimoto,_Shonen Jump,_Series,_false,_false,_true,_\r\n"
			+ "<|>Fire Punch,_2016,_83,_Tatsuki Fujimoto,_Shonen Jump,_Series,_true,_false,_false,_Worth a reread\r\n"
			+ "<|>Look Back,_2021,_1,_Tatsuki Fujimoto,_Shonen Jump,_Special,_true,_false,_false,_Fav oneshot so far\r\n"
			+ "maybe should purchase?";

	/**
	 * Tests reading data from 3 manga and ensures data is correct.
	 */
	@Test
	void testGetMangaFromString() {
		SortedMediaList list = MangaIO.getMangaFromString(THREE_WORKING_MANGA);

		//Test that manga are added
		assertEquals(3, list.size());
		Manga m1 = (Manga) list.get(0);
		Manga m2 = (Manga) list.get(1);
		Manga m3 = (Manga) list.get(2);
		
		//Test manga 1
		assertAll(
	            () -> assertEquals("Chainsaw Man", m1.getTitle()),
	            () -> assertEquals(2018, m1.getYear()),
	            () -> assertEquals(99, m1.getCount()),
	            () -> assertEquals("Tatsuki Fujimoto", m1.getAuthor()),
	            () -> assertEquals("Shonen Jump", m1.getPublisher()),
	            () -> assertEquals("Series", m1.getType()),
	            () -> assertFalse(m1.isFinished()),
	            () -> assertFalse(m1.isDropped()),
	            () -> assertTrue(m1.isOngoing()),
	            () -> assertEquals("", m1.getNotes())
	    );

		//Test manga 2
		assertAll(
	            () -> assertEquals("Fire Punch", m2.getTitle()),
	            () -> assertEquals(2016, m2.getYear()),
	            () -> assertEquals(83, m2.getCount()),
	            () -> assertEquals("Tatsuki Fujimoto", m2.getAuthor()),
	            () -> assertEquals("Shonen Jump", m2.getPublisher()),
	            () -> assertEquals("Series", m2.getType()),
	            () -> assertTrue(m2.isFinished()),
	            () -> assertFalse(m2.isDropped()),
	            () -> assertFalse(m2.isOngoing()),
	            () -> assertEquals("Worth a reread", m2.getNotes())
	    );

		//Test manga 3
		assertAll(
	            () -> assertEquals("Look Back", m3.getTitle()),
	            () -> assertEquals(2021, m3.getYear()),
	            () -> assertEquals(1, m3.getCount()),
	            () -> assertEquals("Tatsuki Fujimoto", m3.getAuthor()),
	            () -> assertEquals("Shonen Jump", m3.getPublisher()),
	            () -> assertEquals("Special", m3.getType()),
	            () -> assertTrue(m3.isFinished()),
	            () -> assertFalse(m3.isDropped()),
	            () -> assertFalse(m3.isOngoing()),
	            () -> assertEquals("Fav oneshot so far\r\nmaybe should purchase?", m3.getNotes())
	    );
	}

	/**
	 * Ensures empty data does not throw exceptions in case user does not have data after preferences
	 */
	@Test
	void testReadEmptyData() {
		SortedMediaList list = MangaIO.getMangaFromString("");
		assertNull(list);
	}
}