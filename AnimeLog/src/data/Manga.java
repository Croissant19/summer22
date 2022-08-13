package data;

import java.util.Objects;

/**
 * Defines a Manga object as a fundamental part of the log program. 
 * Child class of Media.
 * In this program, two Manga cannot have the same name and year.
 * @author Hunter Pruitt
 */
public class Manga extends Media {

	/** Author and/or artist of the manga */
	private String author;

	/** The magazine that the manga was published in, alternatively, the publisher for the user's country of origin */
	private String publisher;
	
	/** If the manga has not concluded */
	private boolean ongoing;
	
	/**
	 * Constructor for Manga object, set fields and checks for invalid arguments
	 * @param title name of entry
	 * @param year release year for first chapter
	 * @param count number chapters read
	 * @param author person(s) who write and draw the story
	 * @param publishedIn source of the Manga
	 * @param type series or special
	 * @param finished if the user finished with the entry
	 * @param dropped if the user stopped watching / reading the entry
	 * @param ongoing if the story has not concluded or is still being serialized
	 * @param notes personal notes about the entry
	 * @throws IllegalArgumentException if passed bad parameters
	 */
	public Manga(String title, int year, int count, String author, String publishedIn, Type type, boolean finished, boolean dropped, boolean ongoing, String notes) {
		//Set common fields with super constructor
		super(title, year, count, type, finished, dropped, notes);
		//Check validity and then set other fields with setters
		setAuthor(author);
		setPublisher(publishedIn);
		setOngoing(ongoing);
	}

	/**
	 * Returns the author/artist as the user presented it
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author after ensuring it is a valid string
	 * @param author the person responsible for the series
	 * @throws IllegalArgumentException if the string contains an invalid substring
	 */
	public void setAuthor(String author) {
		if (isValidString(author)) {
			this.author = author;	
		} else {
			throw new IllegalArgumentException(Media.ILLEGAL_SUBSTRING_EXCEPTION);
		}
	}

	/**
	 * Returns the publisher field as the user presented it
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Sets the publisher field after ensuring it is a valid string
	 * @param source the source of the series
	 * @throws IllegalArgumentException if the string contains an invalid substring
	 */
	public void setPublisher(String source) {
		if (isValidString(source)) {
			this.publisher = source;	
		} else {
			throw new IllegalArgumentException(Media.ILLEGAL_SUBSTRING_EXCEPTION);
		}
	}
	
	/**
	 * Indicates if the series is currently ongoing
	 * @return the ongoing
	 */
	public boolean isOngoing() {
		return ongoing;
	}

	/**
	 * Sets the ongoing flag for this Manga
	 * @param ongoing the ongoing to set
	 */
	public void setOngoing(boolean ongoing) {
		this.ongoing = ongoing;
	}

	
	/**
	 * Generates a String representation of the Manga for use in file io
	 * @return String representation of fields
	 */
	@Override
	public String toString() {
		String s = getTitle() + ",_" + getYear() + ",_" + getCount() + ",_" + author + ",_" + publisher 
				+ ",_" + getType() + ",_" + isFinished() + ",_" + isDropped() + ",_" 
				+ ongoing + ",_" + getNotes();

		return s;
	}

	/**
	 * Hashing method for Manga objects
	 * @return a hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(author, publisher, ongoing);
	}

	/**
	 * Compares an object to this Manga to see if they are the same.
	 * They are the same if they have the same name and release year, as well as being of the same class
	 * @return boolean indicator of equivalence
	 */
	@Override
	public boolean equals(Object obj) {
		//Checks that obj is an manga and if titles and years match
		if (obj instanceof Manga) {
			Manga m = (Manga)obj;
			return getTitle().toLowerCase().equals(m.getTitle().toLowerCase()) && getYear() == m.getYear();
		} else {
			return false;
		}
	}
	
}
