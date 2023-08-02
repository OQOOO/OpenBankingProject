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
	<c:import url="/include/Header.jsp" />
    <div id="main">
    	<h1>비밀번호 확인</h1>
    	<form method="post" action="${pageContext.request.contextPath }/myPage/passwordCheck.do">
    		<input type="password" name="pw">
            <input type="submit" value="확인">
        </form>
    </div>
</body>
</html>