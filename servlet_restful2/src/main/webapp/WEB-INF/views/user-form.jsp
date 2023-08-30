<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>인력 관리 시스템</title>
</head>
<body>
	<center>
		<h1>인력 관리 시스템</h1>
        <h3>
        	<a href="new">새로 등록</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">전체 목록</a>
        </h3>
        
	</center>
    <div align="center">
		<c:if test="${user != null}">
			<form action="update" method="post">
        </c:if>
        <c:if test="${user == null}">
			<form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${user != null}">
            			수정하기
            		</c:if>
            		<c:if test="${user == null}">
            			새로 등록하기
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${user != null}">
        			<input type="hidden" name="empNum" value="<c:out value='${user.empNum}' />" />
        		</c:if>            
            <tr>
                <th>이름: </th>
                <td>
                	<input type="text" name="name" size="45"
                			value="<c:out value='${user.name}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>이메일: </th>
                <td>
                	<input type="text" name="email" size="45"
                			value="<c:out value='${user.email}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>부서: </th>
                <td>
                	<input type="text" name="department" size="15"
                			value="<c:out value='${user.department}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
