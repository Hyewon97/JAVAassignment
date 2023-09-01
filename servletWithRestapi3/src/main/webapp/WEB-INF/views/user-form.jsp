<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>인력 관리 시스템</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
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
        <form id="user-form" action="<c:if test='${user != null}'>update</c:if><c:if test='${user == null}'>insert</c:if>" method="post">
              <c:choose>
                <c:when test="${user != null}">
                    <input type="hidden" name="_method" value="put" /> <!-- "PUT" 요청을 위한 hidden 필드 추가 -->
                    <input type="hidden" name="empNum" value="<c:out value='${user.empNum}' />" />
                </c:when>
                <c:when test="${user == null}">
                    <input type="hidden" name="_method" value="post" /> <!-- "POST" 요청을 위한 hidden 필드 추가 -->
                </c:when>
            </c:choose>
				<table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${user != null}">수정하기</c:if>
                        <c:if test="${user == null}">새로 등록하기</c:if>
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
    <script>
        $(document).ready(function () {
            $("#user-form").submit(function (event) {
                event.preventDefault(); // 폼 기본 동작 방지

                var method = $(this).find("input[name='_method']").val(); // _method 필드 값 확인
                var formData = $(this).serialize(); // 폼 데이터 직렬화

                $.ajax({
                    url: $(this).attr("action"),
                    type: method, // _method 필드 값에 따라 요청 타입 설정
                    data: formData,
                    success: function (response) {
                        alert("성공적으로 처리되었습니다.");
                        // 예를 들어, 성공 메시지를 화면에 표시하거나 페이지를 갱신할 수 있습니다.
                    },
                    error: function () {
                        alert("오류가 발생했습니다.");
                    }
                });
            });
        });
    </script>
</body>
</html>
