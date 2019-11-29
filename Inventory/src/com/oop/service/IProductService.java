package com.oop.service;

import com.oop.model.Product;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;


@SuppressWarnings("unused")
public interface IProductService {

	public static final Logger log = Logger.getLogger(IProductService.class.getName());
	
	public void addProduct(Product product);
	
	public Product getProductByID(String productID);
	
	public ArrayList<Product> getProduct();
	
	public Product updateProduct(String productID, Product product);

	public void removeProduct(String productID);
	
}
