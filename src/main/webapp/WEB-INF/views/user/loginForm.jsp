<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
<!-- 	<form action="/blog/user/join" method="post"> -->
	<form>
		<div class="form-group">
			<label for="username">Username:</label> <input type="text" class="form-control" placeholder="유저아이디를 입력하세요." id="username">
		</div>

		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="패스워드를 입력하세요." id="password">
		</div>

		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me</label>
		</div>
		
	</form>
	
	<button type="button" class="btn btn-primary">회원가입</button>
</div>

<%@ include file="../layout/footer.jsp"%>