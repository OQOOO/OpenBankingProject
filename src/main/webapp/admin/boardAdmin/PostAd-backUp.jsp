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
	#postTitle {
		
	}
	#postContents {
		width: 700px;
		min-height: 100px;
		text-align: left;
	}
	
	input[type="text"].commentInput {
		position:relative;
		margin: 5px;
		width: 700px;
		height: 70px;
		text-align: left;
		vertical-align: top;
	}
	
	.commentDiv {
		display: grid;
	  	text-align: center;
		grid-template-columns: 
		10px
		100px 10px
		530px 20px
		100px 20px
		20px;
		grid-template-rows: 60px;
		border-top: solid 1px gray;
		transition: background-color 0.3s ease;
		align-items: center;
		justify-content: center;
	}
	.commentDiv:hover {
		background-color: #E6F2FF;
	}
	
	.recommentDiv {
		position: relative;
		left: 25px;
		display: grid;
	  	text-align: center;
		grid-template-columns: 
		10px
		100px 10px
		530px 20px
		100px 20px
		20px;

		grid-template-rows: 60px;
		border-top: solid 1px gray;
		align-items: center;
		justify-content: center;
	}
	
	input[type="submit"].delButton {
		width: 20px;
		height: 20px;
		padding: 0px;
	}
	
	.reDepth2 {
		left: 50px;
	}
	.reDepth3 {
		left: 75px;
	}
	.reDepth4 {
		left: 100px;
	}
	.reDepth5 {
		left: 125px;
	}
	.reDepth6 {
		left: 150px;
	}
	.reDepth7 {
		left: 175px;
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
		<div id="postTitle">
			<h1>${vo.getTitle()}</h1>
		</div>
		<div class="midLine"></div>
		<div id="postContents">
			${vo.getContent() }
		</div>
			<form class="postDelBtn" action="${pageContext.request.contextPath}/board/delPost.do" method="post">
				<input type="hidden" name="postId" value="${vo.getPostId() }">
				<input type="hidden" name="cid" value="${vo.getCommentId() }">
				<input class="insertButton" id="backButton" type="submit" value="개시물 삭제"/><br>
			</form>
			<form class="postDelBtn" action="${pageContext.request.contextPath}/board/pages/toUpdateBoard.do" method="post">
				<input type="hidden" name="postId" value="${vo.getPostId() }">
				<input class="insertButton" id="backButton" type="submit" value="수정하기"/><br>
			</form>

		<div class="midLine"></div>
		
		
		
		<form action="${pageContext.request.contextPath }/board/insertComment.do" method="post">
			<input class="commentInput" type="text" name="contents">
			<input type="hidden" name="postId" value="${vo.getPostId() }">
			<input style="margin:5px" class="insertButton" id="backButton" type="submit" value="댓글작성"/><br>
		</form>
		
		<c:forEach var="vo" items="${commentList }">
			<div class="commentDiv" onclick="viewRecommentInput(${vo.getCommentId()})">
				<div>${vo.getCommentId()}</div>
				<div>${vo.getUserId() }</div>
				<div></div>
				<div style="text-align: left">${vo.getContent() }</div>
				<div></div>
				<div style="text-align:left">${vo.getCreateTime() }</div>

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
				<input class="commentInput" type="text" name="contents" placeholder="${vo.getCommentId() }">
				<input type="hidden" name="postId" value="${vo.getPostId() }">
				<input type="hidden" name="commentId" value="${vo.getCommentId() }">
				<input class="insertButton" id="backButton" type="submit" value="작성"/><br>
			</form>
			
			<c:forEach var="rvo" items="${reCommentList }">
				<c:if test="${vo.getCommentId() eq rvo.getCommentId() }">
					<div class="recommentDiv reDepth${rvo.getDepth() }" onclick="viewRecommentInput2(${rvo.getReCommentId()})">
						<div>${rvo.getReCommentId() }</div>
						<div>${rvo.getUserId() }</div>
						<div></div>
						<div style="text-align: left">${rvo.getContent() }</div>
						<div></div>
						<div style="text-align:left">${rvo.getCreateTime() }</div>
						<div></div>

						<form class="reCommentDelBtn" action="${pageContext.request.contextPath}/board/delReComment.do" method="post">
							<input type="hidden" name="postId" value="${vo.getPostId() }">
							<input type="hidden" name="reId" value="${rvo.getReCommentId() }">
							<input class="delButton" type="submit" value="X"/><br>
						</form>

					</div>
				<form class="reCommentInput2" id="inputReRe${rvo.getReCommentId()}" action="${pageContext.request.contextPath}/board/insertReComment.do" method="post" style="display: none;">
					<input class="commentInput" type="text" name="contents" placeholder="${rvo.getReCommentId() }">
					<input type="hidden" name="parentReId" value="${rvo.getReCommentId() }">
					<input type="hidden" name="parentDepth" value="${rvo.getDepth() }">
					<input type="hidden" name="postId" value="${vo.getPostId() }">
					<input type="hidden" name="commentId" value="${vo.getCommentId() }">
					<input class="insertButton" id="backButton" type="submit" value="답글"/><br>
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