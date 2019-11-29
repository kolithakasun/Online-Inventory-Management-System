package com.oop.model;

public class Sales {

	private String SalesID;
	
	private String Date;

	private String ProductID;

	private String UnitPrice;

	private String Quantity;

	private String DAddress;
	
	private String Total;
	
	private String PaidMethod;

	

	public String getSalesID() {
		return SalesID;
	}



	public void setSalesID(String salesID) {
		SalesID = salesID;
	}



	public String getDate() {
		return Date;
	}



	public void setDate(String date) {
		Date = date;
	}



	public String getProductID() {
		return ProductID;
	}



	public void setProductID(String productID) {
		ProductID = productID;
	}



	public String getUnitPrice() {
		return UnitPrice;
	}



	public void setUnitPrice(String unitPrice) {
		UnitPrice = unitPrice;
	}



	public String getQuantity() {
		return Quantity;
	}



	public void setQuantity(String quantity) {
		Quantity = quantity;
	}



	public String getDAddress() {
		return DAddress;
	}



	public void setDAddress(String dAddress) {
		DAddress = dAddress;
	}



	public String getTotal() {
		return Total;
	}



	public void setTotal(String total) {
		Total = total;
	}



	public String getPaidMethod() {
		return PaidMethod;
	}



	public void setPaidMethod(String paidMethod) {
		PaidMethod = paidMethod;
	}



	@Override
	public String toString() {
		
		return "Sales ID = " + SalesID + "\n" + "ProductID = " + ProductID + "\n" + "Delivery Address = " + DAddress + "\n"
				+ "Date = " + Date + "\n" + "Unit Price = " + UnitPrice + "\n" + "Quantity = "
				+ Quantity + "\n" + "Total = " + Total + "\n" + "Paid Method(Cash/Card) = " + PaidMethod;
	}
}
