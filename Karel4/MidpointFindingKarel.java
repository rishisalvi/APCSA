/*
* File: MidpointFindingKarel.java
* ----------------------------
* When you finish writing it, the MidpointFindingKarel class should draw
* a checkerboard using beepers, as described in Assignment Karel4. You
* should make sure that your program works for all of the sample
* worlds supplied in the starter folder.

@user Rishi Salvi
@date 9/4/23
*/

import stanford.karel.*;
public class MidpointFindingKarel extends SuperKarel {
   public void run(){
      putBeeper();
      while (frontIsClear())
         move();
      putBeeper();
      turnLeft();
      turnLeft();
      while (frontIsClear()){
         move();
         if (beepersPresent()){
            turnLeft();
            turnLeft();
            move();
            move();
            if (beepersPresent()){
               while (frontIsClear())
                  move();
            }
            else{
               turnLeft();
               turnLeft();
               move();
               putBeeper();
               turnLeft();
               turnLeft();
            }
         }
      }
      turnLeft();
      turnLeft();
      while (frontIsClear()){
         if (beepersPresent())
            pickBeeper();  
         else
            putBeeper();
         move();
      }
      pickBeeper();
   }
}
