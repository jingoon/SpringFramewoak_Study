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
<!-- 로그인&로그아웃 -->
	<div class="container-fluid">
		<jsp:include page="/WEB-INF/views/header/loginHeader.jsp"></jsp:include>
	</div>
	<hr>
	<div class="container">
		<div class="row">
			<h1 class="jumbotron">게시글 보기</h1>
		</div>
		<div class="row">
			<div class="form-group">
				<label for="read_input_title">제목</label> <input class="form-control"
					name="title" id="read_input_title" readonly value="${vo.title }">
			</div>
			<div class="form-group">
				<label>작성자</label> <input class="form-control" name="writer"
					id="read_input_writer" readonly value="${vo.writer}">
			</div>
			<div class="form-group">
				<label>작성시각</label> <input class="form-control" name="updateDate"
					id="read_input_updateDate" readonly
					value='<fmt:formatDate value="${vo.updateDate}" type="date" pattern="yy년 MM월 dd일 HH:mm"/>'>
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" rows="10" name="content"
					id="read_input_content" readonly>${vo.content}</textarea>
			</div>
<!-- 첨부 파일 -->		
			<div id="fileUI_read" class="form-group hidden-xs"><!-- 폰에서는 안보임  -->
				<jsp:include page="/WEB-INF/views/file/fileUI.jsp"></jsp:include>
			</div>
			
			<div class="form-group">
				<c:if test="${login.name == vo.writer}">
					<button id="read_btn_update" class="btn btn-warning">수정</button>
					<button id="read_btn_delete" class="btn btn-danger">삭제</button>
				</c:if>
				<button id="read_btn_reply" class="btn btn-default">댓글</button>
				<button id="read_btn_list" class="btn btn-primary">목록</button>
			</div>
		</div>	<!-- row -->
<!-- 댓글 입력 창 -->
		<div class="row">
			<div class="collapse" id="replyCollapse">
				<div class="form-group">
					<label for="replyer">작성자</label> <input class="form-control"
						name="replyer" id="replyer">
				</div>
				<div class="form-group">
					<label for="replyText">내용</label> <input class="form-control"
						name="replyText" id="replyText">
				</div>
				<div class="form-group">
					<button id="board_read_btn_reply_insert" class="btn btn-primary">등록</button>
					<button id="board_read_btn_reply_esc" class="btn btn-default">취소</button>
				</div>
			</div>
		</div>	<!-- row -->
<!-- 댓글 리스트 -->
		<div class="row">
			<div id="reply_List">
				
			</div>
		</div> <!-- row -->
<!-- 댓글 수정 modal -->
		<div class="row">
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">댓글 제목은 replyer</h4>
						</div>
						<div class="modal-body">
							<input class="form-control" id="modal_input_rno" type="hidden">
							<input class="form-control" id="modal_input_replyText" >
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
							<button id="modal_update" type="button" class="btn btn-primary" data-dismiss="modal">저장</button>
						</div>
					</div>
				</div>
			</div>
		</div>	<!-- row -->
	</div>	<!-- container -->

	
	<script type="text/javascript">
	$(document).ready(function(){
		var bno = ${vo.bno};
		var curPage = 1;
		// 글보기 view 출력과 동시에 댓글리스트 함수 실행
		replyList(bno, curPage);
		
		// 파일 드랍 레이블과 영역 숨기기
		hiddenInsert();
				
		// 업로드 파일 목록 획득
		readFileList(bno);
				
		//댓글 리셋 함수
		function replyReset() {
			$("#replyer").val("");
			$("#replyText").val("");
		}
		
		// 댓글 리스트 함수
		function replyList(bno, curPage) {
			$.getJSON("/reply/"+bno+"/"+curPage, function(result) { //.ajax와 같은 역할
				var str = '';
				console.log(result);
				console.log(result.list);
				for(i in result.list){
					var obj = result.list[i];
					str += ''
					+ '<div class="panel panel-primary">'
					+ '					<div class="panel-heading">'
					+ '						<h3 class="panel-title">'
					+ '							<span class="glyphicon glyphicon-user"> '+obj.replyer+'</span>'
					+ '							<span class="pull-right glyphicon glyphicon-time"> '+obj.updateDate+'</span>'
					+ '						</h3>'
					+ '					</div>'
					+ '					<div class="panel-body">'
					+ '						<p hidden="">'+obj.replyer+'</p>'
					+ '						<p>'+obj.replyText+'</p>'
					+ '						<button data-rno='+obj.rno+' class="btn btn-warning glyphicon glyphicon-edit reply_update " title="수정"></button>'
					+ '						<button data-rno='+obj.rno+' class="btn btn-danger glyphicon glyphicon-remove reply_delete " title="삭제"></button>'
					+ '					</div>'
					+ '				</div>';
				}
				$("#reply_List").html(str);
			});//select일때 .ajax와 같은 역할
			
		};
		
		// 댓글 입력창 토글 함수
		function input_toggle() {
			$("#replyCollapse").collapse("toggle");
		}
		
		
		// 모달창 열기(댓글 수정)
		$("#reply_List").on("click", ".reply_update", function() {
			var rno = $(this).attr("data-rno");
			var replyText = $(this).prev().text();
			var replyer = $(this).prev().prev().text();
			$("#myModalLabel").text(replyer);	//제목 = 작성자
			$("#modal_input_rno").val(rno);		//댓글번호 하이든
			$("#modal_input_replyText").val(replyText);	// 댓글 내용 입력
			$("#myModal").modal("show");		//모달창 열기
		});
		// 댓글 수정 (모달창 수정 완료(update))
		$("#modal_update").click(function() {
			var rno = $("#modal_input_rno").val();
			var replyText = $("#modal_input_replyText").val();
			$.ajax({
				type : 'put',
				url : '/reply',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "put"
				},
				data : JSON.stringify({
					rno : rno,
					replyText : replyText,
				}),
				dataType : "text",
				success : function(result) {
					replyList(bno, curPage);
				}
			});
		});
		
		// 댓글 삭제
		$("#reply_List").on("click", ".reply_delete", function() {
			var click = $(this);
			var rno = $(this).attr("data-rno");
			var cf = confirm("댓글을 삭제합니다");
			if(cf){
				$.ajax({
					type : 'delete',
					url : '/reply',
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "delete"
					},
					data : JSON.stringify({
						rno : rno,
						bno : bno
					}),
					dataType : "text",
					success : function(result) {
						if(result =="o"){
							click.parent().parent().remove(); //할아버지 태그부터 쭉 삭제. 댓글목록 갱신을 대신함
						}else{
							alert("댓글 삭제 실패");
						}
					}
				});

			}else{
				console.log("삭제취소");
			}
		});
	
		
		
		
		// 댓글 입력창
		$("#read_btn_reply").click(function() {
			$("#replyCollapse").collapse("toggle");
		});
		
		// 댓글 입력
		$("#board_read_btn_reply_insert").click(function() {
			var replyer = $("#replyer").val();
			var replyText = $("#replyText").val();
			if(replyer == "" || replyText == "" ){
				return;
			}
			$.ajax({
				type : "post",
				url : "/reply",
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "post"
				},
				data : JSON.stringify({
					bno : bno,
					replyer : replyer,
					replyText : replyText
				}),
				dataType : "text",
				success : function(result) {
					console.log(typeof(result),result);
					if(result != null){
						replyReset();
						replyList(bno, curPage);
					}else{
						alert("댓글 실패");
					}
					
				}
			});
		});
		
		// 댓글 취소
		$("#board_read_btn_reply_esc").click(function() {
			replyReset();
			input_toggle();
		});
		
		
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