<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
	<input type="hidden" id="id"  value="${principal.user.id}"/>
		<div class="form-group">
			<label for="username">username :</label> 
			<input type="text"  value="${principal.user.username}" class="form-control" placeholder="이름을 써주세요" id="username" readonly>
		</div>
		<div class="form-group">
			<label for="password">password:</label> 
			<input type="password"  class="form-control" placeholder="비밀번호를 작성해주세요" id="password">
		</div>
		<div class="form-group">
			<label for="email">email_address:</label> 
			<input type="email" value="${principal.user.email}" class="form-control" placeholder="이메일을 써주세요" id="email">
		</div>
	</form>
	<button id="btn-user-update" class="btn btn-primary">회원 수정 완료</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


