<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	console.log("userid" + userid);
</script>
<c:if test="${userid !=null || username !=null}"><!-- 로그인이 됬을때 -->
	<script>
 		location.href = "<%=request.getContextPath()%>/index.do";
 	</script>
</c:if>

<c:if test="${userid==null || username==null}">
	<script>
		history.back();
	</script>
</c:if>