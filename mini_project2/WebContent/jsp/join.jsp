<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/httpRequest.js"></script>
<%
	request.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript">

	function load(url) {
		var txt = document.frm.num.value;
		var params = "code=checkId&num=" + txt;
		if (txt != "") {
			sendRequest(url, params, viewMessage, 'POST');
		} else{
			var mySpan = document.getElementById("mySpan");
			mySpan.innerHTML = "";
		}
	}
	function viewMessage() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var result = httpRequest.responseText;
				var mySpan = document.getElementById("mySpan");
				mySpan.innerHTML = result;
			} else {
				alert("실패: " + httpRequest.status);
			}
		}
	}
</script>
<script type="text/javascript">
	function chkForm(a) {
		if (a.num.value == "") {
			alert("NUM 중복체크 하십시오.");
			return false;
		}

		return true;
	}
</script>
<title>회원 등록 페이지</title>
</head>

<h2>회원 등록 페이지</h2>
<body>
	<form action="${pageContext.request.contextPath}/login.do?code=join"
		name="frm" method="post" onsubmit="return chkForm(this)">
		<table border="1">
			<tr>

				<th>NUM:</th>
				<td><input type="text" name="num"
					onkeyup="load('${pageContext.request.contextPath}/login.do')">
					<span id="mySpan"></span></td>
			</tr>
			<tr>
				<th>NAME:</th>
				<td colspan="2"><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>TEL:</th>
				<td><input type="text" name="tel"></td>
			</tr>
			<tr>
				<th>EMAIL:</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<th>DEPT:</th>
				<td><input type="text" name="dept"></td>
			</tr>
			<tr>
				<th>유형:</th>
				<td> <input type="radio" name="ty" value="1">교직원  <input
					type="radio" name="ty" value="2">교수  <input type="radio"
					name="ty" value="3">학생</td>
			</tr>
			<tr>
				<td colspan="2"><input type="hidden" name="type" value="join">
					<input type="submit"
					value="가입">&nbsp; <input type="reset" value="취소">
					<input type="button" value="이전페이지" onclick='javascript:history.back()'>
					 </td>
		</table>
	</form>
</body>
</html>