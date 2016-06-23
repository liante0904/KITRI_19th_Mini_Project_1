<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교수용 과목 개설 페이지</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/httpRequest.js"></script>
<script type="text/javascript">
window.onload = function() {
	listSub();
}
function listSub() {
	sendRequest("${pageContext.request.contextPath}/main.do",
			"code=listSub", prtList, "POST");
}
function prtList() {
	if (httpRequest.readyState == 4) {
		if (httpRequest.status == 200) {
			var res = httpRequest.responseText;
			var subList = eval("(" + res + ")");
			for (i = 0; i < subList.length; i++) {
				makesubDiv(subList[i]);
			}
		}
	}
}
function makesubDiv(sub) {
	var subDiv = document.createElement("tr");
	subDiv.setAttribute("id", "sub_" + sub.num);
	var html = "<th style='width: 80px'>"+sub.num+"</th><td id='rname_"+sub.num+"'>"
			+ sub.name
			+ "</td>"
			+ "<td id='btn_"+sub.num+"'><input type='button' value='생성' onclick='addSub("
			+ sub.num
			+ ")'></td>";
	subDiv.innerHTML = html;
	var subList = document.getElementById("subT");
	subList.appendChild(subDiv);
}
function addSub(sub){
	location.href = "${pageContext.request.contextPath}/main.do?code=subInfo&num="+sub;
}
function _list(){
	location.href = "${pageContext.request.contextPath}/jsp/listOpen.jsp";
}
   </script>
   <style type="text/css">
   table.type04 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: left;
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
<h3>생성된 과목 목록</h3>
<table class='type04'><thead><tr><th style='width: 80px'>과목번호</th><th>과목제목</th><th style='width: 50px'></th></tr></thead><tbody id="subT"></tbody></table>
<input type="button" value="목록으로가기" onclick="_list()">
</body>
</html>