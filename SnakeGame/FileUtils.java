import java.io.PrintWriter; 
import java.io.FileNotFoundException; 
import java.io.File; 

/**
 *	File Utilities for reading and writing
 *
 *	@author Rishi Salvi
 *	@since August 24, 2023
 */
public class FileUtils{
	
	/**
	 * Opens a file to read using the Scanner class. 
	 * @param fileName	name of the file to open
	 * @return 			the Scanner object to the file
	 */
	public static java.util.Scanner openToRead(String fileName){
		java.util.Scanner input = null; 
		try{
			input = new java.util.Scanner(new java.io.File(fileName)); 
		}
		catch(java.io.FileNotFoundException e){
			System.err.println("ERROR: Cannot open " + fileName + 
								" for reading."); 
			System.exit(-1); 
		}
		return input; 
	}
	
	/**
	 * Opens a file to write using the PrintWriter class
	 * @param fileName	name of the file to open
	 * @return			the PrintWriter object to the file
	 */
	public static PrintWriter openToWrite(String fileName){
		PrintWriter output = null; 
		try{
			output = new PrintWriter(new File(fileName)); 
		}
		catch(FileNotFoundException e){
			System.err.println("ERROR: Cannot open " + fileName + 
								" for writing."); 
			System.exit(-1); 
		}
		return output; 
	}
}
