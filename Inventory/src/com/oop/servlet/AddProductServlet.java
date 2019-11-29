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

@SuppressWarnings("unused")
public class AddProductServlet  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public AddProductServlet() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		Product employee = new Product();
		employee.setProductID(request.getParameter("productID"));
		employee.setProductName(request.getParameter("productName"));
		employee.setProductPrice(request.getParameter("productPrice"));
		employee.setQuantity(request.getParameter("quantity"));
		
		IProductService iEmployeeService = new ProductServiceImpl();
		iEmployeeService.addProduct(employee);

		request.setAttribute("employee", employee);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListProduct.jsp");
		dispatcher.forward(request, response);
	}

}
