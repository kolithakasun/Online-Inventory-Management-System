<%@page import="com.oop.model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Employee.css" />
<meta charset="UTF-8">
<title>Inventory Management System</title>
</head>
<body align=center> <div align = center>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<h2 class="h2">Get Product Page</h2>

	Inventory Management System
	<br>
	<br>
	<br>
	<br>
		 <a href="index.jsp">Home</a>
		  <tr>
	<br>
	<br>
	<%
		Product product = (Product) request.getAttribute("product");
	%>

	<form method="POST" action="UpdateProductServlet">
		<table>
			<tr>
				<td>Product ID</td>
				<td><input type="text" name="productID" disabled="disabled"
					value="<%=product.getProductID()%>" /></td>
			</tr>
			<tr>
				<td>Product Name</td>
				<td><input type="text" name="productNames"
					value="<%=product.getProductName()%>" /></td>
			</tr>
			<tr>
				<td>Product Price</td>
				<td><input type="text" name="productPrices"
					value="<%=product.getProductPrice()%>" /></td>
			</tr>
			<tr>
				<td>Quantity</td>
				<td><input type="text" name="quantitys"
					value="<%=product.getQuantity()%>" /></td>
			</tr>
			
		</table>
	</form>

	<table>
		<tr>
			<td colspan="2">
				<form method="POST" action="DeleteProductServlet">
					<input type="hidden" name="productID"
						value="<%=product.getProductID()%>" /> <input type="submit"
						value="Delete Product" class="delete-button"/>
				</form>
			</td>
		</tr>
	</table>

	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</tr></div>
</body>
</html>