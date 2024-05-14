/**
 *	<Describe the SnakeBoard here>
 *
 *	@author	
 *	@since	
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	
	/*	Constructor	*/
	public SnakeBoard(int height, int width) {
		board = new char[height + 2][width + 2]; 
		initialBoard(); 
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {
		placeSnake(snake); 
		placeTarget(target); 
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[0].length; c++){
				if (board[r][c] == '0')
					System.out.print("  "); 
				else
					System.out.print(board[r][c] + " "); 
			}
			System.out.println(); 
		}
	}
	
	/* Helper methods go here	*/
	public void initialBoard(){
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++)
				board[i][j] = '0'; 
		}
		for (int i = 0; i < board.length; i++){
			board[i][0] = '|'; 
			board[i][board[0].length - 1] = '|'; 
		}
		
		for (int i = 0; i < board[0].length; i++){
			board[0][i] = '-'; 
			board[board.length - 1][i] = '-'; 
		}
		
		board[0][0] = '+'; 
		board[0][board[0].length - 1] = '+'; 
		board[board.length - 1][board[0].length - 1] = '+'; 
		board[board.length - 1][0] = '+'; 
	}
	
	public void placeSnake(Snake snake){
		for (int i = 0; i < snake.size(); i++){
			ListNode node = snake.get(i); 
			Coordinate coord = (Coordinate)node.getValue(); 
			if (i == 0)
				board[coord.getRow()][coord.getCol()] = '@'; 
			else
				board[coord.getRow()][coord.getCol()] = '+'; 
		}
	}
	
	public void placeTarget(Coordinate target){
		board[target.getRow()][target.getCol()] = '+'; 
	}
	
	/*	Accessor methods	*/

	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(new Coordinate(3, 3));
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}
