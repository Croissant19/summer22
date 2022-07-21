package anime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import anime.Anime.Language;
import anime.Anime.Type;
import util.SortedList;

/**
 * Tests the Anime class
 * @author Hunter Pruitt
 */
class AnimeTest {

	/**	Test anime for comparisons, Gurren Lagann */
	private static final Anime GURREN = new Anime("Gurren Lagann", 2007, 26, 
			Language.SUB, Type.SERIES, true, false, "Hiroyuki Imaishi", false, "Very good op!");

	/**	Test anime for comparisons, Fullmetal Alchemist */
	private static final Anime FMA03 = new Anime("Fullmetal Alchemist", 2003, 51, 
			Language.SUB, Type.SERIES, false, true, "Seiji Mizushima", false, "");
	
	/**	Test anime for comparisons, Fullmetal Alchemist Brotherhood */
	private static final Anime FMA09 = new Anime("Fullmetal Alchemist", 2009, 26, 
			Language.OTHER, Type.SERIES, false, false, "Yasuhiro Irie", false, null);
	
	
	/**
	 * Tests a generic case of the constructor and that 
	 * field methods return correct values
	 */
	@Test
	void testAnimeSuccess() {
		Anime a = new Anime("Gurren Lagann", 2007, 26, Language.SUB, Type.SERIES, true, false, 
				"Hiroyuki Imaishi", false, "Very good op!");

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
				() -> new Anime("Bleach", 2004, 100, Language.DUB, Type.SERIES, true, true, "Multiple directors", false, ""));
		assertEquals("Show cannot be both dropped and finished", e1.getMessage());

		//Blank title
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Anime("", 2004, 100, Language.DUB, Type.SERIES, true, false, "Multiple directors", false, ""));
		assertEquals("Title cannot be blank", e2.getMessage());
		
		//Year out of bounds
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Anime("Bleach", 2101, 100, Language.DUB, Type.SERIES, true, false, "Multiple directors", false, ""));
		assertEquals("Invalid year", e3.getMessage());
	
		Exception e4 = assertThrows(IllegalArgumentException.class, 
				() -> new Anime("Bleach", 1899, 100, Language.DUB, Type.SERIES, true, false, "Multiple directors", false, ""));
		assertEquals("Invalid year", e4.getMessage());
		
		//Negative count
		Exception e5 = assertThrows(IllegalArgumentException.class, 
				() -> new Anime("Bleach", 2004, -1, Language.DUB, Type.SERIES, true, false, "Multiple directors", false, ""));
		assertEquals("Count must be non-negative", e5.getMessage());
		
	}
	
	/**
	 * Tests the compareTo methods for anime
	 */
	@Test
	void testCompareTo() {
		assertTrue(GURREN.compareTo(FMA03) > 0);
		assertTrue(FMA09.compareTo(GURREN) < 0);
		assertTrue(FMA03.compareTo(FMA09) < 0);
		assertTrue(FMA09.compareTo(FMA03) > 0);
		assertEquals(0, GURREN.compareTo(GURREN));
	}

	/**
	 * Tests the compareTo methods for anime using SortedList
	 */
	@Test
	void testCompareToSortedList() {
		SortedList<Anime> list = new SortedList<Anime>();
		list.add(FMA09);
		list.add(FMA03);
		list.add(GURREN);
		
		Anime a = new Anime("a", 2003, 51, 
				Language.SUB, Type.SERIES, false, true, "Seiji Mizushima", false, "");
		
		Anime num = new Anime("0", 2003, 51, 
				Language.SUB, Type.SERIES, false, true, "Seiji Mizushima", false, "");
		
		Anime symbol = new Anime("%", 2003, 51, 
				Language.SUB, Type.SERIES, false, true, "Seiji Mizushima", false, "");

		
		
		assertEquals(FMA03, list.get(0));
		assertEquals(FMA09, list.get(1));
		assertEquals(GURREN, list.get(2));
		//Ensures adding is correct regardless of case
		list.add(a);
		assertEquals(0, list.indexOf(a));
		
		//Ensures numbers and other symbols are added before letters
		list.add(num);
		assertEquals(0, list.indexOf(num));
		list.add(symbol);
		assertEquals(0, list.indexOf(symbol));
		
	}

	/**
	 * Tests image related methods 
	 */
	@Test
	void testImgMethods() {
		Anime a = new Anime("Gurren Lagann", 2007, 26, Language.SUB, Type.SERIES, true, false, 
				"Hiroyuki Imaishi", false, "Very good op!");
		assertFalse(a.hasImage());
		a.setHasImage(true);
		assertTrue(a.hasImage());

		assertEquals("Gurren+Lagann_2007.png", a.getImageFileName());
	
		a = new Anime("Fullmetal Alchemist", 2003, 51, 
				Language.SUB, Type.SERIES, false, true, "Seiji Mizushima", false, "");
		
		assertEquals("Fullmetal+Alchemist_2003.png", a.getImageFileName());
	}
	
	
	/**
	 * Tests toString and equals methods for Anime objects
	 */
	@Test
	void testToStringAndEquals() {

		Anime a = new Anime("Gurren Lagann", 2007, 26, Language.SUB, Type.SERIES, true, false, 
				"Hiroyuki Imaishi", false, "Very good op!");
		Anime b = new Anime("Your Name", 2017, 1, Language.SUB, Type.SPECIAL, true, false, 
				"Makoto Shinkai",false, "");
		
		//Test toString
		assertEquals("Gurren Lagann,_2007,_26,_Sub,_Series,_true,_false,_Hiroyuki Imaishi,_false,_Very good op!",
				a.toString());
		
		assertEquals("Your Name,_2017,_1,_Sub,_Special,_true,_false,_Makoto Shinkai,_false,_",
				b.toString());
	
		//Test equals
		assertTrue(a.equals(GURREN));
		assertTrue(GURREN.equals(a));
		assertFalse(a.equals(b));
	}
	
}
