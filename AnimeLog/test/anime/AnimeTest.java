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
	 * Tests a generic case of the constructor and that 
	 * field methods return correct values
	 */
	@Test
	void testAnimeSuccess() {
		Anime a = new Anime("Gurren Lagann", 2007, 26, Language.SUB, Type.SERIES, true, false, 
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
		//Finished and dropped
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Anime("Bleach", 2004, 100, Language.DUB, Type.SERIES, true, true, "Multiple directors", ""));
		assertEquals("Show cannot be both dropped and finished", e1.getMessage());

		//Blank title
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Anime("", 2004, 100, Language.DUB, Type.SERIES, true, false, "Multiple directors", ""));
		assertEquals("Title cannot be blank", e2.getMessage());
		
		//Year out of bounds
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Anime("Bleach", 2101, 100, Language.DUB, Type.SERIES, true, false, "Multiple directors", ""));
		assertEquals("Invalid year", e3.getMessage());
	
		Exception e4 = assertThrows(IllegalArgumentException.class, 
				() -> new Anime("Bleach", 1899, 100, Language.DUB, Type.SERIES, true, false, "Multiple directors", ""));
		assertEquals("Invalid year", e4.getMessage());
		
		//Negative count
		Exception e5 = assertThrows(IllegalArgumentException.class, 
				() -> new Anime("Bleach", 2004, -1, Language.DUB, Type.SERIES, true, false, "Multiple directors", ""));
		assertEquals("Count must be non-negative", e5.getMessage());
		
	}
	
	


	/**
	 * Tests toString and equals methods for Anime objects
	 */
	@Test
	void testToStringAndEquals() {

		Anime a = new Anime("Gurren Lagann", 2007, 26, Language.SUB, Type.SERIES, true, false, 
				"Hiroyuki Imaishi", "Very good op!");
		Anime b = new Anime("Gurren Lagann", 2007, 26, Language.DUB, Type.SERIES, true, false, 
				"Hiroyuki Imaishi", "Very good op!");
		Anime c = new Anime("Your Name", 2017, 1, Language.SUB, Type.SPECIAL, true, false, 
				"Makoto Shinkai","");
		
		//Test toString
		assertEquals("Gurren Lagann,2007,26,Sub,Series,finished,,Hiroyuki Imaishi,Very good op!",
				a.toString());

		assertEquals("Gurren Lagann,2007,26,Dub,Series,finished,,Hiroyuki Imaishi,Very good op!",
				b.toString());
		
		assertEquals("Your Name,2017,1,Sub,Special,finished,,Makoto Shinkai,",
				c.toString());
	
		//Test equals
		assertTrue(a.equals(b));
		assertTrue(b.equals(a));
		assertFalse(a.equals(c));
		assertFalse(a.equals(null));
	}
	
}
