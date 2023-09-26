// imports go here

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
			cipher.toUppercase(); 
			if (cipher.length() >= 3){
				for (int i = 0; i < cipher; i++){
					char current = cipher.charAt(i); 
					if (
				}
			}
		} while (!sCipher);
		
		/* Prompt for encrypt or decrypt */
			
			
		/* Prompt for an input file name */
		
		
		/* Prompt for an output file name */
		
		
		/* Read input file, encrypt or decrypt, and print to output file */
		
		
		/* Don't forget to close your output file */
	}
	
	// other methods go here
	
}
