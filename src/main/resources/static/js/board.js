let index={
	init:function(){
		$("#btn-board-save").on("click", ()=>{
			this.save();
		});
		$("#btn-board-delete").on("click", ()=>{
			this.board_delete();
		});
		$("#btn-board-update").on("click", ()=>{
			this.board_update();
		});
	},
	
	save: function(){
		//alert('user.js 의 save 함수 호출 됨!');
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), // http 바디 데이터
			contentType: "application/json; charset=uft-8", // body 데이터가 어떤 타입인지(MIME)
			dataType:"json" // 요청을 서버로 해서 "응답이 돌아왔을때"는 기본적으로 모든것이 문자열이다
			// 여기에 json 이라고 적어놓으면 json을 자바스크립트 언어로 변환 해서 줌 
		}).done(function(resp){
			// 성공 할때
			alert("글 쓰기가 완료 되었습니다");
			location.href="/";
			
		}).fail(function(error){
			// 실패 할때
			alert(JSON.stringify(error));
		}); 
	},
	
	board_delete: function(){
		let id = $("#id").text();
		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataType:"json" 
		}).done(function(resp){
			// 성공 할때
			alert("삭제가 완료 되었습니다");
			location.href="/";
			
		}).fail(function(error){
			// 실패 할때
			alert(JSON.stringify(error));
		}); 
	},

	board_update: function(){
		//alert('user.js 의 save 함수 호출 됨!');
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data), // http 바디 데이터
			contentType: "application/json; charset=uft-8", // body 데이터가 어떤 타입인지(MIME)
			dataType:"json" // 요청을 서버로 해서 "응답이 돌아왔을때"는 기본적으로 모든것이 문자열이다
			// 여기에 json 이라고 적어놓으면 json을 자바스크립트 언어로 변환 해서 줌 
		}).done(function(resp){
			// 성공 할때
			alert("글 수정이 완료 되었습니다");
			location.href="/";
			
		}).fail(function(error){
			// 실패 할때
			alert(JSON.stringify(error));
		}); 
	}
}
index.init();