<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<style>
	div {
		margin: 5px;
	}
	a {
		margin: 5px;
	}
	.newsBox {
		width: 700px;
	}
	.newsTitle {
		font-weight: bold;
		font-size: 18px;
	}
	
</style>

</head>
<body>
	<c:import url="/include/Header.jsp"/>
	<div id="main">
		<h1>금융 뉴스</h1>
		<c:forEach var="vo" items="${newsList}">
			<div class="newsBox">
				<div class="newsTitle">${vo.getTitle() }</div>
				<div class="content">${vo.getDescription() } </div>
				<a href="${vo.getLink() }"> 원본 기사 보기 </a>
				<div class="date">${vo.getPubDate() } </div>
				<br>
			</div>
		</c:forEach>

	</div>
</body>
</html>