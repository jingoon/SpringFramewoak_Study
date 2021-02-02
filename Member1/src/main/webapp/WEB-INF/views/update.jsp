<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update?id=${vo.userId}</title>
</head>
<body>
<h1>update</h1>
<form action="update" method="post">
	<input name="userId" placeholder="ID" value="${vo.userId}" readonly><br>
	<input name="userName" placeholder="name" value="${vo.userName}"><br>
	<input name="email" type="email" placeholder="email" value="${vo.email}"><br>
	<input type="submit" value="update">
</form>
<br>
가입날짜: ${vo.regdate}<br>
<a href="/">홈으로</a>
</body>
</html>