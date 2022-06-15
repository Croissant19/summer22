package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import anime.Anime;
import anime.Anime.Language;
import anime.Anime.Type;
import util.SortedList;

/**
 * Input Output class for Anime objects. 
 * Works with Anime.toString() to write data into .txt files
 * Bad files 
 * @author Hunter Pruitt
 *
 */
public class AnimeIO {

	/**
	 * 
	 * @param filename
	 * @throws IllegalArgumentException if the file cannot be read
	 * @return sorted collection of anime from a user import, null if an error occurs
	 */
	public static SortedList<Anime> readFile(String filename) {
		//Setup list and file contents
		SortedList<Anime> list = new SortedList<Anime>();
		String contents = "";
		FileInputStream fis;

		try {
			fis = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file");
		}
		
		//Read file into one long String
		Scanner fileReader = new Scanner(fis);
		while (fileReader.hasNextLine()) {
			contents += fileReader.nextLine() + "\n";
		}
		fileReader.close();

		//Break file into Strings containing each Anime
		String[] splits = contents.split("<\\|>");

		//Process each Anime and add to the list
		for (String s : splits) {
			//Skip empty Strings
			if (!s.isEmpty()) {
				Anime a = processAnime(s);
				list.add(a);	
			}
		}
		
		
		
		return list;
		
	}

	/**
	 * Processes a String containing information to construct an Anime object
	 * @param blurb
	 * @return Anime with imported user data
	 */
	private static Anime processAnime(String blurb) {
		
		Scanner in = new Scanner(blurb);
		in.useDelimiter(",_");
		
		//Read data
		String title = in.next();
		int year = Integer.parseInt(in.next());
		int count = Integer.parseInt(in.next());
		Language lang = Language.parseLang(in.next());
		Type type = Type.parseType(in.next());
		boolean finished = Boolean.parseBoolean(in.next());
		boolean dropped = Boolean.parseBoolean(in.next());
		String director = in.next();
		String notes = in.next();
		if (in.hasNext()) {
			in.close();
			throw new IllegalArgumentException("Incorrect data");
		}
		in.close();
		
		//Construct Anime and return
		Anime a = new Anime(title, year, count, lang, type, finished, 
				dropped, director, notes);
		return a;
	}
	
}
