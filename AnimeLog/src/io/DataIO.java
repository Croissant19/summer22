package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import data.Anime;
import data.Anime.Language;
import data.Media.Type;
import data.Data;
import data.Media;
import data.Preferences;
import data.Preferences.ColorMethod;
import data.Preferences.SortFocus;
import util.SortedMediaList;

/**
 * Input Output class for user Data. 
 * Works with Media child classes' toString() and Preferences.toString() to write data into .txt style files.
 * Employed by the Manager class to load data from plaintext files.
 * 
 * @author Hunter Pruitt
 */
public class DataIO {
	//TODO move enum to Data?

	/**
	 * Indicates which type of media is involved, 
	 * useful for when the class isn't visible and certain checks/behaviour need to be involved.
	 */
	private static enum MediaType{NOT_FOUND, ANIME, MANGA};
	
	/**
	 * Writes data from files into a plaintext file compatible with the program's file reader
	 * @param d data containing a sorted list of Anime and preferences to be saved
	 * @param filename save data destination
	 * @throws IllegalArgumentException if an error occurs
	 */
	public static void writeData(Data d, File filename) {

		try {
			PrintWriter out = new PrintWriter(filename);

			//Print Anime data if it exists
			//Use preferences to check because you cannot have entries without preferences
			if (d.getAnimePreferences() != null) {
				out.print("{{ANIME}}\n{PREFERENCES}" + d.getAnimePreferences().toString() + "\n");

				for (Media anime : d.getAlphabeticalAnimeList()) {
					out.println("<|>" + anime.toString());
				}
			}
			
			//Print Manga data if it exists
			if (d.getMangaPreferences() != null) {
				out.print("{{MANGA}}\n{PREFERENCES}" + d.getMangaPreferences().toString() + "\n");

				for (Media manga : d.getAlphabeticalMangaList()) {
					out.println("<|>" + manga.toString());
				}
			}
			
			out.close();
			
		} catch (FileNotFoundException e1) {
			throw new IllegalArgumentException("Data cannot be written");
		}

	}
	
	
	/**
	 * Transforms a passed file into a sorted collection of Anime objects
	 * @param filename
	 * @throws IllegalArgumentException if the file cannot be read or file contains bad data
	 * @return Data object containing sorted collection of anime and/or manga from a user import
	 */
	public static Data readFile(String filename) {
		//Setup list and file contents
		SortedMediaList animeList = null;
		Preferences animePreferences = null;
		SortedMediaList mangaList = null;
		Preferences mangaPreferences = null;

		String animeDataString = "";
		String mangaDataString = "";

		FileInputStream fis;

		try {
			fis = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file");
		}
		
		//Read file into Preferences object and String of Anime data
		Scanner in = new Scanner(fis);
		MediaType mt = MediaType.NOT_FOUND;
		String nextLine;

		//Prepare String data for each content type
		while (in.hasNextLine()) {
			nextLine = in.nextLine();
			
			switch (nextLine) {
			case "{{ANIME}}":
				mt = MediaType.ANIME;
				break;
			case "{{MANGA}}":
				mt = MediaType.MANGA;
				break;
			default:
				if (mt == MediaType.ANIME) {
					animeDataString += nextLine + "\n";
				} else if (mt == MediaType.MANGA) {
					mangaDataString += nextLine + "\n";
				} else {
					throw new IllegalArgumentException("Data present but no media type declared.");
				}
			}
		}

		in.close();
		
		//TODO: test with just preferences

		//Create objects from data
		if (!animeDataString.isBlank()) {
			//Split data into preferences and list data
			String[] animeDataArray = animeDataString.split("\r\n|\n|\r", 2);
			
			//Process data
			animePreferences = processPreferences(animeDataArray[0], MediaType.ANIME);
			animeList = AnimeIO.getAnimeFromString(animeDataArray[1]);
		}
		
		if (!mangaDataString.isBlank()) {
			//Split data into preferences and list data
			String[] mangaDataArray = mangaDataString.split("\r\n|\n|\r", 2);
			
			//Process data
			mangaPreferences = processPreferences(mangaDataArray[0], MediaType.MANGA);
			mangaList = MangaIO.getMangaFromString(mangaDataArray[1]);
		}

		
		return new Data(animeList, animePreferences, mangaList, mangaPreferences);
	}
	
	/**
	 * Processes a line of data into a set of preferences for the program to use
	 * @param data line of data from which preferences can be understood
	 * @param mt indicates the media subclass involved, in case certain behaviors are/aren't necessary
	 * @return Preferences object with information on how user wants data displayed
	 * @throws IllegalArgumentException if data is wrong or an error occurs
	 */
	private static Preferences processPreferences(String data, MediaType mt) {
		//Ensure line is lead by "{PREFERENCES}"
		if (!data.substring(0, 13).equals("{PREFERENCES}")) {
			throw new IllegalArgumentException("Missing lead delimiter");
		}
		//Remove the delimiter
		data = data.substring(13);

		//Scan and parse the data
		Scanner in = new Scanner(data);
		in.useDelimiter(",_");
		SortFocus sortBy = SortFocus.parseSort(in.next());
		ColorMethod colorBy = ColorMethod.parseSort(in.next());
		int c1 = Integer.parseInt(in.next());
		int c2 = Integer.parseInt(in.next());
		
		if (in.hasNext()) {
			in.close();
			throw new IllegalArgumentException("Too much preference data.");
		}
		in.close();
		if (mt == MediaType.MANGA && colorBy == ColorMethod.SUB_DUB) {
			throw new IllegalArgumentException("Using language-based color method with Manga, not allowed.");
		}
		return new Preferences(sortBy, colorBy, c1, c2);
	}
}
