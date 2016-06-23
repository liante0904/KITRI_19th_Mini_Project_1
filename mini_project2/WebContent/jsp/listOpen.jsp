<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교수용 강의개설 페이지</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/httpRequest.js"></script>
<script type="text/javascript">
	var allnum = 0;
	window.onload = function() {
		var param = "code=listOpen";
		sendRequest("${pageContext.request.contextPath}/main.do", param,
				listResult, "POST");
	}

	function listResult() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var o = eval("(" + str + ")");
				var message = "<h3>교수님이 개설하신 강의 목록</h3><br>";
				message +="<table class='type04'><thead><tr><th style='width: 80px'>강의번호</th><th>강의제목</th><th style='width:80px '>강의실</th><th style='width: 100px'>강의날짜</th><th  style='width: 90px'>강의시간</th><th style='width: 50px'></th></tr></thead><tbody>"
				if(o.length==0){
					message+="<tr><td colspan='5'>개설하신 강의가 없습니다.</td></tr>"
				} 
				for (i = 0; i < o.length; i++) {
					message += "<tr><td>"+o[i].num+"</td><td>"+o[i].subject_name+"</td><td>"+o[i].room+"</td><td>"+o[i].sub_day+"</td><td>"+o[i].sub_time+"</td><td style='width:10px'>"+"<input type='button' value='삭제' onclick='regList("
					+ o[i].num + ")'>"+"</td></tr>";
				}
				message +="</tbody></table>"
				document.getElementById("lis").innerHTML = message;
			}
		}
	}
	function mod(n,m){
		alert(m);
		window.open("${pageContext.request.contextPath}/jsp/openMod.jsp?opennum="+n.num+"&name="+m, '', 'width=640,height=480');
	}
	function add() {
		location.href = "${pageContext.request.contextPath}/jsp/addOpen_list.jsp";
	}
	function logout() {
		location.href = "${pageContext.request.contextPath}/login.do?code=logout";
	}
	function regList(num) {
		allnum = num;
		var param = "code=regList&num=" + num;
		sendRequest("${pageContext.request.contextPath}/main.do", param, reg,
				"POST");
	}
	function reg() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var o = eval("(" + str + ")");
				var message = "<h4>수강중인 학생들 목록</h4><table class='type04'><tr><th style='width: 150px'>이름</th></tr>";
				if(o.length==0){
					message+="<tr><td colspan='5' style='font-size: 12px'>수강중인 학생이 없습니다.</td></tr>"
				} 
				for (i = 0; i < o.length; i++) {
					message += "<tr><td>" + o[i].name + "</td></tr>";
				}
				message += "</table><input type='button' value='강의취소' onclick='delOK()'>"
				//html += "num: "+o.num+", name: "+o.name+", tel: "+o.tel;
				document.getElementById("members").innerHTML = message;
			}
		}
	}
	function delOK() {
		var flag=confirm("강의를 삭제하시면 수강중인 학생들 모두 강의 취소 됩니다. \n동의하십니까?");
		if(flag){
		var param = "code=delRegAll&num=" + allnum;
		sendRequest("${pageContext.request.contextPath}/main.do", param, delGo,
				"POST");
		}else{return;}
	}
	function delGo() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var o = eval("(" + str + ")");
				if(o.flag){
					alert("삭제 성공");location.reload();
				}
			}
		}
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

<h2>교수용 강의개설 페이지</h2>
	<div id="lis"></div>
	<input type="button" value="강의개설" onclick="add()">
	<input type="button" value="로그아웃" onclick='logout()'>
	<div id="members">
		<!-- 수강신청한 학생들 목록 -->
	</div>
</body>
</html>
