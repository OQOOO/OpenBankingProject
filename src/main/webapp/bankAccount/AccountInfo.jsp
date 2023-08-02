<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CloudBank</title>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<style>

	
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
	
	
	.btnBox input {
		margin: 5px;
	}
	
	.trAcNum {
		font-size: 12px;
	}
	
	
	#myInfoContainer {
		display:grid;
  		grid-template-columns: 500px 260px;
		grid-template-rows: 250px;
		margin: 10px;
	}
	
	#infoBox {
		padding: 10px;
		padding-right: 30px;
		text-align: right;
	
	}
		#acName {
			font-size: 30px;
		}
		
		#acNumber {
			position: relative;
			bottom: 5px;
			margin-bottom: 20px;
		}
		
		#crText {
			position:relative;
			top: 10px;
			margin: 0px;
		}
		
		#currency {
			margin-top: 0px;
			font-size: 40px;
			
		}
		#kr {
			text-align: right;
		}
	
	#btnBox {
		padding: 10px;
		display: block;
	 	justify-content: center;
	 	align-items: center;
	 	border-left: solid 1px gray;
	}
		#toSendButton {
			height: 150px;
			margin-bottom: 10px;
			font-size: 25px;
		}
		
		#amountBtn {
			margin-bottom: 10px;
		}
	
</style>
<script>
	document.addEventListener('DOMContentLoaded', function() {
		const isUpdate = '${isDataUpdate}';
	
		if (isUpdate == "1") {
			
			Swal.fire({
			    icon: 'success', // Alert 타입
			    title: '변경 성공',         // Alert 제목
			    text: '계좌정보 변경에 성공했습니다.',  // Alert 내용
			});
		}
	});
</script>
</head>
<body>
	<c:import url="/include/Header.jsp" />
	<div id="main">
	
		<!-- 내 계좌 정보 | 송금버튼 -->
		
		<div id="myInfoContainer">
			<div id="infoBox">
				<div id="acName">${svo.getAcName() }</div>
				<div id="acNumber">${svo.getBankCode()} ${svo.getAcNumber() }</div>
				<div id="crText">잔액</div>
				<div id="currency">￦ ${svo.getCurrency() }</div>
				<p class="currencyKR" id="kr">${svo.getCurrencyKR() }원</p>
			</div>
			
			<div id="btnBox">
				<form method="post" action="${pageContext.request.contextPath }/pages/toAccountTransfer.do">
		    		<input type="hidden" name="acNumber" value="${svo.getAcNumber() }">
		    		<input id="toSendButton" type="submit" value="송금">
		    	</form>
				<form method="post" action="${pageContext.request.contextPath }/bankAccount/pages/toManagement.do">
		    		<input type="hidden" name="acNumber" value="${svo.getAcNumber() }">
		    		<input type="submit" value="관리">
		    	</form>
	    	</div>
    	</div>
    	
    	<!-- 이체 기록 -->
    	
    	<h3>이체기록</h3>
    	<c:forEach var="vo" items="${hList }">
    		<div class="recodeBox cloudBorder">
	    		<div class="trName">${vo.getTrName() }</div>
	    		<div class="trAmount${vo.getCurrency().substring(0, 1)}c">${vo.getCurrency() } 원</div>
	    		<div class="currencyKR trInfoKr">${vo.getCurrencyKR() }원</div>
	    		<div class="trTime">${vo.getTrTime().substring(0, vo.getTrTime().lastIndexOf('.')) }</div>
	    		<div class="trAcNum">${vo.getTrAcNumber() }</div>
	    		
    		</div>
    	
    	</c:forEach>
    	
    	<h5>페이지 버튼</h5>
    	<div id="pageButtonBox">
	    	<form method="post" action="${pageContext.request.contextPath }/bankAccount/pages/toAccountInfo.do">
	    			<input id="infoClickAcNum" type="hidden" name="acNumber" value="${svo.getAcNumber() }">
	    			<input type="hidden" name="page" value="${btnStart-1}">
	    			<input id="beforePage" class="pageButton" type="submit" value="<<">
			 </form>
	    	<c:forEach var="i" begin="${btnStart}" end="${btnEnd}">
	    		<form method="post" action="${pageContext.request.contextPath }/bankAccount/pages/toAccountInfo.do">
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
	    	<form method="post" action="${pageContext.request.contextPath }/bankAccount/pages/toAccountInfo.do">
    			<input id="infoClickAcNum" type="hidden" name="acNumber" value="${svo.getAcNumber() }">
    			<input type="hidden" name="page" value="${btnEnd+1}">
    			<input id="nextPage" class="pageButton" type="submit" value=">>">
			 </form>
		</div>
	</div>
</body>
</html>