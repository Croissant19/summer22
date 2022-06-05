package anime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import anime.Anime.Language;
import anime.Anime.Type;

/**
 * Tests the Anime class
 * @author Hunter Pruitt
 */
class AnimeTest {

	/**
	 * Tests a generic case of the constructor
	 */
	@Test
	void testAnimeSuccess() {
		Anime a = new Anime("Gurren Lagann", 26, Language.SUB, Type.SERIES, true, false, 
				"Hiroyuki Imaishi", "Very good op!");

		//Test getters and setters
		assertAll(
				() -> assertEquals("Gurren Lagann", a.getTitle()),
				() -> assertEquals(26, a.getCount()),
				() -> assertEquals("Sub", a.getLanguage()),
				() -> assertEquals("Series", a.getType()),
				() -> assertTrue(a.isFinished()),
				() -> assertFalse(a.isDropped()),
				() -> assertEquals("Hiroyuki Imaishi", a.getDirector()),
				() -> assertEquals("Very good op!", a.getNotes())
				);
	}

	/**
	 * Tests exceptions for Anime constructor
	 */
	@Test
	void testAnimeExceptions() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Anime("Bleach", 100, Language.DUB, Type.SERIES, true, true, "Multiple directors", ""));

		assertEquals("Show cannot be both dropped and finished", e1.getMessage());
	}
	
	

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testEquals() {
		fail("Not yet implemented");
	}

}
