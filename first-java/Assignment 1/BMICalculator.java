/*
Devontae Reid
CIS 183
BMICalculator
*/

/* Application that Calculates a Person's BMI (Body Mass Index) from
   their weight and height
   
   Inputs:  Weight and Height
   Outputs: BMI, with a String message of explaing what BMI values mean    
*/

import java.util.Scanner;
import java.util.Calendar;

public class BMICalculator
{
    public static void main(String args[]) {
	
       	Scanner input = new Scanner(System.in);
	char retry = 'y';
	
	do {
	    
	    // Variable for current day and time
	    Calendar dateTime = Calendar.getInstance();
    
	    //Declare variables
	    double weight = 0.0;
	    double height = 0.0;
	    double bmi = 0.0;

	    //Get Input from the User (remember to convert String input to Double to use in calculations)
	    System.out.print("\nEnter your weight: ");
	    weight = input.nextDouble();
	    

	    System.out.print("Enter your height(inches): ");
	    height = input.nextDouble();
	    
	    //Calculate BMI
	    bmi = ((weight * 703)/(height * height));
	    
	    //Output Information to the user
	    System.out.printf("\nYour BMI: %.4f \n", bmi);
	    System.out.println();

	    System.out.println("BMI VALUES");
	    System.out.println("Underweight: less than 18.5");
	    System.out.println("Normal: between 18.5 and 24.9");
	    System.out.println("Overweight: between 25 and 29.9");
	    System.out.println("Obese: 30 or greater");

	    System.out.printf("\n%tc\n", dateTime);

	    System.out.print("\nDo you want to retry(y/n): ");
	    retry = input.next().charAt(0);
    

	    if (retry == 'n' || retry == 'N')
		break;
	}while(retry != 'n'|| retry == 'N');

    }
}