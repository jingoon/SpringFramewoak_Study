<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
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
		<h1 class="jumbotron">글쓰기</h1>	
	</div>
	<div class="row">
		<form action="/board/insert" method="post">
			<div class="form-group">
				<label for="insert_input_title">제목</label>
				<input class="form-control" id="insert_input_title" name="title" >
			</div>
			<div class="form-group">
				<label for="insert_input_writer">작성자</label>
				<input class="form-control" id="insert_input_writer" name="writer" >
			</div>
			<div class="form-group">
				<label for="insert_input_content">내용</label>
				<textarea class="form-control" rows="10" id="insert_input_content" name="content" required="required"></textarea>
			</div>
		</form>
		
<!-- 첨부 파일 -->		
		<div id="fileUI" class="form-group hidden-xs"><!-- 폰에서는 안보임  -->
			<jsp:include page="/WEB-INF/views/file/fileUI.jsp"></jsp:include>
		</div>
		
			<div class="form-group">
				<button type="submit" class="btn btn-primary" id="insert_btn_submit">완료</button>
				<button class="btn btn-default" id="insert_btn_back">취소</button>
			</div>
		
		
	</div>

</div><!--container  -->
<script type="text/javascript">
	$(document).ready(function(){
	 	$("#insert_btn_submit").click(function(){
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
			
		$("#insert_btn_back").click(function(){
			// 업로드 파일 전부 삭제
			$(".deleteFile").each(function(index) {
				var that = $(this);
				deleteFile(that);
				
			});
			history.back();
		})	
	})
	
</script>

</body>
</html>