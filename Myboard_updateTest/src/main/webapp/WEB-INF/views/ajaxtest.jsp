<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script src="/resources/js/ajaxUpload.js" type="text/javascript"></script>
<style type="text/css">
	.fileDrop{
		width: 100%;
		height: 100px;
		border: 1px red dotted;
	}
</style>
</head>
<body>

<h1>ajax를 이용한 파일 업로드</h1>

<div class="fileDrop"></div>
<div class="uploadList"></div>

<script type="text/javascript">
	$(document).ready(function() {
		
		$(".fileDrop").on("dragenter dragover", function(e) {
			e.preventDefault();
		})
		
		$(".fileDrop").on("drop", function(e) {
			e.preventDefault();
			var files = e.originalEvent.dataTransfer.files;
			var file = files[0];
			console.log(files);
			var formData = new FormData();
			formData.append("file", file);
			$.ajax({
				type : 'post',
				url : 'ajaxtest',
				processData : false,
				contentType : false,
				data : formData,
				dataType : 'text',
				success : function(data) {
					console.log(data);
					var str = uploadImage(data);					
					$(".uploadList").append(str);
				}
			});
			
			
		})
		
		

		
		
	});
	

</script>

</body>
</html>