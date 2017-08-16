/*
	Devontae Reid
	CIS 183
	Shapes Lab
*/

import java.util.Scanner;
import java.lang.Math;

public class Shapes {
	
	// Declare private variables
	private int radius;
	
	// Constructor for shapes class
	public Shapes( int radius) {
		this.radius = radius;
	} // End Shapes Constructor

	// set radius method
	public void setRadius( int radius) {
		this.radius = radius;
	} // End  setRadius

	// get radius method
	public int getRadius() {
		return radius;
	} // End getRadius
	
	public void report() {
		
		// Declare variables
		double perimeter = 2 * Math.PI * radius;
		double area = Math.PI * Math.pow(radius,2);
		double volume = (4 * Math.PI * Math.pow(radius,3))/3;
		
		System.out.println("\nCircle perimeter, area / Sphere volume:");
		System.out.printf("   perimeter : %.15f\n",perimeter);
		System.out.printf("   area      : %.15f\n",area);
		System.out.printf("   volume    : %.16f\n",volume);
		
		double side = (2 * radius) / Math.sqrt(2);
		double areaOfSquare = Math.pow(side,2);
		double perimeterOfSquare = (2 * side) + (2 * side);
		
		System.out.println("\nSquare perimeter, area:");
		System.out.printf("   perimeter : %.15f\n",perimeterOfSquare);
		System.out.printf("   area      : %.15f\n\n",areaOfSquare);
		
	} // End report
	
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int input;
		
		System.out.print("Radius (as integer) of bounding sphere? ");
		input = sc.nextInt();
		
		Shapes circle = new Shapes(input);
		circle.report();
	} // End Main
	
} // End Shapes