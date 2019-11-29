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

@SuppressWarnings("unused")
public class AddSalesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	public AddSalesServlet() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		Sales sales = new Sales();
		
		sales.setProductID(request.getParameter("productID"));
		sales.setDate(request.getParameter("date"));
		sales.setDAddress(request.getParameter("DAddress"));
		sales.setUnitPrice(request.getParameter("unitPrice"));
		sales.setQuantity(request.getParameter("quantity"));
		sales.setTotal(request.getParameter("total"));
		sales.setPaidMethod(request.getParameter("paymentMethod"));

		ISalesService iSalesService = new SalesServiceImpl();
		iSalesService.addSales(sales);

		request.setAttribute("sales", sales);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListSales.jsp");
		dispatcher.forward(request, response);
	}

}
