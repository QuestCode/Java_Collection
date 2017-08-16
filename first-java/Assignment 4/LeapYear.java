/*
    Devontae Reid
    CIS 183
    LeapYear Lab
 */

import java.util.Scanner;

public class LeapYear {
    private int year;
    
    public LeapYear() {
        setYear(2000);
    } // end constructor

    public void setYear(int y) {
        year = y;
    } // end setYear
    
    public int getYear() {
        return year;
    } // end getYear
    
    public void report() {
        if (year >= 1752) {
            if (year % 400 == 0)
            {
                System.out.print("Leap Year\n\n");
            } // end if
            else if (year % 100 == 0)
            {
                System.out.print("Regular Year\n\n");
            } // end else if
            else if (year % 4 == 0)
            {
                System.out.print("Leap Year\n\n");
            } // end else if
            else {
                System.out.print("Regular Year\n\n");
            } // end else
        } // end if
        
    } // end report
    
    public static void main(String[] args){
        int year = -1;
        Scanner input = new Scanner(System.in);
        LeapYear ly = new LeapYear();
        
        do {
            System.out.print("Enter a year (0 to end)? ");
            year = input.nextInt();
            ly.setYear(year);
            ly.report();
        } while(year != 0);
        
        System.out.print("End of Run\n\n");
    } // end main
}; // end class