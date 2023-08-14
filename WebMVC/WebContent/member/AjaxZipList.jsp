<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 12345 -->
<hr id="z"/>
<c:forEach var="vo" items="${list}">
	<li><span class="zCode">${vo.zipcode}</span> <span class="addr">${vo.sido} ${vo.sigungu}<c:if test="${vo.um!=null}"> ${vo.um}</c:if> ${vo.doro} ${vo.build1}<c:if test="${vo.build2!=0}">-${vo.build2}</c:if> (${vo.dong} ${vo.gibun1}<c:if test="${vo.gibun2!=0}">-${vo.gibun2}</c:if><c:if test="${vo.sibuild!=null}">, ${vo.sibuild}</c:if>)</span></li>
</c:forEach>
<hr id="z"/>