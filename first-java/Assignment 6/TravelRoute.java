/*
    Devontae Reid
    CIS 183
    TravelRoute.java
*/

import java.util.Scanner;

public class TravelRoute {
    
    private double xArray[];
    private double yArray[];
    private double speedArray[];
    private String cityArray[];
    private int index;
    
    public TravelRoute() {
        xArray = new double[20];
        yArray = new double[20];
        speedArray = new double[20];
        cityArray = new String[20];
    }// end Constructor
    
    // AddRoute Function
    public void addRoute(double x,double y,double speed,String city,int i)
    {
        index = i;
        xArray[index] = x;
        yArray[index] = y;
        speedArray[index] = speed;
        cityArray[index] = city;
    }
    
    // Report Function
    public void report() {
        
        // Create variables to store data
        double totalDist = 0.0;
        double totalTime = 0.0;
        double averageSpeed = 0.0;
        
        double distance = 0.0;
        double speed = 0.0;
        
        // Loop through array to grab items at index
        for(int i = 0; i < index;i++)
        {
            distance = (Math.sqrt(Math.pow(xArray[i + 1] - xArray[i],2) + Math.pow(yArray[i + 1] - yArray[i],2)));
            
            totalDist += distance;
            totalTime += ((distance/speedArray[i])*60);
            speed += speedArray[i];
        }
        
        averageSpeed = speed/(index);
        
        System.out.printf("\nTotal distance to destination: %.1f units\n",totalDist);
        
        double toHours = totalTime/60;
        
        // Check time by number of total minutes
        if (totalTime >= 60)
        {
            System.out.printf("Total time to the destination: %.0fh %.0fm\n",toHours,(totalTime%60));
        }
        else {
            System.out.printf("Total time to the destination: %.0fh %.0fm\n",toHours,(totalTime%60));
        }
        System.out.printf("Average speed over this route: %.1f units/hour\n\n",averageSpeed);
    }// end report
    
    
    // Dump Function
    public void dump() {
        for(int i = 0; i <= index;i++)
        {
            System.out.printf("Way point %02d: %.1f %.1f %.1f %8s\n",i + 1,xArray[i],yArray[i],speedArray[i],cityArray[i]);
        }
    }// end dump
    
    // Main Function
    public static void main(String[] args)
    {
        // Message to prompt
        System.out.println("This program calculates your travel distance and time from a travel route you enter at the keyboard.  Enter each way point as two floating point numbers followed by a positive number for the speed to the next way point and a waypoint name (i.e., the X and Y coordinates of the way point, the speed of travel to the next way point and the name of this waypoint). Enter a speed of 0 with the final way point:\n\n");
        
        Scanner sc = new Scanner(System.in);
        int waypoint = 1;
        
        // Travel report initalizer
        TravelRoute tr = new TravelRoute();
        System.out.printf("Way point %02d: ",waypoint);
        double xCoordinate = sc.nextDouble();
        double yCoordinate = sc.nextDouble();
        double speed = sc.nextDouble();
        String city = sc.next();
        tr.addRoute(xCoordinate,yCoordinate,speed,city,waypoint - 1);
        
        // Start loop
        do{
            waypoint++;
            
            System.out.printf("Way point %02d: ",waypoint);
            xCoordinate = sc.nextDouble();
            yCoordinate = sc.nextDouble();
            speed = sc.nextDouble();
            city = sc.next();
            tr.addRoute(xCoordinate,yCoordinate,speed,city,waypoint - 1);
            
            
            if (speed == 0)
                break;
            
        }while(speed != 0);
        
        
        tr.report();
        tr.dump();
    }// end main
}// end TravelReport