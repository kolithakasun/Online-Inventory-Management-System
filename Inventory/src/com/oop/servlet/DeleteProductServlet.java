package com.oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.model.Product;
import com.oop.service.ProductServiceImpl;
import com.oop.service.IProductService;

public class DeleteProductServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1871871796669342804L;

	public DeleteProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String employeeID = request.getParameter("productID");			
		
		IProductService iEmployeeService = new ProductServiceImpl();
		iEmployeeService.removeProduct(employeeID);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListProduct.jsp");
		dispatcher.forward(request, response);
	}

}
