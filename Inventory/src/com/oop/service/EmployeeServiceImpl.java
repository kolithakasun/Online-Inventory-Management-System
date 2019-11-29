/**
 * OOP 2018
 * 
 * @author Udara Samaratunge  Department of Software Engineering, SLIIT 
 * 
 * @version 1.0
 * Copyright: SLIIT, All rights reserved
 * 
 */
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

import com.oop.model.Employee;
import com.oop.util.CommonConstants;
import com.oop.util.CommonUtil;
import com.oop.util.DBConnectionUtil;
import com.oop.util.QueryUtil;

public class EmployeeServiceImpl implements IEmployeeService {
	

	public static final Logger log = Logger.getLogger(EmployeeServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createEmployeeTable();
	}

	private PreparedStatement preparedStatement;

	public static void createEmployeeTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE));
			// Create new employees table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_TABLE));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public void addEmployee(Employee employee) {

		String employeeID = CommonUtil.generateIDs(getEmployeeIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_EMPLOYEES));
			connection.setAutoCommit(false);
			
			//Generate employee IDs
			employee.setEmployeeID(employeeID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employee.getEmployeeID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, employee.getName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, employee.getDesignation());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, employee.getFacultyName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, employee.getDepartment());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, employee.getAddress());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, employee.getQualifications());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, employee.getGender());
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
	public Employee getEmployeeByID(String employeeID) {

		return actionOnEmployee(employeeID).get(0);
	}
	
	
	@Override
	public ArrayList<Employee> getEmployees() {
		
		return actionOnEmployee(null);
	}

	
	@Override
	public void removeEmployee(String employeeID) {

		// Before deleting check whether employee ID is available
		if (employeeID != null && !employeeID.isEmpty()) {
			/*
			 * Remove employee query will be retrieved from EmployeeQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_EMPLOYEE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employeeID);
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

	
	private ArrayList<Employee> actionOnEmployee(String employeeID) {

		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching employee it checks whether employee ID is
			 * available
			 */
			if (employeeID != null && !employeeID.isEmpty()) {
				/*
				 * Get employee by ID query will be retrieved from
				 * EmployeeQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_EMPLOYEE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employeeID);
			}
			/*
			 * If employee ID is not provided for get employee option it display
			 * all employees
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_EMPLOYEES));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				employee.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				employee.setAddress(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				employee.setFacultyName(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				employee.setDepartment(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				employee.setDesignation(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				employee.setQualifications(resultSet.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				employee.setGender(resultSet.getString(CommonConstants.COLUMN_INDEX_EIGHT));
				employeeList.add(employee);
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
		return employeeList;
	}

	@Override
	public Employee updateEmployee(String employeeID, Employee employee) {

		/*
		 * Before fetching employee it checks whether employee ID is available
		 */
		if (employeeID != null && !employeeID.isEmpty()) {
			/*
			 * Update employee query will be retrieved from EmployeeQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_EMPLOYEE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employee.getName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, employee.getDesignation());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, employee.getFacultyName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, employee.getDepartment());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, employee.getAddress());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, employee.getQualifications());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, employee.getGender());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, employee.getEmployeeID());
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
		return getEmployeeByID(employeeID);
	}
	

	private ArrayList<String> getEmployeeIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of employee IDs will be retrieved from EmployeeQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_EMPLOYEE_IDS));
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
