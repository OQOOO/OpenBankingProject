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
	#infoContainer {
		display: grid;
	    text-align: center;
		grid-template-columns: 150px 300px;
		grid-template-rows: 50px 50px 50px 50px 50px 50px 50px 50px;
		font-weight: bold;
	}
	.who {
		text-align: right;
		padding:15px;
	}
	.infoDiv {
		border-left: solid gray 1px;
		text-align: left;
		padding: 15px;
	}
</style>
</head>
<body>
	<c:import url="/include/Header.jsp" />
    
    <div id="main">
		<h1>마이페이지</h1>

		<div id="infoContainer">

			<div class="who">아이디 </div><div class="infoDiv"> ${vo.id}</div>
			<div class="who">비밀번호 </div><div class="infoDiv"> ${vo.password }</div>
			<div class="who">이름 </div><div class="infoDiv"> ${vo.name }</div>
			<div class="who">생년월일 </div><div class="infoDiv"> ${vo.birthDate }</div>
			<div class="who">이메일 </div><div class="infoDiv"> ${vo.email }</div>
			<div class="who">전화번호 </div><div class="infoDiv"> ${vo.phoneNum }</div>
			<div class="who">주소 </div><div class="infoDiv"> ${vo.address }</div>
			<div class="who">계정연동 </div><div class="infoDiv"> ${vo.accountLink }</div>
			
		</div>
		
		<form method="post" action="${pageContext.request.contextPath }/pages/toUserInfoUpdate.do">
            <input type="submit" value="내정보 수정">
        </form>
	</div>


</body>
</html>