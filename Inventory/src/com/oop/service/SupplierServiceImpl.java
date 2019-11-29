
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

import com.oop.model.Supplier;
import com.oop.util.CommonConstants;
import com.oop.util.CommonUtil;
import com.oop.util.DBConnectionUtil;
import com.oop.util.QueryUtil;


public class SupplierServiceImpl implements ISupplierService {
	

	public static final Logger log = Logger.getLogger(SupplierServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createSupplierTable();
	}

	private PreparedStatement preparedStatement;


	public static void createSupplierTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_SUPPLIER_TABLE));
			
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_SUPPLIER_TABLE));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}


	@Override
	public void addSupplier(Supplier supplier) {

		String supplierID = CommonUtil.generateSIDs(getSupplierIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in SupplierQuery.xml file and use
			 * insert_supplier key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_SUPPLIER));
			connection.setAutoCommit(false);
			
			//Generate supplier IDs
			supplier.setSupplierID(supplierID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, supplier.getSupplierID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, supplier.getName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, supplier.getDesignation());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, supplier.getStockName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, supplier.getDepartment());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, supplier.getAddress());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, supplier.getStockKeeper());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, supplier.getOrigin());
			// Add employee
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
	public Supplier getSupplierByID(String supplierID) {

		return actionOnSupplier(supplierID).get(0);
	}
	

	@Override
	public ArrayList<Supplier> getSupplier() {
		
		return actionOnSupplier(null);
	}

	
	@Override
	public void removeSupplier(String supplierID) {

		// Before deleting check whether supplier ID is available
		if (supplierID != null && !supplierID.isEmpty()) {
			/*
			 * Remove supplier query will be retrieved from SupplierQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_SUPPLIER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, supplierID);
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

	
	private ArrayList<Supplier> actionOnSupplier(String supplierID) {

		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching supplier it checks whether supplier ID is
			 * available
			 */
			if (supplierID != null && !supplierID.isEmpty()) {
				/*
				 * Get supplier by ID query will be retrieved from
				 * SupplierQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_SUPPLIER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, supplierID);
			}
			/*
			 * If supplier ID is not provided for get supplier option it display
			 * all supplier
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_SUPPLIER));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Supplier supplier = new Supplier();
				supplier.setSupplierID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				supplier.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				supplier.setAddress(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				supplier.setStockName(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				supplier.setDepartment(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				supplier.setDesignation(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				supplier.setStockKeeper(resultSet.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				supplier.setOrigin(resultSet.getString(CommonConstants.COLUMN_INDEX_EIGHT));
				supplierList.add(supplier);
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
		return supplierList;
	}

	
	@Override
	public Supplier updateSupplier(String supplierID, Supplier supplier) {

		/*
		 * Before fetching supplier it checks whether supplier ID is available
		 */
		if (supplierID != null && !supplierID.isEmpty()) {
			/*
			 * Update supplier query will be retrieved from SupplierQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_SUPPLIER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, supplier.getName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, supplier.getDesignation());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, supplier.getStockName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, supplier.getDepartment());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, supplier.getAddress());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, supplier.getStockKeeper());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, supplier.getOrigin());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, supplier.getSupplierID());
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
		// Get the updated employee
		return getSupplierByID(supplierID);
	}
	
	
	private ArrayList<String> getSupplierIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of supplier IDs will be retrieved from EmployeeQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_SUPPLIER_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
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
		return arrayList;
	}
}
