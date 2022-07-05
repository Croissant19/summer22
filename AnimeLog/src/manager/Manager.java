package manager;

import anime.Anime;
import util.SortedList;

/**
 * Connects actions on the GUI to computations performed and data retrieved from other classes.
 * Operates on the singleton pattern.
 * @author Hunter Pruitt
 */
public class Manager {

	/** Collection of user anime*/
	private SortedList<Anime> animeList;
	
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
}
