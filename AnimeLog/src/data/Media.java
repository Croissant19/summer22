package data;

/**
 * Defines a generic Media object with fields that are common between 
 * all entry types planned for the log project, including Anime, Manga, etc...
 * 
 * @author Hunter Pruitt
 */
public abstract class Media {

	/** String used in data files for delimiting properties */
	private static final String INVALID_STRING_1 = ",_";

	/** String used in data files for delimiting entries */
	private static final String INVALID_STRING_2 = "<|>";
	
	/** String used in data files for delimiting preference data, not included in text-check procedure due to improbability */
	private static final String INVALID_STRING_3 = "{PREFERENCES}";

	/** Message for exceptions thrown that contain illegal substrings */
	public static final String ILLEGAL_SUBSTRING_EXCEPTION = "Text fields cannot contain the sequences \",_\" or \"<|>\"";
	
	/** Title of the Media object */
	private String title;

	/** First release year of the Media */
	private int year;

	/** Number of episodes watched, chapters read, etc. */
	private int count;
		
	/** Indicates the type of content this is, meaning different things for different child classes. */
	private Type type;
	
	/** Indicates if the user has finished consuming the content */
	private boolean finished;
	
	/** Indicates if the user has decided to stop consuming the content */
	private boolean dropped;
	
	/** Optional notes for the user to attach to this entry */
	private String notes;
	
	/**
	 * Indicates whether the content is a series, or a special.
	 * In Anime, specials may refer to movies or OVAs, Manga Type Special may refer to one shots or unusual entries.
	 * Ultimately, interpretation is up to the user.
	 */
	public enum Type {
		SERIES("Series"), 
		SPECIAL("Special");
		
		/** Formatted String for each Type for display in the GUI */
		public final String formattedName;

		/**
		 * Constructor for each Type enum, setting the formattedName field
		 * @param formattedName String representation of the Type
		 */
		Type(String formattedName){
			this.formattedName = formattedName;
		}

		/**
		 * Classifies a string of text into the correct type enum
		 * @param text containing a type name
		 * @return Type classification
		 * @throws IllegalArgumentException is the type isn't valid.
		 */
		public static Type parseType(String text) {
				if (text.equals(SERIES.formattedName)) {
					return SERIES;
				} else if (text.equals(SPECIAL.formattedName)) {
					return SPECIAL;
				} else {
					throw new IllegalArgumentException("Not a valid type");
				}
		}
	
	}
	
	/**
	 * Constructor for an Media object, sets fields shared by Anime, Manga, etc.
	 * 
	 * @param title name of entry
	 * @param year release year for first piece of content
	 * @param count number consumed
	 * @param type series or special
	 * @param finished if the user finished with the entry
	 * @param dropped if the user stopped watching / reading the entry
	 * @param notes personal notes about the entry
	 * @throws IllegalArgumentException if passed bad parameters
	 */
	public Media(String title, int year, int count, Type type, boolean finished, boolean dropped, String notes) {
		//Delegate checks to setters
		setTitle(title);
		setYear(year);
		setCount(count);
		setType(type);
		setFinished(finished);
		setDropped(dropped);
		setNotes(notes);
	}

	
	/**
	 * Compares two media for sorting using alphabetical order (and year if both titles match)
	 * This method indicates if this media precedes the other.
	 * Sorting is not case-sensitive and numbers/symbols come before letters
	 * 
	 * @param otherMedia Media to be compared to
	 * @return boolean indicator of if this Media comes before the parameter Media
	 * @throws IllegalArgumentException if passed a Media identical to this
	 */
	public boolean sortsBeforeTitleFocus(Media otherMedia) {
		//If media are equal, throw an exception
		//Cannot have duplicate Media in the program
		if (this.equals(otherMedia)) {
			throw new IllegalArgumentException("Cannot compare duplicate entries.");
		}
		
		String thisTitle = this.getTitle().toLowerCase();
		String otherTitle = otherMedia.getTitle().toLowerCase();
		if (!thisTitle.equals(otherTitle)) {
			//If titles are not equal, compare them ignoring case
			return thisTitle.compareTo(otherTitle) < 0 ;
			
		} else {
			//If titles are the same refer to release year
			return this.getYear() < otherMedia.getYear();
		}
	}
	
	
	/**
	 * Alternate sorting based method. Indicates if this Media precedes the Media parameter 
	 * based on year comparison and then alphabetical precedence.
	 * @param otherMedia Media to be compared against
	 * @return boolean indicator of if this Media comes before the parameter Media
	 * @throws IllegalArgumentException if passed a Media identical to this
	 */
	public boolean sortsBeforeYearFocus(Media otherMedia) {
		//If media are equal, throw an exception
		//Cannot have duplicate media in the program
		if (this.equals(otherMedia)) {
			throw new IllegalArgumentException("Cannot compare duplicate entries.");
		}
		
		if (this.getYear() != otherMedia.getYear()) {
			//If years do not match
			return this.getYear() < otherMedia.getYear();

		} else {
			//If years do match, check alphabetical precedence
			String thisTitle = this.getTitle().toLowerCase();
			String otherTitle = otherMedia.getTitle().toLowerCase();

			return thisTitle.compareTo(otherTitle) < 0 ;
		}
	}
	
	
	/**
	 * Returns the title for display on the GUI
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Changes or sets the title field
	 * @param title the title to set
	 * @throws IllegalArumentException if title is blank or contains an illegal String
	 */
	public void setTitle(String title) {
		if (title.isBlank()) {
			throw new IllegalArgumentException("Title cannot be blank");
		} else if (!isValidString(title)) {
			throw new IllegalArgumentException(ILLEGAL_SUBSTRING_EXCEPTION);
		}
		this.title = title;
	}

