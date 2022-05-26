let index={
	init:function(){
		$("#btn-save").on("click", ()=>{
			this.save();
		});
		$("#btn-user-update").on("click", ()=>{
			this.user_update();
		});
		
		
		
	},
	
	save: function(){
		//alert('user.js 의 save 함수 호출 됨!');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		// 통신을 이용해서 3개의 데이터를 json으로 변경해서 insert 요청
		$.ajax({
			// 회원 가입 수행 요청을 함
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http 바디 데이터
			contentType: "application/json; charset=uft-8", // body 데이터가 어떤 타입인지(MIME)
			dataType:"json" // 요청을 서버로 해서 "응답이 돌아왔을때"는 기본적으로 모든것이 문자열이다
			// 여기에 json 이라고 적어놓으면 json을 자바스크립트 언어로 변환 해서 줌 
		}).done(function(resp){
			// 성공 할때
			alert("회원 가입이 완료 되었습니다");
			console.log(resp);
			location.href="/";
			
		}).fail(function(error){
			// 실패 할때
			alert(JSON.stringify(error));
		}); 
	},
	
	user_update: function(){
		//alert('user.js 의 save 함수 호출 됨!');
		let data = {
			id : $("#id").val(),			
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		
		$.ajax({
			// 회원 가입 수행 요청을 함
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // http 바디 데이터
			contentType: "application/json; charset=uft-8", // body 데이터가 어떤 타입인지(MIME)
			dataType:"json" // 요청을 서버로 해서 "응답이 돌아왔을때"는 기본적으로 모든것이 문자열이다
			// 여기에 json 이라고 적어놓으면 json을 자바스크립트 언어로 변환 해서 줌 
		}).done(function(resp){
			// 성공 할때
			alert("회원 수정이 완료 되었습니다");
			location.href="/";
			
		}).fail(function(error){
			// 실패 할때
			alert(JSON.stringify(error));
		}); 
	}

}
index.init();