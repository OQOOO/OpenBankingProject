<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CloudBank</title>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
</head>
<body>
	<h1>계정 연동을 진행할까요?</h1>
	<form id="loginForm" method="post" action="">
        <input class="headerMenus" type="submit" value="카카오 계정 연동">
    </form>
	<form id="loginForm" method="post" action="${pageContext.request.contextPath }/pages/IndexMain.do">
        <input class="headerMenus" type="submit" value="다음에 연동하기">
    </form>
</body>
</html>