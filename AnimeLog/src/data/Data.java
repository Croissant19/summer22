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
	
	/** Reference Manga list sorted by Title */
	private SortedMediaList mangaByTitle = new SortedMediaList(SortFocus.ALPHABETICAL);

	/** Reference Manga list sorted by Year */
	private SortedMediaList mangaByYear = new SortedMediaList(SortFocus.NUMERICAL);
	
	/** Preferences for sorting Anime */
	private Preferences animePreferences;

	/** Preferences for sorting Manga */
	private Preferences mangaPreferences;
	
	/**
	 * Constructs the Data object, setting defaults and initializing fields when they are declared
	 */
	public Data() {
		//Set default preferences
		animePreferences = new Preferences();
		mangaPreferences = new Preferences();
	}

	/**
	 * Constructor compatible with imported data, sets fields to values specified in DataIO
	 * @param animeList SortedMediaList to overwrite default empty list fields
	 * @param animeP user preferences from import
	 * @param mangaList SortedMediaList to overwrite default empty list fields
	 * @param mangaP user preferences from import
	 */
	public Data(SortedMediaList animeList, Preferences animeP, SortedMediaList mangaList, Preferences mangaP) {
		//Always import and export with alphabetical list
		animeByTitle = animeList;
		mangaByTitle = mangaList;
		//Construct numerical list from alphabetical list
		if (animeList != null) {
			for (Media m : animeList) {
				animeByYear.add(m);
			}	
		}
		if (mangaList != null) {
			for (Media m : mangaList) {
				mangaByYear.add(m);
			}	
		}

		animePreferences = animeP;
		mangaPreferences = mangaP;
	}

	/**
	 * Adds an anime to both sorted lists in the data
	 * @param a Anime to be added
	 * @throws NullPointerException if a is null
	 * @throws IllegalArgumentException if a is a copy of any other element
	 * @throws IllegalArgumentException if the parameter is not an Anime
	 */
	public void addAnime(Media a) {
		if (a instanceof Anime) {
			animeByTitle.add(a);
			animeByYear.add(a);	
		} else {
			throw new IllegalArgumentException("Tried to add non-Anime object to Anime list.");
		}
	}

	/**
	 * Adds a manga to both sorted lists in the data
	 * @param m Manga to be added
	 * @throws NullPointerException if m is null
	 * @throws IllegalArgumentException if m is a copy of any other element
	 * @throws IllegalArgumentException if the parameter is not a Manga
	 */
	public void addManga(Media m) {
		if (m instanceof Manga) {
			mangaByTitle.add(m);
			mangaByYear.add(m);	
		} else {
			throw new IllegalArgumentException("Tried to add non-Manga object to Manga list.");
		}
	}
	
	/**
	 * Removes the passed Anime from both lists in data
	 * @param a Anime to be removed
	 * @return removed Anime
	 * @throws IndexOutOfBoundsException if the passed index is out of bounds
	 * @throws IllegalArgumentException if the parameter is not an Anime
	 */
	public Media removeAnime(Media a) {
		if (a instanceof Anime) {
			animeByTitle.remove(animeByTitle.indexOf(a));
			return animeByYear.remove(animeByYear.indexOf(a));	
		} else {
			throw new IllegalArgumentException("Tried to remove non-Anime from Anime list.");
		}
	}
	
	/**
	 * Removes the passed Manga from both lists in data
	 * @param m Manga to be removed
	 * @return removed Manga
	 * @throws IndexOutOfBoundsException if the passed index is out of bounds
	 * @throws IllegalArgumentException if the parameter is not a Manga
	 */
	public Media removeManga(Media m) {
		if (m instanceof Manga) {
			mangaByTitle.remove(mangaByTitle.indexOf(m));
			return mangaByYear.remove(mangaByYear.indexOf(m));
		} else {
			throw new IllegalArgumentException("Tried to remove non-Manga from Manga list.");
		}
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
	 * Retrieves the manga list organized by title
	 * @return SortedMediaList sorted by title
	 */
	public SortedMediaList getAlphabeticalMangaList() {
		return mangaByTitle;
	}

	/**
	 * Retrieves the manga list organized by year
	 * @return SortedMediaList sorted by release year
	 */
	public SortedMediaList getNumericalMangaList() {
		return mangaByYear;
	}
	
	/**
	 * Returns a Preferences object with information on how Anime data is to be displayed
	 * @return preference data
	 */
	public Preferences getAnimePreferences() {
		return animePreferences;
	}

	/**
	 * Returns a Preferences object with information on how Manga data is to be displayed
	 * @return preference data
	 */
	public Preferences getMangaPreferences() {
		return mangaPreferences;
	}

}
