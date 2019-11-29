<%@page import="com.oop.model.Sales"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Employee.css" />
<meta charset="UTF-8">
<title>SLIIT OOP Employee Management</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<h2 class="h2">Get Sales Page</h2>

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
		Sales sales = (Sales) request.getAttribute("sales");
	%>

	<form method="POST" action="UpdateSalesServlet">
		<table>
			<tr>
				<td>Employee ID</td>
				<td><input type="text" name="salesID" disabled="disabled"
					value="<%=sales.getSalesID()%>" /></td>
			</tr>
			<tr>
				<td>Employee Name</td>
				<td><input type="text" name="productID"
					value="<%=sales.getProductID()%>" /></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><input type="text" name="date"
					value="<%=sales.getDate()%>" /></td>
			</tr>
			<tr>
				<td>Employee Designation</td>
				<td><input type="text" name="DAddress"
					value="<%=sales.getDAddress()%>" /></td>
			</tr>
			<tr>
				<td>Faculty</td>
				<td><input type="text" name="unitPrice"
					value="<%=sales.getUnitPrice()%>" /></td>
			</tr>
			<tr>
				<td>Department</td>
				<td><input type="text" name="quantity"
					value="<%=sales.getQuantity()%>" /></td>
			</tr>
			<tr>
				<td>Qualifications</td>
				<td><input type="text" name="total"
					value="<%=sales.getTotal()%>" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><input type="radio" name="paymentMethod" value="Cash"
					checked="checked" tabindex="1" /> Cash</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="paymentMethod" value="Card"
					tabindex="2" /> Card</td>
			</tr>
			<tr>
				<td colspan="2"><input type="hidden" name="salesID"
					value="<%=sales.getSalesID()%>" /> <input type="submit"
					value="Update Sales" class="update-button"/></td>
			</tr>
		</table>
	</form>

	<table>
		<tr>
			<td colspan="2">
				<form method="POST" action="DeleteSalesServlet">
					<input type="hidden" name="salesID"
						value="<%=sales.getSalesID()%>" /> <input type="submit"
						value="Delete Employee" class="delete-button"/>
				</form>
			</td>
		</tr>
	</table>

	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

</body>
</html>