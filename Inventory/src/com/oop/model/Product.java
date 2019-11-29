package com.oop.model;

public class Product {

	private String productID;
	private String productName;
	private String productPrice;
	private String quantity;
	
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		return  "ProductID: " +productID + "\n"  + "Product Name:" +productName + "\n" + "Product Price: " +productPrice + "\n"  + "Quantity: " +quantity;
	}
}
