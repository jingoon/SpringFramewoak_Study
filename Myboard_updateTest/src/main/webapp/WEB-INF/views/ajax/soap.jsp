<%@page import="kr.co.domain.BoardVO"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.domain.SearchPageTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Soap</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>

<span>단일 데이터</span>
<button id="btn1">단일 데이터 전송</button><br>

<span>배열 데이터</span>
<button id="btn2">배열 데이터 전송(리턴배열)</button><br>

<span>배열 데이터</span>
<button id="btn22">배열 데이터 전송2(문자열화)</button><br>

<span>JSON 데이터</span>
<button id="btn3">JSON 데이터 전송</button><br>

<span>list 데이터</span>
<button id="btn4">List 데이터 전송</button><br>
<%
	List<SearchPageTO<BoardVO>> list = new ArrayList<SearchPageTO<BoardVO>>();
	list.add(new SearchPageTO<BoardVO>("타입1","키워드1",1));
	list.add(new SearchPageTO<BoardVO>("타입2","키워드2",2));
	list.add(new SearchPageTO<BoardVO>("타입3","키워드3",3));
	
	
	ObjectMapper mapper = new ObjectMapper();
	String strList = mapper.writeValueAsString(list);	// String 으로 .. spring의 작업
	pageContext.setAttribute("strList", strList);	// el로 사용하기 위해 page에 바인딩
%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn1").click(function() {
			var sp1 = $("span:nth-of-type(1)").text();
			$.ajax({
				type : "post",
				url : "/ajax/soapOne",
				data : {
					'str' : sp1	//보낼 데이터 -Json형식 ''묶어준다
				},
				datatype : "text",
				success: function(result) {
					console.log(result)
					$("#btn1").after("<h1></h1>")
					$("h1").text(result);
				}
			});
		});
		
		$("#btn2").click(function() {
			var arr = ["1번","2번","3번"];	// js에서는 배열이 대괄호[]
			$.ajax({
				type : "post",
				url : "/ajax/soapArr",
				traditional : true,	// 배열데이터를 전송할때 추가
				data: {
					'arr': arr
				},
			//	dataType: "text", object로 리턴됨
				success: function(result) {
					console.log(result)
					console.log(typeof(result));// 타입확인 
					//var reArr = JSON.parse(result); 문자열이 아니라 사용불가
					for(i in result){
						console.log(result[i])
					}
					$("#btn2").after("<h2></h2>")
					$("h2").text(result);
				}
				
			})
		});
		
		$("#btn22").click(function() {
			var arr = ["1번","2번","3번"];
			$.ajax({
				type : "post",
				url : "/ajax/soapArr",
				traditional : true,
				data: {
					'arr': arr
				},
				dataType: "text",	// 문자열화
				success: function(result) {
					console.log(result)
					console.log(typeof(result));// 타입확인 '["s","s"]' 배열을 감싼 text다
					var result = JSON.parse(result); // 문자열화를 풀어줌, 문자열 양끝 따옴표(' ')제거
					console.log(typeof(result));// 타입확인
					for(i in result){
						console.log(result[i])
					}
					$("#btn22").after("<h3></h3>")
					$("h3").text(result);
				}
				
			})
		});
		
		$("#btn3").click(function() {
			$.ajax({
				type : 'post',
				url : '/ajax/soapJson',
				data : {
					'searchType' : '서치타입',
					'keyword' : '키워드',
					'curPage' : 2
				},
				dataType : 'text',
				success : function(result) {
					console.log(result, typeof(result));
					var result = JSON.parse(result);
					console.log(result, typeof(result));
					$("#btn3").after("<h4></h4>")
					$("h4").text(result.keyword);
				}
			});
		});
		
		$("#btn4").click(function() {
			console.log(${strList});
			console.log(JSON.stringify(${strList}));
			$.ajax({
				type : "post",
				url : "/ajax/soapList",
				data : { 
					list : JSON.stringify(${strList})		//key : value
				},
				dataType : "text",
				success : function(result) {
					console.log(result, typeof(result));
					var result = JSON.parse(result);
					console.log(result, typeof(result));
					for(x in result){
						var reList = result[x];
						console.log(reList.searchType);
					}
					$("#btn4").after("<h5></h5>")
					$("h5").text(reList.searchType);
				}
			})
		});
		
		
	});


</script>


</body>
</html>