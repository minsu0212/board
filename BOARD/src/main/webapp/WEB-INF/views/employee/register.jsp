<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<link rel="stylesheet" href="../../resources/css/employee.css">
	<body class="page--employee">
		<form id="registerForm" class="shadow" action="/employee/register.eansoft" method="post" enctype="multipart/form-data"> 
			<div class="subConts1">
				<label>아이디</label>
				<input name="emplId" type="text" placeholder="20자 내의 영문으로 작성하셔야 합니다."> 
			</div>
			
			<div class="subConts2">
				<label>비밀번호</label>
				<input name="emplPw" type="password" placeholder="비밀번호를 입력해 주세요.">
			</div>
			
			<div class="subConts3">
				<label>이름</label>
				<input name="emplName" type="text" placeholder="이름을 입력해주세요.">
			</div>
			
			<button class="point mt-20" type="submit" onclick="btnRegister();">회원가입</button>
		</form>
		<script> 
			function btnRegister(){ 
				alert('회원가입이 완료되었습니다.'); 
				$('#registerForm').submit();
			} 
		</script>
	</body>
</html>