<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<body>
	<form action="/board/register.eansoft" method="post">
		<table>
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
				<td><input type="file" size="50" name="uploadFile" value="파일선택"></td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td><textarea name="boardContents"></textarea></td>
			</tr>
		</table>
		<button type="submit">등록</button>
	</form>
</body>
</html>