<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//cdn.ckeditor.com/4.16.0/full/ckeditor.js"></script>
<!-- 
	다중업로드
	https://commons.apache.org/
	FileUpload,IO를 다운로드
	commons-io-2.8.0.jar를 lib에 넣기 WEB-INF>lib
	commons-fileupload-1.4.jar를 lib에 넣기 WEB-INF>lib
	
 -->
 <script>
 	$(function(){
 			CKEDITOR.replace("content");
 	});
 </script>
 <div class="container">
 	<br/>
 	<h1>자료실 글올리기</h1>
 	<form method="post" action="<%=request.getContextPath()%>/data/multiUploadOk.do" id="dataFrm" enctype="multipart/form-data">
		<ul>
			<li>제목 : <input type="text" name="title" id="title" size="50"/></li>
			<li> 
				<textarea name="content" id="content"></textarea>
			</li>
			<li>첨부파일 <input type="file" name="filename" id="filename" multiple/></li>
			<li>
				<input type="submit" value="자료올리기(다중파일로)"/>
				<input type="reset" value="취소"/>
			</li>
		</ul>
	</form>
 </div>