<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String kkk = (String)request.getAttribute("kkk");
%>
<html>
<head>
    <title>인력 관리 시스템</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>


<body>

<!-- 스크립트 추가 -->
<script>




/* onLoad 처리.. 데이터 받아서 리스트로 뿌려줌 */
window.onload = function(){
	$(document).ready(function() {
		/*
		$.ajax({
		    url: "./users/", // 요청할 url
		    type: "get", // 통신 타입 설정
		    dataType: "json",
		    success: function (data) {
		    	console.log("111111111111")
		        var userListTable = $("#user-list-table"); // 유저 정보 출력할 list선언

		        userListTable.find("tr:gt(0)").remove(); // 첫 번째 행 제외 모두 제거

		        $.each(data, function (index, user) {
		            var newRow = $("<tr>" +
		                "<td>" + user.empNum + "</td>" +
		                "<td>" + user.name + "</td>" +
		                "<td>" + user.email + "</td>" +
		                "<td>" + user.department + "</td>" +
		                "<td><a href='edit?empNum=" + user.empNum + "'>수정</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
		                "<a href='delete?empNum=" + user.empNum + "'>삭제</a></td>" + 
		                "</tr>");
		            userListTable.append(newRow);
		        });
		    },
		    error: function (data) {
		    	console.log("data = ", data)
		        alert("Error fetching data.");
		    }
		});
		*/
	});
	
}


</script>
<!-- 스크립트 추가 끝 -->
	<input type="text" value="${kkk}">
    <center>
        <h1>인력 관리 시스템</h1>
        <h3>
            <a href="new">새로 등록</a> &nbsp;&nbsp;&nbsp; <a href="list">전체 목록</a>
        </h3>
    </center>
    
    <div align="center">
      
        <table border=1>
		<thead>
			<tr align='center'>
				<th>사원번호</th>
                <th>이름</th>
                <th>이메일</th>
                <th>부서</th>
			</tr>
		</thead>
		<tbody id="#user-list-table">
		</tbody>
	</table>
	        
    </div>
</body>
</html>
