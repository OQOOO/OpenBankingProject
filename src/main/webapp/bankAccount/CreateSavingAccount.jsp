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
			grid-template-rows: 100px 45px 100px 60px 100px;
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
	</script>
</head>
<body>
	<c:import url="/include/Header.jsp" />
	<div id="main">
		<h1>예금계좌 개설</h1>
		<form action="${pageContext.request.contextPath }/bankAccount/createSavingAccount.do" method="post">
			<div id="container">
			
				<div class="textDiv">
					계좌 이름
				</div>
				<div class="inputDiv">
					<input type="text" name="accountName" value="${requestScope.acName}">
					<p class="errerMessage">${requestScope.acNameErrer}</p> <br>
				</div>
				
				<div class="textDiv">
					계좌 비밀번호
				</div>
				<div class="inputDiv">
					<input style="width:70px;"  type="password" name="accountPw" 
					oninput="checkNumberLength(this)" value="${requestScope.acPw}"><br>
					
				</div>
				
				<div class="textDiv">
					비밀번호 확인
				</div>
				<div class="inputDiv">
					<input style="width:70px;"  type="password" name="accountPwCheck" 
					oninput="checkNumberLength(this)" value="${requestScope.accountPwCheck}"><br>
					<p class="errerMessage">${requestScope.acPwErrer}</p> <br>
				</div>
				
				<div class="textDiv">
					최초 입금액
				</div>
				<div class="inputDiv">
					<input type="number" name="diposit" value="${requestScope.diposit}">
					<p class="errerMessage">${requestScope.dipositErrer}</p>
				</div>
				<div class="textDiv">
					입금계좌번호
				</div>
				<div class="inputDiv">
					<select style="width:70px; height:30px;" name="bankCode">
						<option value="a">A</option>
						<option value="b">B</option>
					</select>
					<input style="width:170px;" type="number" name="phoneNum3" oninput="checkNumberLength(this)" value="${requestScope.phoneNum3}" placeholder="계좌번호"><br>
					<p class="errerMessage">${requestScope.phoneNumErrer}</p> <br>
				</div>
				<input type="submit" value="계좌개설">
			</div>
		</form>
		<form action="${pageContext.request.contextPath }/pages/toBankAccountSelection.do" method="post">
			<input type="submit" value="뒤로">
		</form>
	</div>

</body>
</html>