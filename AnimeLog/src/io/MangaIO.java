package io;

import data.Manga;
import data.Media.Type;
import data.Preferences.SortFocus;
import util.SortedMediaList;

/**
 * Input Output class for Manga.
 * Managed importing Manga information from loaded user data. Exports are handled as a whole in DataIO by 
 * using Manga.toString()
 * 
 * @author Hunter Pruitt
 */
public class MangaIO {

	/**
	 * Takes information from String and creates Manga objects accordingly,
	 * returning them in a list sorted by title
	 * @param data string containing the Manga to be added to the program
	 * @return SortedMediaList of user Manga, empty if no Manga to read
	 * @throws IllegalArgumentException if an error occurs
	 */
	public static SortedMediaList getMangaFromString(String data) {
		SortedMediaList list = new SortedMediaList(SortFocus.ALPHABETICAL);
		
		//Ensures there is data to read, returns null if not
		if (!data.isBlank()) {
			
			if (data.substring(0, 3).equals("<|>")) {
				//Remove the initial delimiter after verifying in the if conditional
				data = data.substring(3);
			} else {
				//If missing first delimiter, throw exception
				throw new IllegalArgumentException("Bad file data");
			}
			
			//Break file into Strings containing each Manga
			String[] splits = data.split("\\n?<[|]>");

			//Process each Manga and add to the list
			try {
				for (String s : splits) {
					//Skip empty Strings
					if (!s.isEmpty()) {
						Manga m = processManga(s);
						list.add(m);	
					}
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("Bad file data");
			}	

		} //If data is not blank
		
		
		return list;
	}

	/**
	 * Processes a String containing information to construct a Manga object
	 * @param data to be interpreted
	 * @return Manga with imported user data
	 * @throws IllegalArgumentException if error occurs in reading data
	 */
	private static Manga processManga(String data) {
		
		//Break Manga data into Strings containing each component
		//Use argument with -1 so empty notes strings are retained
		String[] splits = data.split(",_", -1);

		//Throw an exception if there are the wrong number of components
		if (splits.length != 10) {
			throw new IllegalArgumentException("Incorrect amount of manga components found.");
		}
				
		//Read data
		String title = splits[0].trim();
		int year = Integer.parseInt(splits[1]);
		int count = Integer.parseInt(splits[2]);
		String author = splits[3].trim();
		String publisher = splits[4].trim();
		Type type = Type.parseType(splits[5]);
		boolean finished = Boolean.parseBoolean(splits[6]);
		boolean dropped = Boolean.parseBoolean(splits[7]);
		boolean ongoing = Boolean.parseBoolean(splits[8]);
		String notes = splits[9].trim();

		
		//Construct Manga and return
		Manga m = new Manga(title, year, count, author, publisher, type, finished, dropped, ongoing, notes);
		return m;
	}
}