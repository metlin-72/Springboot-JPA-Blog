<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
<!-- 	<form action="/blog/user/join" method="post"> -->
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username:</label>
			<input type="text" name="username" class="form-control" placeholder="유저아이디를 입력하세요." id="username">
		</div>

		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" name="password" class="form-control" placeholder="패스워드를 입력하세요." id="password">
		</div>

<!-- 		<div class="form-group form-check"> -->
<!-- 			<label class="form-check-label">  -->
<!-- 			<input name="remember" class="form-check-input" type="checkbox"> Remember me</label> -->
<!-- 		</div> -->
		
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=f0fd118d74eefd5e1e9a8234c1572acf&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"><img height="38" src="/images/kakao_login_button.png"></a>
	</form>
	
	
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>