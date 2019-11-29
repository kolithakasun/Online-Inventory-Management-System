package com.oop.service;

import com.oop.model.Employee;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;


public interface IEmployeeService {

	public static final Logger log = Logger.getLogger(IEmployeeService.class.getName());

	public void addEmployee(Employee employee);
	public Employee getEmployeeByID(String empoyeeID);
	public ArrayList<Employee> getEmployees();
	public Employee updateEmployee(String employeeID, Employee employee);
	public void removeEmployee(String employeeID);

}
