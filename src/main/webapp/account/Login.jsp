<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CloudBank</title>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<style>
	#container {
		text-align: center;
		position: relative;
		top: 100px;
		box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
		
		width: 500px;
		height: 500px;
	}
	
	.text1 {
		position: relative;
		text-align: left;
		left: 120px;
		font-weight: bold;
		font-size: 20px;
	}
	
	.errerMessage {
		height: 10px;
		position: relative;
		text-align: left;
		left: 120px;
		margin-bottom: 15px;
	}
	
	#loginBtn {
		position: relative;
		top: 0px;
		left: 80px;
		height: 100px;
		width: 100px;
		font-size: 20px;
		font-weight: bold;
	}
	#signUpBtn {
		position: relative;
		bottom: 10px;
		
		left: -15px;
		top: -100px;
		
		height: 70px;
		width: 70px;
		
	}
	
	#a1 {
		position: relative;
		color:gray;
		bottom: 95px;
		left: -15px;
	}
	
	#kakaoLoginBtn {
		width: 260px;
		z-index: 2;
		background-color: #FEE500;
		height: 35px;
		border: none;
		position: relative;
		font-size: 15px;
		font-weight: bold;
		box-shadow: none;
	}

	#kakaoImg {
		width: 260px;
	}

	
</style>
</head>
<body>
	<header>
        <form style="height: 40px;" class="topMenuForm" method="post" action="${pageContext.request.contextPath }/pages/IndexMain.do">
            <input type="text" name="logout" value="1" style="display: none;">
            <input class="headerMenus" type="submit" value="CloudBank">
        </form>
        <div id="headerContainer" class="container">
            <form id="loginForm" method="post" action="${pageContext.request.contextPath }/pages/toLogin.do">
                <input id="loginbutton" class="headerMenus" type="submit" value="로그인">
            </form>
        </div>
    </header>
    <div id="main">
    	<div id="container">
		<h1>로그인</h1>
	    	<form id=executeLogin method="post" action="${pageContext.request.contextPath }/account/login.do">
	    		<div class=text1>아이디</div>
	    		<input id="idInput" type="text" name="id" value="${id }">
	    		<div class="errerMessage">${idErrer }</div>
	    		<div class=text1 style="margin-top: 10px">비밀번호</div>
	    		<input id="pwInput" type="password" name="password" value="${pw }">
	    		<div class="errerMessage"  style="margin-bottom: 30px">${pwErrer }</div>
	    		<div>
		    		<input id="loginBtn" type="submit" value="로그인">
	    		</div>
	   		</form>
	   		<form method="post" action="${pageContext.request.contextPath }/pages/toSignUp.do">
	    		<input id="signUpBtn" type="submit" value="회원가입">
	   		</form>
	   		<a id="a1" href="https://accounts.kakao.com/weblogin/account/info">계정 분실</a>
    	</div>
    	
    	<!--<input style="z-index: 2" type="button" id="kakaoLoginBtn" onclick="kakaoLogin();" value="카카오 계정으로 로그인">
    	-->
    	
    	<img id="kakaoImg" style="z-index:2" src="${pageContext.request.contextPath }/css/kakao_login_medium_wide.png" onclick="kakaoLogin();">
    	
   		<form id="kakaoLoginForm" style="z-index: 2" method="post" action="${pageContext.request.contextPath }/account/kakaoLogin.do">
   			<input id="kakaoId" name="kakaoId" type="hidden">
   		</form>

		
	<!-- 카카오 스크립트 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script>
	Kakao.init('${kakaoKey}'); //발급받은 키 중 javascript키를 사용해준다.
	console.log(Kakao.isInitialized()); // sdk초기화여부판단
	//카카오로그인
	function kakaoLogin() {
	    Kakao.Auth.login({
		      success: function (response) {
		        Kakao.API.request({
		          url: '/v2/user/me',
		          success: function (response) {
		        	  console.log(response)
		        	  console.log(response.id);
		        	  document.getElementById("kakaoId").value= response.id;
		        	  document.getElementById("kakaoLoginForm").submit();
		          },
		          fail: function (error) {
		            console.log(error)
		          },
		        })
		      },
		      fail: function (error) {
		        console.log(error)
		      },
		    })
		  }
		//카카오로그아웃  
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
	</script>

    	
    </div>
	
</body>
</html>