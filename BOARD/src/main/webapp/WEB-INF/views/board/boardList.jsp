<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<body>
	<h1>게시판</h1>
	
	<table class="table--basic">
		<colgroup>
			<col style="width:8%;">
			<col style="width:9%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:10%;">
			<col style="width:15%;">
			<col style="width:8%;">
		</colgroup>
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
					<c:url var="bDetail" value="/board/detail.eansoft">
						<c:param name="boardNo" value="${board.boardNo }"></c:param>
					</c:url>
                          <td><a href="${bDetail}">${board.boardTitle }</a></td>
					<td></td>
					<td>${board.emplId }</td>
					<td>${board.boardWriteDate }</td>
					<td>${board.boardCount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<button class="basic" type="button" onclick="location.href='/board/write.eansoft'">게시글 등록</button>
	
	<div class="btns--paging">
		<c:if test="${pi.currentPage > '1' }">
			<button class="fa-solid fa-angle-left prev" onclick="location.href='/board/list.eansoft?page=${pi.currentPage-1 }'"></button>
		</c:if>
		<c:forEach var="p" begin="${pi.startNavi }" end="${pi.endNavi }">
			<c:url var="pagination" value="/board/list.eansoft">
				<c:param name="page" value="${p }"></c:param>
			</c:url>
			&nbsp;<a href="${pagination }">${p }</a>&nbsp;
		</c:forEach>
		<c:if test="${pi.currentPage < pi.endNavi }">
			<button class="fa-solid fa-angle-right next" onclick="location.href='/board/list.eansoft?page=${pi.currentPage+1 }'"></button>
		</c:if>
	</div>
</body>
</html>
