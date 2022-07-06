<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<body>
	<h1>프로젝트 통계</h1>

	<button class="basic" type="button" onclick="prev();">이전</button>
	<input type="date" id="startDate" value="2022-01-01">
	<input type="date" id="endDate">
	<button class="basic" type="button" id="selectDate" onclick="selectDate();">조회</button>
	<table class="table--basic" id="statisticsTable" style="width:60%; margin:auto;">
		<thead>
			<tr>
				<td>아이디</td>
				<td>갯수</td>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<script>
		document.getElementById("endDate").value = new Date().toISOString().substring(0, 10);
	
		function prev() {
			history.back();
		}
		
		function selectDate() {
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			$.ajax({
				url : "http://127.0.0.1:8888/statistics/count.hirp",
// 				url : "http://192.168.0.18:8888/board/statistic.hirp",
				type : "get",
				data : { "startDate" : startDate,
						 "endDate" : endDate },
				dataType : "jsonp",
				jsonp : "callback",
				success : function(data) {
// 					var $statisticTable = $("#statisticsTable tbody");
// 					$statisticTable.html("");
// 		               var $tr = $("<tr>");
// 		               for (var i = 0; i < data.length; i++) {
// 		                  var emplId = data[i].emplId;
// 		                  var statisticCount = data[i].statisticCount;
// 		                  var statisticData = "<tr>" + "<td>" + emplId
// 		                        + "</td>" + "<td>" + statisticCount
// 		                        + "</td>" + "</tr>"
// 		                  $statisticTable.append(statisticData);
// 		               }
		            
//		                      내 코드
					var $tableBody = $("#statisticsTable tbody");
					$tableBody.html("");
					var $trCount = $("<tr>");
					
					$tableBody.append($trCount);
					for(var i = 0; i < data.length; i++) {
						var $tr = $("<tr>");
						var $br = $("<br>");
						var $projectManager = $("<td>").text(data[i].projectManager);
						var $projectManagerCount = $("<td>").text(data[i].projectManagerCount);
						
						$tr.append($projectManager);
						$tr.append($projectManagerCount);
						$tableBody.append($tr);
					}
				},
				error : function() {
					alert("Ajax 실패!");
				}
			});
		}
		
		$(function() {
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			$.ajax({
				url : "http://127.0.0.1:8888/statistics/count.hirp",
// 				url : "http://192.168.0.18:8888/board/statistic.hirp",
				type : "get",
				data : { "startDate" : startDate,
						 "endDate" : endDate },
				dataType : "jsonp",
				jsonp : "callback",
				success : function(data) {
// 					var $statisticTable = $("#statisticsTable tbody");
// 		               var $tr = $("<tr>");
// 		               for (var i = 0; i < data.length; i++) {
// 		                  var emplId = data[i].emplId;
// 		                  var statisticCount = data[i].statisticCount;
// 		                  var statisticData = "<tr>" + "<td>" + emplId
// 		                        + "</td>" + "<td>" + statisticCount
// 		                        + "</td>" + "</tr>"
// 		                  $statisticTable.append(statisticData);
// 		               }

// 		                      내 코드
					var $tableBody = $("#statisticsTable tbody");
					$tableBody.html("");
					var $trCount = $("<tr>");
					
					$tableBody.append($trCount);
					for(var i = 0; i < data.length; i++) {
						var $tr = $("<tr>");
						var $br = $("<br>");
						var $projectManager = $("<td>").text(data[i].projectManager);
						var $projectManagerCount = $("<td>").text(data[i].projectManagerCount);
						
						$tr.append($projectManager);
						$tr.append($projectManagerCount);
						$tableBody.append($tr);
					}
				},
				error : function() {
					alert("Ajax 실패!");
				}
			});
		});
	</script>
</body>
</html>