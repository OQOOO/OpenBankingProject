<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>CloudBank</title>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">

<style>
	.accountInfo {
		background: linear-gradient(to right, 000, #F2F2F2);
		-border:2px solid black;
		width: 500px;
		height : 100px;
		margin: 10px;
		padding: 5px;
		position: relative;
	  	transition: background-color 0.3s ease;
	  	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
	  	border-radius: 10px;
	}
	
	.accountInfo:hover {
	  background-color: #E6F2FF;
	}
	
	.acTitle {
		font-size: 20px;
	}
	
	.sendBtn {
		position:absolute;
		bottom:10px;
		right:10px;
		border-radius: 10px;
	}
	
	.currency {
		font-size: 30px;
		text-align: right;
		margin-right: 110px
	}
	.ck {
		text-align: right;
		margin-right: 110px
	}

	#total {
		font-size: 50px;
		margin: 10px;
	}
	
	#createAcBtn {
		position: relative;
		left: 197px;
		margin: 0px;
	}
	
	#userName {
		font-size:20px;
		font-weight: bold;
		margin: 0px;
	}
	
	#totalCurrencyKR {
		margin-bottom: 30px;
	}
	
	#doughnut-chart {
		width: 100px;
		height: 100px;
	}
	
	#bankCode {
		position:relative;
		bottom: 65px;
		font-size: 12px;
	}
	
	.bank-JHBank {
		background-color: #FFECB5;
	}
	.bank-JOYBank {
		background-color: #F4B691;
	}
	.bank-CYBank {
		background-color: #BCC5CE;
	}
</style>
<script>
	// 업데이트 확인
	document.addEventListener('DOMContentLoaded', function() {
		const isUpdate = '${isDataUpdate}';
	
		if (isUpdate == "delDeposit") {
			
			Swal.fire({
			    icon: 'success', // Alert 타입
			    title: '적금 해지 완료',         // Alert 제목
			    text: '적금 해지에 성공했습니다.',  // Alert 내용
			});
		}
		
		if (isUpdate == "transfer") {
			
			Swal.fire({
			    icon: 'success', // Alert 타입
			    title: '송금 완료',         // Alert 제목
			    text: '계좌 송금에 성공했습니다.',  // Alert 내용
			});
		}
	});

	// 예금계좌 확인
	function submitFormSaving(acNumber, acPw) {
	    document.getElementById("infoClickAcNum").value = acNumber;
	    (async () => {
	        const { value: inputPw } = await Swal.fire({
	            title: '비밀번호를 입력해 주세요.',
	            text: '4자리 숫자.',
	            input: 'password',
	            inputPlaceholder: '비밀번호'
	            
	        })

	        // 이후 처리되는 내용.
	        if (inputPw == acPw) {
	            document.getElementById("infoClickForm").submit();
	        } else {
	        	Swal.fire({
		            icon: 'warning', // Alert 타입
		            title: '잘못된 비밀번호입니다.',         // Alert 제목
		            text: '다시 시도해 주세요.',  // Alert 내용
		        });
	        }
	    })()
	    
  	}
	
	// 적금계좌 확인
	function submitFormDeposit(acNumber, acPw) {
	    document.getElementById("infoClickAcNumDeposit").value = acNumber;
	    (async () => {
	        const { value: inputPw } = await Swal.fire({
	            title: '비밀번호를 입력해 주세요.',
	            text: '4자리 숫자.',
	            input: 'text',
	            inputPlaceholder: '비밀번호'
	            
	        })

	        // 이후 처리되는 내용.
	        if (inputPw == acPw) {
	            document.getElementById("infoClickFormDeposit").submit();
	        } else {
	        	Swal.fire({
		            icon: 'warning', // Alert 타입
		            title: '잘못된 비밀번호입니다.',         // Alert 제목
		            text: '다시 시도해 주세요.',  // Alert 내용
		        });
	        }
	    })()
	    
  	}
	
	// 차트
	document.addEventListener('DOMContentLoaded', function() {
		const saving = Number('${totalSaving }')
		const deposit = Number('${totalDeposit }')
		
		
		new Chart(document.getElementById("doughnut-chart"), {
		    type: 'doughnut',
		    data: {
		      labels: ["예금", "적금"],
		      datasets: [
		        {
		          label: "Population (millions)",
		          backgroundColor: ["#73B2D9", "#048ABF"],
		          data: [saving, deposit],
		        }
		      ]
		    },
		    options: {
		      title: {
		        display: true,
		        text: '자산 포트폴리오',
		      },
		      cutoutPercentage: 60
		    }
		});
	});
	

	function sendBtnClick() {
	  // 클릭 이벤트 전파(stopPropagation)를 중단하여 div1의 클릭 이벤트가 발생하지 않도록 함
	  
	  event.stopPropagation();
	}
	
