<%@page import="com.oop.model.*"%>
<%@page import="java.util.*"%>
<%@page import="com.oop.service.*"%>
<%@page import="com.oop.service.*"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%// @taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Employee.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Products</title>
</head>
<body class="body1"> <div align = center>
	<h3>List of Products</h3>
	Inventory Management System
	<br>
	<br>
	  <div align="left">
		<table border="1" cellpadding="12">
		 <caption>  <h1> List of Products</h1></caption>
		 <form action="productadd.jsp">
    <input type="submit" value="Add Product" class="button"/>
	</form><form action="index.jsp">
    <input type="submit" value="Home" class="button"/>
	</form>
	
		  <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Product Price</th>
                <th>Quantity</th>
                <th>Select</th>
            </tr>
            <%
            IProductService iProductService = new ProductServiceImpl();
			ArrayList<Product> arrayList = iProductService.getProduct();
			
			for(Product product : arrayList){
			%>
			 <tr>
				<td> <%=product.getProductID() %> </td>
				<td> <%=product.getProductName() %> </td>
				<td> <%=product.getProductPrice() %> </td>
				<td> <%=product.getQuantity() %> </td>
				<td> 
				<form method="POST" action="GetProductServlet">
				<input type="hidden" name="productID" value="<%=product.getProductID()%>"/>
				 <input type="submit" value= "Select Product" class="select-button" /> 
				 </form>
				 </td>	
				</tr>			
			<%	
			   }
            %>     
		</table>
		</div>
		</div>
</body>
</html>