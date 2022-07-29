package manager;

import java.awt.Color;
import java.io.File;

import data.Anime;
import data.Anime.Language;
import data.Anime.Type;
import data.Data;
import data.Preferences;
import data.Preferences.ColorMethod;
import data.Preferences.SortFocus;
import io.DataIO;
import util.SortedAnimeList;

/**
 * Connects actions on the GUI to computations performed and data retrieved from other classes.
 * Operates on the singleton pattern.
 * @author Hunter Pruitt
 */
public class Manager {
	
	/** Singleton pointer to the Manager object */
	private static Manager instance = new Manager();
	
	/** Pointer to list to display */
	private SortedAnimeList animeList;

	/** Object containing references to user data and preferences */
	private Data userData;
	
	/**
	 * Initializes the Manager object, and creates empty or default user data so that it is not null.
	 * Sorting method is set to alphabetical by default.
	 */
	private Manager() {
		userData = new Data();
		animeList = userData.getAlphabeticalAnimeList();
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
		if (userData.getPreferences().getSortMethod() == SortFocus.ALPHABETICAL) {
			animeList = userData.getAlphabeticalAnimeList();
		} else if (userData.getPreferences().getSortMethod() == SortFocus.NUMERICAL) {
			animeList = userData.getNumericalAnimeList();
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
	 * Returns user Preferences 
	 * @return Object containing the user's preferences
	 */
	public Preferences getPreferences() {
		return this.userData.getPreferences();
	}
	
	/**
	 * Provides the animeList use in other classes
	 * @return the animeList
	 */
	public SortedAnimeList getAnimeList() {
		return animeList;
	}

	/**
	 * Sets the pointer animeList to the SortedAnimeList relevant to the user's
	 * sorting preference
	 * @param focus method to sort anime data
	 * @throws IllegalArgumentException if passed an invalid sorting method
	 */
	public void setAnimeList(SortFocus focus) {
		if (focus == SortFocus.ALPHABETICAL) {
			animeList = userData.getAlphabeticalAnimeList();
		} else if (focus == SortFocus.NUMERICAL) {
			animeList = userData.getNumericalAnimeList();
		} else {
			throw new IllegalArgumentException("Invalid sort method");
		}
	}
	
	/**
	 * Adds an anime to userData
	 * @param a Anime to be added
	 * @throws NullPointerException if a is null
	 * @throws IllegalArgumentException if a is a copy of any other element
	 */
	public void addAnime(Anime a) {
		//TODO: If dupe "This entry already exists. Anime are considered the same if they share the same title and year"
		userData.addAnime(a);
	}
	
	
	/**
	 * Removes a selected anime from the animeList
	 * @param idx index to remove from
	 * @throws IndexOutOfBoundsException if the passed index is out of bounds
	 */
	public void removeAnime(int idx) {
		userData.removeAnime(animeList.get(idx));
	}

	
	/**
	 * Grabs title, year, and count for each anime for display on the GUI. 
	 * Return is type Object[][] so that ints and Strings are both displayed in the table together.
	 * It could be type String[][], but ultimately doesn't matter.
	 * 
	 * The sorting for this method is dependant on the sorting method of the list that 
	 * the pointer animeList is set to. This pointer can be changed by the user in the OptionsView 
	 * 
	 * @return 2D array of anime data for display on the GUI
	 */
	public Object[][] getAllAnimeAsArray() {
		Object[][] list = new Object[animeList.size()][3];
		
		//Get wanted data for each anime
		int index = 0;
		for (Anime a : animeList) {
			list[index][0] = a.getYear();
			list[index][1] = a.getTitle();
			list[index][2] = a.getCount();
			index++;
		}

		return list;
	}
	
	////////////////////////
	//Preference related methods
	////////////////////////
	
	/**
	 * Changes the sorting method and retrieves the proper animeList to use
	 * @param sortBy SortFocus to sort anime by
	 */
	public void setSortMethod(SortFocus sortBy) {
		userData.getPreferences().setSortMethod(sortBy);
		if (sortBy == SortFocus.ALPHABETICAL) {
			animeList = userData.getAlphabeticalAnimeList();
		} else if (sortBy == SortFocus.NUMERICAL) {
			animeList = userData.getNumericalAnimeList();
		}
	}
	
	/**
	 * Changes the sorting method and retrieves the proper animeList to use
	 * @param sortBy SortFocus to sort anime by
	 */
	public void setColorMethod(ColorMethod colorBy) {
		userData.getPreferences().setColorMethod(colorBy);
	}
	
	public void setColor(Color c, Color pointer) {
		//TODO:

	}
	
	////////////////////////
	//Stat retrieval methods
	////////////////////////

	/**
	 * Returns the number of entries for use as a stat on the HomeView
	 * @return total number of entries
	 */
	public String getEntryCount() {
		return "" + animeList.size();
	}
	
	/**
	 * Returns the number of entries that are series for use as a stat on the HomeView
	 * @return total number of series
	 */	
	public String getNumSeries() {
		int tally = 0;
		//Increment each time an anime on the list is Type Series
		for (Anime a : animeList) {
			if (a.getType().equals(Type.SERIES.formattedName)) {
				tally++;
			}
		}
		

		return "" + tally;
	}
	
	/**
	 * Returns the number of entries that are classified as specials for use as a stat on the HomeView
	 * @return total number of specials
	 */		
	public String getNumSpecial() {
		int tally = 0;
		//Increment each time an anime on the list is Type Special
		for (Anime a : animeList) {
			if (a.getType().equals(Type.SPECIAL.formattedName)) {
				tally++;
			}
		}

		return "" + tally;
	}
	
	/**
	 * Returns the sum of all counts for use as a stat on the HomeView
	 * @return sum of all anime counts
	 */
	public String getCountSum() {
		int tally = 0;
		for (Anime a : animeList) {
			tally += a.getCount();
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
		for (Anime a : animeList) {
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
	 * Provides a string representation of the user's percent of finished anime,
	 * followed by the percent sign (%)
	 * @return percent of total anime finished, "N/A" if list is empty
	 */
	public String getPercentFinished() {
		//If list is empty, return placeholder value
		if (animeList == null || animeList.size() == 0) {
			return "N/A";
		}
		
		int sumCt = 0;
		int finCt = 0;
		
		//Iterate through each anime and tally the desired flag
		for (Anime a : animeList) {
			if (a.isFinished()) {
				finCt++;
			}
			sumCt++;
		}
		
		//Get finished percent and build String
		int percent = finCt * 100 / sumCt;

		return percent + "%";
	}
	
	/**
	 * Provides a string representation of the user's percent of dropped anime,
	 * followed by the percent sign (%)
	 * @return percent of total anime dropped, "N/A" if list is empty
	 */
	public String getPercentDropped() {
		//If list is empty, return placeholder value
		if (animeList == null || animeList.size() == 0) {
			return "N/A";
		}
		
		int sumCt = 0;
		int finCt = 0;
		//Iterate through each anime and tally the desired flag
		for (Anime a : animeList) {
			if (a.isDropped()) {
				finCt++;
			}
			sumCt++;
		}
		
		//Get finished percent and build String
		int percent = finCt * 100 / sumCt;

		return percent + "%";
	}
}
