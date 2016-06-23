<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/httpRequest.js"></script>
<script type="text/javascript">

   function open_chk() {
      var loopback = document.openForm;
/*       if (loopback.open_num.value == "") {
         alert("강의 번호을 입력 해주세요.")
         loopback.open_num.focus;
      } else  */if (loopback.open_sub_num.value == "") {
         alert("과목번호를 입력 해주세요.")
         loopback.open_sub_num.focus;
      } else if (loopback.open_room.value == "") {
         alert("강의실을 입력 해주세요.")
         loopback.open_room.focus;
      } else if (loopback.open_sub_day.value == "") {
          alert("강의 요일을 입력 해주세요.")
          loopback.open_sub_day.focus;
       } else if (loopback.open_sub_time.value == "") {
           alert("강의시간을 입력 해주세요.")
           loopback.open_sub_time.focus;
        } else {
         loopback.code.value = "open_chk";
         alert("강의가 개설되었습니다.");
         loopback.submit();
      }    
   }
</script>


<title>교수용 강의 개설 페이지</title>
</head>
<body>




   <h2>과목 등록</h2> 

   <form action="${pageContext.request.contextPath}/main.do?code=addOpen" method="post" name=openForm>
<!--       *강의 번호 : &nbsp;&nbsp;<input type="text" name="open_num" size="10" placeholder="번호">
          <br> --> <br> 
         *과목 번호 : <input
         type="text" name="open_sub_num" size="8" readonly="readonly" value="${num }"> <br>
      <br>
              *강의실 : <input
         type="text" name="open_room" size="8" placeholder="강의실"> <br>
      <br>
      	*과목 제목 : <input
         type="text" name="open_sub_name" size="8" readonly="readonly" value="${name }"> <br>
      <br>
              *강의 요일 : <select name="open_sub_day">
    <option value="">강의 요일 선택</option>
    <option value="월">월</option>
    <option value="화">화</option>
    <option value="수">수</option>
    <option value="목">목</option>
    <option value="금">금</option>
    </select>
     <br>
      <br>
              *강의 시간 : <select name="open_sub_time">
    <option value="">강의 시간</option>
    <option value="08:00~09:00">8시~9시</option>
    <option value="09:00~10:00">9시~10시</option>
    <option value="10:00~11:00">10시~11시</option>
    <option value="11:00~12:00">11시~12시</option>
    <option value="12:00~13:00">12시~13시</option>
    <option value="13:00~14:00">13시~14시</option>
    <option value="14:00~15:00">14시~15시</option>
    <option value="15:00~16:00">15시~16시</option>
</select> 
<br>
      <br>
           *교수 번호 : <input
         type="text" name="open_prof_num" size="8" readonly="readonly" value='<%= session.getAttribute("num")%>'> <br>
      <br>          

      <input type="button"
         value="강의 개설" name="name" onclick="open_chk()"> 
         <input type="hidden" name="code" value="">
   
   </form>
</body>
</html>
