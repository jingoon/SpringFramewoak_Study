<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update?id=${vo.userId}</title>
</head>
<body>
<h1>ID: ${vo.userId}</h1>
<form action="/update" method="post">
	<input name="userId" placeholder="ID" value="${vo.userId}" type="hidden"><br>
	<input name="userName" placeholder="name" value="${vo.userName}"><br>
	<input name="email" type="email" placeholder="email" value="${vo.email}"><br>
	<input name="userPw" placeholder="비밀번호를 입력하세요" type="password"><br>
	<input type="submit" value="update">
</form>
<br>
가입날짜: <fmt:formatDate value="${vo.regdate }" type="date" pattern="yyyy-MM-dd" /><br>
</body>
</html>