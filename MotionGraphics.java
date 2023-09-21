/**
 *	MotionGraphics.java
 *
 *	To compile Linux:	javac -cp .:acm.jar SimpleGraphics.java
 *	To execute Linux:	java -cp .:acm.jar SimpleGraphics
 *	To compile MS Powershell:	javac -cp ".;acm.jar" SimpleGraphics.java
 *	To execute MS Powershell:	java -cp ".;acm.jar" SimpleGraphics
 *
 *	@author	Rishi Salvi
 *	@since	9/21/23
 */
 
/*	All package classes should be imported before the class definition.
 *	"java.awt.Color" means package java.awt contains class Color. */
import java.awt.Color;

/*	The following libraries are in the acm.jar file. */
import acm.program.GraphicsProgram;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import acm.util.SwingTimer; 

public class MotionGraphics extends GraphicsProgram implements MouseListener{
	private SwingTimer timer; 
	private boolean running;
	private GOval oval; 
	private int[] dimen;
	private boolean decline; 
	private boolean down; 
	private boolean right; 
	public void init() {
		addMouseListener(this);
		Movement move = new Movement();  
		timer = new SwingTimer(10, move); 
		timer.start();
		running = false;
		decline = false; 
		down = true; 
		right = true; 
		dimen = new int[]{100, 100, 50, 50};
		oval = new GOval(dimen[0], dimen[1], dimen[2], dimen[3]); 
	}
	
	/**	The run() method is executed after init().
	 *	The bulk of the program should be performed here.
	 *	Exercise hint: Use one-dimensional arrays for the GOval's and GRect's.
	 */
	 
	public void run() {
		add(oval);
	}
	
	public void mouseClicked(MouseEvent e){
		if (!running){
			timer.start(); 
			running = true; 
		}
		else{
			timer.stop(); 
			running = false; 
		}
	}
	
	public class Movement implements ActionListener{
		public void actionPerformed(ActionEvent e){
		if (!decline){
			dimen[2] += 10;
			dimen[3] += 10;
			if (dimen[2] > 100)
				decline = true; 
		}
		else{
			dimen[2] -= 10;
			dimen[3] -= 10;
			if (dimen[2] < 10)
				decline = false; 
		}
		
		if (right){
			dimen[0] += 10; 
			if (dimen[0] + dimen[2] > getWidth())
				right = false;
		}
		else{
			dimen[0] -= 10; 
			if (dimen[0] < 0)
				right = true; 
		}
		 
		if (down){
			dimen[1] += 10; 
			if (dimen[1] + dimen[3] > getHeight())
				down = false;
		}
		else{
			dimen[1] -= 10; 
			if (dimen[1] < 0)
				down = true; 
		}
		oval = new GOval(dimen[0], dimen[1], dimen[2], dimen[3]);
		removeAll(); 
		add(oval);
	}
	}
}
