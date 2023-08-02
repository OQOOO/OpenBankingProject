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
	#infoTypeBox {
		display: grid;
	  	text-align: center;
		grid-template-columns: 
		100px 10px 
		500px 10px
		100px 30px 
		50px 10px 
		190px;
		grid-template-rows: 30px;
	}
	.post {
		display: grid;
	  	text-align: center;
		grid-template-columns: 
		100px 10px 
		500px 10px
		100px 30px 
		50px 10px 
		190px;
		grid-template-rows: 60px;
	}
	
	.post div {
		border-bottom: solid 1px gray;
		padding-top: 15px;
		padding-bottom: 5px;
	}
	#infoTypeBox div {
		-border: solid 1px;
	}
	
	.insertButton {
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
	    <h1>게시판 관리</h1>
	    <div class="buttonsDivMid" style="display: flex;">
			<form action="${pageContext.request.contextPath }/pages/IndexMain.do" method="post">
				<input class="insertButton" type="submit" value="뒤로">
			</form>
			<form action="${pageContext.request.contextPath }/pages/toInsertBoard.do" method="post">
				<input class="insertButton" type="submit" value="새 글 작성">
			</form>
		</div>
		
		<div id="infoTypeBox">
			<div>No</div>
			<div></div>
			<div style="text-align: left;">제목</div>
			<div></div>
			<div>작성자</div>
			<div></div>
			<div>조회</div>
			<div></div>
			<div>작성시간</div>
		</div>
		
		<div class="midLine"></div>
		<c:forEach  var="post" items="${postList}">
			<div class="post">
				<div class="postNum">
					${post.postId}
				</div>
				<div></div>	
				<div class="title" style="text-align: left;">
					<a href="${pageContext.request.contextPath }/admin/pages/toPost.do?seq=${post.postId}"> ${post.title}</a>
				</div>
				<div></div>
				<div>
					${post.userId}
				</div>
				<div></div>
				<div>
					${post.views}
				</div>
				<div></div>

				<div class="time">
					${post.createTime}
				</div>

				
			</div>
		</c:forEach>
		<h4>pages</h4>
		<div id="pageButtonBox">
	    	<form method="post" action="${pageContext.request.contextPath }/admin/pages/toBoard.do">
	    			<input id="infoClickAcNum" type="hidden" name="acNumber" value="${svo.getAcNumber() }">
	    			<input type="hidden" name="page" value="${btnStart-1}">
	    			<input id="beforePage" class="pageButton" type="submit" value="<<">
			 </form>
	    	<c:forEach var="i" begin="${btnStart}" end="${btnEnd}">
	    		<form method="post" action="${pageContext.request.contextPath }/admin/pages/toBoard.do">
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
	    	<form method="post" action="${pageContext.request.contextPath }/admin/pages/toBoard.do">
    			<input id="infoClickAcNum" type="hidden" name="acNumber" value="${svo.getAcNumber() }">
    			<input type="hidden" name="page" value="${btnEnd+1}">
    			<input id="nextPage" class="pageButton" type="submit" value=">>">
			 </form>
		</div>
	</div>
</body>
</html>