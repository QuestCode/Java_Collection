public class Main {
	public static void main(String[] args) {
		TimeStamp3 ts1 = new TimeStamp3();
		TimeStamp3 ts2 = new TimeStamp3(2016);
		TimeStamp3 ts3 = new TimeStamp3(12,2006);
		TimeStamp3 ts4 = new TimeStamp3(3,1,2015);
		TimeStamp3 ts5 = new TimeStamp3(2,28,2004,4);
		TimeStamp3 ts6 = new TimeStamp3(1,13,1996,5,30);
		TimeStamp3 ts7 = new TimeStamp3(4,17,1993,12,21,49);
		TimeStamp3 ts8 = new TimeStamp3(ts4);

		// Print TimeStamps
		printTimeStamp(ts1);
        printTimeStamp(ts2);
        printTimeStamp(ts3);
        printTimeStamp(ts4);
        printTimeStamp(ts5);
        printTimeStamp(ts6);
        printTimeStamp(ts7);
        printTimeStamp(ts8);

        // Compare TimeStamps
        checkIfEqual(ts1,ts2);
        checkIfEqual(ts1,ts3);
        checkIfEqual(ts1,ts4);
        checkIfEqual(ts1,ts5);
        checkIfEqual(ts1,ts6);
        checkIfEqual(ts1,ts7);
        checkIfEqual(ts1,ts8);

        checkIfEqual(ts2,ts3);
        checkIfEqual(ts2,ts4);
        checkIfEqual(ts2,ts5);
        checkIfEqual(ts2,ts6);
        checkIfEqual(ts2,ts7);
        checkIfEqual(ts2,ts8);

        checkIfEqual(ts3,ts4);
        checkIfEqual(ts3,ts5);
        checkIfEqual(ts3,ts6);
        checkIfEqual(ts3,ts7);
        checkIfEqual(ts3,ts8);

        checkIfEqual(ts4,ts5);
        checkIfEqual(ts4,ts6);
        checkIfEqual(ts4,ts7);
        checkIfEqual(ts4,ts8);
        
        checkIfEqual(ts5,ts6);
        checkIfEqual(ts5,ts7);
        checkIfEqual(ts5,ts8);
        
        checkIfEqual(ts6,ts7);
        checkIfEqual(ts6,ts8);
        
        checkIfEqual(ts7,ts8);
    }

	static void printTimeStamp(TimeStamp3 timeStamp) {
		System.out.println(timeStamp.toString());
	}

	static void checkIfEqual(TimeStamp3 timeStamp1,TimeStamp3 timeStamp2) {
		if (timeStamp1.equals(timeStamp2)) {
			System.out.printf("%s is equal to %s\n",timeStamp1.toString(),timeStamp2.toString());
		} else {
			System.out.printf("%s is not equal to %s\n",timeStamp1.toString(),timeStamp2.toString());
		}
	}
}
