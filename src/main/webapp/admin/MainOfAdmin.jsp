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
	#buttonContainer input {
		margin: 10px;
		width: 300px;
		height: 70px;
		font-size: 17px;
		
	}
</style>
</head>
<header>
	<div id="headerContainer" class="container">
		<form id="loginForm" method="post" action="${pageContext.request.contextPath }/admin/pages/toMainOfAdmin.do">
		    <input id="loginbutton" class="headerMenus" type="submit" value="어드민 메인페이지">
		</form>
		<form id="loginForm" method="post" action="${pageContext.request.contextPath }/account/logout.do">
		    <input id="loginbutton" class="headerMenus" type="submit" value="로그아웃">
		</form>
	</div>
</header>
<body>
	<div id="main">
		<h1>어드민 메인페이지</h1>
<%-- 		${sessionScope.adminRight } --%>
		<div id="buttonContainer">
			<form action="${pageContext.request.contextPath }/admin/pages/toBoard.do" method="post">
				<input type="submit" value="문의게시판 관리"/><br>
			</form>
			<form action="${pageContext.request.contextPath }/admin/pages/toUser.do" method="post">
				<input type="submit" value="사용자 관리"/><br>
			</form>
		</div>
	</div>
</body>
</html>