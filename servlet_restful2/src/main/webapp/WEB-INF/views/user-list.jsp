<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>인력 관리 시스템</title>
</head>
<body>
	<center>
		<h1>인력 관리 시스템</h1>
		<h3>
			<a href="new">새로 등록</a> &nbsp;&nbsp;&nbsp; <a href="list">전체 목록</a>

		</h3>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>전체 목록</h2>
			</caption>
			<tr>
				<th>사원번호</th>
				<th>이름</th>
				<th>이메일</th>
				<th>부서</th>
				<th></th>
			</tr>
			<c:forEach var="user" items="${listUser}">
				<tr>
					<td><c:out value="${user.empNum}" /></td>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.department}" /></td>
					<td><a href="edit?empNum=<c:out value='${user.empNum}' />">수정</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="delete?empNum=<c:out value='${user.empNum}' />">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
