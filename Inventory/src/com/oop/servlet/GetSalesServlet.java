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

public class GetSalesServlet extends HttpServlet {



	private static final long serialVersionUID = 1L;

	public GetSalesServlet() {
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
		Sales sales = iSalesService.getSalesByID(salesID);

		request.setAttribute("sales", sales);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/GetSales.jsp");
		dispatcher.forward(request, response);
	}

}
