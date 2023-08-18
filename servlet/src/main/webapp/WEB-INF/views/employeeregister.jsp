<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>사원 등록 employee register</h1>
		<form action="<%= request.getContextPath() %>/register" method="post">
			<table style="width:80%">
	
				<tr>
					<td>이름 name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				
				<tr>
					<td>사원번호 empNum</td>
					<td><input type="text" name="empNum" /></td>
				</tr>
				
				<tr>
					<td>이메일 email</td>
					<td><input type="text" name="email" /></td>
				</tr>
				
				<tr>
					<td>비밀번호 password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				
				<tr>
					<td>입사일 hireDate</td>
					<td><input type="text" name="hireDate" /></td>
				</tr>
				
				
				
			</table>
			<input type="submit" value="submit">
		</form>

	</div>

</body>
</html>