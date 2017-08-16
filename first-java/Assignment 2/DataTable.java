/*
    Devontae Reid
    CIS 183
    DataTable
*/

import java.util.Scanner;
import java.lang.Math;

public class DataTable {
    
    // Declare variable
    private int length;
    
    public DataTable(int length){
        setLength(length);
    } // End DataTable Constructor
    
    public void setLength(int length){
        this.length = length;
    } // End setLength
    
    public int getLength(){
        return length;
    } // End getLength
    
    public void report(){
        
        System.out.printf("%s%9s%13s\n","Number","Squared","Square Root");
        int poweredValued;
        double squaredValued;
    
        for(int i = 1;i <= length;i++)
        {
            poweredValued = (int)Math.pow(i,2);
            squaredValued = Math.sqrt(i);
           System.out.printf("%6d%9d%13f\n",i,poweredValued,squaredValued);
        }// End for loop
    } // End report

    
    // Main
    public static void main(String[] args){
    
        Scanner sc = new Scanner(System.in);
        int input;
        
        // Console Print Out
        System.out.print("Length (as integer) of data table? ");
        input = sc.nextInt();
        System.out.println();
        
        DataTable dt = new DataTable(input);
        dt.report();
        
    } // End main
    
} // End DataTable