// 글저장
$("#btn-save").on("click", function(){
	var data = {
			title: $("#title").val(),
			content: $("#content").val()
	}
	
	$.ajax({
		type: "post",
		url: "/api/board",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(resp ){
		alert("글쓰기가 완료되었습니다.");
		location.href="/"
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
});

//글삭제
$("#btn-delete").on("click", function(){
	var id = $("#spnId").text();
	
	$.ajax({
		type: "delete",
		url: "/api/board/" + id,
		dataType: "json"
	}).done(function(resp ){
		alert("삭제가 완료되었습니다.");
		location.href="/"
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
});

//글수정
$("#btn-update").on("click", function(){
	var id = $("#boardId").val();
	
	var data = {
			title: $("#title").val(),
			content: $("#content").val()
	}
	
	$.ajax({
		type: "put",
		url: "/api/board/" + id,
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(resp ){
		alert("글수정이 완료되었습니다.");
		location.href="/board/" + id; 
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
});

//댓글저장
$("#btn-reply-save").on("click", function (){
	var data = {
		userId: $("#userId").val(),
		boardId: $("#boardId").val(),
		content: $("#reply_content").val()
	}

	$.ajax({
		type: "post",
		url: `/api/board/${data.boardId}/reply`,
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(resp ){
		alert("댓글작성이 완료되었습니다.");
		location.href = `/board/${data.boardId}`;
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
});

//댓글삭제
$(".badge").on("click", function (){
	var boardId = $(this).data("boardid");
	var replyId = $(this).data("replyid");

	$.ajax({
		type: "delete",
		url: "/api/board/" + boardId + "/reply/" + replyId,
		dataType: "json"
	}).done(function(resp ){
		alert("댓글삭제 성공.");
		location.href = "/board/" + boardId;
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
});