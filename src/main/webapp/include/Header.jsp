<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
	<form style="height: 40px;" class="topMenuForm" method="post" action="${pageContext.request.contextPath }/pages/IndexMain.do">
         <input type="text" name="logout" value="1" style="display: none;">
         <input class="headerMenus" type="submit" value="CloudBank">
    </form>
    <div id="headerContainer" class="container">
		<form style="height: 40px;" class="topMenuForm" method="post" action="${pageContext.request.contextPath }/pages/IndexMain.do">
          	<input type="text" name="logout" value="1" style="display: none;">
         	<input class="headerMenus" type="submit" value="내 계좌 목록">
      	</form>
        <form id="loginForm" method="post" action="${pageContext.request.contextPath }/toNews.sp">
             <input id="loginbutton" class="headerMenus" type="submit" value="금융 뉴스">
        </form>
        <form id="loginForm" method="post" action="${pageContext.request.contextPath }/pages/toBoard.do">
             <input id="loginbutton" class="headerMenus" type="submit" value="문의게시판">
        </form>
        <form id="loginForm" method="post" action="${pageContext.request.contextPath }/pages/passwordMyPage.do">
             <input id="loginbutton" class="headerMenus" type="submit" value="마이페이지">
        </form>
        <form id="loginForm" method="post" action="${pageContext.request.contextPath }/account/logout.do">
             <input id="loginbutton" class="headerMenus" type="submit" value="로그아웃">
        </form>
     </div>
</header>