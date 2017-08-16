public class TimeStamp3 extends Date2 {

    private Time3 time;

    public TimeStamp3(int month,int day,int year,int hour,int minute,int second) {
        super(month,day,year);
        setTime(hour,minute,second);
    }

    public TimeStamp3(int month,int day,int year,int hour,int minute) {
        super(month,day,year);
        setTime(hour,minute);
    }

    public TimeStamp3(int month,int day,int year,int hour) {
        super(month,day,year);
        setTime(hour);
    }

    public TimeStamp3(int month,int day,int year) {
        super(month,day,year);
        setTime();
    }

    public TimeStamp3(int month,int year) {
        super(month,year);
        setTime();
    }

    public TimeStamp3(int year) {
        super(year);
        setTime();
    }

    public TimeStamp3() {
        super();
        setTime();
    }

    public TimeStamp3(TimeStamp3 ots) {
        this(ots.getMonth(),ots.getDay(),ots.getYear(),ots.getTime().getHour(),ots.getTime().getMinute(),ots.getTime().getSecond());
    }

    /* SetTime methods*/
    public void setTime(int h,int m,int s) {
        time = new Time3(h,m,s);
    }

    public void setTime(int h, int m) {
        time = new Time3(h,m);
    }

    public void setTime(int h) { 
        time = new Time3(h);
    }

    public void setTime() {
        time = new Time3();
    }

    public Time3 getTime() {
        return time;
    }

    public String toString() {
        return String.format("%d/%d/%d  %d:%02d:%02d",super.getMonth(),super.getDay(),super.getYear(),time.getHour(),time.getMinute(),time.getSecond());
    }

    public boolean equals(TimeStamp3 timeStamp) {
            
            boolean isEqual = false;
            
            if (this.toString().equals(timeStamp.toString())) {
                isEqual = true;
            } else {
                isEqual = false;
            }
            
            return isEqual;
        }


}// End Class
