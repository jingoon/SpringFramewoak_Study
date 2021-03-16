<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
 src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
 src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
 src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
<!-- 로그인&로그아웃 -->
	<div class="container-fluid">
		<jsp:include page="/WEB-INF/views/header/loginHeader.jsp"></jsp:include>
	</div>
	<hr>
<div class="container">
	<div class="row">
		<h1 class="jumbotron">게시글 수정</h1>
	</div>
	<div class="row">
		<form action="/board/update/${curPage }" method="post">
			<input type="hidden" name="bno" value="${vo.bno }">
			<div class="form-group">
				<label for="read_input_title">제목</label>
				<input class="form-control" name="title" id="update_input_title" value="${vo.title }">
			</div>
			<div class="form-group">
				<label>작성자</label>
				<input class="form-control" name="writer" id="update_input_writer"  value="${vo.writer}">
			</div>
			
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" rows="10" name="content" id="read_input_content" >${vo.content}</textarea>
			</div>
		</form>

<!-- 첨부 파일 -->		
			<div id="fileUI_update" class="form-group hidden-xs"><!-- 폰에서는 안보임  -->
				<jsp:include page="/WEB-INF/views/file/fileUI.jsp"></jsp:include>
			</div>		
		
		<div class="form-group">
			<button id="update_btn_update" class="btn btn-warning">수정완료</button>
			<button id="update_btn_back" class="btn btn-danger">취소</button>
		
		</div>
	</div>
</div>





<script type="text/javascript">
	$(document).ready(function(){
		var bno = ${vo.bno };
						
		// 업로드 파일 목록 획득
		uploadFileList(bno);
		
		$("#update_btn_update").click(function(){
			// 업로드 파일 전부 append
	 		var str="";
	 		$(".deleteFile").each(function(index){
	 			var that = $(this);
	 			var data = that.attr("data-src");
	 			str += "<input type='hidden' name='files["+index+"]' value='"+data+"' >";
	 		});
	 			 		
	 		$("form").append(str);
			$("form").submit();
		});
		$("#update_btn_back").click(function(){
			history.back();
		});
		// 파일 삭제 (태그만 삭제&등록시 DB수정)
		$(".uploadList").on("click", ".deleteFile", function() {
			var that = $(this);
			that.parent().parent().parent().remove();
		});
	})
</script>
</body>
</html>