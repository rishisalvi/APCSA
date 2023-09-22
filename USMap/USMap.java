import java.util.Scanner;
import java.util.ArrayList;
/*
 * File: USMap.java
 * This program draws a map of the United States by plotting the locations of several cities. It plots 
 * smaller cities where they would be in grey and plots cities of higher population in blue and red
 * pen with circles that correspond to the population of the city. 
 * -------------------------------
 * @user Rishi Salvi
 * @date 9/5/23
 */

public class USMap{
	private static Scanner big;
	private static Scanner small;
	private static ArrayList <City> cities;
	public static void main(String[] args){
		USMap map = new USMap();
		map.setupCanvas();
		
		FileUtils fu = new FileUtils(); 
		small = fu.openToRead("cities.txt"); 
		cities = new ArrayList<>();

		map.loadCities();
		map.drawSmall();
		
		big = fu.openToRead("bigCities.txt"); 
		StdDraw.setPenColor(StdDraw.RED);
		for (int i = 0; i < 10; i++){
			map.findCity();
		}

		StdDraw.setPenColor(StdDraw.BLUE);
		while (big.hasNext()){
			map.findCity();
		}

		//map.drawSmall();
	}
	
	public void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}

	public void loadCities(){
		City current = null;
		double yVal = 0.0;
		double xVal = 0.0;
		String name = "";
		while (small.hasNext()){
			yVal = Double.parseDouble(small.next());
			xVal = Double.parseDouble(small.next());
			name = small.nextLine().trim();
			current = new City(name, xVal, yVal);
			cities.add(current);
		}
	}

	public void findCity(){
		big.next();
		String whole = big.nextLine();
		String bigName = whole.substring(0, whole.indexOf(',') + 4).trim();
		int bigPop = Integer.parseInt(whole.substring(whole.indexOf(',') + 4).trim());
		StdDraw.setPenRadius(0.6 * (Math.sqrt(bigPop)/18500)); 
		for (int j = 0; j < cities.size(); j++){
			if (bigName.equals(cities.get(j).getName())){
				StdDraw.point(cities.get(j).getX(), cities.get(j).getY());
				StdDraw.show();
				j = cities.size();
			}
		}
	}

	public void drawSmall(){
		StdDraw.setPenRadius(0.006); 
		StdDraw.setPenColor(StdDraw.GRAY); 
		for (int k = 0; k < cities.size(); k++){
			StdDraw.point(cities.get(k).getX(), cities.get(k).getY());
			StdDraw.show();
		}
	}
}

class City{
	private String name;
	private double yCoord;
	private double xCoord;

	public City(String name, double xCoord, double yCoord){
		this.name = name;
		this.yCoord = yCoord;
		this.xCoord = xCoord;
	}

	public String getName(){
		return name;
	}

	public double getY(){
		return yCoord;
	}

	public double getX(){
		return xCoord;
	}
}
