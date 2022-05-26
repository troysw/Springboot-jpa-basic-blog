<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<input type="text"  class="form-control" placeholder="제목을 써주세요" id="title">
		</div>

		<div class="form-group">
  <textarea class="form-control summernote"  rows="5" id="content"></textarea>
</div>
	</form>
		<button id="btn-board-save"  class="btn btn-primary">작성완료</button>
</div>
    <script>
      $('.summernote').summernote({
        placeholder: '내용을 입력 해 주세요',
        tabsize: 2,
        height: 300
      });
    </script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


