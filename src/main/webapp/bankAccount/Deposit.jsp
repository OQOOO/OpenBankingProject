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
		<h1>계좌 입금</h1>
		<form method="post" action="${pageContext.request.contextPath }/bankAccount/diposit.do">
			<input type="hidden" name="giveAcNumber" value="${acNumber }">
			<h1>${acNumber }</h1>
			입금할 계좌번호<br>
			<input type="text" name="takeAcNumber"><br>
			<p class="errerMessage">${acNumErrer }</p>
			<br>
			입금할 금액<br>
			<input type="number" name="amount">
			<p class="errerMessage">${amountErrer }</p>
			
			<input type="submit" value="전송">
		</form>
		<h1>내계좌</h1>
		<c:forEach var="vo" items="${ acList }">
			${vo.getAcName() }<br>
			${vo.getAcNumber() }<br>
			${vo.getBalance() }<br>
			<br>
		</c:forEach>
	</div>
</body>
</html>