<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script type="text/javascript">

function checkFnuc() {
	
	const alertSwitch = '${alertSwitch}';
	const acPw = '${ansPw}';
	
	if (alertSwitch == "balanceErrer") {
	
		Swal.fire({
	        icon: 'warning', // Alert 타입
	        title: '계좌를 해지할 수 없습니다.',         // Alert 제목
	        text: '잔금을 모두 인출해주세요.',  // Alert 내용
	    });
	} else if(alertSwitch == "inputPassword") {
		(async () => {
	        const { value: inputPw } = await Swal.fire({
	            title: '비밀번호를 입력해 주세요.',
	            text: '4자리 숫자.',
	            input: 'text',
	            inputPlaceholder: '비밀번호'
	        })

	        // 이후 처리되는 내용.
	        if (inputPw == acPw) {
	            document.getElementById("delSavingAccount").submit();
	        } else {
	        	Swal.fire({
		            icon: 'warning', // Alert 타입
		            title: '잘못된 비밀번호입니다.',         // Alert 제목
		            text: '다시 시도해 주세요.',  // Alert 내용
		        });
	        }
	    })()
		
	}
}
	    
	    
  	
</script>
</head>
<body>
	<c:import url="/include/Header.jsp" />
    <div id="main">
		<h1>계좌 관리</h1>
		<form method="post" action="${pageContext.request.contextPath }/bankAccount/pages/toAccountDataUpdate.do">
            <input type="hidden" name="acNumber" value="${acNumber }">
            <input type="submit" value="계좌정보 변경">
        </form>
		<form method="post" action="${pageContext.request.contextPath }/bankAccount/delSavingAccount.do">
            <input type="hidden" name="acNumber" value="${acNumber }">
            <input type="hidden" name="inputPw" value="-1">
            <input type="hidden" name="delSwitch" value="0">
            <input type="submit" value="계좌 해지">
        </form>
		<form method="post" action="${pageContext.request.contextPath }/bankAccount/pages/toAccountInfo.do">
			<input type="hidden" name="acNumber" value="${acNumber }">
			<input type="hidden" name="page" value="1">
            <input type="submit" value="뒤로">
        </form>
    </div>
    <form id="delSavingAccount" method="post" action="${pageContext.request.contextPath }/bankAccount/delSavingAccount.do">
        <input type="hidden" name="acNumber" value="${acNumber }">
        <input type="hidden" name="inputPw" value="-1">
        <input type="hidden" name="delSwitch" value="1">
    </form>
	<script>
		checkFnuc();
	</script>
</body>
</html>
