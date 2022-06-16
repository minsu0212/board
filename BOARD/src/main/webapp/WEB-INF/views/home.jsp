<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<body>
	<h1>게시판</h1>
	
	<table class="table--basic">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>글 종류</th>
				<th>글 제목</th>
				<th>첨부파일(개수)</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bList }" var="board">
				<tr>
					<td>${board.boardNo }</td>
					<td>${board.boardType }</td>
					<td>${board.boardTitle }</td>
					<td></td>
					<td>${board.emplId }</td>
					<td>${board.boardWriteDate }</td>
					<td>${board.boardCount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<button type="button" onclick="location.href='/board/write.eansoft'">게시글 등록</button>
</body>
</html>
