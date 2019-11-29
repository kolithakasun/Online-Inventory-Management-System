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

import com.oop.model.Sales;
import com.oop.util.CommonConstants;
import com.oop.util.CommonUtil;
import com.oop.util.DBConnectionUtil;
import com.oop.util.QueryUtil;

public class SalesServiceImpl implements ISalesService {
	

	public static final Logger log = Logger.getLogger(SalesServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createSalesTable();
	}

	private PreparedStatement preparedStatement;

	public static void createSalesTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE_SALES));
			// Create new saless table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_SALES_TABLE));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public void addSales(Sales sales) {

		String salesID = CommonUtil.generateSaIDs(getSalesIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
		
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_SALES));
			connection.setAutoCommit(false);
			
			//Generate sales IDs
			sales.setSalesID(salesID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, sales.getSalesID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, sales.getProductID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, sales.getDate());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, sales.getDAddress());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, sales.getUnitPrice());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, sales.getQuantity());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, sales.getTotal());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, sales.getPaidMethod());
			// Add sales
			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			
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
	public Sales getSalesByID(String salesID) {

		return actionOnSales(salesID).get(0);
	}
	
	@Override
	public ArrayList<Sales> getSales() {
		
		return actionOnSales(null);
	}
	@Override
	public void removeSales(String salesID) {

		
		if (salesID != null && !salesID.isEmpty()) {
			
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_SALES));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, salesID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				
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

		private ArrayList<Sales> actionOnSales(String salesID) {

		ArrayList<Sales> salesList = new ArrayList<Sales>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			
			if (salesID != null && !salesID.isEmpty()) {
				
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_SALES));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, salesID);
			}
			
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_SALES));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Sales sales = new Sales();
				sales.setSalesID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				sales.setProductID(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				sales.setDate(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				sales.setDAddress(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				sales.setUnitPrice(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				sales.setQuantity(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				sales.setTotal(resultSet.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				sales.setPaidMethod(resultSet.getString(CommonConstants.COLUMN_INDEX_EIGHT));
				salesList.add(sales);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			
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
		return salesList;
	}

	
	@Override
	public Sales updateSales(String salesID, Sales sales) {

		/*
		 * Before fetching sales it checks whether sales ID is available
		 */
		if (salesID != null && !salesID.isEmpty()) {
			/*
			 * Update sales query will be retrieved from EmployeeQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_SALES));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, sales.getSalesID());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, sales.getProductID());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, sales.getDate());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, sales.getDAddress());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, sales.getUnitPrice());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, sales.getQuantity());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, sales.getTotal());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, sales.getPaidMethod());
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				
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
		// Get the updated sales
		return getSalesByID(salesID);
	}

	private ArrayList<String> getSalesIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_SALES_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			
			
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
		return arrayList;
	}
}
