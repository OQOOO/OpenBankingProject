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
	
	#postContainer {
		width: 1000px;
		text-align: left;
		background-color: white;
		border-radius: 5px;
		margin: 5px;
		margin-top: 30px;
	}
	
	input[type="text"].commentInput {
		position:relative;
		margin: 5px;
		width: 900px;
		height: 70px;
		text-align: left;
		vertical-align: top;
		border-radius: 5px;
	}
	input[type="submit"].commentInsertBtn {
		width: 70px;
		height: 70px;
		margin: 5px;
	}
	
	.commentDiv {
		position: relative;
		width: 1000px;
		background-color:#EFF6FA;
		margin: 5px;
		border-radius: 5px;
		min-height: 120px;
	}
	.commentDiv:hover {
		background-color: #E6F2FF;
	}
	
	.recommentDiv {
		position: relative;
		width: 1000px;
		background-color:#E3E1E0;
		margin: 5px;
		border-radius: 5px;
		min-height: 120px;
	}
	
	input[type="submit"].delButton {
		width: 20px;
		height: 20px;
		padding: 0px;
	}
	
	.reDepth1 {
		background-color:#d6e9f3;
		left: 10px;
		width: 980px;
	}
	
	.reDepth2 {
		background-color:#c3dfed;
		left: 10px;
		width: 980px;

	}
	.reDepth3 {
		background-color:#afd4e8;
		left: 10px;
		width: 980px;

	}
	.reDepth4 {
		background-color:#9bcae2;
		left: 10px;
		width: 980px;

	}
	.reDepth5 {
		background-color:#87c0dc;
		left: 10px;
		width: 980px;
	}
	.reDepth6 {
		background-color:#73b5d7;
		left: 10px;
		width: 980px;
	}
	.reDepth7 {
		background-color:#60abd1;
		left: 10px;
		width: 980px;
	}

	#delAndUpdate {
		text-align: right;
		height: 70px;
	}
	
	#delAndUpdate form {
		margin: 0px;
		display: inline-block;
	}
	
	#delAndUpdate input {
		width: 100px;
		margin-bottom: 5px;
	}
	
	#postContent {
		min-height: 150px;
		margin:10px;
	}
	#postTitle {
		position: relative;
		height: 80px;
	}
	#pUid {
		position: absolute;
		right: 10px;
		top: 40px;
	}
	#postCreateTime {
		position:absolute;
		font-size: 14px;
		right: 10px;
		top: 60px;
	}
	#pTitleContent {
		position: absolute;
		left: 20px;
		top: 30px;
		margin: 0px;
	}
	
	#postView {
		position: absolute;
		bottom: 3px;
		font-size: 14px;
		left: 10px;
	}
	
	#postDelBtn {
		margin-right: 5px;
	}
	
	input[type="submit"].delButton {
		position: absolute;
		bottom: 10px;
		right: 10px;
		border-radius: 0px;
	}
	
	.commentUid {
		position: absolute;
		top: 10px;
		left: 10px;
		font-weight: bold;
	}
	.commentContent {
		position: relative;
		top: 35px;
		left: 10px;
		max-width: 700px;
		overflow: visible;
		margin-bottom: 50px;
	}
	
	.commentCreateTime {
		position: absolute;
		bottom: 10px;
		right: 40px;
	}
	
	.commentInput {
		background: white;
	}
	
	input[type="text"].commentInput2 {
		margin-right: 0px;
		margin-left: 50px;
		background-color: white;
		width: 850px;
		height: 65px;
		border-radius: 10px;
		border: 2px gray solid;
		
	}
	.reCommentInputDiv {
		width: 1000px;
		margin-bottom: 50px;
		padding: 0px;
		position: relative;
		left: 10px;
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
<!-- 		<div class="midLine"></div> -->

		<!-- 메인페이지 -->

		<div id="postContainer" class="cloudBorder">
			<div id="postTitle">
				<h3 id="pTitleContent">${vo.getTitle()}</h3>
				<div id="postView">조회: ${vo.views }</div>
				<div id="pUid">작성자: ${vo.userId }</div>
				<div id="postCreateTime">${vo.createTime }</div>
			</div>
			<hr style="margin:5px;">
			<div id="postContent">
				${vo.getContent() }
			</div>
			<hr style="margin:5px;">

				<div id="delAndUpdate">
					<form class="postUpBtn" action="${pageContext.request.contextPath}/board/delPost.do" method="post">
						<input type="hidden" name="postId" value="${vo.getPostId() }">
						<input type="hidden" name="cid" value="${vo.getCommentId() }">
						<input class="insertButton" id="backButton" type="submit" value="개시물 삭제"/><br>
					</form>
				</div>

		</div>
		
		<!-- 댓글 작성 -->
		
		<form action="${pageContext.request.contextPath }/board/insertComment.do" method="post">
			<div class="reCommentInputDiv">
				<input class="commentInput2" type="text" name="contents">
				<input type="hidden" name="postId" value="${vo.getPostId() }">
				<input style="margin:5px" class="insertButton commentInsertBtn" id="commentInsertBtn" type="submit" value="댓글작성"/><br>
			</div>
		</form>
		
		<!-- 댓글 목록 -->
		
		<c:forEach var="vo" items="${commentList }">
			<div class="commentDiv cloudBorder" onclick="viewRecommentInput(${vo.getCommentId()})">
				<div class="commentUid">${vo.getUserId() }</div>
				<div class="commentContent" style="text-align: left">${vo.getContent() }</div>
				<div class="commentCreateTime" style="text-align:left">${vo.getCreateTime() }</div>

				<div></div>
				<div style="text-align: right">
	
						<form class="commentDelBtn" action="${pageContext.request.contextPath}/board/delComment.do" method="post">
							<input type="hidden" name="postId" value="${vo.getPostId() }">
							<input type="hidden" name="cid" value="${vo.getCommentId() }">
							<input class="delButton" type="submit" value="X"/><br>
						</form>

				</div>
			</div>
			<form class="reCommentInput" id="inputRe${vo.getCommentId()}" action="${pageContext.request.contextPath}/board/insertReComment.do" method="post" style="display: none;">
				<div class="reCommentInputDiv">				
					<input class="commentInput2" type="text" name="contents" placeholder="답글 작성">
					<input type="hidden" name="postId" value="${vo.getPostId() }">
					<input type="hidden" name="commentId" value="${vo.getCommentId() }">				
					<input class="insertButton commentInsertBtn" id="backButton" type="submit" value="답글"/><br>
				</div>
			</form>
			
			<!-- 대댓글 목록... 일종의 기술적 부채임 -->
			
			<c:forEach var="rvo" items="${reCommentList }">
				<c:if test="${vo.getCommentId() eq rvo.getCommentId() }">
					<div class="recommentDiv reDepth${rvo.getDepth() }" onclick="viewRecommentInput2(${rvo.getReCommentId()})">
						<div class="commentUid">${rvo.getUserId() }</div>
						<div class="commentContent">${rvo.getContent() }</div>
						<div class="commentCreateTime">${rvo.getCreateTime() }</div>


							<form class="reCommentDelBtn" action="${pageContext.request.contextPath}/board/delReComment.do" method="post">
								<input type="hidden" name="postId" value="${vo.getPostId() }">
								<input type="hidden" name="reId" value="${rvo.getReCommentId() }">
								<input class="delButton" type="submit" value="X"/><br>
							</form>
	
					</div>
				<form class="reCommentInput2" id="inputReRe${rvo.getReCommentId()}" action="${pageContext.request.contextPath}/board/insertReComment.do" method="post" style="display: none;">
					<div class="reCommentInputDiv">
						<input class="commentInput2 reDepth${rvo.getDepth() }" type="text" name="contents" placeholder="답글 작성">
						<input type="hidden" name="parentReId" value="${rvo.getReCommentId() }">
						<input type="hidden" name="parentDepth" value="${rvo.getDepth() }">
						<input type="hidden" name="postId" value="${vo.getPostId() }">
						<input type="hidden" name="commentId" value="${vo.getCommentId() }">
						<input class="insertButton commentInsertBtn" id="backButton" type="submit" value="답글"/><br>
					</div>
				</form>
				
				</c:if>
				
			</c:forEach>
			
		</c:forEach>
		
	</div>
	<script>
		let bf = "";
		function viewRecommentInput(cid) {
			
			const elements = document.getElementsByClassName('reCommentInput');
			const elements2 = document.getElementsByClassName('reCommentInput2');
		    for (let i = 0; i < elements.length; i++) {
		      elements[i].style.display = 'none';
		    }
		    for (let i = 0; i < elements2.length; i++) {
			      elements2[i].style.display = 'none';
			    }
			
		    
			const viewInput = document.getElementById('inputRe' + cid);
			console.log(viewInput);
			if (bf !== viewInput) {
				viewInput.style.display = 'block';
				bf=viewInput;
			} else {
				bf = "-1"
			}
			
		}
		
		function viewRecommentInput2(cid) {
			const elements = document.getElementsByClassName('reCommentInput');
			const elements2 = document.getElementsByClassName('reCommentInput2');
			for (let i = 0; i < elements.length; i++) {
			      elements[i].style.display = 'none';
			}
		    for (let i = 0; i < elements2.length; i++) {
		      elements2[i].style.display = 'none';
		    }
			
		    
			const viewInput = document.getElementById('inputReRe' + cid);
			console.log(viewInput);
			
			if (bf !== viewInput) {
				viewInput.style.display = 'block';
				bf=viewInput;
			} else {
				bf = "-1"
			}
			
		}
	</script>
</body>
</html>