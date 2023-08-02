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
	.recodeBox {
		position:relative;
		width: 500px;
		height: 90px;
		margin: 5px;
		padding: 0px 5px;
		border-radius: 5px;
	}
	.recodeBox:hover {
		background-color: #E6F2FF;
	}
	.trName {
		font-size: 20px;
		display: inline-block;
	}
	.acNumber {
		
	}
	
	#container1 {
		display: grid;
  		grid-template-columns: 400px 700px;
		grid-template-rows: 300px;
	}
	
	#myAccountInfoDiv {
		text-align: right;
		padding: 10px;
		padding-top: 20px;
	}
	
	#trAccountInfoDiv {
		border-left: solid gray 1px;
		padding: 10px;
		
		padding-top: 40px;
	}
	
		#trAccountInfoDiv input[type="number"], input[type="text"]{
			width: 350px;
		}
	
	#container1 div {
		-border: solid 1px black;
	}
	
	#acTitle {
		font-size: 30px;
		margin-bottom: 0px;
	}
	
	#to {
		position:relative;
		bottom: 5px;
		right: 5px;
		font-size: 12px;
		margin: 0px;
		margin-bottom: 14px;
	}
	
	#can {
		position:relative;
		top: 5px;
		right: 5px;
		font-size: 14px;
		margin: 0px;
	}

	#acCurrency {
		font-size: 35px;
		margin: 0px;
	}
	
	.pText {
		margin: 2px;
		font-size: 15px;
	}
	
	.errerMessage {
		height: 1px;
		z-index: -1;
	}
	
	#trInput {
		position: relative;
		top: 30px;
	}
	#korean {
		height:1px;
		z-index: -2;
	}
	#amountErrer {
		position: relative;
		bottom: 4px;
	}
</style>
<script>
	function asCurrencyWithKorean(num) {
	  var sNum = num.toString();
	  var korean = [];
	  var digitsCnt = 0;

	  for (var i = 0; i < sNum.length; i++) {
	    if (i % 4 === 0) {
	      switch (digitsCnt) {
	        case 1:
	          korean.push(":만 :");
	          break;
	        case 2:
	          korean.push(":억 :");
	          break;
	        case 3:
	          korean.push(":조 :");
	          break;
	        case 4:
	          korean.push(":경 :");
	          break;
	        default:
	          break;
	      }
	      digitsCnt++;
	    }

	    korean.push(sNum.charAt(sNum.length - 1 - i));
	  }
	  korean.reverse();

	  var result = [];
	  var kArr = korean.join("").split(":");

	  for (var i = 0; i < kArr.length; i++) {
	    if (i % 2 === 0) { // 홀수일 때 항상 숫자임
	      var chNum = parseInt(kArr[i]);
	      var chStr = chNum.toString();
	      kArr[i] = chStr;
	    }

	    if (i !== kArr.length) {
	      result.push(":" + kArr[i]);
	    }
	  }

	  var r = result.join("");
	  
	  r = r.replace(":0:만", "");

	  r = r.replace(":0:억", "");

	  r = r.replace(":0:조", "");

	  r = r.replace(":0", "");

	  r = r.replace(/:/g, "");

	  if (r === "") {
	    r = "0";
	  }

	  return r;
	}
	

	function setAcNumber(acNumber) {
		document.getElementById("acInput").value = acNumber;
		document.getElementById("acNumErrer").textContent = "";
	}
	
	window.onload = function(){
		document.getElementById('amountInput').addEventListener('input', amountInputChange);
		document.getElementById('acInput').addEventListener('input', accountInputChange);
	}
	
	function amountInputChange() {

		var inputValue = document.getElementById('amountInput').value;
		
		var maxLength = 18; // long 형의 최대 길이

		if (inputValue.length > maxLength) {
		   	inputValue = inputValue.slice(0, maxLength); // 값의 길이를 최대 길이로 조정
		   	document.getElementById('amountInput').value = inputValue; // 입력 필드에 조정된 값 설정
		}
		
		if (inputValue.length > 0 && inputValue.charAt(0) === '0') {
		    inputValue = inputValue.slice(1);
		    document.getElementById('amountInput').value = inputValue;
		  }
		
		
		var currencyKR = asCurrencyWithKorean(inputValue);
		if(currencyKR == 'NaN') {
			currencyKR = 0;
		}
		document.getElementById("korean").textContent = currencyKR + '원';
		document.getElementById("amountErrer").textContent = "";
		
		
		
	}
	
	function accountInputChange() {
		document.getElementById("acNumErrer").textContent = "";
	} 
	
	
	
	
	
	
</script>
</head>
<body>
	<c:import url="/include/Header.jsp" />
	<div id="main">
		<h1>계좌 송금</h1>
		<div id="container1">
		
			<!-- 내정보 -->
		
			<div id="myAccountInfoDiv">
				<p id="acTitle">${myAcVo.getAcName() }</p>
				<p id="to">에서 송금</p>
				<p id="can">송금 가능 금액</p>
				<p id="acCurrency">￦ ${myAcVo.getCurrency() }</p>
				<p id="kr" class="currencyKR">${myAcVo.getCurrencyKR() }원</p>
			</div>
			
			<!-- 계좌정보 입력 -->
			
			<div id="trAccountInfoDiv">
				<form method="post" action="${pageContext.request.contextPath }/bankAccount/accountTransfer.do">
					<input type="hidden" name="giveAcNumber" value="${myAcVo.getAcNumber() }">
					<p class="pText">계좌번호</p>
					<input id="acInput" type="text" name="takeAcNumber" value="" placeholder="송금 계좌번호"><br>
					<p id="acNumErrer" class="errerMessage">${acNumErrer }</p>
					<br>
					<p class="pText">송금 금액</p>
					<input id="amountInput" type="number" name="amount" placeholder="10,000">
					<p id="korean" class=currencyKR></p>
					<p id="amountErrer" class="errerMessage">${amountErrer }</p>
					
					<input id="trInput" type="submit" value="송금하기">
				</form>
			</div>
		</div>
		
		<!-- 다른 계좌 정보 -->
		
		<h1>내 계좌</h1>
		<c:forEach var="vo" items="${ acList }">
			<c:if test="${myAcVo.getAcNumber() != vo.getAcNumber() }">
				<div class="recodeBox cloudBorder" onclick="setAcNumber('${vo.getAcNumber()}')">
					<div class="trName">${vo.getAcName() }</div>
					<div class="acNumber">${vo.getBankCode()} ${vo.getAcNumber() }</div>
					<br>
				</div>
			</c:if>
		</c:forEach>
	</div>
</body>
</html>