package manager;

import java.awt.Color;
import java.io.File;

import data.Anime;
import data.Anime.Language;
import data.Media.Type;
import data.Media.MediaType;
import data.Data;
import data.Manga;
import data.Media;
import data.Preferences;
import data.Preferences.ColorMethod;
import data.Preferences.SortFocus;
import io.DataIO;
import util.SortedMediaList;

/**
 * Connects actions on the GUI to computations performed and data retrieved from other classes.
 * Operates on the singleton pattern.
 * @author Hunter Pruitt
 */
public class Manager {
	
	/** Singleton pointer to the Manager object */
	private static Manager instance = new Manager();

	/** Pointer to anime list to display */
	private SortedMediaList animeList;
	
	/** Pointer to manga list to display */
	private SortedMediaList mangaList;

	/** Object containing references to user data and preferences */
	private Data userData;
	
	/** Pointer to the list to currently display */
	private SortedMediaList currentList;
	
	/**
	 * Initializes the Manager object, and creates empty or default user data so that it is not null.
	 * Sorting method is set to alphabetical by default.
	 */
	private Manager() {
		userData = new Data();
		animeList = userData.getAlphabeticalAnimeList();
		mangaList = userData.getAlphabeticalMangaList();
		currentList = animeList;
	}

	/** 
	 * Retrieves the manager for method calls
	 * @return the Singleton instance of the Manager
	 */
	public static Manager getInstance() {
		return instance;
	}

	/**
	 * Retrieves data from a user selected file
	 * @param filename file selected by user in JFileChooser
	 */
	public void processFile(String filename) {
		userData = DataIO.readFile(filename);

		//Set animeList
		if (userData.getAlphabeticalAnimeList() != null ) {
			if (userData.getAnimePreferences().getSortMethod() == SortFocus.ALPHABETICAL) {
				animeList = userData.getAlphabeticalAnimeList();
			} else if (userData.getAnimePreferences().getSortMethod() == SortFocus.NUMERICAL) {
				animeList = userData.getNumericalAnimeList();
			}			
		}

		//Set mangaList
		if (userData.getAlphabeticalMangaList() != null) {
			if (userData.getMangaPreferences().getSortMethod() == SortFocus.ALPHABETICAL) {
				mangaList = userData.getAlphabeticalMangaList();
			} else if (userData.getMangaPreferences().getSortMethod() == SortFocus.NUMERICAL) {
				mangaList = userData.getNumericalMangaList();
			}			
		}

		
		//The animeList is the default
		currentList = animeList;

		//However, if animeList is empty and mangaList is not, then mangaList is used
		if (animeList == null && mangaList != null) {
			currentList = mangaList;
		}
		
	}
	
	/**
	 * Saves data to a file location specified by the user in JFileChooser
	 * @param file to save data in
	 */
	public void saveFile(File file) {		
		DataIO.writeData(userData, file);		
	}
	
	/**
	 * Returns user's Anime list Preferences 
	 * @return Object containing the user's anime preferences
	 */
	public Preferences getAnimePreferences() {
		return this.userData.getAnimePreferences();
	}
	
	/**
	 * Returns user's Manga list Preferences 
	 * @return Object containing the user's manga preferences
	 */
	public Preferences getMangaPreferences() {
		return this.userData.getMangaPreferences();
	}

	/**
	 * Provides whichever SortedMediaList is currently being used for use in other classes
	 * @return the currentList
	 */
	public SortedMediaList getList() {
		return currentList;
	}

	/**
	 * Sets the currentList for use with the GUI.
	 * @param mt MediaType enum for which list should be used
	 * @param focus sort method to sort data, null if no change
	 */
	public void setCurrentList(MediaType mt, SortFocus focus) {
		switch (mt) {
		case ANIME:
			if (focus == SortFocus.ALPHABETICAL) {
				animeList = userData.getAlphabeticalAnimeList();
			} else if (focus == SortFocus.NUMERICAL) {
				animeList = userData.getNumericalAnimeList();
			}

			currentList = animeList;
			break;
		case MANGA:
			if (focus == SortFocus.ALPHABETICAL) {
				mangaList = userData.getAlphabeticalMangaList();
			} else if (focus == SortFocus.NUMERICAL) {
				mangaList = userData.getNumericalMangaList();
			} 

			currentList = mangaList;
			break;
		}
	}

