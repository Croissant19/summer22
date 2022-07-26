package manager;

import java.awt.Color;

/**
 * Container class for preference enums, related to Sorting and Color preferences
 * @author Hunter Pruitt
 */
public class Preferences {

	/** Default color for table data that meets selected conditions */
	public static final Color DEFAULT_COLOR_1 = Color.GREEN;
	
	/** Default color for table data that meets selected conditions */
	public static final Color DEFAULT_COLOR_2 = Color.CYAN;
	
	
	/**
	 * Indicates whether the data is sorted by title (alphabetical) or by year (numerical).
	 */
	public enum SortFocus {
		ALPHABETICAL("Alphabetical"), 
		NUMERICAL("Numerical");
		
		/** Formatted String for each SortFocus for display in the GUI */
		public final String formattedName;

		/**
		 * Constructor for each SortFocus enum, setting the formattedName field
		 * @param formattedName String representation of the sorting method
		 */
		SortFocus(String formattedName){
			this.formattedName = formattedName;
		}

		/**
		 * Classifies a string of text into the correct SortFocus enum
		 * @param text containing a sort mechanism name
		 * @return SortFocus classification
		 * @throws IllegalArgumentException if the text does not match any declared SortFocus
		 */
		public static SortFocus parseSort(String text) {
				if (text.equals(ALPHABETICAL.formattedName)) {
					return ALPHABETICAL;
				} else if (text.equals(NUMERICAL.formattedName)) {
					return NUMERICAL;
				} else {
					throw new IllegalArgumentException("Not a valid sorting mechanism");
				}
		}
	}
	
	/**
	 * Indicates how the data is to be colored, if at all.
	 * Placed here rather than in Manager because Manager is a singleton class
	 */
	public enum ColorMethod {
		NO_COLOR("NoColor"), 
		FIN_DROP("FinDrop"),
		SERIES_SPECIAL("SeriesSpecial"),
		SUB_DUB("SubDub");

		/** Formatted String for each ColorMethod for display in the GUI */
		public final String formattedName;

		/**
		 * Constructor for each ColorMethod enum, setting the formattedName field
		 * @param formattedName String representation of the coloring method
		 */
		ColorMethod(String formattedName){
			this.formattedName = formattedName;
		}

		/**
		 * Classifies a string of text into the correct ColorMethod enum
		 * @param text containing a color method name
		 * @return ColorMethod classification
		 * @throws IllegalArgumentException if the text does not match any declared ColorMethod
		 */
		public static ColorMethod parseSort(String text) {
				if (text.equals(NO_COLOR.formattedName)) {
					return NO_COLOR;
				} else if (text.equals(FIN_DROP.formattedName)) {
					return FIN_DROP;
				} else if (text.equals(SERIES_SPECIAL.formattedName)) {
					return SERIES_SPECIAL;
				} else if (text.equals(SUB_DUB.formattedName)) {
					return SUB_DUB;
				} else {
					throw new IllegalArgumentException("Not a valid coloring method");
				}
		}
	}
	
}
