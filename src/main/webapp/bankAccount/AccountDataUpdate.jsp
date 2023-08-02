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
	#container {
			display: grid;
		    text-align: center;
			grid-template-columns: 150px 400px;
			grid-template-rows: 100px 100px 45px 100px;
	}
	#container div {
	
		/*background-color:powderblue;*/
		padding-top: 10px;
	}
	.textDiv {
		text-align: right;
		padding-right: 15px;
		font-weight: bold;
	}
	
	.inputDiv {
		border-left: solid 1px gray;
		text-align: left;
		padding-left: 15px;
	}
	.inputDiv input {
		height: 25px;
		width: 300px;
	}
	.pNumInput {
		width: 100px;
	}
</style>
	<script>
		function checkNumberLength(input) {
		  var maxLength = 4; // 최대 자릿수 제한
		  var value = input.value;
		
		  if (value.length > maxLength) {
		    input.value = value.slice(0, maxLength); // 최대 자릿수까지 잘라냄
		  }
		}
		
		window.setTimeout(function() {
	      var success = '${success}'; // success 어트리뷰트 값 가져오기
	  
	      if (success === "1") {
	        alert("정보 변경에 성공했습니다."); // 원하는 메시지를 표시하는 코드로 변경하세요
	      }
	    }, 50);
	</script>
</head>
<body>
	<c:import url="/include/Header.jsp" />
	<div id="main">
		<h1>계좌정보 변경</h1>
		${acNumber }
		<form action="${pageContext.request.contextPath }/bankAccount/accountDataUpdate.do" method="post">
			<div id="container">
				<div class="textDiv">
					계좌번호
				</div>
				<div class="inputDiv">
					<input type="hidden" name="acNumber" value="${vo.getAcNumber()}">
					${vo.getAcNumber() }
				</div>
			
				<div class="textDiv">
					계좌 이름
				</div>
				<div class="inputDiv">
					<input type="hidden" name="bfAcName" value="${vo.getAcName()}">
					<input type="text" name="accountName" value="${vo.getAcName()}">
					<p class="errerMessage">${requestScope.acNameErrer}</p> <br>
				</div>
				
				<div class="textDiv">
					계좌 비밀번호
				</div>
				<div class="inputDiv">
					<input style="width:70px;"  type="password" name="accountPw" 
					oninput="checkNumberLength(this)" value="${vo.getAcPassword()}"><br>
					
				</div>
				
				<div class="textDiv">
					비밀번호 확인
				</div>
				<div class="inputDiv">
					<input style="width:70px;"  type="password" name="accountPwCheck" 
					oninput="checkNumberLength(this)" value="${vo.getAcPassword()}"><br>
					<p class="errerMessage">${requestScope.acPwErrer}</p> <br>
				</div>
				
				<input type="submit" value="정보 변경">
			</div>
		</form>
		<form action="${pageContext.request.contextPath }/bankAccount/pages/toManagement.do" method="post">
			<input type="hidden" name="acNumber" value="${vo.getAcNumber() }">
			<input type="submit" value="뒤로">
		</form>
	</div>
</body>
</html>