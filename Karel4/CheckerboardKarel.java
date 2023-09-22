/*
* File: CheckerboardKarel.java
* ----------------------------
* When you finish writing it, the CheckerboardKarel class should draw
* a checkerboard using beepers, as described in Assignment Karel4. You
* should make sure that your program works for all of the sample
* worlds supplied in the starter folder.

@user Rishi Salvi
@date 9/4/23
*/

import stanford.karel.*;
public class CheckerboardKarel extends SuperKarel {
    public void run(){
        putBeeper();
        while (frontIsClear()){
            move();
            if (frontIsClear()){
                move();
                putBeeper();
            }
        }
        turnAround();
        while (frontIsClear()){
            move();
        }
        turnRight();
        while (frontIsClear()){
            if (beepersPresent()){
                move();
                turnRight();
                if (frontIsClear()){
					move();
					putBeeper();
				}
            }
            else{
                move();
                turnRight();
                putBeeper();
            }
            
            while (frontIsClear()){
                move();
                if (frontIsClear()){
                    move();
                    putBeeper();
                }
            }
            turnAround();
            while (frontIsClear()){
                move();
            }
            turnRight();
        }
    }
}
