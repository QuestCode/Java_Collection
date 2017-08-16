class Main {
    public static void main(String[] args) {
        Date2 date1 = new Date2(2005);
        printDate(date1);
        isLeapYear(date1);
        
        Date2 date2 = new Date2(12,2004);
        printDate(date2);
        isLeapYear(date2);
        
        Date2 date3 = new Date2(12,25,2016);
        printDate(date3);
        isLeapYear(date3);
        
        Date2 date4 = new Date2(date2);
        printDate(date4);
        isLeapYear(date4);
        
        
        checkIfEqual(date1,date2);
        checkIfEqual(date1,date3);
        checkIfEqual(date1,date4);
        
        checkIfEqual(date2,date3);
        checkIfEqual(date2,date4);
        
        checkIfEqual(date3,date4);
        
    }
    
    static void printDate(Date2 date) {
        System.out.printf("Date : %s\n",date.toString());
    }
    
    static void checkIfEqual(Date2 date1, Date2 date2) {
        if(date1.equals(date2)) {
            System.out.printf("%s & %s are equal!\n",date1.toString(),date2.toString());
        } else {
            System.out.printf("%s & %s are not equal!\n",date1.toString(),date2.toString());
        }
    }
    
    static void isLeapYear(Date2 date) {
        if (date.isLeapYear()) {
            System.out.printf("%s is a leap year\n",date.toString());
        } else {
            System.out.printf("%s is not a leap year\n",date.toString());
        }
    }
}