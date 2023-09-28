// imports go here
import java.util.Scanner; 
import java.io.PrintWriter;

/**
 *	MVCipher - Add your description here
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	
 *	@since	
 */
public class MVCipher {
	
	// fields go here
		
	/** Constructor */
	public MVCipher() { }
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Method header goes here
	 */
	public void run() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		String cipher = "";
		boolean isCipher = false; 
		Prompt pr = new Prompt();
		do{
			cipher = pr.getString("Please input a word to use as key (letters only)");
			cipher = cipher.toUpperCase(); 
			if (cipher.length() >= 3){
				for (int i = 0; i < cipher.length(); i++){
					char current = cipher.charAt(i); 
					if (current < 65 || current > 90)
						i = cipher.length();
					if (i == cipher.length() - 1)
						isCipher = true;  
				}
			}
			if (!isCipher)
				System.out.println("ERROR: Key must be all letters and at least 3 characters long");
		} while (!isCipher);
		
		/* Prompt for encrypt or decrypt */
		System.out.println(); 
		int encrypter = pr.getInt("Encrypt or decrypt?", 1, 2); 
			
		/* Prompt for an input file name */
		System.out.println(); 
		String inputFile = pr.getString("Name of file to encrypt"); 
		
		/* Prompt for an output file name */
		System.out.println(); 
		String outputFile = pr.getString("Name of output file"); 
		
		
		/* Read input file, encrypt or decrypt, and print to output file */
		int[] ciphers = new int[cipher.length()]; 
		for (int i = 0; i < cipher.length(); i++){
			ciphers[i] = (cipher.charAt(i) - 64);
			System.out.println((int) ciphers[i]); 
		}
		
		FileUtils fu = new FileUtils();
		Scanner s = fu.openToRead(inputFile); 
		PrintWriter pw = fu.openToWrite(outputFile); 
		int cipherLetter = 0; 
		String cipherLine = ""; 
		char newChar = '.';
		int flipper = 1; 
		if (encrypter == 2)
			flipper = -1; 
		while (s.hasNext()){
			cipherLine = ""; 
			newChar = '.'; 
			String current = s.nextLine(); 
			for (int i = 0; i < current.length(); i++){
				char oldChar = current.charAt(i); 
				newChar = (char)((int)oldChar + flipper*ciphers[cipherLetter]);
				if (oldChar >= 65 && oldChar <= 90){
					if (newChar > 90)
						newChar = (char)((int)oldChar + (newChar - 90));
					else if (newChar < 65)
						newChar = (char)((int)'Z' - (64 - newChar));
					cipherLine += "" + newChar; 
				}
				else if (oldChar >= 97 && oldChar <= 122){
					if (newChar > 122)
						newChar = (char)((int)oldChar + (newChar - 122));
					else if (newChar < 97)
						newChar = (char)((int)'Z' - (97 - newChar));
					cipherLine += "" + newChar; 
				}
				else
					cipherLine += "" + oldChar; 
				cipherLetter = (cipherLetter + 1) % ciphers.length; 
			}
			pw.println(cipherLine); 
		}
		
		/* Don't forget to close your output file */
		pw.close(); 
	}
	
	// other methods go here
	
}
