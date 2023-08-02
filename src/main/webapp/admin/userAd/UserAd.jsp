<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
</head>
<body>
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
	<div id="main">
		<h1>유저관리페이지</h1>
		<form method="post" action="${pageContext.request.contextPath }/admin/userSearch.do">
			<select id="userSearch" name="searchType">
			  <option value="userId">아이디로 검색</option>
			  <option value="userName">이름으로 검색</option>
			  <option value="addr">주소로 검색</option>
			  <option value="phonNum">전화번호로 검색</option>
			</select>
			<input type="text" name="searchText" placeholder="입력 없을경우 모든 유저 출력">
		    <input type="submit" value="유저 검색">
		</form>
		
		<!-- 유저 검색 결과값 반환 -->
		
		<c:forEach var="vo" items="${userList }">
			<form method="post" action="${pageContext.request.contextPath }/admin/pages/userBankAcount.do">
				<input type="hidden" name="userId" value="${vo.getId() }">
				<input type="submit" value="계좌 정보 보기">
			</form>
			${vo.getId() }<br>
			${vo.getEmail() }<br>
			${vo.getPassword() }<br>
			${vo.getName() }<br>
			${vo.getBirthDate() }<br>
			${vo.getPhoneNum() }<br>
			${vo.getAddress() }<br>
			${vo.getAccountLink() }<br>
			${vo.getAdminRight() }<br>
		</c:forEach>
	</div>
</body>
</html>