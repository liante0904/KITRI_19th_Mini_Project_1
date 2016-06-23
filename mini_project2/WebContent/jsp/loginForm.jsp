<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

window.onload = function(){
	if (frm.code.value == '0') {
		alert('학번과 이름을 확인해주세요.');
	} else if(frm.code.value=='1'){
		alert('세션오류::');
	}
}
   function login() {
      var frm = document.frm;
      frm.code.value = "login";
      if (frm.num.value == '') {
         alert('학번을 입력해주세요.');
      } else if(frm.name.value == ''){
         alert('이름을 입력해주세요.');         
      } else if(frm.type.value ==0){
         alert('교내 역할을 선택해주세요.');
      } else {
         frm.submit();   
      }
   }
   function join() {
      var frm = document.frm;
      frm.code.value = "join_ok";
      frm.submit();
   }
   function keyevent() {
      // 키보드 enter키 눌렀을때 동작하기
      if(event.keyCode ==13){
         // TODO : 실행시킬 코드
         login();
      }
   }
</script>
<title>학사 관리 메인페이지</title>
</head>
<body>
<h3>학사 시스템 메인페이지 입니다.</h3>
   <form action="${pageContext.request.contextPath}/login.do" name="frm"
      method="post">
      <p>
         NUM: <input type="text" name="num" onkeydown="keyevent()">
      </p>
      <p>
         NAME: <input type="text" name="name" onkeydown="keyevent()">
      </p>
      <select name="type" size="1">
         <option value="0" selected onkeydown="keyevent()">교내 역할을 선택하세요</option>
         <option value="1">교직원</option>
         <option value="2">교수</option>
         <option value="3">학생</option>
      </select>
      <p>
         <input type="button" onclick="login()" value="로그인"> <input
            type="button" onclick="join()" value="회원가입"> <input
            type="hidden" name="code" value="${error }">
   </form>
</body>
</html>