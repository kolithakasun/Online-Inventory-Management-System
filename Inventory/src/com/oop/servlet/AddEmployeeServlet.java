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

public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddEmployeeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		Employee employee2 = new Employee();
		
		employee2.setName(request.getParameter("staffName"));
		employee2.setAddress(request.getParameter("address"));
		employee2.setDesignation(request.getParameter("nic"));
		employee2.setFacultyName(request.getParameter("faculty"));
		employee2.setDepartment(request.getParameter("department"));
		employee2.setQualifications(request.getParameter("qualification"));
		employee2.setGender(request.getParameter("gender"));

		IEmployeeService iEmployeeService = new EmployeeServiceImpl();
		iEmployeeService.addEmployee(employee2);

		request.setAttribute("employee", employee2);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListEmployees.jsp");
		dispatcher.forward(request, response);
	}

}
