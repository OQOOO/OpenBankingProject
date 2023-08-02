<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<style>

	#acName {
		font-size: 20px;
	}
	#currency {
		font-size: 40px;
	}
	
	.recodeBox {
		position:relative;
		width: 500px;
		height: 90px;
		margin: 5px;
		padding: 0px 5px;
		border-radius: 5px;
	}
	.trName {
		font-size: 20px;
		display: inline-block;
	}
	.trAmount-c {
		color: black;
		
		display: inline-block;
		position: absolute;
		
		top: 25px;
		right: 10px;
		
		font-size: 30px;
		text-align: right;
		margin:0px;
	}
	.trAmount\+c {
		color: #0080ff;

		display: inline-block;
		position: absolute;
		
		top: 25px;
		right: 10px;
		
		font-size: 30px;
		text-align: right;
		margin:0px;
	}
	.trTime {
		display: inline-block;
		position: absolute;

		text-align: left;
		width: 200px;
		
		bottom: 5px;
		left: 5px;
	}
	
	.trInfoKr {
		display: inline-block;
		position: absolute;
		
		top: 60px;
		right: 10px;
		
		text-align: right;
	}
	
	.btnBox {
		display: flex;
	 	justify-content: center;
	 	align-items: center;
	}
	
	.btnBox input {
		margin: 5px;
	}
</style>
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
		<h1>계좌 상세정보</h1>
		<div id="infoBox">
			<div id="acName">${svo.getAcName() }</div>
			<div id="acNumber">${svo.getAcNumber() }</div>
			<div id="currency">￦ ${svo.getCurrency() }</div>
			<p class="currencyKR">${svo.getCurrencyKR() }원</p>
		</div>
		
		<!--  뒤로   -->
		
		<div class="btnBox">
			<form method="post" action="${pageContext.request.contextPath }/admin/pages/userBankAcount.do">
	    		<input type="hidden" name="userId" value="${svo.getUserId() }">
	    		<input type="submit" value="뒤로">
	    	</form>

    	</div>
    	<h3>이체기록</h3>
    	<c:forEach var="vo" items="${hList }">
    		<div class="recodeBox cloudBorder">
	    		<div class="trName">${vo.getTrName() }</div>
	    		<div class="trAmount${vo.getCurrency().substring(0, 1)}c">${vo.getCurrency() } 원</div>
	    		<div class="currencyKR trInfoKr">${vo.getCurrencyKR() }원</div>
	    		<div class="trTime">${vo.getTrTime() }</div>
    		</div>
    	
    	</c:forEach>
    	
    	<h5>페이지 버튼</h5>
    	<div id="pageButtonBox">
	    	<form method="post" action="${pageContext.request.contextPath }/admin/pages/toAcountInfo.do">
	    			<input id="infoClickAcNum" type="hidden" name="acNumber" value="${svo.getAcNumber() }">
	    			<input type="hidden" name="page" value="${btnStart-1}">
	    			<input id="beforePage" class="pageButton" type="submit" value="<<">
			 </form>
	    	<c:forEach var="i" begin="${btnStart}" end="${btnEnd}">
	    		<form method="post" action="${pageContext.request.contextPath }/admin/pages/toAcountInfo.do">
	    			<input id="infoClickAcNum" type="hidden" name="acNumber" value="${svo.getAcNumber() }">
	    			<input type="hidden" name="page" value="${i}">
	    			<c:if test="${nowPage eq i }">
	    				<input id="nowPage" class="pageButton" type="submit" value="${i}">
	    			</c:if>
	    			<c:if test="${nowPage != i }">
	    				<input class="pageButton" type="submit" value="${i}">
	    			</c:if>
			 	</form>
			</c:forEach>
	    	<form method="post" action="${pageContext.request.contextPath }/admin/pages/toAcountInfo.do">
    			<input id="infoClickAcNum" type="hidden" name="acNumber" value="${svo.getAcNumber() }">
    			<input type="hidden" name="page" value="${btnEnd+1}">
    			<input id="nextPage" class="pageButton" type="submit" value=">>">
			 </form>
		</div>
	</div>
</body>
</html>