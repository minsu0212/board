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
	<div id="reply-div">
		<div>
			<span>${sessionScope.emplName }</span>
			<textarea id="rContents" maxlength="500" placeholder="댓글을 입력해 주세요."></textarea>
		</div>
		<div><button id="rSubmit">댓글 작성</button></div>
	</div>
	<table class="reply-table" id="rtb">
		<thead>
		</thead>
		<tbody>
		</tbody>
	</table>
	<script>
		getReplyList();
	
		$("#rSubmit").on("click", function() {
			var boardNo = "${board.boardNo}";
			var replyContents = $("#rContents").val();
			$.ajax({
				url : "/board/replyAdd.eansoft",
				type : "post",
				data : { "boardNo" : boardNo,
						 "replyContents" : replyContents },
				success : function(data) {
					if(data == "success") {
						alert("댓글 등록 성공");
						$("#reply-div textarea").val("");
						getReplyList();
					}else {
						alert("댓글 등록 실패");
					}
				},
				error : function() {
					alert("ajax 실패!");
				}
			});
		});
		
		function getReplyList() {
			var boardNo = "${board.boardNo}";
			$.ajax({
				url : "/board/replyList.eansoft",
				type : "get",
				data : { "boardNo" : boardNo },
				dataType : "json",
				success : function(data) {
					var count = data.length;
					var $tableBody = $("rtb tbody");
					$tableBody.html("");
					var $trCount = $("<tr>");
					
					$tableBody.append($trCount);
					for(var i = 0; i < data.length; i++) {
						var $tr = $("<tr>");
						var $br = $("<br>");
						var $rWriter 	 = $("<td width='160'><b>").text(data[i].emplId).append("</b>");
						var $reWriter 	 = $("<td width='160' id='reWriter'><img src='../../../../resources/images/icons/rereply.png' style='width:20px; height:auto; vertical-align: middle; align :right;'/><b>"+data[i].emplId+"</b>");
						var $rContent 	 = $("<td width='250' colspan='2' class='rContent'>").text(data[i].replyContents);
						var $reContent 	 = $("<td width='250' colspan='2' class='rContent' >").text(data[i].replyContents);
						var $rCreateDate = $("<td class='t-c' width='120'>").text(data[i].writeDate);
						var $btnArea 	 = $("<td class='t-c' width='100'>")
											.append("<a href='javascript:void(0)' onclick='modReplyView(this, "+data[i].replyNo+", \""+data[i].replyContents+"\");'>수정</a> ")
											.append("<a href='javascript:void(0)' onclick='removeReply("+data[i].replyNo+");'>삭제</a>")

							
						var $btnReReply	 = $("<td class='t-c' width='100'>").append("<a href='javascript:void(0)' onclick='ReReplyWriteView(this, "+data[i].replyNo+", \""+data[i].replyContents+"\");'>답글</a>");
						
						if(data[i].replyOrder == 0){
							$tr.append($rWriter);
							$tr.append($rContent);
							$tr.append($rCreateDate);
							$tr.append($btnArea);
							$tr.append($btnReReply);
							$tableBody.append($tr);
						}else{
							$tr.append($reWriter);
							$tr.append($reContent);
							$tr.append($rCreateDate);
							$tr.append($btnArea);
							$tr.append("<td></td>");
							$tableBody.append($tr);
						}
					}
				},
				error : function() {
					var $tableBody = $("#rtb tbody");
					$tableBody.html(""); 
					var $trCount = $("<tr>");
					var $trMsg = $("<tr>");
					var $tdCount = $("<td colspan='4'>").html("<b>댓글 (0)</b>");
					var $tdMsg = $("<td colspan='4'>").text("댓글이 없습니다.");
					$trCount.append($tdCount);
					$trMsg.append($tdMsg);
					$tableBody.append($trCount);
					$tableBody.append($trMsg);
				}
			});
		}
		
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