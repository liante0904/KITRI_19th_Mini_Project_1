<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학생 시간표 페이지</title>
<style>
table.type07 {
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;
	border: 1px solid #ccc;
	margin: 20px 10px;
}

table.type07 thead {
	border-right: 1px solid #ccc;
	border-left: 1px solid #ccc;
	background: #e7708d;
}

table.type07 thead th {
	text-align: center;
	padding: 7px;
	font-weight: bold;
	vertical-align: top;
	color: #fff;
}

table.type07 tbody th {
	width: 15px;
	padding: 15px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #fcf1f4;
	font-size: 12px;
}

table.type07 tbody td:hover{
	background: #efefef;
}

table.type07 td {
	width: 250px;
	padding: 10px;
	text-align: center;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	border-right: 1px solid #ccc;
}
table.type07 td a:link {
	text-decoration: none; color: black;
}
table.type07 td a:hover {
	color: red;
	}



</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/httpRequest.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var param = "code=mySublist";
		sendRequest("${pageContext.request.contextPath}/main.do", param,
				listResult, "POST");
	}

	function listResult() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var o = eval("(" + str + ")");
				var message = "";
				for (i = 0; i < o.length; i++) {
					message = "<a href='javascript:delSub("
						+ o[i].reg_num + ")'>"+o[i].prof_name + "-" + o[i].sub_name + " :"
							+ o[i].room + "호</a>";

					document.getElementById("tab_" + o[i].sub_day + "_"
							+ o[i].sub_time).innerHTML = message;
				}
			}
		}
	}
	function delSub(m) {
		var flag = confirm("수강취소하시겠습니까?");
		if(flag){
		var param = "code=delReg&num="+m;
		sendRequest("${pageContext.request.contextPath}/main.do", param, delOK,
				"POST");
		}
		else{return;}
	}
	function delOK() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var o = eval("(" + str + ")");
				if(o.flag){
					alert("수강취소완료");
					location.reload();
				}
			}

		}
	}
	function logout() {
		location.href = "${pageContext.request.contextPath}/login.do?code=logout";
	}
</script>
</head>
<body>
	<h2>나의 시간표 보기</h2>

	<h5>해당 강의를 클릭하면 수강취소가 가능합니다.</h5>
	<div id="members"></div>
	<table class="type07">
		<thead>
			<tr>
				<th></th>
				<th width="300">월</th>
				<th width="300">화</th>
				<th width="300">수</th>
				<th width="300">목</th>
				<th width="300">금</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<th>08:00~09:00</th>
			<td id='tab_월_08:00~09:00'></td>
			<td id='tab_화_08:00~09:00'></td>
			<td id='tab_수_08:00~09:00'></td>
			<td id='tab_목_08:00~09:00'></td>
			<td id='tab_금_08:00~09:00'></td>

		</tr>
		<tr>
			<th>09:00~10:00</th>
			<td id='tab_월_09:00~10:00'></td>
			<td id='tab_화_09:00~10:00'></td>
			<td id='tab_수_09:00~10:00'></td>
			<td id='tab_목_09:00~10:00'></td>
			<td id='tab_금_09:00~10:00'></td>
		</tr>
		<tr>
			<th>10:00~11:00</th>
			<td id='tab_월_10:00~11:00'></td>
			<td id='tab_화_10:00~11:00'></td>
			<td id='tab_수_10:00~11:00'></td>
			<td id='tab_목_10:00~11:00'></td>
			<td id='tab_금_10:00~11:00'></td>
		</tr>
		<tr>
			<th>11:00~12:00</th>
			<td id='tab_월_11:00~12:00'></td>
			<td id='tab_화_11:00~12:00'></td>
			<td id='tab_수_11:00~12:00'></td>
			<td id='tab_목_11:00~12:00'></td>
			<td id='tab_금_11:00~12:00'></td>
		</tr>
		<tr>
			<th>12:00~13:00</th>
			<td id='tab_월_12:00~13:00'></td>
			<td id='tab_화_12:00~13:00'></td>
			<td id='tab_수_12:00~13:00'></td>
			<td id='tab_목_12:00~13:00'></td>
			<td id='tab_금_12:00~13:00'></td>
		</tr>
		<tr>
			<th>13:00~14:00</th>
			<td id='tab_월_13:00~14:00'></td>
			<td id='tab_화_13:00~14:00'></td>
			<td id='tab_수_13:00~14:00'></td>
			<td id='tab_목_13:00~14:00'></td>
			<td id='tab_금_13:00~14:00'></td>
		</tr>
		<tr>
			<th>14:00~15:00</th>
			<td id='tab_월_14:00~15:00'></td>
			<td id='tab_화_14:00~15:00'></td>
			<td id='tab_수_14:00~15:00'></td>
			<td id='tab_목_14:00~15:00'></td>
			<td id='tab_금_14:00~15:00'></td>
		</tr>
		<tr>
			<th>15:00~16:00</th>
			<td id='tab_월_15:00~16:00'></td>
			<td id='tab_화_15:00~16:00'></td>
			<td id='tab_수_15:00~16:00'></td>
			<td id='tab_목_15:00~16:00'></td>
			<td id='tab_금_15:00~16:00'></td>
		</tr>

		</tbody>
	</table>
	<input type="button" value="로그아웃" onclick='logout()'>
	<input type="button" value="수강신청목록" onclick='javascript:history.back()'>
</body>
</html>
