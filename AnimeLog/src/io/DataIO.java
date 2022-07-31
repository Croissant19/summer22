package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import data.Anime;
import data.Anime.Language;
import data.Anime.Type;
import data.Data;
import data.Preferences;
import data.Preferences.ColorMethod;
import data.Preferences.SortFocus;
import util.SortedAnimeList;

/**
 * Input Output class for user Data. 
 * Works with Anime.toString() and Preferences.toString() to write data into .txt style files.
 * Employed by the Manager class to load data from plaintext files.
 * 
 * @author Hunter Pruitt
 */
public class DataIO {
	
	/**
	 * Writes data from files into a plaintext file compatible with the program's file reader
	 * @param d data containing a sorted list of Anime and preferences to be saved
	 * @param filename save data destination
	 * @throws IllegalArgumentException if an error occurs
	 */
	public static void writeData(Data d, File filename) {

		try {
			PrintWriter out = new PrintWriter(filename);

			Preferences p = d.getPreferences();
			SortedAnimeList list = d.getAlphabeticalAnimeList();
			
			//Write Preference Data at top of file
			out.println("{PREFERENCES}" + p.toString());
			
			//Write data for each Anime to file
			for (int i = 0; i < list.size(); i++) {
				out.println("<|>" + list.get(i).toString());
			}
			
			out.close();
			
		} catch (FileNotFoundException e1) {
			throw new IllegalArgumentException("Data cannot be written");
		}

	}
	
	
	/**
	 * Transforms a passed file into a sorted collection of Anime objects
	 * @param filename
	 * @throws IllegalArgumentException if the file cannot be read
	 * @return sorted collection of anime from a user import, null if an error occurs
	 */
	public static Data readFile(String filename) {
		//Setup list and file contents
		SortedAnimeList list = new SortedAnimeList(SortFocus.ALPHABETICAL);
		String contents = "";
		FileInputStream fis;

		try {
			fis = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file");
		}
		
		//Read file into Preferences object and String of Anime data
		Scanner fileReader = new Scanner(fis);

		//Turn first line into preferences
		Preferences preferences = processPreferences(fileReader.nextLine());
		
		//Read rest of lines into Anime data String
		while (fileReader.hasNextLine()) {
			contents += fileReader.nextLine();
			if (fileReader.hasNextLine()) {
				contents += "\n";
			}
		}
		fileReader.close();


		
		//Check that the file type is correct
		if (contents.substring(0, 3).equals("<|>")) {
			//Remove the initial delimiter after verifying in the if conditional
			contents = contents.substring(3);
		} else {
			//If missing first delimiter, throw exception
			throw new IllegalArgumentException("Bad file data");
		}

		
		//Break file into Strings containing each Anime
		String[] splits = contents.split("\\n?<[|]>");

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
		
		
		
		return new Data(list, preferences);
		
	}

	/**
	 * Processes a String containing information to construct an Anime object
	 * @param data
	 * @return Anime with imported user data
	 * @throws IllegalArgumentException if there is too much information
	 */
	private static Anime processAnime(String data) {
		
		Scanner in = new Scanner(data);
		in.useDelimiter(",_");
		
		//Read data
		String title = in.next().trim();
		int year = Integer.parseInt(in.next());
		int count = Integer.parseInt(in.next());
		Language lang = Language.parseLang(in.next());
		Type type = Type.parseType(in.next());
		boolean finished = Boolean.parseBoolean(in.next());
		boolean dropped = Boolean.parseBoolean(in.next());
		String director = in.next().trim();
		String notes = in.next().trim();
		if (in.hasNext()) {
			in.close();
			throw new IllegalArgumentException();
		}
		in.close();
		
		//Construct Anime and return
		Anime a = new Anime(title, year, count, lang, type, finished, 
				dropped, director, notes);
		return a;
	}
	
	/**
	 * Processes a line of data into a set of preferences for the program to use
	 * @param data line of data from which preferences can be understood
	 * @return Preferences object with information on how user wants data displayed
	 * @throws IllegalArgumentException if data is wrong or an error occurs
	 */
	private static Preferences processPreferences(String data) {
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
		
		return new Preferences(sortBy, colorBy, c1, c2);
		
	}
}
