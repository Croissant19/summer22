package data;

import java.util.Objects;

/**
 * Defines an Anime object as a fundamental piece of the AnimeLog program.
 * In this program, two anime cannot have the same name and year
 * @author Hunter Pruitt
 */
public class Anime extends Media {	
	
	/** Indicates the audio the show was watched with */
	private Language language;
		
	/** Director credits on the show */
	private String director;
	
	/** Studio credited with producing the show */
	private String studio;

	
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
		 * @throws IllegalArgumentException is the language isn't valid.
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
	 * @param studio producers of the show
	 * @param notes personal notes about the entry
	 * @throws IllegalArgumentException if passed bad parameters
	 */
	public Anime(String title, int year, int count, Language lan, Type type, boolean finished, 
			boolean dropped, String director, String studio, String notes) {

		//Use superclass constructor
		super(title, year, count, type, finished, dropped, notes);

		//Set remaining fields here, using setters for checks
		setLanguage(lan);
		setDirector(director);
		setStudio(studio);
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
	 * Returns the director(s) for the GUI
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Changes or sets the director field
	 * @param director the director to set
	 * @throws IllegalArguemntException if the string contains an illegal substring
	 */
	public void setDirector(String director) {
		if (!isValidString(director)) {
			throw new IllegalArgumentException(Media.ILLEGAL_SUBSTRING_EXCEPTION);
		}
		//Accepts empty values
		this.director = director;
	}

	/**
	 * Returns the studio(s) for the GUI
	 * @return the studio
	 */
	public String getStudio() {
		return studio;
	}

	/**
	 * Changes or sets the studio field
	 * @param studio the studio to set
	 * @throws IllegalArguemntException if the string contains an illegal substring
	 */
	public void setStudio(String studio) {
		if (!isValidString(studio)) {
			throw new IllegalArgumentException(ILLEGAL_SUBSTRING_EXCEPTION);
		}
		//Accepts empty values
		this.studio = studio;
	}


	/**
	 * Generates a String representation of an anime for use in file io
	 * @return String representation of fields
	 */
	@Override
	public String toString() {
		String s = getTitle() + ",_" + getYear() + ",_" + getCount() + ",_" + language.formattedName 
				+ ",_" + getType() + ",_" + isFinished() + ",_" + isDropped() + ",_" 
				+ director + ",_" + studio + ",_" + getNotes();

		return s;
	}

	/**
	 * Hashing method for Anime objects
	 * @return a hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(language, director, studio);
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
			return getTitle().toLowerCase().equals(a.getTitle().toLowerCase()) && getYear() == a.getYear();
		} else {
			return false;
		}
	}

}