	/**
	 * Adds an anime to userData
	 * @param a Anime to be added
	 * @throws IllegalArgumentException if a is a copy of any other element
	 */
	public void addAnime(Media a) {
		userData.addAnime(a);
	}

	/**
	 * Removes a selected anime from the animeList
	 * @param a Media object (hopefully an Anime) to remove
	 * @throws IndexOutOfBoundsException if the passed index is out of bounds
	 */
	public void removeAnime(Media a) {
		userData.removeAnime(a);
	}

	/**
	 * Adds a manga to userData
	 * @param m Manga to be added
	 * @throws IllegalArgumentException if m is a copy of any other element
	 */
	public void addManga(Media m) {
		userData.addManga(m);
	}
	
	
	/**
	 * Removes a selected manga from the mangaList
	 * @param m Media object (hopefully a Manga) to remove
	 * @throws IndexOutOfBoundsException if the passed index is out of bounds
	 */
	public void removeManga(Media m) {
		userData.removeManga(m);
	}

	/**
	 * Grabs title, year, and count for each anime for display on the GUI. 
	 * Return is type Object[][] so that ints and Strings are both displayed in the table together.
	 * It could be type String[][], but ultimately doesn't matter.
	 * 
	 * The sorting for this method is dependent on the sorting method of the list that 
	 * the pointer animeList is set to. This pointer can be changed by the user in the OptionsView 
	 * 
	 * @return 2D array of anime data for display on the GUI
	 */
	public Object[][] getAllAnimeAsArray() {
		Object[][] list = new Object[animeList.size()][3];

		//Get wanted data for each anime
		int index = 0;
		for (Media a : animeList) {
			list[index][0] = a.getYear();
			list[index][1] = a.getTitle();
			list[index][2] = a.getCount();
			index++;
		}

		return list;
	}

	/**
	 * Grabs title, year, and count for each manga for display on the GUI. 
	 * Return is type Object[][] so that ints and Strings are both displayed in the table together.
	 * It could be type String[][], but ultimately doesn't matter.
	 * 
	 * The sorting for this method is dependent on the sorting method of the list that 
	 * the pointer mangaList is set to. This pointer can be changed by the user in the OptionsView 
	 * 
	 * @return 2D array of manga data for display on the GUI
	 */
	public Object[][] getAllMangaAsArray() {
		Object[][] list = new Object[mangaList.size()][3];

		//Get wanted data for each manga
		int index = 0;
		for (Media m : mangaList) {
			list[index][0] = m.getYear();
			list[index][1] = m.getTitle();
			list[index][2] = m.getCount();
			index++;
		}

		return list;
	}

	////////////////////////
	//Preference related methods
	////////////////////////
	
	/**
	 * Changes the sorting method and retrieves the proper SortedMediaList to use
	 * @param mt MediaType indicating which data the method needs to work with
	 * @param sortBy SortFocus to sort media by
	 */
	public void setSortMethod(MediaType mt, SortFocus sortBy) {
		switch (mt) {
		case ANIME:
			userData.getAnimePreferences().setSortMethod(sortBy);
			if (sortBy == SortFocus.ALPHABETICAL) {
				animeList = userData.getAlphabeticalAnimeList();
			} else if (sortBy == SortFocus.NUMERICAL) {
				animeList = userData.getNumericalAnimeList();
			}
			currentList = animeList;
			break;
		case MANGA:
			userData.getMangaPreferences().setSortMethod(sortBy);
			if (sortBy == SortFocus.ALPHABETICAL) {
				mangaList = userData.getAlphabeticalMangaList();
			} else if (sortBy == SortFocus.NUMERICAL) {
				mangaList = userData.getNumericalMangaList();
			}
			currentList = mangaList;
			break;
		}
	}

