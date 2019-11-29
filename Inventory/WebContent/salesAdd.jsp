<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Employee.css" />

<meta charset="UTF-8">
<title>Add Sales</title>
</head>
<body class="body">
<div align = center>
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<h2 class="h2">Add Sales Page</h2>

	   Inventory Management System
	

		 
		  <tr><br>
	</form><form action="home.jsp">
    <input type="submit" value="Home" class="homebutton"/>
	</form>
	<form method="POST" action="AddSalesServlet">
		<table>

			<tr>
				<td>Product ID</td>
				<td><input type="text" name="productID" /></td>
			</tr>
			<tr>
				<td>Date</td>
				<td><input type="text" name="date" /></td>
			</tr>
			<tr>
				<td>Delivery Address</td>
				<td><input type="text" name="DAddress" /></td>
			</tr>
			<tr>
				<td>Unit Price</td>
				<td><input type="text" name="unitPrice" /></td>
			</tr>
			<tr>
				<td>Quantity</td>
				<td><input type="text" name="quantity" /></td>
			</tr>
			<tr>
				<td>Total</td>
				<td><input type="text" name="total" /></td>
			</tr>
			<tr>
				<td>Payment Method</td>
				<td><input type="radio" name="paymentMethod" value="Cash"
					checked="checked" tabindex="1" /> Cash</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="paymentMethod" value="Card"
					tabindex="2" /> Card</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add" class="add-button" /> </td>
			</tr>
			<tr>	
				<td colspan="2"><input type="reset" value="Reset" class="reset-button" /></td>
			</tr>
		</table>
	</form>

	<form method="POST" action="ListSalesServlet">
		<table>
			<tr>
				<td colspan="2"><input type="submit" value="List All Sales" class="list-button" />
				</td>
			</tr>
		</table>
	</form>

	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</tr></div>
</body>
</html>