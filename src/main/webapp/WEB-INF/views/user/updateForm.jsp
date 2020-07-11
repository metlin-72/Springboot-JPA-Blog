<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	
	<form>
		<input type="hidden"  id="id" value="${principal.blogUser.id}">
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text" value="${principal.blogUser.username}" class="form-control" placeholder="유저아이디를 입력하세요." id="username" readonly>
		</div>

		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" placeholder="패스워드를 입력하세요." id="password">
		</div>
		
		<div class="form-group">
			<label for="email">Email:</label> 
			<input type="text" value="${principal.blogUser.email}" class="form-control" placeholder="이메일을 입력하세요." id="email">
		</div>
	</form>
	
	<button id="btn-update" type="button" class="btn btn-primary">회원정보수정</button>
	
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>