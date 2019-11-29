package com.oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.model.Sales;
import com.oop.service.SalesServiceImpl;
import com.oop.service.ISalesService;


public class DeleteSalesServlet extends HttpServlet {


	private static final long serialVersionUID = 1871871796669342804L;

	public DeleteSalesServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String salesID = request.getParameter("salesID");			
		
		ISalesService iSalesService = new SalesServiceImpl();
		iSalesService.removeSales(salesID);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListSales.jsp");
		dispatcher.forward(request, response);
	}

}
