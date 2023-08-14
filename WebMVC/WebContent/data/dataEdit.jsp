<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//cdn.ckeditor.com/4.16.0/full/ckeditor.js"></script>
<style>
	#dataForm li{
		padding: 10px 5px;
	}
	#title{width:90%;}
</style>
<script>
	$(function(){
		CKEDITOR.replace("content");
		
		$("#dataFrm").submit(function(){
			if($("#title").val()==""){
				alert("제목을 입력하세요");
				return false;
			}
			if(CKEDITOR.instances.content.getData()==""){
				alert("글 내용을 입력하세요");
				return false;
			};
			
			/* var fileCnt = 0;
			if($("#filename1").val() !=""){
				fileCnt++;
			}
			if($("#filename2").val()!=""){
				fileCnt++;
			}
			if(fileCnt<1){
				alert("첨부파일이 최소 한개는 있어야합니다.");
				return false;
			} */
			f($('#filename1').attr('type')!='hidden' && $('#filename2').attr('type')!='hidden'){
				if(($('#filename1').val()==null || $('#filename1').val()=="") && ($('#filename2').val()==null || $('#filename2').val()=="")){
					alert("1개의 첨부파일 업로드 필수입니다.");
					return false;
				}				
			}
			return true;
		});	
		//////////첨부파일
		$(document).on('click', 'b.f', function(){
			$(this).parent().css("display","none");
			$(this).parent().next().attr("type","file");
			//삭제 파일명이 있는 객체를 name 설정
			$(this).parent().next().next().attr("name","delfile");
		});
	});
</script>
<div class="container">
	<h1>자료실 글 수정하기</h1>
																							<!--  파일업로드일 경우 반드시 enctype속성이 있어야한다. -->
	<form method="post" action="<%=request.getContextPath()%>/data/dataEditOk.do" id="dataFrm" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${vo.no}"/>
		<ul>
			<li>제목 : <input type="text" name="title" id="title" size="50" value="${vo.title}"/></li>
			<li> 
				<textarea name="content" id="content">${vo.content}</textarea>
			</li>
			<!-- 첫번째 첨부파일 : vo.filename1, vo.filename[0] -->
			<li>첨부파일 :
				<div>${vo.filename1} <b class="f">X</b></div>
				<input type="hidden" name="filename1" id="filename1"/>
				<input type="hidden" name="" id="delfile1" value="${vo.filename1}"/>
			</li>
			<!-- 두번쨰 첨부파일: vo.filename2, vo.filename[1] -->
			<li>
				<c:if test="${vo.filename2 != null && vo.filename2 !='' }">
					<div>${vo.filename2} <b class="f">X</b></div>
					<input type="hidden" name="filename2" id="filename2"/>
					<input type="hidden" name="" id="delfile2" value="${vo.filename2}"/>
				</c:if>
				<c:if test="${vo.filename2 == null || vo.filename2 =='' }">
					<input type="file" name="filename2" id="filename2"/>
				</c:if>
			</li>
			<li>
				<input type="submit" value="자료 수정하기"/>
				<input type="reset" value="취소"/>
			</li>
		</ul>
	</form>
</div>