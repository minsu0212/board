<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<body>
	<h1>게시글 상세보기</h1>

	<button class="basic" type="button" onclick="deleteBoard(${board.boardNo});">삭제</button>
	<button class="basic" type="button" onclick="location.href='/board/modify.eansoft'">수정</button>
	<button class="basic" onclick="historyBack();">목록</button>
	<table class="table--basic">
		<colgroup>
			<col style="width:10%;">
			<col style="width:90%;">
		</colgroup>
		<tr>
			<td>글 제목</td>
			<td>${board.boardTitle }</td>
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
			<td colspan="2">${board.boardContents }</td>
		</tr>
	</table>
	<script>
		function historyBack() {
			window.location = document.referrer;
		}
		
		function deleteBoard(boardNo) {
			var emplId = "${board.emplId}";
			var loginId = "<%=(String)session.getAttribute("emplId")%>";
			if(emplId == loginId) {
				var result = confirm("게시글을 삭제하시겠습니까?");
				if(result == true) {
					$.ajax({
						url : "/board/deleteBoard.eansoft",
						type : "get",
						data : { "boardNo" : boardNo },
						success : function() {
							window.location = document.referrer;
						},
						error : function() {
							alert("ajax 실패!");
						}
					});
				}else {}
			}else {
				alert("작성자만 삭제할 수 있습니다.");
			}
		}
	</script>
</body>
</html>