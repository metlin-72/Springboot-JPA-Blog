//let index={
//		init: function(){
//// $("#btn-save").on("click", ()=>{
//// this.save();
//// });
//			$("#btn-save").on("click", function(){
//				index.save();
//			});
//		},		
//		save: function() {
//			let data = {
//					username: $("#username").val(),
//					password: $("#password").val(),
//					email: $("#email").val()
//			}
//			
//			$.ajax({
//				type: "post",
//				url: "/blog/api/user",
//				data: JSON.stringify(data),
//				contentType: "application/json; charset=utf-8",
//				dataType: "json"
//			}).done(function(resp ){
//				alert("회원가입이 완료되었습니다.");
//				location.href="/blog"
//			}).fail(function(error){
//				alert(JSON.stringify(error));
//			});
//		}
//}

$("#btn-save").on("click", function(){
	var data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
	}
	
	$.ajax({
		type: "post",
		url: "/auth/joinProc",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(resp ){
		alert("회원가입이 완료되었습니다.");
		location.href="/"
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
});

//회원 정보 수정
$("#btn-update").on("click", function(){
	var data = {
			id: $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val()
	}
	
	$.ajax({
		type: "put",
		url: "/user",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(resp ){
		alert("회원수정이 완료되었습니다.");
		location.href="/"
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
});


