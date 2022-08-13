package data;

import data.Preferences.SortFocus;
import util.SortedMediaList;

/**
 * Class representing the whole of user data. Stores anime and preferences for export and import
 * @author Hunter Pruitt
 */
public class Data {

	/** Reference Anime list sorted by Title */
	private SortedMediaList animeByTitle = new SortedMediaList(SortFocus.ALPHABETICAL);

	/** Reference Anime list sorted by Year */
	private SortedMediaList animeByYear = new SortedMediaList(SortFocus.NUMERICAL);
	
	private Preferences preferences;
	
	
	/**
	 * Constructs the Data object, setting defaults and initializing fields when they are declared
	 */
	public Data() {
		//Set default preferences
		preferences = new Preferences();
	}

	/**
	 * Constructor compatible with imported data, sets fields to values specified in DataIO
	 * @param animeList SortedAnimeList to overwrite default empty list fields
	 * @param p user preferences from import
	 */
	public Data(SortedMediaList animeList, Preferences p) {
		//Always import and export with alphabetical list
		animeByTitle = animeList;
		//Construct numerical list from alphabetical list
		for (Media m : animeList) {
			animeByYear.add(m);
		}

		this.preferences = p;
	}
	
	/**
	 * Adds an anime to both sorted lists in the data
	 * @param a Anime to be added
	 * @throws NullPointerException if a is null
	 * @throws IllegalArgumentException if a is a copy of any other element
	 */
	public void addAnime(Anime a) {
		animeByTitle.add(a);
		animeByYear.add(a);
	}

	/**
	 * Removes the passed Anime from both lists in data
	 * @param a Anime to be removed
	 * @return removed Anime
	 * @throws IndexOutOfBoundsException if the passed index is out of bounds
	 */
	public Anime removeAnime(Anime a) {
		animeByTitle.remove(animeByTitle.indexOf(a));
		return (Anime) animeByYear.remove(animeByYear.indexOf(a));
	}
	
	/**
	 * Retrieves the anime list organized by title
	 * @return SortedMediaList sorted by title
	 */
	public SortedMediaList getAlphabeticalAnimeList() {
		return animeByTitle;
	}

	/**
	 * Retrieves the anime list organized by year
	 * @return SortedMediaList sorted by release year
	 */
	public SortedMediaList getNumericalAnimeList() {
		return animeByYear;
	}
	
	/**
	 * Returns a Preferences object with information on how data is to be displayed
	 * @return preference data
	 */
	public Preferences getPreferences() {
		return preferences;
	}


}
