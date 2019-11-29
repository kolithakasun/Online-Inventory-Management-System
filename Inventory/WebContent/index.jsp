<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel = "stylesheet"
   type = "text/css"
   href = "Employee.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inventory Management System</title>
</head>
<body class="body2">
<div align = center>
	<h1 class="index">Admin Page </h1> 
	
	<h1 class="index">Inventory Management System</h1><br>
     <table> <tr> <td>
      <form action="productadd.jsp">
    <input type="submit" value="Add Product" class="button"/> 
	</form><br>
	</td><td>
	<form action="staffAdd.jsp">
    <input type="submit" value="Add Staff" class="button"/>
	</form><br>
	</td></tr><tr><th>
	<form action="salesAdd.jsp">
    <input type="submit" value="Add Sales" class="button"/>
	</form><br><td>
	
	<form action="supplieradd.jsp">
    <input type="submit" value="Add Supplier" class="button"/>
	</form><br></td></tr>
	<tr> <td>
	<form action="login.jsp">
    <input type="submit" value="Logout" class="button"/>
	</form><br><td>
	
	<form action="home.jsp">
    <input type="submit" value="Home" class="button"/>
	</form><br></td></tr></table>
</div>
</body>
</html>

