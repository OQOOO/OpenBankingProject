<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<style type="text/css">
	input[type="text"] {
		border-radius: 5px;
	}
	textarea {
		border-radius: 5px;
	}
	#back {
		position: relative;
		left: 215px;
		bottom: 30px;
	}
	
	hr {
	
	}
</style>
</head>
<body>
	<c:import url="/include/Header.jsp" />
	<div id="main">
	 	<h1>글작성</h1>
	 	
		<form action="${pageContext.request.contextPath }/board/insertBoard.do" method="post">
				<p style="margin:3px; font-size: 14px">작성자 ${sessionScope.uid}</p>
			<div id="titleIdDiv">
				<input id="title" type="text" name="title" placeholder="제목을 입력하세요"
				style="width: 800px; height: 35px;"
				/><br>
			</div>
			<hr>
			<textarea name="content" cols="40" rows="10" placeholder="내용을 입력하세요"
			style="width: 800px; height: 300px"
			></textarea><br>
			<hr>
			<div style="text-align: right;">
				<input class="insertButton" id="insertButton" type="submit" value="작성완료"/><br>
			</div>
		</form>
		<form action="${pageContext.request.contextPath }/pages/toBoard.do" method="post">
			<input id="back" class="insertButton" id="backButton" type="submit" value="취소"/><br>
		</form>
	</div>
</body>
</html>