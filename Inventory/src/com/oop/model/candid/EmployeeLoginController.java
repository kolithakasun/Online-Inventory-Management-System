package com.oop.model.candid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EmployeeLoginController")
public class EmployeeLoginController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String un=request.getParameter("username");
		String pw=request.getParameter("password");
		
		// Connect to mysql and verify username password
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		 // loads driver
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/oop", "root", ""); // gets a new connection
		
		PreparedStatement ps1 = c.prepareStatement("DROP TABLE IF EXISTS stafflogin");
		PreparedStatement ps2 = c.prepareStatement("create table stafflogin( userName varchar(20), pass varchar(20), primary key (userName))");
		PreparedStatement ps3 = c.prepareStatement("INSERT INTO stafflogin(userName, pass) VALUES ('admin', 'admin')");
		PreparedStatement ps4 = c.prepareStatement("INSERT INTO stafflogin(userName, pass) VALUES ('dmkk', 'dmkk')");
		
		ResultSet rs1 = ps1.executeQuery();
		ResultSet rs2 = ps2.executeQuery();
		ResultSet rs3 = ps3.executeQuery();
		ResultSet rs4 = ps4.executeQuery();
		
		PreparedStatement ps = c.prepareStatement("select userName,pass from login where userName=? and pass=?");
		ps.setString(1, un);
		ps.setString(2, pw);
 
		ResultSet rs = ps.executeQuery();
 
		while (rs.next()) {
			response.sendRedirect("index2.jsp");
			return;
		}
		response.sendRedirect("error2.html");
		return;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
