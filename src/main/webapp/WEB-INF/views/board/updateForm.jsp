<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

<div class="container">
	<form>
		<input type="hidden" id="boardId" value="${board.id}" />
		<div class="form-group">
			<label for="title">제목:</label> 
			<input type="text" name="title" class="form-control" placeholder="제목을 입력하세요." id="title" value="${board.title}">
		</div>

		<div class="form-group">
			<label for="content">글작성:</label>
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>
	</form>
	
	<button id="btn-update" class="btn btn-primary" >글수정</button>
</div>

<script type="text/javascript">
	$(".summernote").summernote({
		placeholder : '',
		tabsize : 2,
		height : 300
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>