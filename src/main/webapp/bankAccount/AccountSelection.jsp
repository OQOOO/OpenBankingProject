<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CloudBank</title>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">

<style>
	#selecDivContainer {
		position: relative;
		top: 100px;	
		display: flex;
	}
	
	#createSavingAccountDiv {
		
	}
	
	#createDepositAccountDiv {
	
	}
	
	.buttonDiv {
		width: 300px;
		height: 300px;
		margin: 10px;
		text-align: center;
		justify-content: center;
		background-color: white;
		box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
		transition: background-color 0.3s ease, color 0.3s ease;

	}
	
	.buttonDiv:hover {
		background-color: #348ABF;
		color:white;
	}
	
	.title {
		font-size: 35px;
	}
	
	.text1 {
	
	}
	
	.text2 {
		font-size: 25px;
	}
	
	#backButton {
		position: relative;
		top: 100px;
		left: 250px;
	}
</style>
<script type="text/javascript">
	function createSavingAc() {
		document.getElementById("savingForm").submit();
	}
	function createDepositAc() {
		document.getElementById("depositForm").submit();
	}
</script>

</head>
<body>
	<c:import url="/include/Header.jsp" />
	<div id="main">
		<form id="savingForm" style="display: none" method="post" action="${pageContext.request.contextPath }/pages/toCreateSavingAccount.do">
			<input type="submit" value="예금계좌 개설">
		</form>
		<form id="depositForm" style="display: none" method="post" action="${pageContext.request.contextPath }/pages/toCreateDepositAccount.do">
			<input type="submit" value="적금계좌 개설">
		</form>
		
		<div id="selecDivContainer">
			<div id="createSavingAccountDiv" class="buttonDiv" onclick="createSavingAc()">
				<p class="title">예금계좌 개설</p>
				<p class="text1">자유입출금식 예금</p>
				<p class="text2">연 2% 이자</p>
			</div>
			<div id="createDepositAccountDiv" class="buttonDiv" onclick="createDepositAc()">
				<p class="title">적금계좌 개설</p>
				<p class="text1">적립식 적금</p>
				<p class="text2">연 5% 이자</p>
				
			</div>
		</div>
		<form id="backButton" method="post" action="${pageContext.request.contextPath }/pages/IndexMain.do">
			<input type="submit" value="뒤로">
		</form>
	</div>
</body>
</html>