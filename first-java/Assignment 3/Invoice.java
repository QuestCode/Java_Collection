/*
	Devontae Reid
	CIS 183
	Invoice Lab
*/

public class Invoice {
	
	// Private data
	private String partNumber;
	private String partDescription;
	private int quantity;
	private double itemPrice;
	private double totalCost;

	// Constructor for Invoice Class
	public Invoice( String part, String description, int count, double price) {
		
		// determine if count is a non negative
		if (count > 0) {
			// determine if price is a non negative
			if (price  > 0.0){
				partNumber = part;
				partDescription = description;
				quantity = count;
				itemPrice = price;
			} // End If
			else{
				//System.out.println("\nPrice can not be less than 0.o!\n");
				partNumber = part;
				partDescription = description;
				quantity = count;
				itemPrice = 0.0;
			} // End Else
		} // End If
		else {
			//System.out.println("\nCount can not be less than 0!");
			if (price  > 0.0){
				partNumber = part;
				partDescription = description;
				quantity = 0;
				itemPrice = price;
			} // End If
			else{
				//System.out.println("\nPrice can not be less than 0.0!");
				partNumber = part;
				partDescription = description;
				quantity = 0;
				itemPrice = 0.0;
			} // End Else
		} // Else
	} // End Constructor
	
	// set part number
	public void setPartNumber( String part) {
		partNumber = part;
	} // End setPartNumber

	// get part number
	public String getPartNumber() {
		return partNumber;
	} // End getPartNumber

	// set description
	public void setPartDescription( String description) {
		partDescription = description;
	} // End setPartDescription

	// get description
	public String getPartDescription() {
		return partDescription;
	} // End getPartDescription 

	// set quantity 
	public void setQuantity( int count) {
		quantity = count;
	} //End setQuantity

	// get quantity 
	public int getQuantity() {
		return quantity;
	}// End getQuantity

	// set price per item
	public void setPricePerItem( double price) {
		itemPrice = price;
	} // End setPricePerItem

	// get price per item
	public double getPricePerItem() {
		return itemPrice;
	} // End  getPricePerItem

	// get Invoice amount
	public double getInvoiceAmount() {
		double total = quantity * itemPrice;
		return total;
	} //End getInvoiceAmount
	
	// Display Invoice
	public void displayInvoice() {
		System.out.printf( "Part number: %s\n", getPartNumber() );
		System.out.printf( "Description: %s\n",	getPartDescription() );
		System.out.printf( "Quantity: %d\n", getQuantity() );
		System.out.printf( "Price: %.2f\n", getPricePerItem() );
		System.out.printf( "Invoice amount: %.2f\n", getInvoiceAmount() );
	} // End displayInvoice
} // End Invoice Class 