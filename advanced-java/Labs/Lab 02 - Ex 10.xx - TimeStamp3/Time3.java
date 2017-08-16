public class Time3 extends Time2 {

	public Time3() {
		super(1,1,1);
	}

   public Time3(int h) 
   { 
      super(h,1,1); 
   }

   public Time3(int h,int m) 
   { 
      super(h,m,1);
   } 

   public Time3(int h,int m,int s) 
   { 
      setTime(h,m,s); 
   } 

   public Time3(Time3 time)
   {
      super(time.getHour(),time.getMinute(),time.getSecond());
   }

    public boolean equals(Time3 time) {
            
        boolean isEqual = false;
        
        if (this.toString().equals(time.toString())) {
            isEqual = true;
        } else {
            isEqual = false;
        }
        
        return isEqual;
    }
}// End Class
