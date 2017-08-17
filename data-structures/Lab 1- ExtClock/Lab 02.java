/*
 Devontae Reid
 January 26, 2017
 Lab 02.java
 */

// Ext Clock class
class ExtClock extends Clock {
    
    // Fields for the class
    private int zone;
    
    // Constructor without parameters
    public ExtClock(){
        super();
        this.zone = 0;
    }
    
    // Constructor with parameters
    public ExtClock(int hour,int minute,int second, int zone) {
        super(hour,minute,second);
        if(validTimeZone(zone)) {
            this.zone = zone;
        } else {
            this.zone = 0;
        }
    }
    
    // Mutators
    public void setZone(int zone){
        if(validTimeZone(zone)) {
            this.zone = zone;
        } else {
            this.zone = 0;
        }
    }
    
    public int getZone(){
        return zone;
    }
    
    // Change zone for if you changed time zones
    public void changeZone(int zone){
        if(validTimeZone(zone)) {
            this.zone = zone;
        }
    }
    
    // Validate time zone
    private boolean validTimeZone(int zone) {
        return (zone >= -12 && zone <= 12);
    }
    
    // Override Equals method
    public boolean equals(ExtClock otherClock){
        return(super.getHours() == otherClock.getHours()
               && super.getMinutes() == otherClock.getMinutes()
               && super.getSeconds() == otherClock.getSeconds()
               && zone == otherClock.zone);
    }
    
    //Overwritten method from superclass/base class
    public String toString(){
        return String.format("%d:%d:%d UTC%+d",super.getHours(),super.getMinutes(),super.getSeconds(),this.zone);
    }
    
}