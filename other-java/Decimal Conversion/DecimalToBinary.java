
public class DecimalToBinary {
	private int decimal;
	private String binary;
	
	public DecimalToBinary(int decimal){
		this.setDecimal(decimal);
	}
	
	public int getDecimal() {
		return decimal;
	}
	public void setDecimal(int decimal) {
		this.decimal = decimal;
		this.setBinary(this.toBinary(decimal));
	}
	
	public String getBinary() {
		return binary;
	}
	public void setBinary(String binary) {
		this.binary = binary;
	}
	
	private String toBinary(int decimal){
		String binaryString = "";
		
		int highBit = this.wholeNumber(decimal);
		
		while(highBit != -1){
			if(decimal - Math.pow(2, highBit) >= 0){
				decimal -= Math.pow(2, highBit);
				binaryString += "1";
			}else {
				binaryString += "0";
			}
			highBit--;
		}
		
		return binaryString;
	}
	
	private int wholeNumber(int decimal){
		int num = 15;
		
		while(decimal < Math.pow(2, num)) {
			num--;
		}
		
		return num;
	}
	
	public String toString() {
		return String.format("Binary: %s",this.getBinary());
	}
	
}
