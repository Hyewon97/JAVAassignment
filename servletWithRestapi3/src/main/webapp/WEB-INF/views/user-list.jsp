<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>인력 관리 시스템</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script> -->
</head>


<!-- ajax 받는 부분 추가하기.. jsp 부분만 수정하면 될 것 같은데 ajax 사용하면 lib에 jar 파일 추가 해야하는지도 보기  -->

<!-- 스크립트 추가 -->
<!-- <script>
$(function (){
    
    $("button").click(function(){
//         alert("button click");
        
        $.ajax({
            url:"./hello", // HelloServlet.java로 접근
            type: "get", // GET 방식
            success:function(data){
                alert(data); // [object Object]
                alert(JSON.stringify(data)); // {"human":{"age":24,"name":"홍길동"}}
                alert(data.human.name); // 홍길동
                alert(data.human.age); // 24
            },
            error:function(){
                alert("error");
            }
            
        });
        
    });
    
});
 
 
</script> -->

<!--스크립트 추가 끝 -->

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
