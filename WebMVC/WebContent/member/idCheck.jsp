<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	#menu, footer{display:none}
</style>
<script>
	$(function(){
		
        $("#setId").click(function() {
	         // opener 내장객체 사용 (중복검사창은 회원가입창에서 열린것으로 opener = 회원가입창)
	         opener.document.getElementById("userid").value = $("#checkId").text();
	         opener.document.getElementById("hiddenCheck").value = "Y";
	         self.close();
        });
        $("#frm").submit(function(){
        	if($("#userid").val()== ""){
        		alert("아이디를 입력후 다시 시도해주세요");
        		return false;
        	}
        });
	});
</script>
<div>
	<c:if test="${ !checkResult }">
		<span style="color:red" id="checkId">${userid }</span>은 사용가능한 아이디입니다.
		<input type="button" value="아이디 사용하기" id="setId"/>
	</c:if>
	<c:if test="${checkResult}">
		<span style="color:red">${userid }</span>은 사용불가능한 아이디입니다.
	</c:if>
	<hr/>
	<h3>아이디 입력후 중복검사 버튼 누르세요.</h3>
	<form id="frm" method="post" action="<%=request.getContextPath()%>/member/idCheck.do">
		아이디 : <input type="text" name="userid" id="userid"/>
		<input type="submit" value="아이디 중복검사하기"/>
	</form>

</div>