/**
 *	A SinglyLinkedList of Coordinate objects representing
 *	a snake on a two-dimensional grid.
 *
 *	@author	
 *	@since	
 */
public class Snake extends SinglyLinkedList<Coordinate> {
		
	/**	Constructor for making a Snake that is 5 grids high facing north.
	 *	Places the snake head at Coordinate location and the tail below.
	 *	Precondition: To place the Snake, the board must have at
	 *				least location.getRow() + 4 more rows.
	 */
	public Snake(Coordinate location) { 
		add(location); 
		for (int i = 1; i < 5; i++)
			add(new Coordinate(location.getRow() + i, location.getCol())); 
	}
	
	
}
