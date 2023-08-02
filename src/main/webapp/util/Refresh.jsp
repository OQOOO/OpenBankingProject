<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="myForm" action="${pageContext.request.contextPath }${toPage}" method="post">
	  <!-- 새로고침할때 다시 실행되는거 방지하는 페이지 -->
	  <!-- ... -->
	</form>
	
	<script>
	  window.addEventListener('DOMContentLoaded', function() {
	    document.getElementById('myForm').submit();
	  });
	</script>
</body>
</html>