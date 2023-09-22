import java.util.Scanner; 
/**
 *	Counts the frequency of letters in a file and produces a histogram.
 *
 *	@author	Rishi Salvi
 *	@since 9/12/23
 */
public class LetterCount {
	
	// Fields go here, all must be private
	int[] lettercounts; 
	int[] numPlus; 
	
	/* Constructor */							
	public LetterCount() {
		lettercounts = new int[26];
		numPlus = new int[26]; 
	}
	
	/* Main routine */
	public static void main(String[] args) {
		LetterCount lc = new LetterCount();
		if (args.length != 1)
			System.out.println("Usage: java LetterCount <inputFile>");
		else
			lc.run(args);
	}
	
	/**	The core program of the class, it
	 *	- opens the input file
	 *	- reads the file and counts the letters
	 *	- closes the input file
	 *	- prints the histogram of the letter count
	 */
	public void run(String[] args) {
		String fileName = args[0];
		FileUtils fu = new FileUtils(); 
		Scanner reader = fu.openToRead(fileName); 
		String current = "";
		int ascii = 0; 
		while (reader.hasNext()){
			current = reader.nextLine(); 
			for (int i = 0; i < current.length(); i++){
				ascii = (int)(current.charAt(i)); 
				if ((ascii >= 97 && ascii <= 122) || (ascii >= 65 && ascii <= 90)){
					if (ascii > 96)
						lettercounts[ascii - 97]++; 
					else
						lettercounts[ascii - 65]++; 
				}
			}
		}
		int maxVal = 0; 
		for (int j = 0; j < 26; j++){
			maxVal = Math.max(maxVal, lettercounts[j]); 
		}
		double scaling = (double)(60)/maxVal;
		for (int k = 0; k < 26; k++){
			numPlus[k] = (int)(lettercounts[k]*scaling); 
		}
		
		System.out.println("Histogram of letter frequency in " + fileName + "\n");
		for (int a = 0; a < 26; a++){
			System.out.print((char)(a + 97) + ":");
			System.out.printf("%10d ", lettercounts[a]); 
			for (int b = 0; b < numPlus[a]; b++)
				System.out.print("+"); 
			System.out.println();
		}
	}
	
}