	/**
	 * Changes the sorting method and retrieves the proper animeList to use
	 * @param mt MediaType indicating which data the method needs to work with
	 * @param colorBy ColorMethod to sort anime by
	 */
	public void setColorMethod(MediaType mt, ColorMethod colorBy) {
		switch (mt) {
		case ANIME:
			userData.getAnimePreferences().setColorMethod(colorBy);
			break;
		case MANGA:
			if (colorBy == ColorMethod.SUB_DUB) {
				throw new IllegalArgumentException("Cannot color by language with Manga");
			} else {
				userData.getMangaPreferences().setColorMethod(colorBy);
			}
			break;
		}
	}

	/**
	 * Changes the sorting method and retrieves the proper animeList to use
	 * @param mt MediaType indicating which data the method needs to work with
	 * @param color flag indicating if only media that is finished should be colored
	 */
	public void setColorOnlyFinished(MediaType mt, boolean color) {
		switch (mt) {
		case ANIME:
			userData.getAnimePreferences().setColorOnlyFinished(color);
			break;
		case MANGA:
			userData.getMangaPreferences().setColorOnlyFinished(color);
			break;
		}	
	}
	
	/**
	 * Sets the designated color to the color passed as a parameter
	 * @param mt MediaType indicating which data the method needs to work with
	 * @param pointer String name indicating which user color is to be changed
	 * @param c Color to set the user color to
	 * @throws IllegalArgumentException if passed a bad pointer
	 */
	public void setColor(MediaType mt, String pointer, Color c) {
		switch (mt) {
		case ANIME: 
			if (pointer.equals("Color1")) {
				getAnimePreferences().setColor1(c.getRGB());
			} else if (pointer.equals("Color2")) {
				getAnimePreferences().setColor2(c.getRGB());
			} else {
				throw new IllegalArgumentException("Pointer does not exist.");
			}
			break;
		case MANGA:
			if (pointer.equals("Color1")) {
				getMangaPreferences().setColor1(c.getRGB());
			} else if (pointer.equals("Color2")) {
				getMangaPreferences().setColor2(c.getRGB());
			} else {
				throw new IllegalArgumentException("Pointer does not exist.");
			}
			break;
		}
	}
	
	////////////////////////
	//Stat retrieval methods
	////////////////////////

	/**
	 * Returns the number of entries for use as a stat on the HomeView
	 * @param mt MediaType to get data for
	 * @throws IllegalArgumentException if no MediaType is indicated
	 * @return total number of entries
	 */
	public String getEntryCount(MediaType mt) {
		switch (mt) {
		case ANIME:
			return "" + animeList.size();
		case MANGA:
			return "" + mangaList.size();
		default:
			throw new IllegalArgumentException("No MediaType indicated");	
		}
	}
	
	/**
	 * Returns the number of entries that are series for use as a stat on the HomeView
	 * @param mt MediaType to get data for
	 * @throws IllegalArgumentException if no MediaType is indicated
	 * @return total number of series
	 */	
	public String getNumSeries(MediaType mt) {
		int tally = 0;
		switch (mt) {
		case ANIME:
			//Increment each time an anime on the list is Type Series
			for (Media a : animeList) {
				if (a.getType().equals(Type.SERIES.formattedName)) {
					tally++;
				}
			}
			break;
		case MANGA:
			//Increment each time a manga on the list is Type Series
			for (Media m : mangaList) {
				if (m.getType().equals(Type.SERIES.formattedName)) {
					tally++;
				}
			}
			break;
		default:
			throw new IllegalArgumentException("No MediaType indicated");
		}
		
		return "" + tally;
	}
	
