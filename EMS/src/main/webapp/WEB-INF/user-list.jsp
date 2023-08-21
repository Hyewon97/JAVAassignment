<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcnd.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a class="navbar-brand">User Management</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">

		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new class="btnbtn-success">
					Add New User </a>
			</div>
			<br>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th>EmpNum</th>
						<th>Name</th>
						<th>Email</th>
						<th>Department</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="emsUser" items="${listUser}">
						<tr>
							<td><c:out value="${emsUser.empNum}" /></td>
							<td><c:out value="${emsUser.name}" /></td>
							<td><c:out value="${emsUser.email}" /></td>
							<td><c:out value="${emsUser.department}" /></td>
							<td><a href="edit?id=<c:out value='${emsUser.empNum}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${emsUser.empNum}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
