package anime;

/**
 * 
 * @author Hunter Pruitt
 */
public class Anime {
	
	////////////
	//Fields
	////////////
	
	/**
	 * Indicates whether the content is a series, or a special.
	 * Specials include OVAs, movies, and special one-off episodes.
	 * 
	 * @author Hunter Pruitt
	 */
	public enum Type {SERIES, SPECIAL}
	
	/**
	 * Indicates whether the content features
	 * Specials include OVAs, movies, and special one-off episodes.
	 * 
	 * @author Hunter Pruitt
	 */
	public enum Language {SUB, DUB, OTHER}
	//TODO: feature so that when language is other, have a field to elaborate

	/** Title of the anime */
	private String title;

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
	
	//TODO: maybe make directors into an array
	/** Director credits on the show */
	private String director;
	
	/** Optional notes for the user to attach to this entry */
	private String notes;
	
	//TODO: add image data when get closer to GUI implementation
	
	
	
	////////////
	//METHODS
	////////////
	
	/**
	 * Constructor for an anime object
	 */
	public Anime() {
		
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
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Returns the language for display on the GUI
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
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
	public Type getType() {
		return type;
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
	 */
	public void setFinished(boolean finished) {
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
	 */
	public void setDropped(boolean dropped) {
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
		this.notes = notes;
	}
	
}