	/**
	 * Returns the number of entries that are classified as specials for use as a stat on the HomeView
	 * @param mt MediaType to get data for
	 * @throws IllegalArgumentException if no MediaType is indicated
	 * @return total number of specials
	 */		
	public String getNumSpecial(MediaType mt) {
		int tally = 0;
		switch (mt) {
		case ANIME:
			//Increment each time an anime on the list is Type Series
			for (Media a : animeList) {
				if (a.getType().equals(Type.SPECIAL.formattedName)) {
					tally++;
				}
			}
			break;
		case MANGA:
			//Increment each time a manga on the list is Type Series
			for (Media m : mangaList) {
				if (m.getType().equals(Type.SPECIAL.formattedName)) {
					tally++;
				}
			}
			break;
		default:
			throw new IllegalArgumentException("No MediaType indicated");
		}
		
		return "" + tally;
	}
	
	/**
	 * Returns the sum of all counts for use as a stat on the HomeView
	 * @param mt MediaType to get data for
	 * @throws IllegalArgumentException if no MediaType is indicated
	 * @return sum of all anime or manga counts
	 */
	public String getCountSum(MediaType mt) {
		int tally = 0;
		switch (mt) {
		case ANIME:
			for (Media a : animeList) {
				tally += a.getCount();
			}
			break;
		case MANGA:
			for (Media m : mangaList) {
				tally += m.getCount();
			}
			break;
		default:
			throw new IllegalArgumentException("No MediaType indicated");
		}

		return "" + tally;
	}

	/**
	 * Computes the most used language type by entry (not count) and the percent for display 
	 * @return most used language and its percent as a String for the GUI, "N/A" if list is empty
	 */
	public String getFavoredLanguageAndPercent() {
		//If list is empty, return placeholder value
		if (animeList == null || animeList.size() == 0) {
			return "N/A";
		}

		
		int subCt = 0, dubCt = 0, otherCt = 0;
		boolean tie = false;
		//Tally counts for each language classification
		for (Media m : animeList) {
			Anime a = (Anime) m;
			switch (Language.parseLang(a.getLanguage())) {
			
			case SUB: 
				subCt++;
				break;
			case DUB: 
				dubCt++;
				break;
			case OTHER: 
				//Only count other if some episodes have been watched
				//That way other can't win if the list if full of unwatched TBDs
				if (a.getCount() > 0) {
					otherCt++;	
				}
				break;	
			}
				
		}
		//Determine winning count
		String winner;
		int winnerCt = 0;
		if (subCt > dubCt && subCt > otherCt) {
			winner = "Sub ";
			winnerCt = subCt;
		} else if (dubCt > subCt && dubCt > otherCt) {
			winner = "Dub ";
			winnerCt = dubCt;
		} else if (otherCt > subCt && otherCt > dubCt){
			winner = "Other ";
			winnerCt = otherCt;
		} else {
			tie = true;
			winner = "Tie";
		}

		//Get winner percent and finish building string if not a tie
		if (!tie) {
			int sumCt = subCt + dubCt + otherCt;
			int percent = winnerCt * 100 / sumCt;
			
			winner += percent + "%";			
		}
		
		return winner;	
	}
	
	
	/**
	 * Provides a string representation of the user's percent of finished media,
	 * followed by the percent sign (%)
	 * @param mt MediaType to get data for
	 * @throws IllegalArgumentException if no MediaType is indicated
	 * @return percent of total anime or manga finished, "N/A" if list is empty
	 */
	public String getPercentFinished(MediaType mt) {
		int sumCt = 0;
		int finCt = 0;
		
		switch (mt) {
		case ANIME:
			//If list is empty, return placeholder value
			if (animeList == null || animeList.size() == 0) {
				return "N/A";
			}

			//Iterate through each anime and tally the desired flag
			for (Media a : animeList) {
				if (a.isFinished()) {
					finCt++;
				}
				sumCt++;
			}
			break;

		case MANGA:
			//If list is empty, return placeholder value
			if (mangaList == null || mangaList.size() == 0) {
				return "N/A";
			}

			//Iterate through each anime and tally the desired flag
			for (Media m : mangaList) {
				if (m.isFinished()) {
					finCt++;
				}
				sumCt++;
			}
			break;

		default:
			throw new IllegalArgumentException("No MediaType indicated");
		}

		//Get finished percent and build String
		int percent = finCt * 100 / sumCt;

		return percent + "%";
	}
	
