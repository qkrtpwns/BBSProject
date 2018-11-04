<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.DriverManager" %>

<%
	Connection conn = null;

	try {
		String url = "jdbc:mysql://localhost:330/project?useSSL=false";
		String user = "root";
		String password = "dlrlFgh!@#$1234";
					
		Class.forName("com.mysql.jdbc.Driver");
		
		conn = DriverManager.getConnection(url, user, password);
		
		out.println("성공 ");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		out.println("[에러] : " + e.getMessage());
	} catch (SQLException e) {
		out.println("[에러] : " + e.getMessage());
	}

%>