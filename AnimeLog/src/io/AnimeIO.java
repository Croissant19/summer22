package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import anime.Anime;
import anime.Anime.Language;
import anime.Anime.Type;
import util.SortedList;

/**
 * Input Output class for Anime objects. 
 * Works with Anime.toString() to write data into .txt files.
 * Employed by the Manager class to load data from .txt files.
 * @author Hunter Pruitt
 *
 */
public class AnimeIO {

	//TODO: FIX README in test-files
	
	/**
	 * Writes data from files into a plaintext file compatible with the program's file reader
	 * @param list sorted list of Anime to be saved
	 * @param filename save data destination
	 * @throws IllegalArgumentException if an error occurs
	 */
	public static void writeData(SortedList<Anime> list, File filename) {

		try {
			PrintWriter out = new PrintWriter(filename);
			
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
		
		
		return list;
		
	}

	/**
	 * Processes a String containing information to construct an Anime object
	 * @param blurb
	 * @return Anime with imported user data
	 * @throws IllegalArgumentException if there is too much information
	 */
	private static Anime processAnime(String blurb) {
		
		Scanner in = new Scanner(blurb);
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
	
}
