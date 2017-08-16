import java.util.*;

public class BinaryToHex {
	private String binary;
	private String hexadecimal;
	
	public BinaryToHex(String binary){
		this.setBinary(binary);
	}
	
	public String getBinary() {
		return binary;
	}
	public void setBinary(String binary) {
		this.binary = binary;
		this.setHexadecimal(this.toHexadecimal(binary));
	}
	public String getHexadecimal() {
		return hexadecimal;
	}
	public void setHexadecimal(String hexadecimal) {
		this.hexadecimal = hexadecimal;
	}
	
	private String toHexadecimal(String binary){
		String hex = "";
		String updatedBinary = this.checkBinary(binary);
		Queue<Character> s = new LinkedList<Character>();
		
		//Check four digits
		// Add letters to a queue
		for(int i = 0;i<updatedBinary.length();i++){
			s.add(updatedBinary.charAt(i));
		}
		
		String temp = "";
		
		while(!s.isEmpty()){
			temp += s.remove();
			if(s.size()%4 == 0){
				hex += this.Hexadecimal(temp);
				temp = "";
				continue;
			}
		}
		
		return hex;
	}
	
	private String checkBinary(String binary){
		String bin = binary;
		Queue<Character> s = new LinkedList<Character>();
		
		int remainder = 0;
		int n = 1;
		
		
		if(bin.length() != 0){
			while(bin.length() > (4*n)){
				n++;
			}
			remainder = (4*n) - bin.length();
		}
		
		for(int i = 0;i < bin.length();i++){
			s.add(bin.charAt(i));
		}
		
		bin = "";
		
		while(remainder != 0){
			bin += "0";
			remainder--;
		}
		
		while(!s.isEmpty()){
			bin += s.remove();
		}
		
		return bin;
	}
	
	// Return hexadecimal value for 
	private String Hexadecimal(String bits){
		String hex = "";
		
		switch(bits){
			case "0000":
				hex = "0";
				break;
			case "0001":
				hex = "1";
				break;
			case "0010":
				hex = "2";
				break;
			case "0011":
				hex = "3";
				break;
			case "0100":
				hex = "4";
				break;
			case "0101":
				hex = "5";
				break;
			case "0110":
				hex = "6";
				break;
			case "0111":
				hex = "7";
				break;
			case "1000":
				hex = "8";
				break;
			case "1001":
				hex = "9";
				break;
			case "1010":
				hex = "A";
				break;
			case "1011":
				hex = "B";
				break;
			case "1100":
				hex = "C";
				break;
			case "1101":
				hex = "D";
				break;
			case "1110":
				hex = "E";
				break;
			case "1111":
				hex = "F";
				break;
			default:
				hex = "Non binary";
				break;
		}
		return hex;
	}
	
	public String toString(){
		return String.format("Hexadecimal: %s", this.getHexadecimal());
	}
	
}
