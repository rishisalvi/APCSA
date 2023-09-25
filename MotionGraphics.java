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
	private GPolygon arrow; 
	private int[] dimen;
	private int[] xCoord; 
	private int[] yCoord; 
	private boolean decline; 
	private boolean down; 
	private boolean right; 
	private boolean aDown, aRight; 
	private int[] rgb; 
	public void init() {
		this.addMouseListeners();
		Movement move = new Movement();  
		timer = new SwingTimer(15, move); 
		//timer.start();
		running = false;
		decline = false; 
		down = true; 
		right = true; 
		aDown = true;
		aRight = true; 
		
		dimen = new int[]{100, 100, 50, 50};
		oval = new GOval(dimen[0], dimen[1], dimen[2], dimen[3]); 
		oval.setFilled(true);
		oval.setFillColor(Color.GREEN); 
		
		xCoord = new int[]{100, 200, 200, 250, 200, 200, 100};
		yCoord = new int[]{100, 100, 60, 125, 190, 150, 150}; 
		rgb = new int[]{50, 100, 150};
		arrow = new GPolygon(new GPoint[]{new GPoint(xCoord[0], yCoord[0]),
			new GPoint(xCoord[1], yCoord[1]), new GPoint(xCoord[2], yCoord[2]),
			new GPoint(xCoord[3], yCoord[3]), new GPoint(xCoord[4], yCoord[4]),
			new GPoint(xCoord[5], yCoord[5]), new GPoint(xCoord[6], yCoord[6])});
		Color arrowColor = new Color(rgb[0], rgb[1], rgb[2]); 
		arrow.setFilled(true);
		arrow.setFillColor(arrowColor); 
	}
	
	/**	The run() method is executed after init().
	 *	The bulk of the program should be performed here.
	 *	Exercise hint: Use one-dimensional arrays for the GOval's and GRect's.
	 */
	 
	public void run() {
		add(oval);
		add(arrow); 
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
	
	public GPolygon moveArrow(){
		int diff; 
		if (aRight){
			for (int i = 0; i < 7; i++)
				xCoord[i] += 10; 
			if (xCoord[3] > getWidth()){
				diff = getWidth() - xCoord[3]; 
				for (int i = 0; i < 7; i++)
					xCoord[i] -= diff;
				aRight = false;  
			}
		}
		else{
			for (int i = 0; i < 7; i++)
				xCoord[i] -= 10; 
			if (xCoord[0] < 0){
				diff = Math.abs(xCoord[0]); 
				for (int i = 0; i < 7; i++)
					xCoord[i] += diff;
				aRight = true;  
			}
		}
		if (aDown){
			for (int i = 0; i < 7; i++)
				yCoord[i] += 10; 
			if (yCoord[4] > getHeight()){
				diff = getHeight() - xCoord[4]; 
				for (int i = 0; i < 7; i++)
					yCoord[i] -= diff;
				aDown = false;  
			}
		}
		else{
			for (int i = 0; i < 7; i++)
				yCoord[i] -= 10; 
			if (yCoord[2] < 0){
				diff = Math.abs(yCoord[2]); 
				for (int i = 0; i < 7; i++)
					yCoord[i] += diff;
				aDown = true;  
			}
		}
		arrow = new GPolygon(new GPoint[]{new GPoint(xCoord[0], yCoord[0]),
			new GPoint(xCoord[1], yCoord[1]), new GPoint(xCoord[2], yCoord[2]),
			new GPoint(xCoord[3], yCoord[3]), new GPoint(xCoord[4], yCoord[4]),
			new GPoint(xCoord[5], yCoord[5]), new GPoint(xCoord[6], yCoord[6])});
		for (int i = 0; i < 3; i++){
			rgb[i] += 10; 
			if (rgb[i] > 250)
				rgb[i] = 0; 
		}
		Color arrowColor = new Color(rgb[0], rgb[1], rgb[2]); 
		arrow.setFilled(true);
		arrow.setFillColor(arrowColor); 
		return arrow; 
	}
	
	class Movement implements ActionListener{
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
				if (dimen[0] + dimen[2] > getWidth() - 25)
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
			oval.setFilled(true);
			oval.setFillColor(Color.GREEN); 
			removeAll();
			add(moveArrow());
			add(oval);
		}
	}
}
