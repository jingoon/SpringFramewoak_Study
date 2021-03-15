<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 게시글 목록</title>
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
		<h1 class="jumbotron">검색("${to.searchType}":"${to.keyword }") 목록</h1>
	</div>
	<div class="row">
		<a href="/board/insert" type="button" class="btn btn-primary">글쓰기</a>
	</div>
	<div class="row">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${to.list }" var="vo">
					<tr>
						<td>${vo.bno }</td>
						<td><a href="/sboard/read/${to.searchType}/${to.keyword}/${to.curPage}/${vo.bno }">${vo.title }</a></td>
						<td>${vo.writer }</td>
						<td>${vo.viewCnt }</td>
						<td><fmt:formatDate type="date" pattern="yy/MM/dd" value="${vo.updateDate }"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div><!-- row -->
		<div class="row">
			<!-- 페이징  -->
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li class="${to.curPage<=1? 'hidden':'' }">
						<a href="/sboard/list/${to.searchType}/${to.keyword}/${to.curPage>1? to.curPage-1: '' }" aria-label="Previous"> 
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
					<c:forEach var="page" begin="${to.beginPageNum }" end="${to.stopPageNum}">
						<li class="${to.curPage==page? 'active': '' }"><a href="/sboard/list/${to.searchType}/${to.keyword}/${page}">${page}</a></li>
					</c:forEach>
					<li class="${to.curPage>=to.totalPage? 'hidden' : ''}">
						<a href="/sboard/list/${to.searchType}/${to.keyword}/${to.curPage<to.totalPage? to.curPage+1: '' }" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</ul>
			</nav>

		</div>
</div><!-- container -->

<script type="text/javascript">
	$(document).ready(function() {
		
	})
</script>
</body>
</html>