<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- /myapp 프로젝트 경로 리턴 -->

<!--  <p>${contextPath}</p> -->

<div id="bodywrap">
	<form id="frm" method="get" action="write.do">
		<input type="submit" id="btnWrite" value="글쓰기" />
	</form>

	<table class="table table-border mt-1">
		<tr>
			<th class="col-md-1 text-center">번호</th>
			<th class="col-md-7 text-center">제목</th>
			<th class="col-md-2 text-center">작성자</th>
			<th class="col-md-2 text-center">조회수</th>
		</tr>

		<!-- 테이블에 담긴것을 리스트에 넘겨준다. 이걸 forEach로 처리한다. -->
		<c:forEach items="${aList}" var="dto">
			<tr>
				<td class="text-center">${dto.empNum}</td>
				<td class="pl-2 text-left">
				<c:url var="path" value="view.do">
					<c:param name="currentPage" value="${pv.currentPage}" />
					<c:param name="empNum" value="${dto.empNum}" />
					</c:url> 
					

					<a href="${path}"> ${dto.depName}</a></td>
				<td class="text-center">${dto.depDTO.depName}</td>
			</tr>
		</c:forEach>
  
	</table>
	
	<ul class="pagination justify-content-center mt-5 mb-5">
	
	<!-- 이전 출력 시작 -->
	<c:if test="${pv.startPage > 1}">
			<li class="page=item"> 												
				<a class="page-link" href="list.do?currentPage=${pv.startPage - pv.blockPage}">Prev</a>
			</li>
		</c:if>
	<!-- 이전 출력 끝 -->
	
		<!--  페이지 출력 시작 -->
		<c:forEach var="i" begin="${pv.startPage}" end ="${pv.endPage}">
		
			<li>
			<c:choose>
			<c:when test="${i==pv.currentPage}">
				<a class="page-link page-item active" href="list.do?currentPage=${i}" > ${i}</a>
			</c:when>
			<c:otherwise>
				<a class="page-link" href="list.do?currentPage=${i}">${i}</a>   
			</c:otherwise>
			</c:choose>
			</li>  
		</c:forEach>
		<!--  페이지 출력 끝 -->
		
		<!-- 다음 출력 시작 -->
		<c:if test="${pv.endPage < pv.totalPage}">
			<li> 												<!-- 스타트 페이지 =1 -->
				<a class="page-link" href="list.do?currentPage=${pv.startPage + pv.blockPage}">Next</a>
			</li>
		</c:if>
		<!-- 다음 출력 끝 -->
	</ul>
</div>








