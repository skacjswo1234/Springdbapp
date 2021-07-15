<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 절대경로가 아닌 상대경로한다. -->
<%@ include file="../layout/header.jsp" %>

<div class="container">
<!-- submit를 하면 username=xxx&password=xxx&email=xxx&address=xxx -->
<form action="/user/${principal.id}" method="post"> <!-- 원래 method = put -->
 <div class="form-group">
    <label for="username">Username:</label>
    <input value="${principal.username}" type="text" class="form-control" placeholder="Enter username" readonly="readonly"/>
  </div>
  <div class="form-group">
    <label for="email">Email:</label>
    <input value="${principal.email}"  type="email" class="form-control" placeholder="Enter email" readonly="readonly"/>
  </div>
  <div class="form-group">
    <label for="pwd">Password:</label>
    <input value="${principal.password}"  type="password" class="form-control" placeholder="Enter password" name="password" required="required"/>
  </div>
    <input class="btn-btn-info" type="button" onClick="goPopup();" value="주소 찾기"/>
   <div class="form-group">
    <label for="address">Address:</label>
    <input value="${principal.address}"  type="text" class="form-control" placeholder="Enter address" name="address" id="address" readonly="readonly"/>
  </div>
  <button type="submit" class="btn btn-primary">회원수정</button>
</form>
</div>


<script>

function goPopup(){
	var pop = window.open("/juso","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}

function jusoCallBack(roadFullAddr){
	let addressEL = document.querySelector("#address");
	addressEL.value =  roadFullAddr;
	console.log(addressEL);
// 		document.form.address.value = roadFullAddr;
}
</script>

<%@ include file="../layout/footer.jsp" %>