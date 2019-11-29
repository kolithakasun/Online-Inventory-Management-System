package com.oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.model.Employee;
import com.oop.service.EmployeeServiceImpl;
import com.oop.service.IEmployeeService;


public class UpdateEmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public UpdateEmployeeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		Employee employee = new Employee();
		String employeeID = request.getParameter("employeeID");	
		employee.setEmployeeID(employeeID);
		employee.setName(request.getParameter("employeeName"));
		employee.setAddress(request.getParameter("addresss"));
		employee.setDesignation(request.getParameter("designation"));
		employee.setFacultyName(request.getParameter("faculty"));
		employee.setDepartment(request.getParameter("department"));
		employee.setQualifications(request.getParameter("qualification"));
		employee.setGender(request.getParameter("gender"));
		
		IEmployeeService iEmployeeService = new EmployeeServiceImpl();
		iEmployeeService.updateEmployee(employeeID, employee);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListEmployees.jsp");
		dispatcher.forward(request, response);
	}

}
