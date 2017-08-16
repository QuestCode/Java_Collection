/*
    Devontae Reid
    CIS 183
    TravelReport Lab
*/

import java.util.Scanner;

public class TravelReport {

    private double totalDist;
    private double totalTime;
    private double averageSpeed;
    
    public TravelReport() {
        totalDist = 0.0;
        totalTime = 0.0;
        averageSpeed = 0.0;
    }// end Constructor
    
    public void setTotalDist(double d) {
        totalDist = d;
    }// end setXCoordinate
    
    public void setTotalTime(double t) {
        totalTime = t;
    }// end setYCoordinate
    
    public void setAverageSpeed(double s) {
        averageSpeed = s;
    }// end setSpeed
    
    public double getTotalDist() {
        return totalDist;
    }// end getXCoordinate
    
    public double getTotalTime() {
        return totalTime;
    }// end getYCoordinate
    
    public double getAverageSpeed() {
        return averageSpeed;
    }// end getSpeed
    
    public void report() {
        
        System.out.printf("\nTotal distance to destination: %.1f units\n",getTotalDist());
        
        double toHours = getTotalTime()/60;
        setTotalTime(totalTime % 60);
        
        if (getTotalTime() >= 60)
        {
            System.out.printf("Total time to the destination: %.0fh %.0fm\n",toHours,getTotalTime());
        }
        else {
            System.out.printf("Total time to the destination: %.0fh %.0fm\n",toHours,getTotalTime());
        }
        System.out.printf("Average speed over this route: %.1f units/hour\n\n",getAverageSpeed());
    }// end report
    
    public static void main(String[] args)
    {
        // Message to prompt
        System.out.println("This program calculates your travel plan distance and time.  Enter each way point as three floating point numbers (for the x and y coordinates of the way point and the speed, which will always be positive, to the next way point).  Providing zero as the speed at a waypoint indicates it is the last waypoint and the end of data:\n\n");
        
        Scanner sc = new Scanner(System.in);
        int waypoint = 1;
        
        // Travel report initalizer
        TravelReport tr = new TravelReport();
        
        System.out.printf("Way point %02d: ",waypoint);
        double xCoordinate = sc.nextDouble();
        double yCoordinate = sc.nextDouble();
        double speed1 = sc.nextDouble();
        
        double distance = 0;
        double averageSpeed = 0;

        
        // Start loop
        do{
            waypoint++;
            System.out.printf("Way point %02d: ",waypoint);
            double newX = sc.nextDouble();
            double newY = sc.nextDouble();
            double speed2 = sc.nextDouble();
            
            distance = (Math.sqrt(Math.pow(newX - xCoordinate,2) + Math.pow(newY - yCoordinate,2)));
            
            tr.setTotalDist(tr.getTotalDist() + distance);
            tr.setTotalTime(tr.getTotalTime() +  ((distance/speed1)* 60));
            averageSpeed += speed1;
            
            // Set first x and y to first X and Y
            xCoordinate = newX;
            yCoordinate = newY;
            speed1 = speed2;
            
            if (speed1 == 0)
                break;
            
        }while(speed1 != 0);
        
        
        tr.setAverageSpeed(averageSpeed/(waypoint - 1));
        tr.report();
        
    }// end main
}// end TravelReport