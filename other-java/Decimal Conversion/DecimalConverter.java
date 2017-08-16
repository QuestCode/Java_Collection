import java.util.*;

public class DecimalConverter {
	
	static Scanner input;
	static DecimalToBinary dtb;
	static BinaryToHex bth;

	public static void main(String[] args) {
        try {
            input = new Scanner(System.in);
            
            System.out.print("Enter Decimal: ");
            
            dtb = new DecimalToBinary(input.nextInt());
            bth = new BinaryToHex(dtb.getBinary());
            
            
            System.out.println(dtb.toString()+ " "+ bth.toString());
        }catch(Exception e){
            System.out.println("Invalid input");
        }

	}

}
