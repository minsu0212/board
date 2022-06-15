<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<link rel="stylesheet" href="../../resources/css/employee.css">
<c:if test="${not empty sessionScope }">
	<script>location.href="/home.eansoft";</script>
</c:if>
<body id="loginPage">
	<form action="/employee/login.eansoft" method="post" enctype="multipart/form-data">
		<input name="emplId" type="text" placeholder="아이디를 입력해 주세요.">		
		<input class="mt-10" name="emplPw" type="password" placeholder="비밀번호를 입력해 주세요.">
		
		<button class="point mt-20" type="submit">로그인</button>
		
		<div class="mt-10 t-c">
			<a id="myLink1" style="text-decoration:underline" href="/employee/registerView.eansoft">회원가입</a>
		</div>
	</form>
</body>
</html>