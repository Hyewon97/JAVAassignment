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
		<nav clas="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="new">User Management APP</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
	</header>
	<br>



	<div align="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${emsUser != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${emsUser == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${emsUser != null}">
            			Edit User
            		</c:if>
						<c:if test="${emsUser == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>


				<c:if test="${emsUser != null}">
					<input type="hidden" name="empNum"
						value="<c:out value='${emsUser.empNum}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>User Name:</label><input type="text"
						value="<c:out value='${emsUser.name}' />" class="form-control"
						name="name" requied="requied">
				</fieldset>

				<fieldset class="form-group">
					<label>User Email:</label><input type="text"
						value="<c:out value='${emsUser.email}' />" class="form-control"
						name="email">
				</fieldset>


				<fieldset class="form-group">
					<label>User Department:</label><input type="text"
						value="<c:out value='${emsUser.department}' />"
						class="form-control" name="department">
				</fieldset>



				<button type="submit" class="btn btn-success">Save</button>

				</form>
			</div>
		</div>
	</div>
</body>
</html>
