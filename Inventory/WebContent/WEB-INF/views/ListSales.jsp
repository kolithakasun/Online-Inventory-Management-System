<%@page import="com.oop.model.Sales"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oop.service.SalesServiceImpl"%>
<%@page import="com.oop.service.ISalesService"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Employee.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inventory Management System</title>
</head>
<body class="body1">
	  <div align="center"><h3>List of Sales</h3>
	Inventory Management System
	<br>
	<br>
		<table border="1" cellpadding="12">
		 <caption><h2>List of Sales</h2></caption>
		 <form action="salesAdd.jsp">
    <input type="submit" value="Add Sales" class="button"/>
	</form><form action="index.jsp">
    <input type="submit" value="Home" class="button"/>
	</form>
		
		  <tr>
                <th>SalesID</th>
                <th>ProductID</th>
                <th>Date</th>
                <th>Delivery Address</th>
                <th>Unit Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Paid Method</th>
                <th>Select</th>
            </tr>
            <%
            ISalesService iSalesService = new SalesServiceImpl();
			ArrayList<Sales> arrayList = iSalesService.getSales();
			
			for(Sales sales : arrayList){
			%>
			 <tr>
				<td> <%=sales.getSalesID() %> </td>
				<td> <%=sales.getProductID() %> </td>
				<td> <%=sales.getDate() %> </td>
				<td> <%=sales.getDAddress() %> </td>
				<td> <%=sales.getUnitPrice() %> </td>
				<td> <%=sales.getQuantity() %> </td>
				<td> <%=sales.getTotal() %> </td>
				<td> <%=sales.getPaidMethod() %> </td>	
				<td> 
				<form method="POST" action="GetSalesServlet">
				<input type="hidden" name="salesID" value="<%=sales.getSalesID()%>"/>
				 <input type="submit" value= "Select sales" class="select-button" /> 
				 </form>
				 </td>	
				</tr>			
			<%	
			   }
            %>     
		</table>
		</div>
		
</body>
</html>