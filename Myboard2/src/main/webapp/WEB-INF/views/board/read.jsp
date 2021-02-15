<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
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
<div class="container">
	<div class="row">
		<h1 class="jumbotron">게시글 보기</h1>
	</div>
	<div class="row">
		<div class="form-group">
			<label for="read_input_title">제목</label>
			<input class="form-control" name="title" id="read_input_title" readonly value="${vo.title }">
		</div>
		<div class="form-group">
			<label>작성자</label>
			<input class="form-control" name="writer" id="read_input_writer" readonly value="${vo.writer}">
		</div>
		<div class="form-group">
			<label>작성시각</label>
			<input class="form-control" name="updateDate" id="read_input_updateDate" readonly value='<fmt:formatDate value="${vo.updateDate}" type="date" pattern="yy년 MM월 dd일 HH:mm"/>'>
		</div>
		<div class="form-group">
			<label>내용</label>
			<textarea class="form-control" rows="10" name="content" id="read_input_content" readonly>${vo.content}</textarea>
		</div>
		<div class="form-group">
			<button id="read_btn_update" class="btn btn-warning">수정</button>
			<button id="read_btn_delete" class="btn btn-danger">삭제</button>
			<button id="read_btn_reply" class="btn btn-default">댓글</button>
			<button id="read_btn_list" class="btn btn-primary">목록</button>
		</div>
	</div>
</div>





<script type="text/javascript">
	$(document).ready(function(){
		$("#read_btn_update").click(function(){
			location.assign("/board/update/${curPage}/${bno}");
		});
		$("#read_btn_delete").click(function(){
			var deleteConfig = 1234;
			var inputConfig= prompt("삭제하려면 ["+deleteConfig+"]를 입력하세요");
			if(inputConfig == deleteConfig){
				console.log(inputConfig);
				console.log(deleteConfig);
				location.assign("/board/delete/${curPage}/${vo.bno}");
			}
		});
		$("#read_btn_list").click(function(){
			location.assign("/board/list/${curPage}");
		});
	})
</script>
</body>
</html>