<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${result>0 }"> <!--삭제했을때  -->
	<Script>
		location.href="<%=request.getContextPath()%>/board/list.do?${pParam}"
	</Script>
</c:if>

<c:if test="${result<=0}"><!--삭제안했을때  -->
	<Script>
		history.go(-1);
	</Script>
</c:if>