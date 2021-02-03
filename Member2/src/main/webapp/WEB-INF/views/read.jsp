<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>read</title>
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
		<h1>회원 정보 보기</h1>
		<form method="post">
			ID <input name="userId" value="${vo.userId }" readonly><br>
			<%-- PW<input id="pw" name="userPw" value="${vo.userPw}" type="password" readonly><br> --%>
			NAME<input name="userName" value="${vo.userName }" readonly><br>
			email<input name="email" value="${vo.email }" readonly><br>
			Date<input value='<fmt:formatDate value="${vo.regdate }" type="date" pattern="yyyy-MM-dd" />' readonly><br>
		</form>
		<a id="update" href="/update/${vo.userId }">수정</a>
		<a id="delete" href="/deleteConfirm/${vo.userId }">삭제</a>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#delete").click(function(e){
				e.preventDefault();
				var pw = $("#pw").val();
				var inpw = prompt("비밀번호를 입력하세요");
				if(pw == inpw){
					location.assign("/deleteConfirm/${vo.userId }");
				}else{
					alert("비밀번호가 틀렸습니다.")
				}
			})

		})
	</script>
	
</body>
</html>