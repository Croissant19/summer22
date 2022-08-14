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
		for (Media m : animeList) {
			animeByYear.add(m);
		}
		for (Media m : mangaList) {
			mangaByYear.add(m);
		}

		animePreferences = animeP;
		mangaPreferences = mangaP;
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
	 * Adds a manga to both sorted lists in the data
	 * @param m Manga to be added
	 * @throws NullPointerException if m is null
	 * @throws IllegalArgumentException if m is a copy of any other element
	 */
	public void addManga(Manga m) {
		mangaByTitle.add(m);
		mangaByYear.add(m);
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
	 * Removes the passed Manga from both lists in data
	 * @param m Manga to be removed
	 * @return removed Manga
	 * @throws IndexOutOfBoundsException if the passed index is out of bounds
	 */
	public Manga removeManga(Manga m) {
		mangaByTitle.remove(mangaByTitle.indexOf(m));
		return (Manga) mangaByYear.remove(mangaByYear.indexOf(m));
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
