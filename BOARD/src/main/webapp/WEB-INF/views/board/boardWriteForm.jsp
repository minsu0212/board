<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<body>
	<h1>게시글 작성</h1>

	<form action="/board/register.eansoft" method="post" enctype="multipart/form-data">
		<table class="table--basic">
			<tr>
				<td>글 종류</td>
				<td>
					<select name="boardType">
						<option value="10">공지</option>
						<option value="20">유머</option>
						<option value="30">뉴스</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td><input type="text" name="boardTitle"></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
					<input type="file" name="uploadFiles" multiple accept=".png, .jpg, .jpeg,.doc,.docx,.xlsx,.xml,.pptx,.hwp,.pdf,.txt,.zip" onchange="checkSize(this)">
				</td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td><textarea name="boardContents"></textarea></td>
			</tr>
		</table>
		<button class="basic" type="submit">등록</button>
	</form>
	<script>
		//첨부파일 사이즈 3MB로 제한
		function checkSize(input) {
		   if(input.files && input.files[0]) {
		      var maxSize = 3 * 1024 * 1024;
		      var fileSize = input.files[0].size;
		      
		      if(fileSize > maxSize) {
		      alert("첨부파일 사이즈는 3MB 이내로 등록 가능합니다.");
		      input.value = null;
		      }
		   }
		}
	</script>
</body>
</html>