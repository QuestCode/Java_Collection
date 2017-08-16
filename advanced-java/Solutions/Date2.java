/** Instructor's solution to the the Date2 class implementation.
  * Includes the optional default constructor to set to the current date & time.
  */

import java.util.Calendar;  // Only needed for the optional default constructor.

public class Date2
{
   private int month;
   private int day;
   private int year;
   private Calendar now = Calendar.getInstance();  // Added to store sys values.

   public Date2()
   {
      year = now.get(Calendar.YEAR);
      month = now.get(Calendar.MONTH) + 1;
      day = now.get(Calendar.DAY_OF_MONTH);
   }

   public Date2(int y)
   {
      this(1, 1, y);
   }

   public Date2(int m, int y)
   {
      this(m, 1, y);
   }

   public Date2( int m, int d, int y )
   {
      setYear(y);
      month = checkMonth(m);
      day = checkDay(d);
   }

   public Date2(Date2 date)
   {
      this(date.getMonth(), date.getDay(), date.getYear());
   }

   public int getMonth()
   {
      return month;
   }

   public int getDay()
   {
      return day;
   }

   public int getYear()
   {
      return year;
   }

   public void setMonth(int m)
   {
      month = checkMonth(m);
   }

   public void setDay(int d)
   {
      day = checkDay(d);
   }

   public void setYear(int y)
   {
      year = (y > 1752 ? y : 1900);
   }

   private int checkMonth(int m)
   {
      if (m > 0 && m <= 12)
         return m;
      else
         return 1;
   }

   private int checkDay(int d)
   {
      int daysPerMonth[] = {0,
         31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
      };

      if (d > 0 && d <= daysPerMonth[month])
         return d;

      if (month == 2 && d == 29 &&
         (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)))
         return d;

      return 1;
   }

   public boolean equals(Object o)
   {
      if (o instanceof Date2)
      {
         Date2 d = (Date2) o;
         return (
            d.getYear() == year &&
            d.getMonth() == month &&
            d.getDay() == day
         );
      }
      else return false;
   }

   public String toString()
   {
      return String.format("%d/%d/%d", month, day, year); 
   }
}
