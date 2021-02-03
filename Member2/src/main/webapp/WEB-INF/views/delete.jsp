<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>delete</title>
</head>
<body>
<h1>ID: ${userId} 삭제 하시겠습니까?</h1>
<form action="/delete" method="post">
	<input name="userId" placeholder="ID" value="${userId}" type="hidden"><br>
	<input name="userPw" placeholder="비밀번호를 입력하세요" type="password"><br>
	<input type="submit" value="DELETE">
</form>
<br>

</body>
</html>