/*
 * File: MazeSolvingKarel.java
 * -----------------------------
 * The MazeSolvingKarelFinal class extends the basic Karel class
 * so that Karel solves a maze.
 */

import stanford.karel.*;

public class MazeSolvingKarel extends SuperKarel {

	public void run() {
		while (noBeepersPresent()){
			if (rightIsClear()){
				turnRight();
				move();
			}
			else if (frontIsClear()){
				move(); 
			}
			else if (leftIsClear()){
				turnLeft();
				move();
			}
			else{
				turnAround();
				move();
			}
		}
	}
}
