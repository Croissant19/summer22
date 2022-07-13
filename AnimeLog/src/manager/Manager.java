package manager;

import java.io.File;

import anime.Anime;
import anime.Anime.Language;
import anime.Anime.Type;
import io.AnimeIO;
import util.SortedList;

/**
 * Connects actions on the GUI to computations performed and data retrieved from other classes.
 * Operates on the singleton pattern.
 * @author Hunter Pruitt
 */
public class Manager {

	/** Singleton pointer to the Manager object */
	private static Manager instance = new Manager();
	
	/** Collection of user anime */
	private SortedList<Anime> animeList;
	
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
		animeList = AnimeIO.readFile(filename);
	}
	
	
	/**
	 * Grabs title, year, and count for each anime for display on the GUI.
	 * @return 2D array of anime data for display on the GUI
	 */
	public Object[][] getAllAnimeAsArray()	{
		Object[][] list = new Object[animeList.size()][3];
		
		//Get wanted data for each anime
		for (int i = 0; i < animeList.size(); i++) {
			Anime currAnime = animeList.get(i);
			list[i][0] = currAnime.getYear();
			list[i][1] = currAnime.getTitle();
			list[i][2] = currAnime.getCount();
		}

		return list;
	}

	/**
	 * Saves data to a file location specified by the user in JFileChooser
	 * @param filename location to save data at
	 */
	public void saveFile(String filename) {
		AnimeIO.writeData(animeList, new File(filename));
		// TODO: warning to prevent overwriting existing file?
		
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
		//TODO: VERY IMPORTANT
		//TODO: make sortedlist iteratable to decrease computation time or switch to arraylist model
		//Increment each time an anime on the list is Type Series
		for (int i = 0; i < animeList.size(); i++) {
			if (animeList.get(i).getType().equals(Type.SERIES.formattedName)) {
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
		//TODO: VERY IMPORTANT
		//TODO: make sortedlist iteratable to decrease computation time or switch to arraylist model
		//Increment each time an anime on the list is Type Special
		for (int i = 0; i < animeList.size(); i++) {
			if (animeList.get(i).getType().equals(Type.SPECIAL.formattedName)) {
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
		for (int i = 0; i < animeList.size(); i++) {
			Anime currAnime = animeList.get(i);
			tally += currAnime.getCount();
		}

		return "" + tally;
	}

	/**
	 * Computes the most used language type by entry (not count) and the percent for display 
	 * @return most used language and its percent as a String for the GUI
	 */
	public String getFavoredLanguageAndPercent() {
		int subCt = 0, dubCt = 0, otherCt = 0;
		
		//Tally counts for each language classification
		for (int i = 0; i < animeList.size(); i++) {
			Anime currAnime = animeList.get(i);

			switch (Language.parseLang(currAnime.getLanguage())) {
			
			case SUB: 
				subCt++;
				break;
			case DUB: 
				dubCt++;
				break;
			case OTHER: 
				otherCt++;
				break;	
			}
				
		}
		//TODO: handle ties
		//Determine winning count
		String winner;
		int winnerCt = 0;
		if (subCt > dubCt) {
			winner = "Sub ";
			winnerCt = subCt;
		} else if (dubCt > subCt) {
			winner = "Dub ";
			winnerCt = dubCt;
		} else {
			winner = "Tie ";
		}

		//Get winner percent and finish building string
		int sumCt = subCt + dubCt + otherCt;
		int percent = winnerCt * 100 / sumCt;
		
		winner += percent + "%";
		
		return winner;	
	}
	
	
	/**
	 * Provides a string representation of the user's percent of finished anime,
	 * followed by the percent sign (%)
	 * @return percent of total anime finished
	 */
	public String getPercentFinished() {
		int sumCt = 0;
		int finCt = 0;
		//Iterate through each anime and tally the desired flag
		for (int i = 0; i < animeList.size(); i++) {
			Anime currAnime = animeList.get(i);
			if (currAnime.isFinished()) {
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
	 * @return percent of total anime dropped
	 */
	public String getPercentDropped() {
		int sumCt = 0;
		int finCt = 0;
		//Iterate through each anime and tally the desired flag
		for (int i = 0; i < animeList.size(); i++) {
			Anime currAnime = animeList.get(i);
			if (currAnime.isDropped()) {
				finCt++;
			}
			sumCt++;
		}
		
		//Get finished percent and build String
		int percent = finCt * 100 / sumCt;

		return percent + "%";
	}
	//TODO: test!!!
	//TODO: can we combine the above stat methods? def need an iterator!
	
}
