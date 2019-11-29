<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Employee.css" />

<meta charset="UTF-8">
<title>Product Add</title>
</head>
<body class="body">
<div align = center>
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<h1 class="h2">Add Product Page</h1>

	   Inventory Management System
	<br>
	<br>
	<br>
		</form><form action="home.jsp">
    <input type="submit" value="Home" class="homebutton"/>
	</form>
	<br>
	<br>

	<form method="POST" action="AddProductServlet">
		<table>
			<tr>
				<td>Product ID</td>
				<td><input type="text" name="productID" /></td>
			</tr>
			<tr>
				<td>Product Name</td>
				<td><input type="text" name="productName" /></td>
			</tr>
			<tr>
				<td>Product Price</td>
				<td><input type="text" name="productPrice" /></td>
			</tr>
			<tr>
				<td>Quantity</td>
				<td><input type="text" name="quantity" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Product" class="add-button" /> </td>
			</tr>
			<tr>	
				<td colspan="2"><input type="reset" value="Reset" class="reset-button" /></td>
			</tr>
		</table>
	</form>

	<form method="POST" action="ListProductServlet">
		<table>
			<tr>
				<td colspan="2"><input type="submit" value="List All Products" class="list-button" />
				</td>
			</tr>
		</table>
	</form>
</tr></div>
	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

</body>
</html>