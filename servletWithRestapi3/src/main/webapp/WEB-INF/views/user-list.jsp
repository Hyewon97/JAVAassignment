<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>인력 관리 시스템</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>

<!-- 스크립트 추가 -->
<script>
$(function () {
    $("button").click(function () {
        $.ajax({
            url: "./users", // 수정된 URL 경로
            type: "GET",
            dataType: "json",
            success: function (data) {
                var userListTable = $("#user-list-table");

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
            error: function () {
                alert("Error fetching data.");
            }
        });
    });
});
</script>
<!-- 스크립트 추가 끝 -->

<body>
    <center>
        <h1>인력 관리 시스템</h1>
        <h3>
            <a href="new">새로 등록</a> &nbsp;&nbsp;&nbsp; <a href="list">전체 목록</a>
        </h3>
    </center>
    <div align="center">
        <button>데이터 불러오기</button>
        <table id="user-list-table" border="1" cellpadding="5">
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
