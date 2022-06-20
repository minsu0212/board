<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<body>
	<h1>게시판</h1>
	
	<form class="form--srch" action="/board/searchBoard.eansoft" method="get">
		<select name="searchCondition">
			<option value="title">제목</option>
			<option value="contents">내용</option>
			<option value="writer">글쓴이</option>
		</select>
            <input type="text" name="searchValue" placeholder="검색">
            <button type="submit"></button>
        </form>
	
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
		<button class="fa-solid fa-angles-left first"
		<c:if test="${pi.currentPage > '1' }"> onclick="location.href='/board/list.eansoft?page=${pi.startNavi }'"</c:if>></button>
		<button class="fa-solid fa-angle-left prev"
		<c:if test="${pi.currentPage > '1' }"> onclick="location.href='/board/list.eansoft?page=${pi.currentPage-1 }'"</c:if>></button>
		<c:forEach var="p" begin="${pi.startNavi }" end="${pi.endNavi }">
			<c:url var="pagination" value="/board/list.eansoft">
				<c:param name="page" value="${p }"></c:param>
			</c:url>
			&nbsp;<a href="${pagination }">${p }</a>&nbsp;
		</c:forEach>
		<button class="fa-solid fa-angle-right next"
		<c:if test="${pi.currentPage < pi.endNavi }">onclick="location.href='/board/list.eansoft?page=${pi.currentPage+1 }'"</c:if>></button>
		<button class="fa-solid fa-angles-right last"
		<c:if test="${pi.currentPage < pi.endNavi }">onclick="location.href='/board/list.eansoft?page=${pi.endNavi }'"</c:if>></button>
	</div>
	<script>
		// 현재 페이지 하이라이팅
		$(function () {
		    var pageNo = new URLSearchParams(location.search).get("page");
		    if (pageNo != null) {
		        $(".btns--paging a:nth-of-type(" + pageNo + ")").addClass("on");
		    } else {
		        $(".btns--paging a:nth-of-type(1)").addClass("on");
		    }
		});
	</script>
</body>
</html>
