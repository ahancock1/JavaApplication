package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class contains methods required to read text from a specified file and
 * return the contents in an {@link ArrayList}.
 * @author Adam-PC
 *
 */
public class FileHandler {

	private File file;
	private BufferedReader reader;

	/**
	 * Creates a new FileHandle for reading the contents of a .data file. 
	 * This constructor initialises the FileHandle class.
	 */
	public FileHandler() {
		file = null;
		reader = null;
	}
	
	/**
	 * Reads the contents of the specified file using {@link BufferedReader} and 
	 * returns an {@link ArrayList} of separated/split {@link String} containing 
	 * the contents of the file.
	 * @param filename  String - Filename
	 * @return ArrayList
	 */
	public ArrayList<String[]> read(String filename) {

		file = new File(filename);
		
		// Specify the regex used for splitting the line
		String regex = ",|\\|";
		ArrayList<String[]> contents = new ArrayList<String[]>();		
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			// Ignore the first line
			String line = reader.readLine();
			
			// Count number of columns
			String[] split = line.split(regex);
			int count = split.length;
			
			while ((line = reader.readLine()) != null) {
				
				// Split, trim and convert the line to upper case
				split = line.split(regex);
				
				// If split line contains correct fields and primary key is not empty
				if (split.length == count && !split[0].isEmpty()) {				
					for (int i = 0; i < split.length; i++)
						split[i] = split[i].trim().toUpperCase();
					
					contents.add(split);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		}

		return contents;
	}
}