	/**
	 * Provides a string representation of the user's percent of dropped media,
	 * followed by the percent sign (%)
	 * @param mt MediaType to get data for
	 * @throws IllegalArgumentException if no MediaType is indicated
	 * @return percent of total anime dropped, "N/A" if list is empty
	 */
	public String getPercentDropped(MediaType mt) {
		int sumCt = 0;
		int dropCt = 0;
		
		switch (mt) {
		case ANIME:
			//If list is empty, return placeholder value
			if (animeList == null || animeList.size() == 0) {
				return "N/A";
			}

			//Iterate through each anime and tally the desired flag
			for (Media a : animeList) {
				if (a.isDropped()) {
					dropCt++;
				}
				sumCt++;
			}
			break;

		case MANGA:
			//If list is empty, return placeholder value
			if (mangaList == null || mangaList.size() == 0) {
				return "N/A";
			}

			//Iterate through each anime and tally the desired flag
			for (Media m : mangaList) {
				if (m.isDropped()) {
					dropCt++;
				}
				sumCt++;
			}
			break;

		default:
			throw new IllegalArgumentException("No MediaType indicated");
		}

		//Get finished percent and build String
		int percent = dropCt * 100 / sumCt;

		return percent + "%";
	}
	
	/**
	 * Provides a string representation of the user's percent of ongoing media,
	 * followed by the percent sign (%)
	 * @return percent of total manga still ongoing, "N/A" if list is empty
	 */
	public String getPercentOngoing() {
		//If list is empty, return placeholder value
		if (mangaList == null || mangaList.size() == 0) {
			return "N/A";
		}
		
		int sumCt = 0;
		int ongoingCt = 0;

		//Currently only Manga can be ongoing, so no need to use a switch statement
		for (Media m : mangaList) {
			if (((Manga) m).isOngoing()) {
				ongoingCt++;
			}
			sumCt++;
		}
		
		//Get finished percent and build String
		int percent = ongoingCt * 100 / sumCt;

		return percent + "%";	
	}
	
	/**
	 * Provides the name of the publisher found most frequently in the media list.
	 * Subject to error if user is inconsistent with publisher spelling and spacing.
	 * In case of a tie, "Tie" is returned
	 * @return name of most common publisher
	 */
	public String getFavoredPublisher() {
		/**
		 * Object for linking a publisher String and an int counter of its frequency
		 * @author Hunter Pruitt
		 */
		class PublisherAndCount { 
		    String name;
		    int frequency;
		}

		PublisherAndCount[] array = new PublisherAndCount[mangaList.size()];
		
		for (int i = 0; i < mangaList.size(); i++) {
			String currentName = ((Manga) mangaList.get(i)).getPublisher();
			int index = 0;
			boolean alreadyInList = false;
			while (array[index] != null) {
				//If the name is already in the array
				if (array[index].name.equals(currentName)) {
					array[index].frequency++;
					alreadyInList = true;
					break;
				}
				index++;
			}
			//If the name is not in the array
			if (!alreadyInList) {
				PublisherAndCount aac = new PublisherAndCount();
				aac.frequency = 1;
				aac.name = currentName;
				array[index] = aac;
			}			
		}
		
		//Get the PublisherAndCount from the array with the highest frequency
		String currentHighestPublisher = "Not Found";
		int currentHighestCount = 0;
		String secondHighestPublisher = "Not Found"; //Not used, but maybe in future development?
		int secondHighestCount = 0;
		for (PublisherAndCount pac : array) {
			if (pac != null && pac.frequency >= currentHighestCount) {
				//A new favored Publisher displaces the previous currentHighestAuthor
				secondHighestPublisher = currentHighestPublisher;
				secondHighestCount = currentHighestCount;
				
				currentHighestPublisher = pac.name;
				currentHighestCount = pac.frequency;
			}
		}
		
		if (currentHighestCount == secondHighestCount) {
			return "Tie";
		}
		
		return currentHighestPublisher;
	}

}
