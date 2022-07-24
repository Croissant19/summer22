package manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	 * Initializes the Manager object, and creates the animeList so that it is not null
	 */
	private Manager() {
		animeList = new SortedList<Anime>();
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
		animeList = AnimeIO.readFile(filename);
	}
	
	
	/**
	 * Grabs title, year, and count for each anime for display on the GUI. 
	 * Return is type Object[][] so that ints and Strings are both displayed in the table together.
	 * It could be type String[][], but ultimately doesn't matter.
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
	 * @param file to save data in
	 */
	public void saveFile(File file) {		
		AnimeIO.writeData(animeList, file);		
	}
		
	
	/**
	 * Provides the animeList use in other classes
	 * @return the animeList
	 */
	public SortedList<Anime> getAnimeList() {
		return animeList;
	}

	/**
	 * Adds an anime to the animeList
	 * @param a Anime to be added
	 * @throws NullPointerException if a is null
	 * @throws IllegalArgumentException if a is a copy of any other element
	 */
	public void addAnime(Anime a) {
		//TODO: If dupe "This entry already exists. Anime are considered the same if they share the same title and year"
		animeList.add(a);
	}
	
	
	/**
	 * Removes a selected anime from the animeList
	 * @param idx index to remove from
	 * @throws IndexOutOfBoundsException if the passed index is out of bounds
	 */
	public void removeAnime(int idx) {
		animeList.remove(idx);
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
