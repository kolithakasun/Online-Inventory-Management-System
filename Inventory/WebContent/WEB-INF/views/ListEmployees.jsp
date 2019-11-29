<%@page import="com.oop.model.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oop.service.EmployeeServiceImpl"%>
<%@page import="com.oop.service.IEmployeeService"%>
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
	<h3>List of Employees</h3>
	Inventory Management System
	<br>
	<br>
	  <div align="left">
		<table border="1" cellpadding="12">
		 <caption><h2>List of Employees</h2></caption>
		 <form action="staffadd.jsp">
    <input type="submit" value="Add Employee" class="button"/>
	</form><form action="index.jsp">
    <input type="submit" value="Home" class="button"/>
	</form>
		
		  <tr>
                <th>Staff ID</th>
                <th>Name</th>
                <th>Address</th>
                <th>NIC No</th>
                <th>Branch</th>
                <th>Department</th>
                <th>Qualifications</th>
                <th>Gender</th>
                <th>Select</th>
            </tr>
            <%
            IEmployeeService iEmployeeService = new EmployeeServiceImpl();
			ArrayList<Employee> arrayList = iEmployeeService.getEmployees();
			
			for(Employee employee : arrayList){
			%>
			 <tr>
				<td> <%=employee.getEmployeeID() %> </td>
				<td> <%=employee.getName() %> </td>
				<td> <%=employee.getDesignation() %> </td>
				<td> <%=employee.getAddress() %> </td>
				<td> <%=employee.getFacultyName() %> </td>
				<td> <%=employee.getDepartment() %> </td>
				<td> <%=employee.getQualifications() %> </td>
				<td> <%=employee.getGender() %> </td>	
				<td> 
				<form method="POST" action="GetEmployeeServlet">
				<input type="hidden" name="employeeID" value="<%=employee.getEmployeeID()%>"/>
				 <input type="submit" value= "Select Employee" class="select-button" /> 
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