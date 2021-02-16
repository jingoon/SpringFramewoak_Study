<%@page import="java.util.ArrayList"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
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
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>

<button id="btn1">rest1</button>
<span id="sp1">문자열 전송</span><br>

<button id="btn2">rest2</button>
<span id="sp2">배열 전송</span><br>

<button id="btn3">rest3</button>
<span id="sp3">JSON 전송</span><br>

<button id="btn4">rest4</button>
<span id="sp4">List 전송</span><br>

<%/* 4 */
	List<SearchPageTO>list = new ArrayList<SearchPageTO>();
	list.add(new SearchPageTO("m001", "p001", 1));
	list.add(new SearchPageTO("m002", "p002", 2));
	
	ObjectMapper mapper = new ObjectMapper();
	String strList = mapper.writeValueAsString(list);
	pageContext.setAttribute("strList", strList);
%>



<script type="text/javascript">
	$(document).ready(function() {
		// 1,2,3
		var arr = {test1: "hello", test2 : "world", test3 : "good"};
		var test1 = "hello";
		var test2 = "world";
		var test3 = [
		      {mid : "m001", mpw : "p001", mname : "a"},
		      {mid : "m002", mpw : "p002", mname : "b"},
		      {mid : "m003", mpw : "p003", mname : "c"}
		   ];
		
		// Rest ajax String 데이터
		// 입력받은 데이터 출력
		$("#btn1").click(function() {
			$.ajax({
				type : "post",
				url : "/ajax/rest1",
				headers : {
					'Content-Type' : 'Application/Json',
					'X-HTTP-Method-Override' : 'post'
				},
				data : JSON.stringify({
					test1 : test1
				}),
				dataType : 'text',
				success : function(result) {
					console.log(result);
					$("#sp1").text(result);
				}
			})
		});
		
		// Rest ajax ARRAY 데이터
		// 입력받은 데이터중 hello, world 출력
		$("#btn2").click(function() {
			$.ajax({
				type : "post",
				url : "/ajax/rest2",
				headers:{
					'Content-Type':'application/json',
					'X-HTTP-Method-Override':'post'
				},
				data : JSON.stringify({
					arr : arr
				}),
				dataType : 'text',
				success : function(result) {
					console.log(result);
					var obj = JSON.parse(result); // 문자열 벗기고
					console.log(obj.arr.test2); // JSON.key.key
					console.log(obj["arr"]["test2"]); // JSON["key"]["key"]
					$("#sp2").text(obj["arr"]["test1"]+","+obj["arr"]["test2"]);
				}
			});
		});
		
		// Rest ajax JSON 데이터
		// 입력받은 데이터중 p002 출력
		$("#btn3").click(function() {
			$.ajax({
				type : 'post',
				url : '/ajax/rest3',
				headers :{
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'post',
					
				},
				data : JSON.stringify({
					test1 : test1,
					test2 : test2,
					test3 : test3
				}),
				dataType : 'text',
				success : function(result) {
					console.log(result)
					var obj= JSON.parse(result);
					$("#sp3").text(obj.test3[1].mpw);
				}
			})
		})
		
		// Rest ajax List 데이터
		// 입력받은 데이터중 m001출력
		$("#btn4").click(function() {
			$.ajax({
				type: 'post',
				url: '/ajax/rest4',
				headers: {
					'Content-Type':'application/json',
					'X-HTTP-Method-Override':'post'
				},
				data: JSON.stringify({
					list : ${strList}
				}),
				dataType: 'text',
				success: function(result) {
					console.log(result);
					result = JSON.parse(result);
					var point = result.list[0].searchType;
					console.log(point);
					$("#sp4").text(point);
				}
				
			})
		});
		
		
	});
	
</script>
</body>
</html>