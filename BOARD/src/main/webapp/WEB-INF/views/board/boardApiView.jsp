<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<body>
	<h1>API 접속 기록</h1>
	
	<button class="basic" type="button" onclick="prev();">이전</button>
	
	<!-- 검색 -->
	<div class="form--srch">
		<select id="searchCondition">
			<option value="ip">IP</option>
			<option value="date">날짜</option>
			<option value="dateTime">시간</option>
		</select>
		<input type="text" id="searchValue" placeholder="검색">
		<button onclick="search();"></button>
	</div>
	
	<table class="table--basic" id="apiTable" style="width:60%; margin:auto;">
		<thead>
			<tr>
				<td>IP</td>
				<td>날짜</td>
				<td>시간</td>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<script>
		function search() {
			var searchCondition = $("#searchCondition").val();
			var searchValue = $("#searchValue").val();
			$.ajax({
				url : "http://127.0.0.1:8888/api/apiSearch.hirp",
				type : "get",
				data : { "searchCondition" : searchCondition,
						 "searchValue" : searchValue },
				dataType : "jsonp",
				jsonp : "callback",
				success : function(data) {
					var $apiTable = $("#apiTable tbody");
					$apiTable.html("");
					var $tr = $("<tr>");
					for(var i = 0; i < data.length; i++) {
						var remoteAddr = data[i].remoteAddr;
						var connectDate = data[i].connectDate;
						var connectDateTime = data[i].connectDateTime;
						var remoteData = "<tr>" + "<td>" + remoteAddr + "</td>"
									   + "<td>" + connectDate + "</td>"
									   + "<td>" + connectDateTime + "</td>" + "</tr>";
						$apiTable.append(remoteData);
					}
				},
				error : function() {
					alert("Ajax 실패!");
				}
			});
		}
	
		$(function() {
			$.ajax({
				url : "http://127.0.0.1:8888/api/apiView.hirp",
				type : "get",
				dataType : "jsonp",
				jsonp : "callback",
				success : function(data) {
					var $apiTable = $("#apiTable tbody");
					var $tr = $("<tr>");
					for(var i = 0; i < data.length; i++) {
						var remoteAddr = data[i].remoteAddr;
						var connectDate = data[i].connectDate;
						var connectDateTime = data[i].connectDateTime;
						var remoteData = "<tr>" + "<td>" + remoteAddr + "</td>"
									   + "<td>" + connectDate + "</td>"
									   + "<td>" + connectDateTime + "</td>" + "</tr>";
						$apiTable.append(remoteData);
					}
				},
				error : function() {
					alert("Ajax 실패!");
				}
			});
		});
	
		function prev() {
			history.back();
		}
	</script>
</body>
</html>