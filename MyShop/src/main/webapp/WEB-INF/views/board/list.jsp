<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
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
		<h1 class="jumbotron">게시글 목록</h1>
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
						<td><a href="/board/read/${to.curPage }/${vo.bno }">${vo.title } <span class="${vo.replyCnt == 0 ? 'hidden' : '' }">(${vo.replyCnt == 0 ? '':vo.replyCnt})</span></a></td>
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
						<a href="/board/list/${to.curPage>1? to.curPage-1: '' }" aria-label="Previous"> 
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
					<c:forEach var="page" begin="${to.beginPageNum }" end="${to.stopPageNum}">
						<li class="${to.curPage==page? 'active': '' }"><a href="/board/list/${page}">${page}</a></li>
					</c:forEach>
					<li class="${to.curPage>=to.totalPage? 'hidden' : ''}">
						<a href="/board/list/${to.curPage<to.totalPage? to.curPage+1: '' }" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</ul>
			</nav>

		</div>

		<div class="row">
		<div class="input-group">
			<span class="input-group-addon">
				<select id="list_select_searchType">
					<optgroup label="검색조건">
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="content">내용</option>
					</optgroup>
				</select>
			</span>
			<input id="list_input_keyword" type="text" class="form-control" placeholder="검색어를 입력하세요">
			<span class="input-group-btn">
				<button id="list-btn-search" class="btn btn-default" type="button">검색</button>
			</span>
		</div>
		<!-- /input-group -->
	</div>
</div><!-- container -->

<script type="text/javascript">
	
	$(document).ready(function() {
		//var to = ${to};
		
		//console.log(to)
		
		$("#list-btn-search").click(function() {
			var searchType = $("#list_select_searchType").val();
			var keyword = $("#list_input_keyword").val();
			var curPage = 1;
			var url = "/sboard/list/"+searchType+"/"+keyword+"/"+curPage;
			window.open(url);
		})
	})
</script>






</body>
</html>