package com.oop.service;

import com.oop.model.Sales;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
 
public interface ISalesService {

	
	public static final Logger log = Logger.getLogger(ISalesService.class.getName());


	
	public void addSales(Sales sales);

	public Sales getSalesByID(String salesID);
	
	public ArrayList<Sales> getSales();
	
	public Sales updateSales(String salesID, Sales sales);

	public void removeSales(String salesID);

}
