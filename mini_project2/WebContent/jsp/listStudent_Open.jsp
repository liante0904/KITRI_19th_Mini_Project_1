<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학생용 수강 신청 페이지</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/httpRequest.js"></script>
<script type="text/javascript">
   window.onload=function(){
      var param = "code=listOpenAll";
      sendRequest("${pageContext.request.contextPath}/main.do", 
            param, listResult, "POST");
   }
   var nN =0;
   
   function listResult(){
      if(httpRequest.readyState==4){
         if(httpRequest.status==200){
            var str = httpRequest.responseText;
            var o = eval("(" + str + ")");
			var message = "<h3>수강 신청 가능한 목록</h3><table class='type04'><thead><tr><th style='width: 100px'>교수명</th><th>강의제목</th><th style='width: 80px'>강의실</th><th style='width: 100px'>강의날짜</th><th style='width: 90px'>강의시간</th><th style='width: 50px'></th></tr></thead><tbody>";
			for (i = 0; i < o.length; i++) {
				message += "<tr><td>"+o[i].prof_name+"</td><td>"+o[i].subject_name+"</td><td>"+o[i].room+"</td><td>"+o[i].sub_day+"</td><td>"+o[i].sub_time+"</td><td><input type='button' value='신청' onclick='chk("+o[i].num+")'></td></tr>";
				
			}
			message +="</tbody></table>"
			//html += "num: "+o.num+", name: "+o.name+", tel: "+o.tel;
			document.getElementById("members").innerHTML = message;
         }
      }
   }

function logout(){
	location.href= "${pageContext.request.contextPath}/login.do?code=logout";
}
function sub_list(){
	location.href="${pageContext.request.contextPath}/jsp/listStudent_MyOpen.jsp";
}
function bt(){
	var num = '<%=session.getAttribute("num")%>';
    var param = "code=addReg&num="+num+"&sub_num="+nN;
    sendRequest("${pageContext.request.contextPath}/main.do", 
          param, aa, "POST");
	
	}
function chk(n){
	nN = n;
	var num = '<%=session.getAttribute("num")%>';
		var param = "code=regCHK&num=" + num + "&sub_num=" + n;
		sendRequest("${pageContext.request.contextPath}/main.do", param, aa,
				"POST");
	}
	function aa() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var o = eval("(" + str + ")");
				if (o.flag) {
					bt();
					alert("수강 신청되었습니다.");
					location.reload();
				} else {
					alert("해당 시간에 이미 신청된 강의가 있습니다.");
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
	margin: 20px 10px;
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
	<div id="members"></div>
	<input type="button" value="로그아웃" onclick='logout()'>
	<input type="button" value="내강의목록" onclick='sub_list()'>

</body>
</html>
