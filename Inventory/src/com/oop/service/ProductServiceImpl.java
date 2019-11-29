package com.oop.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.oop.model.Product;
import com.oop.util.CommonConstants;
import com.oop.util.CommonUtil;
import com.oop.util.DBConnectionUtil;
import com.oop.util.QueryUtil;

public class ProductServiceImpl implements IProductService {
	public static final Logger log = Logger.getLogger(ProductServiceImpl.class.getName());
	
	private static Connection connection;

	private static Statement statement;
	
	static{
		//create table or drop if exist
		createProductTable();
	}
	
	private PreparedStatement preparedStatement;

	
	public static void createProductTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE_P));
			// Create new staffs table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_TABLE_p));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
		
		@Override
	public void addProduct(Product product) {

			//String staffID = CommonUtil.generatePIDs(getProductIDs());
			
			try {
				connection = DBConnectionUtil.getDBConnection();
				/*
				 * Query is available in StaffQuery.xml file and use
				 * insert_staff key to extract value of it
				 */
				preparedStatement = connection.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_PRODUCTS));
				connection.setAutoCommit(false);
				
				//Generate staff IDs
				//product.setProductID(staffID);
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, product.getProductID());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, product.getProductName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, product.getProductPrice());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, product.getQuantity());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, product.getQuantity());
				
				// Add staff
				preparedStatement.execute();
				connection.commit();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end of
				 * transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}

	@Override
	public Product getProductByID(String productID) {
		
		return actionOnProduct(productID).get(0);
	}
	
	
	public ArrayList<Product> getProduct() {
		
		return actionOnProduct(null);
	}


	public void removeProduct(String productID) {

		// Before deleting check whether staff ID is available
		if (productID != null && !productID.isEmpty()) {
			/*
			 * Remove staff query will be retrieved from StaffQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_PRODUCT));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, productID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}


	private ArrayList<Product> actionOnProduct(String productID) {

		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching staff it checks whether staff ID is
			 * available
			 */
			if (productID != null && !productID.isEmpty()) {
				/*
				 * Get staff by ID query will be retrieved from
				 * StaffQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_PRODUCT));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, productID);
			}
			/*
			 * If staff ID is not provided for get staff option it display
			 * all staffs
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_PRODUCTS));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				Product product = new Product();
				product.setProductID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				product.setProductName (resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				product.setProductPrice(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				product.setQuantity (resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				productList.add(product); // (staff);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return productList;
	}


	@Override
	public Product updateProduct(String productID, Product product) {

		/*
		 * Before fetching staff it checks whether staff ID is available
		 */
		if (productID != null && !productID.isEmpty()) {
			/*
			 * Update staff query will be retrieved from StaffQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_PRODUCT));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, product.getProductName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, product.getProductPrice());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, product.getQuantity() );
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		// Get the updated staff
		return getProductByID(productID);
	}
	

	private ArrayList<String> getProductIDs(){
		
		ArrayList<String> productList = new ArrayList<String>();
		/*
		 * List of staff IDs will be retrieved from StaffQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_PRODUCT_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				productList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return productList;
	}


	
		
		
		
		
		
		
		
		
		
}


