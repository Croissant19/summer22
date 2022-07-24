package anime;

import java.util.Objects;

/**
 * Defines an Anime object as the fundamental piece of the AnimeLog program.
 * In this program, two anime cannot have the same name and year
 * @author Hunter Pruitt
 */
public class Anime implements Comparable<Anime> {
	
	////////////
	//Fields
	////////////
	
	/**
	 * Indicates whether the content is a series, or a special.
	 * Specials include OVAs, movies, and special one-off episodes.
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
	 * Indicates the language used in the anime
	 */
	public enum Language {
		SUB("Sub"), 
		DUB("Dub"),
		OTHER("Other");
		
		/** How the Language will appear when formatted */
		public final String formattedName;
		
		/**
		 * Constructor for Language, assigns formattedName field
		 * @param formattedName String as the name will appear in programming
		 */
		Language(String formattedName){
			this.formattedName = formattedName;
		}
		
		/**
		 * Classifies a string of text into the correct language enum
		 * @param text containing a language name
		 * @return Language classification
		 */
		public static Language parseLang(String text) {
			if (text.equals(SUB.formattedName)) {
				return SUB;
			} else if (text.equals(DUB.formattedName)) {
				return DUB;
			} else if (text.equals(OTHER.formattedName)) {
				return OTHER;
			} else {
				throw new IllegalArgumentException("Not a valid language");
			}
		}
	}

	/** Title of the anime */
	private String title;

	/** First release year of the anime*/
	private int year;

	/** Number of episodes watched */
	private int count;
	
	/** Indicates the audio the show was watched with */
	private Language language;
	
	/** Indicates the type of content this anime is, series, special, movie etc... */
	private Type type;
	
	/** Indicates if the user has finished watching the show */
	private boolean finished;
	
	/** Indicates if the user has decided to stop watching the show */
	private boolean dropped;
	
	/** Director credits on the show */
	private String director;
	
	/** Optional notes for the user to attach to this entry */
	private String notes;
			
	
	////////////
	//METHODS
	////////////
	
	/**
	 * Constructor for an anime object, used when reading in data
	 * 
	 * @param title name of entry
	 * @param year release year for first episode
	 * @param count number of episodes watched
	 * @param lan language
	 * @param type series or special
	 * @param finished if the user finished watching
	 * @param dropped if the user stopped watching
	 * @param director 
	 * @param notes personal notes about the entry
	 * @throws IllegalArgumentException if passed bad parameters
	 */
	public Anime(String title, int year, int count, Language lan, Type type, boolean finished, 
			boolean dropped, String director, String notes) {

		//Delegate checks to setters
		setTitle(title);
		setYear(year);
		setCount(count);
		setLanguage(lan);
		setType(type);
		setFinished(finished);
		setDropped(dropped);
		setDirector(director);
		setNotes(notes);
	}
		
	
	/**
	 * Compared two anime for sorting.
	 * Using alphabetical order (and year if both titles match), this method returns
	 * negative if this anime precedes the other, 0 if both have the same year and title, 
	 * and positve if this anime follows the other in sorted order
	 * Sorting is not case-sensitive and numbers/symbols come before letters
	 * 
	 * @param a anime to be compared to
	 * @return numerical indicator of where this anime should be sorted in compairison to another anime
	 */
	@Override
	public int compareTo(Anime a) {
		//Get and compare titles
		String thisTitle = this.title.toLowerCase();
		String otherTitle = a.getTitle().toLowerCase();
		if (!thisTitle.equals(otherTitle)) {
			return thisTitle.compareTo(otherTitle);
		} else { //Compare years
			Integer thisYear = year;
			Integer otherYear = a.getYear();
			return thisYear.compareTo(otherYear);
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
	 * @throws IllegalArumentException if title is blank
	 */
	public void setTitle(String title) {
		if (title.isBlank()) {
			throw new IllegalArgumentException("Title cannot be blank");
		}
		this.title = title;
	}

	/**
	 * Returns the year the anime was released in
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the release year of the anime
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
	 * Returns the number watched for display on the GUI
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
	 * Returns the formatted name of the language for display on the GUI
	 * @return the language
	 */
	public String getLanguage() {
		return language.formattedName;
	}

	/**
	 * Changes or sets the language field
	 * @param language the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
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
	 * Indicates if the user is finished with the show
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
			throw new IllegalArgumentException("Show cannot be both dropped and finished");
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
			throw new IllegalArgumentException("Show cannot be both dropped and finished");
		}
		this.dropped = dropped;
	}

	/**
	 * Returns the director(s) for the GUI
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Changes or sets the notes field
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		//Accepts empty values
		this.director = director;
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
	 */
	public void setNotes(String notes) {
		//Accepts empty values
		this.notes = notes;
	}

		
	/**
	 * Generates a String representation of an anime for use in file io
	 * @return String representation of fields
	 */
	@Override
	public String toString() {
		String s = title + ",_" + year + ",_" + count + ",_" + language.formattedName 
				+ ",_" + type.formattedName + ",_" + finished + ",_" + dropped + ",_" 
				+ director + ",_" + notes;

		return s;
	}

	/**
	 * Hashing method for Anime objects
	 * @return a hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(count, director, dropped, finished, language, notes, title, type);
	}

	/**
	 * Compares two anime to see if they are the same
	 * They are the same if they have the same name and release year
	 * @return boolean indicator of equivalence
	 */
	@Override
	public boolean equals(Object obj) {
		//Checks that obj is an anime and if titles and years match
		if (obj instanceof Anime) {
			Anime a = (Anime)obj;
			return this.title.toLowerCase().equals(a.getTitle().toLowerCase()) && this.year == a.getYear();
		} else {
			return false;
		}
	}

}