</script>
</head>
<body>
	<c:import url="/include/Header.jsp" />
    <div id="main">
	    <h1>${uvo.getName() }님의 자산</h1>
	    <div style="width:500px; height:500px">
	   		<canvas id="doughnut-chart" width="100" height="100"></canvas>
	    </div>

	    <p id="total">￦ ${totalBalance}</p>
	    <p id="totalCurrencyKR" class="currencyKR">${totalBalanceKR}원</p>
	    
	    
	    <h1 style="margin:0px">내 계좌</h1>
	    <form id="createAcBtn" method="post" action="${pageContext.request.contextPath }/pages/toBankAccountSelection.do">
	    	<input style="margin-top:0px;" type="submit" value="계좌개설">
	    </form>
	    
	    <!-- 예금계좌 목록 -->
	    <h4>예금</h4>
	    <c:forEach var="ac" items="${savingAcList}">
		    <div class="accountInfo bank-${ac.getBankCode() }" onclick="submitFormSaving('${ac.acNumber}', '${ac.getAcPassword() }')">
		    	<div class="acTitle">${ac.acName}</div>
		    	<div class="currency">${ac.currency }원</div>
		    	<p class="currencyKR ck">${ac.currencyKR }원</p>
		    	<form method="post" action="${pageContext.request.contextPath }/pages/toAccountTransfer.do">
		    		<input type="hidden" name="acNumber" value="${ac.acNumber }">
		    		<input style="width:67px; height:65px;" class="sendBtn" type="submit" onclick="sendBtnClick()" value="송금">
		    	</form>
		    	<div id="bankCode">${ac.getBankCode() }</div>
		    </div>
	    </c:forEach>
	    <c:if test="${empty savingAcList}">
	    	예금 계좌가 없습니다.
	    	<br>
	    	<br>
	    </c:if>
	    
	    <!-- 적금계좌 목록 -->
	    <h4>적금</h4>
	    <c:forEach var="ac" items="${depositAcList}">
		    <div class="accountInfo" onclick="submitFormDeposit('${ac.acNumber}', '${ac.getAcPassword() }')">
		    	<div class="acTitle">${ac.acName}</div>
		    	<div class="currency">${ac.currency }원</div>
		    	<p class="currencyKR ck">${ac.currencyKR }원</p>
		    </div>
	    </c:forEach>
	    <c:if test="${empty depositAcList}">
	    	적금 계좌가 없습니다.
	    	<br>
	    	<br>
	    </c:if>
	    
	    <!-- hidden -->
	    
    	<form id="infoClickForm" style="display: none" method="post" action="${pageContext.request.contextPath }/bankAccount/pages/toAccountInfo.do">
    		<input id="infoClickAcNum" type="hidden" name="acNumber" value="">
    		<input type="hidden" name="page" value="1">
    		<input type="submit" value="송금">
    	</form>
    	
    	<form id="infoClickFormDeposit" style="display: none" method="post" action="${pageContext.request.contextPath }/bankAccount/pages/toDepositAcInfo.do">
    		<input id="infoClickAcNumDeposit" type="hidden" name="acNumber" value="">
    		<input type="hidden" name="page" value="1">
    		<input type="submit" value="송금">
    	</form>
  
    </div>
    <hr style="margin-top: 100px">
    <footer>
    	<a style="margin-left: 50px; text-decoration: none; color:gray; font-size: 14px;" href="https://github.com/OQOOO" >깃허브</a>
    	<a style="margin-left: 50px; text-decoration: none; color:gray; font-size: 14px;" href="${pageContext.request.contextPath }/toGame.sp" >쉬어가기</a>
    </footer>
</body>
</html>
