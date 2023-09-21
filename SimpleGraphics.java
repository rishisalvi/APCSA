/**
 *	SimpleGraphics.java
 *
 *	To compile Linux:	javac -cp .:acm.jar SimpleGraphics.java
 *	To execute Linux:	java -cp .:acm.jar SimpleGraphics
 *	To compile MS Powershell:	javac -cp ".;acm.jar" SimpleGraphics.java
 *	To execute MS Powershell:	java -cp ".;acm.jar" SimpleGraphics
 *
 *	@author	Your name
 *	@since	Today's date
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

public class SimpleGraphics extends GraphicsProgram {
	
	/*	All fields and constants should be declared here.
	 *	Only constants (final) are initialized here. */
	private GOval[] circles;
	private final double RADIUS = 200;
	
	private GRect[] bricks;
	private GRect square;
	private final double HEIGHT = 20;
	private final double WIDTH = 50; 
	
	private int panelHeight; 
	private int panelWidth; 
	
	/**	The init() method is executed before the run() method.
	 *	All initialization steps should be performed here.
	 */
	public void init() {
		panelHeight = getHeight();
		panelWidth = getWidth()/2; 
		circles = new GOval[5]; 
		bricks = new GRect[55]; 
	}
	
	/**	The run() method is executed after init().
	 *	The bulk of the program should be performed here.
	 *	Exercise hint: Use one-dimensional arrays for the GOval's and GRect's.
	 */
	public void run() {
		int radiusShorten = 0; 
		for (int i = 0; i < 5; i++){
			circles[i] = new GOval(panelWidth - (RADIUS - radiusShorten), 
				panelHeight - (RADIUS - radiusShorten), 
				2 * (RADIUS - radiusShorten),
				2 * (RADIUS - radiusShorten));
			circles[i].setFilled(true);
			if (i % 2 == 0)
				circles[i].setFillColor(Color.RED);
			else
				circles[i].setFillColor(Color.WHITE);
			add(circles[i]); 
			radiusShorten += 30; 
		}
		
		System.out.println(getHeight()); 
		System.out.println(getWidth()); 
		
		int rowBricks = 10;
		int totalBricks = 0; 
		while (rowBricks > 0){
			for (int i = rowBricks; i > 0; i--){
				bricks[totalBricks] = new GRect(panelWidth - (6 - i)*WIDTH + 25*(10 - rowBricks), (10 - rowBricks)*HEIGHT, WIDTH, HEIGHT);
				add(bricks[totalBricks]); 
				totalBricks++; 
			}
			rowBricks--; 
		}
	}
}
