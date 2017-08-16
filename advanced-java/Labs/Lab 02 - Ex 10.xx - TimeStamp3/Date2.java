/*
Devontae Reid
CIS 282

*/
class Date2 {


    private int month;
    private int day;
    private int year;
    private boolean leapYear;
    
    // Takes the month, day, and year in parameters
    Date2(int month,int day,int year) {
        setDate(month,day,year);
    }
    
    // Takes the month and year in parameters
    Date2(int month,int year) {
        this(month,1,year);
    }
    
    // Just takes the year in parameters
    Date2(int year) {
        this(1,1,year);
    }
    
    Date2() {
        this(1,1,1);
    }
    
    // Copy Constructor
    Date2(Date2 date) {
        this(date.getMonth(),date.getDay(),date.getYear());
    }
    
    public void setDate(int month, int day, int year) {
        setMonth(month);
        setDay(day);
        setYear(year);
    }
    
    public Date2 getDate() {
        return new Date2(this.month,this.day,this.year);
    }

	public void setMonth(int month) {
        checkMonth(month);
	}
    
    private void checkMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            this.month = 1;
        }
    }
    
    public int getMonth() {
        return this.month;
    }
    
    public void setDay(int day) {
        checkDay(day);
    }
    
    private void checkDay(int day) {
        if (day >= 1 && day <= 31) {
            this.day = day;
        } else {
            this.day = 1;
        }
    }
    
    public int getDay() {
        return this.day;
    }
    
    public void setYear(int year) {
        if (year != 0) {
            this.year = year;
        } else {
            this.year = 1;
        }
        
        checkLeapYear(this.year);
    }
    
    public void checkLeapYear(int year) {
        if (year % 400 == 0)
        {
            this.leapYear = true;
        } // end if
        else if (year % 100 == 0)
        {
            this.leapYear = false;
        } // end else if
        else if (year % 4 == 0)
        {
            this.leapYear = true;
        } // end else if
        else {
            this.leapYear = false;
        } // end else
    }
    
    public boolean isLeapYear() {
        return this.leapYear;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public String toString() {
        return String.format("%02d/%02d/%04d",getMonth(),getDay(),getYear());
    }
    
    public boolean equals(Date2 date) {
        
        boolean isEqual = false;
        
        if (this.toString().equals(date.toString())) {
            isEqual = true;
        } else {
            isEqual = false;
        }
        
        return isEqual;
    }
}
