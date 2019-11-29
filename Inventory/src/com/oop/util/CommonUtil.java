package com.oop.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.oop.service.ISalesService;

public class CommonUtil {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ISalesService.class.getName());

	public static final Properties properties = new Properties();

	static {
		try {
			
			// Read the property only once when load the class
			properties.load(QueryUtil.class.getResourceAsStream(CommonConstants.PROPERTY_FILE));
			
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	public static String generateIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.EMPLOYEE_ID_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.EMPLOYEE_ID_PREFIX + next;
		}
		return id;
	}
	
	public static String generateSaIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.Product_ID_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.Product_ID_PREFIX + next;
		}
		return id;
	}
	
	public static String generateSIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.SUPPLIER_ID_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.SUPPLIER_ID_PREFIX + next;
		}
		return id;
	}
}
