package data;

import java.awt.Color;

/**
 * Container class for preference enums, related to Sorting and Color preferences
 * @author Hunter Pruitt
 */
public class Preferences {
	/** Default color for table data that meets selected conditions, a mint green shade*/
	public static final Color DEFAULT_COLOR_1 = new Color(-3342388);
	
	/** Default color for table data that meets selected conditions, a light metallic blue */
	public static final Color DEFAULT_COLOR_2 = new Color(-3355393);
	
	/** Method to sort user data in a GUI JTable */
	private SortFocus sortBy;
	
	/** Method used to shade Anime table data */
	private ColorMethod colorBy;
	
	/** Color one for shading rows */
	private Color color1;

	/** Color two for shading rows */
	private Color color2;


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

	/**
	 * Default constructor for preferences object by setting fields to default settings
	 */
	public Preferences() {
		//Set defaults
		this(SortFocus.ALPHABETICAL, ColorMethod.NO_COLOR, Preferences.DEFAULT_COLOR_1.getRGB(), Preferences.DEFAULT_COLOR_2.getRGB());
	}
	
	/**
	 * Constructs preferences object by setting fields
	 * @param sort SortFocus to sort by
	 * @param color method for coloring data rows
	 * @param c1 RGB of color for shading some data rows
	 * @param c2 RGB of color for shading some other data rows
	 */
	public Preferences(SortFocus sort, ColorMethod color, int c1, int c2) {
		//Set parameters
		setSortMethod(sort);
		setColorMethod(color);
		setColor1(c1);
		setColor2(c2);
	}
	
	/**
	 * Sets the method the user wants to sort their anime data by.
	 * @param sortMethod Enumeration for sort method
	 */
	public void setSortMethod(SortFocus sortMethod) {
		sortBy = sortMethod;
	}
	
	/**
	 * Indicates the sorting method the user is using.
	 * @return SortFocus for the user's data table
	 */
	public SortFocus getSortMethod() {
		return sortBy;
	}
	
	/**
	 * Indicates the coloring method the user is using.
	 * @return ColorMethod for the user's data table
	 */
	public ColorMethod getColorMethod() {
		return colorBy;
	}

	/**
	 * Sets the coloring method the user wants for their data table.
	 * @param colorMethod for the data table
	 */
	public void setColorMethod(ColorMethod colorMethod) {
		colorBy = colorMethod;
	}
	
	/**
	 * Indicates the first color selected by the user or the default if not color selected
	 * @return Primary color
	 */
	public Color getColor1() {
		return color1;
	}

	/**
	 * Sets the first color to a user passed color
	 * @param rgb RGB of the color to be set
	 */
	public void setColor1(int rgb) {
		color1 = new Color(rgb);
	}
	
	/**
	 * Indicates the second color selected by the user or the default if not color selected
	 * @return Secondary color
	 */
	public Color getColor2() {
		return color2;
	}

	/**
	 * Sets the second color to a user passed color
	 * @param rgb RGB of the color to be set
	 */
	public void setColor2(int rgb) {
		color2 = new Color(rgb);
	}
	
	
	/**
	 * Generates a String representation of user preferences for use in file io
	 * @return String representation of fields
	 */
	@Override
	public String toString() {
		String s = sortBy.formattedName + ",_" + colorBy.formattedName + ",_"
				+ color1.getRGB() + ",_" + color2.getRGB();
		
		return s;
	}

	
}
