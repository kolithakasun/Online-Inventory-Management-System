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


public class GetEmployeeServlet extends HttpServlet {



	private static final long serialVersionUID = 1L;

	
	public GetEmployeeServlet() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

 		String employeeID = request.getParameter("employeeID");			
		IEmployeeService iEmployeeService = new EmployeeServiceImpl();
		Employee employee = iEmployeeService.getEmployeeByID(employeeID);

		request.setAttribute("employee", employee);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/GetEmployee.jsp");
		dispatcher.forward(request, response);
	}

}
