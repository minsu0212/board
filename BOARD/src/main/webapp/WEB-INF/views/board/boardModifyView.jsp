<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<body>
	<h1>게시글 수정</h1>
	
	<table class="table--basic">
		<colgroup>
			<col style="width:10%;">
			<col style="width:90%;">
		</colgroup>
		<tr>
			<td>글 제목</td>
			<td><input type="text" value="${board.boardTitle }"></td>
		</tr>
		<tr>
			<td>글 종류</td>
			<td>${board.boardType }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.emplId }</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${board.boardWriteDate }</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${board.boardCount }</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:if test="${boardFile.fileRename ne null }">
					<a href="../../../resources/nuploadFiles/${boardFile.fileRename }" download>${boardFile.fileName }</a>
				</c:if>
				<c:if test="${boardFile.fileRename eq null }">
					등록된 첨부파일이 없습니다.
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2"><textarea>${board.boardContents }</textarea></td>
		</tr>
	</table>
	<button class="basic">수정</button>
</body>
</html>