	/**
	 * Returns the year the Media was first released in
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the release year of the Media
	 * @param year the year to set
	 * @throws IllegalArgumentException if year is not between 1900 and 2100
	 */
	public void setYear(int year) {
		if (year > 2100 || year < 1900) {
			throw new IllegalArgumentException("Invalid year");
		}
		this.year = year;
	}

	/**
	 * Returns the number watched / read for display on the GUI
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Changes or sets the count field
	 * @param count the count to set
	 * @throws IllegalArumentException if passed a negative value
	 */
	public void setCount(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("Count must be non-negative");
		}
		this.count = count;
	}
	
	/**
	 * Returns the type classification for display on the GUI
	 * @return the type
	 */
	public String getType() {
		return type.formattedName;
	}

	/**
	 * Changes or sets the type field
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Indicates if the user is finished with the Media
	 * @return finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Changes or sets the finished field
	 * @param finished the finished to set
	 * @throws IllegalArgumentException if dropped is also true
	 */
	public void setFinished(boolean finished) {
		if (finished && dropped) {
			throw new IllegalArgumentException("Entry cannot be both dropped and finished");
		}
		this.finished = finished;
	}

	/**
	 * Indicates if the user dropped the show
	 * @return dropped
	 */
	public boolean isDropped() {
		return dropped;
	}

	/**
	 * Changes or sets the dropped field
	 * @param dropped the dropped to set
	 * @throws IllegalArgumentException if finished is also true
	 */
	public void setDropped(boolean dropped) {
		if (finished && dropped) {
			throw new IllegalArgumentException("Entry cannot be both dropped and finished");
		}
		this.dropped = dropped;
	}
	
	/**
	 * Returns the notes for the GUI
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Changes or sets the notes field
	 * @param notes the notes to set
	 * @throws IllegalArguemntException if the string contains an illegal substring
	 */
	public void setNotes(String notes) {
		if (!isValidString(notes)) {
			throw new IllegalArgumentException(ILLEGAL_SUBSTRING_EXCEPTION);
		}
		//Accepts empty values
		this.notes = notes;
	}

	/**
	 * Indicates if the string used for any field stored as text contains illegal substrings, i.e., strings that
	 * are also used in DataIO to parse data.
	 * @param s String to be checked
	 * @return boolean indicator of if a String is acceptable
	 */
	public boolean isValidString(String s) {
		return s == null || !s.contains(INVALID_STRING_1) && !s.contains(INVALID_STRING_2);
	}
	
	
}
