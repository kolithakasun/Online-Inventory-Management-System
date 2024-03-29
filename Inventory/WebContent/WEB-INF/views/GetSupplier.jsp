<%@page import="com.oop.model.Supplier"%>
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
<body class="body1">
<div align="center">
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<h2 class="h2">Get Supplier Page</h2>

	 Inventory Management System
	<br>
	<br>
	<%
		Supplier supplier = (Supplier) request.getAttribute("supplier");
	%>

	<form method="POST" action="UpdateSupplierServlet">
		<table>
			<tr>
				<td>Supplier ID</td>
				<td><input type="text" name="supplierID" disabled="disabled"
					value="<%=supplier.getSupplierID()%>" /></td>
			</tr>
			<tr>
				<td>Supplier Name</td>
				<td><input type="text" name="supplierName"
					value="<%=supplier.getName()%>" /></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><input type="text" name="address"
					value="<%=supplier.getAddress()%>" /></td>
			</tr>
			<tr>
				<td>Telephone NO</td>
				<td><input type="text" name="designation"
					value="<%=supplier.getDesignation()%>" /></td>
			</tr>
			<tr>
				<td>Stock Name</td>
				<td><input type="text" name="faculty"
					value="<%=supplier.getStockName()%>" /></td>
			</tr>
			<tr>
				<td>Department</td>
				<td><input type="text" name="department"
					value="<%=supplier.getDepartment()%>" /></td>
			</tr>
			<tr>
				<td>Stock Keeper</td>
				<td><input type="text" name="qualification"
					value="<%=supplier.getStockKeeper()%>" /></td>
			</tr>
			<tr>
				<td>Origin</td>
				<td><input type="radio" name="origin" value="Local"
					checked="checked" tabindex="1" /> Local</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="origin" value="Foreign"
					tabindex="2" /> Foreign</td>
			</tr>
			<tr>
				<td colspan="2"><input type="hidden" name="employeeID"
					value="<%=supplier.getSupplierID()%>" /> <input type="submit"
					value="Update Employee" class="update-button"/></td>
			</tr>
		</table>
	</form>

	<table>
		<tr>
			<td colspan="2">
				<form method="POST" action="DeleteSupplierServlet">
					<input type="hidden" name="supplierID"
						value="<%=supplier.getSupplierID()%>" /> <input type="submit"
						value="Delete Employee" class="delete-button"/>
				</form>
			</td>
		</tr>
	</table>

	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</div>
</body>
</html>