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

//$("#btn-login").on("click", function(){
//	var data = {
//			username: $("#username").val(),
//			password: $("#password").val()
//	}
//	
//	$.ajax({
//		type: "post",
//		url: "/api/user/login",
//		data: JSON.stringify(data),
//		contentType: "application/json; charset=utf-8",
//		dataType: "json"
//	}).done(function(resp ){
//		alert("로그인이 완료되었습니다.");
//		location.href="/"
//	}).fail(function(error){
//		alert(JSON.stringify(error));
//	});
});