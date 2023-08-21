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
		<h1>employee register</h1>
		
		<form action="<%= request.getContextPath() %>/register" method="post">
			<table style="width:80%">
	
				<tr>
					<td>name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				
				
				<tr>
					<td>email</td>
					<td><input type="text" name="email" /></td>
				</tr>
				
				<tr>
					<td>department</td>
					<td><input type="text" name="department" /></td>
				</tr>
				
				
				
			</table>
			<input type="submit" value="submit">
		</form>

	</div>

</body>
</html>