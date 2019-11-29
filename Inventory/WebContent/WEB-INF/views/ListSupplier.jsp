<%@page import="com.oop.model.Supplier"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oop.service.SupplierServiceImpl"%>
<%@page import="com.oop.service.ISupplierService"%>
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
<title>List Supplier</title>
</head>
<body class="body1">
	  <div align="center"><h3>List of Supplier</h3>
	Inventory Management System.
	<br>
	<br>
	
		<table border="1" cellpadding="12">
		 <caption><h2>List of Suppliers</h2></caption>
		 <form action="supplieradd.jsp">
    <input type="submit" value="Add Supplier" class="button"/>
	</form></form><form action="index.jsp">
    <input type="submit" value="Home" class="button"/>
	</form>
		  <tr>
                <th>Supplier ID</th>
                <th>Supplier Name</th>
                <th>Address</th>
                <th>Telephone</th>
                <th>StockName</th>
                
                <th>Department</th>
                <th>StockKeeper</th>
                <th>Origin</th>
                <th>Select</th>
            </tr>
            <%
            	ISupplierService iSupplierService = new SupplierServiceImpl();
                ArrayList<Supplier> arrayList = iSupplierService.getSupplier();
                                    	
               for(Supplier supplier : arrayList){
            %>
			 <tr>
				<td> <%=supplier.getSupplierID() %> </td>
				<td> <%=supplier.getName() %> </td>
				<td> <%=supplier.getDesignation() %> </td>
				<td> <%=supplier.getAddress() %> </td>
				<td> <%=supplier.getStockName() %> </td>
				<td> <%=supplier.getDepartment() %> </td>
				<td> <%=supplier.getStockKeeper() %> </td>
				<td> <%=supplier.getOrigin() %> </td>	
				<td> 
				<form method="POST" action="GetSupplierServlet">
				<input type="hidden" name="employeeID" value="<%=supplier.getSupplierID()%>"/>
				 <input type="submit" value= "View Supplier" class="select-button" /> 
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