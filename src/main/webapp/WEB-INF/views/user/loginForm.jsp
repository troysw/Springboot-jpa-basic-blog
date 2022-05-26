<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">username :</label> 
			<input type="text" name="username" class="form-control" placeholder="이름을 써주세요" id="username">
		</div>

		<div class="form-group">
			<label for="pwd">password:</label> 
			<input type="password" name="password" class="form-control" placeholder="비밀번호를 작성해주세요" id="password">
		</div>

		<button id="btn-login"  class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=8bb7861ee0fb238080eb1c54821ce972&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height = "38px"src="/image/kakao_login_medium.png"></a>
	</form>
</div>
<%@ include file="../layout/footer.jsp"%>


