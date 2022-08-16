package io;

import data.Anime;
import data.Anime.Language;
import data.Media.Type;
import data.Preferences.SortFocus;
import util.SortedMediaList;

/**
 * Input Output class for Anime.
 * Managed importing Anime information from loaded user data. Exports are handled as a whole in DataIO by 
 * using Anime.toString()
 * 
 * @author Hunter Pruitt
 */
public class AnimeIO {

	/**
	 * Takes information from String and creates Anime objects accordingly,
	 * returning them in a list sorted by title
	 * @param data string containing the Anime to be added to the program
	 * @return SortedMediaList of user Anime, null if no Anime to read
	 * @throws IllegalArgumentException if an error occurs
	 */
	public static SortedMediaList getAnimeFromString(String data) {
		SortedMediaList list = null;
		
		//Ensures there is data to read, returns null if not
		if (!data.isBlank()) {

			list = new SortedMediaList(SortFocus.ALPHABETICAL);
			if (data.substring(0, 3).equals("<|>")) {
				//Remove the initial delimiter after verifying in the if conditional
				data = data.substring(3);
			} else {
				//If missing first delimiter, throw exception
				throw new IllegalArgumentException("Bad file data");
			}

			
			//Break file into Strings containing each Anime
			String[] splits = data.split("\\n?<[|]>");

			//Process each Anime and add to the list
			try {
				for (String s : splits) {
					//Skip empty Strings
					if (!s.isEmpty()) {
						Anime a = processAnime(s);
						list.add(a);	
					}
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("Bad file data");
			}

		}//Empty check
		
		
		return list;
	}

	/**
	 * Processes a String containing information to construct an Anime object
	 * @param data
	 * @return Anime with imported user data
	 * @throws IllegalArgumentException if error occurs in reading data
	 */
	private static Anime processAnime(String data) {
		
		//Break Anime data into Strings containing each component
		//Use argument with -1 so empty notes strings are retained
		String[] splits = data.split(",_", -1);

		//Throw an exception if there are the wrong number of components
		if (splits.length != 10) {
			throw new IllegalArgumentException("Incorrect amount of anime components found.");
		}
				
		//Read data
		String title = splits[0].trim();
		int year = Integer.parseInt(splits[1]);
		int count = Integer.parseInt(splits[2]);
		Language lang = Language.parseLang(splits[3]);
		Type type = Type.parseType(splits[4]);
		boolean finished = Boolean.parseBoolean(splits[5]);
		boolean dropped = Boolean.parseBoolean(splits[6]);
		String director = splits[7].trim();
		String studio = splits[8].trim();
		String notes = splits[9].trim();

		
		//Construct Anime and return
		Anime a = new Anime(title, year, count, lang, type, finished, 
				dropped, director, studio, notes);
		return a;
	}
}