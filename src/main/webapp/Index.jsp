<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>CloudBank</title>
<!-- <link rel="stylesheet" type="text/css" href="/OpenBanking/css/common.css"> -->
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Maven+Pro&family=Raleway:wght@200&display=swap" rel="stylesheet">
<style>
	body {
	font-family: 'Maven Pro', sans-serif;
	font-family: 'Raleway', sans-serif;
	}
	#subTitle {
		font-size: 25px;
		font-weight: normal;
		color:#595959;
		margin: 10px;
	}
	#title {
		font-size: 70px;
		margin: 10px;
	}
	
	#div1 {
		width: 100px;
		height: 100px;
		
	}
	.whiteDiv {
		position:relative;
		top: 50px;
		width: 150px;
		height: 150px;
		margin: 5px;
		box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
		display: flex;
		align-items: center;
		justify-content: center;
		font-size: 25px;
		font-weight: bold;
		text-align: center;
		
		color:#348ABF;
		transition: background-color 0.3s ease;
	}
	
	.whiteDiv:hover {
		color:white;
		background-color: #348ABF;
	}
	
	#box1 {
		display: flex;
	}
	

</style>
<script type="text/javascript">

Kakao.init('${kakaoKey}'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }
  } 
  
const flag = '${flag}'

	if(flag == "logout") {
		kakaoLogout();
	}
</script>

</head>
<body>
	<c:if test="${not empty sessionScope.uid}">
		<c:if test="${not empty sessionScope.adminRight }">
	  	  	<c:redirect url="admin/pages/toMainOfAdmin.do" />
		</c:if>
		<c:if test="${empty sessionScope.adminRight }">
	  	  	<c:redirect url="pages/IndexMain.do" />
		</c:if>
		
	</c:if>
	<header>
        <div id="headerContainer" class="container">
            <form id="loginForm" method="post" action="${pageContext.request.contextPath }/pages/toLogin.do">
                <input id="loginbutton" class="headerMenus" type="submit" value="로그인">
            </form>
        </div>
    </header>
    <div id="main">
    	<h2 id=subTitle>보다 편리한 금융</h2>
		<h1 id=title>cloudBank</h1>
		
		<div class="midLine"></div>
		<div class="whiteDiv" onclick="window.location.href='${pageContext.request.contextPath }/pages/toLogin.do'">
			지금 바로 <br>
			시작하기
		</div>
		
    </div>


</body>
</html>