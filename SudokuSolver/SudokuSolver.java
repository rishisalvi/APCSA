import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *	SudokuSolver - Solves an incomplete Sudoku puzzle using recursion and backtracking
 *
 *	@author	Rishi Salvi
 *	@since	25 January 2024
 *
 */
public class SudokuSolver {

	private int[][] puzzle;		// the Sudoku puzzle
	
	private String PUZZLE_FILE = "puzzle1.txt";	// default puzzle file
	
	/* Constructor */
	public SudokuSolver() {
		puzzle = new int[9][9];
		// fill puzzle with zeros
		for (int row = 0; row < puzzle.length; row++)
			for (int col = 0; col < puzzle[0].length; col++)
				puzzle[row][col] = 0;
	}
	
	public static void main(String[] args) {
		SudokuSolver sm = new SudokuSolver();
		sm.run(args);
	}
	
	public void run(String[] args) {
		// get the name of the puzzle file
		String puzzleFile = PUZZLE_FILE;
		if (args.length > 0) puzzleFile = args[0];
		
		System.out.println("\nSudoku Puzzle Solver");
		// load the puzzle
		System.out.println("Loading puzzle file " + puzzleFile);
		loadPuzzle(puzzleFile);
		printPuzzle();
		// solve the puzzle starting in (0,0) spot (upper left)
		solvePuzzle(0, 0);
		printPuzzle();
	}
	
	/**	Load the puzzle from a file
	 *	@param filename		name of puzzle file
	 */
	public void loadPuzzle(String filename) {
		Scanner infile = FileUtils.openToRead(filename);
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
				puzzle[row][col] = infile.nextInt();
		infile.close();
	}
	
	/**	Solve the Sudoku puzzle using brute-force method. */
	public boolean solvePuzzle(int row, int col) {
		if (col == 9){ // if entire row has been filled
			col = 0;
			row++; 
		}
		
		if (row == 9) // the entire grid has been filled up (base case)
			return true;
			
		if (puzzle[row][col] != 0)
			return solvePuzzle(row, col + 1); 

		int[] randNums = generateNums();
		for (int i = 0; i < randNums.length; i++){
			if(checkSolution(randNums[i], row, col)){ // currently valid
				puzzle[row][col] = randNums[i]; // fill value
				if (solvePuzzle(row, col + 1))
					return true;
				else
					puzzle[row][col] = 0; // no longer valid (remove value)
			}
		}
		return false; // no possible solution
	}

	/**
	 * this method generates a list of numbers from 1-9 (grid tiles) in random
	 * order using Math.random in order to select a random index from an ArrayList
	 * containing all remaining unused values
	 * @return		the randomized array
	 */
	public int[] generateNums(){
		ArrayList<Integer> possibleNums = new ArrayList<>();
		for (int i = 1; i <= 9; i++)
			possibleNums.add(i); // fill up the array with the numbers 1-9
		int[] nums = new int[9];
		for (int i = 0; i < 9; i++){
			int temp = (int)(Math.random() * possibleNums.size());
			nums[i] = possibleNums.get(temp);
			possibleNums.remove(temp); // remove used number
		}
		return nums;
	}

	/**
	 * this method checks if the current value selected is valid for the current
	 * puzzle by checking to see if there are any duplicates in row, column, and
	 * 3 by 3 grid
	 * @param check		the current value selected 
	 * @param row		the grid tile's row number
	 * @param col		the grid tile's column number 
	 * @return			whether the current value is valid or not
	 */
	public boolean checkSolution(int check, int row, int col){
		for (int i = 0; i < 9; i++){ // check row
			if(puzzle[row][i] == check)
				return false; 
		}

		for (int j = 0; j < 9; j++){ // check column
			if(puzzle[j][col] == check)
				return false; 
		}

		int rowBorder = row - (row % 3);
		int colBorder = col - (col % 3);
		for (int a = rowBorder; a < rowBorder + 3; a++){ // check grid
			for (int b = colBorder; b < colBorder + 3; b++){
				if (puzzle[a][b] == check)
					return false;
			}
		}
		
		return true; // valid
	}
	
		
	/**
	 *	printPuzzle - prints the Sudoku puzzle with borders
	 *	If the value is 0, then print an empty space; otherwise, print the number.
	 */
	public void printPuzzle() {
		System.out.print("  +-----------+-----------+-----------+\n");
		String value = "";
		for (int row = 0; row < puzzle.length; row++) {
			for (int col = 0; col < puzzle[0].length; col++) {
				// if number is 0, print a blank
				if (puzzle[row][col] == 0) value = " ";
				else value = "" + puzzle[row][col];
				if (col % 3 == 0)
					System.out.print("  |  " + value);
				else
					System.out.print("  " + value);
			}
			if ((row + 1) % 3 == 0)
				System.out.print("  |\n  +-----------+-----------+-----------+\n");
			else
				System.out.print("  |\n");
		}
	}
}
