<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>User Management Application</title>
</head>
<body>
	<center>
		<h1>User Management</h1>
		<h2>
			<a href="register">Add New User</a> &nbsp;&nbsp;&nbsp; <a href="/">List
				All Users</a>

		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Users</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Department</th>
			</tr>
			<c:forEach var="employee" items="${listUser}">
				<tr>
					<td><c:out value="${employee.empNum}" /></td>
					<td><c:out value="${employee.name}" /></td>
					<td><c:out value="${employee.email}" /></td>
					<td><c:out value="${employee.department}" /></td>
					 <td><a href="edit?empNum=<c:out value='${employee.empNum}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="delete?empNum=<c:out value='${employee.empNum}' />">Delete</a></td> 
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
