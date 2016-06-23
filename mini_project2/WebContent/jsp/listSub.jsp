<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교직원용 과목 개설 페이지</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/httpRequest.js"></script>
<script type="text/javascript">
	window.onload = function() {
		listR();
	}
	function listR() {
		var param = "code=listSub";
		sendRequest("${pageContext.request.contextPath}/main.do", param,
				listResult, "POST");
	}

	function listResult() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var o = eval("(" + str + ")");
				var message = "<h3>개설된 과목</h3><table class='type04'><thead><tr><th style='width: 80px'>과목 번호</th><th>과목 제목</th></tr></thead><tbody>";
				for (i = 0; i < o.length; i++) {
					message += "<tr><th style='width: 80px'>"+o[i].num+"</th><td>"+ o[i].name+"</td></tr>";
				}
				//html += "num: "+o.num+", name: "+o.name+", tel: "+o.tel;
				message += "</tbody></table>"
				document.getElementById("members").innerHTML = message;
			}
		}
	}
	function logout() {
		location.href = "${pageContext.request.contextPath}/login.do?code=logout";
	}
	function addsub() {
		document.frm.style.visibility = "visible";
	}
	function addOK() {
		var sub_name = document.frm.sub_name.value;
		var param = "code=addSub&sub_name=" + sub_name;
		sendRequest("${pageContext.request.contextPath}/main.do", param,
				addOK2, "POST");
		cancel();
	}
	function addOK2() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				document.getElementById("members").innerHTML = "";
				listR();
				frm.sub_name.value="";
				frm.sub_name.focus();
			}
		}

	}
	function cancel(){
		document.frm.style.visibility='hidden';
	}
</script>
   <style type="text/css">
   table.type04 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: center;
    line-height: 1.5;
    border-top: 1px solid #ccc;
  margin : 20px 10px;
}
table.type04 th {
    width: 250px;
    padding: 10px;
    text-align: center;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
table.type04 td {
    width: 50px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
table.type04 tbody tr:hover td{
		background: #FAFAFA;
}
table.type04 tbody tr:hover th{
		background: #FAFAFA;
}
   </style>
</head>
<body>
	<div id="members"></div>
	<input type="button" value="과목개설" onclick='addsub()'>
	<input type="button" value="로그아웃" onclick='logout()'>
	<form style='visibility: hidden' name='frm'>
		<table border='1'>
			<tr>
				<th>제목</th>
				<td><input type='text' size="40" name='sub_name'></td>
			</tr>
			<tr>
				<td><input type='button' value="개설"
					onclick='addOK()'></td>
				<td><input type='reset' value="취소"
					onclick="cancel()"></td>
					
			</tr>

		</table>
	</form>
</body>
</html>
