<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ page session="false" %>
<html>
<head>
<title>회원가입</title>

<%@ include file="../rscs/basicRscs.jsp" %>

<link type="text/css" rel="stylesheet" href="./resources/css/homeComponent/adduserkko.css">
<!-- <script src="../resources/js/homeComponent/addUser/Validation.js"></script>
<script src="../resources/js/homeComponent/addUser/kkoGetAddr.js"></script>
<script src="../resources/js/homeComponent/addUser/test.js"></script> -->


<style>
.logintest{
font-size:20px;
border:1px solid #black;
cursor:pointer;
}
</style>

</head>
<body style="min-width:1600px;">

<div class="login-wrapper">

<!-- header -->
<%@ include file="../include/header.jsp" %>

<script>


</script>



<section class="login-box">
		<div class="center-body" name="addUser">
			<c:choose>
				<c:when test="${nowPage eq 'adduserkko'}">
					<h1 class="h1 texts">회원가입</h1>
					<p class="p1 texts"><i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp; 간편하게 카카오로 로그인 버튼을 눌러 회원가입을 해보세요!</p>
					<p class="p1 texts"><i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp; 방문하신 사이트 주소가 아래와 일치한지 확인해주세요</p>
					<p class="p2 texts">https://hwangbit.com</p>
				 	 <a href="https://kauth.kakao.com/oauth/authorize?client_id=0150c62dea4406ba52dbae88e0293319&redirect_uri=http://127.0.0.1:8082/kakaologin&response_type=code">
				 	 	<img src="../resources/image/kakao_login_medium_wide.png"/>
				 	 </a>
				 	 <div class="pBox">
				 	 <p class="p3" style="display:block"><i class="fa-solid fa-circle"></i> &nbsp;&nbsp; 카카오계정으로 간편하게 안전하게 로그인(회원가입)할 수 있습니다.</p>
				 	 <p class="p3" style="display:block"><i class="fa-solid fa-circle"></i> 
				 	 	&nbsp;&nbsp; 다른 방법으로 회원가입 하시겠어요?
				 	 	<a href="http://127.0.0.1:8082/adduser" class="p3">
					 	 다른 방법으로 회원가입하기
					 	</a>
				 	 </p>
				 	 <p class="p3"><i class="fa-solid fa-circle"></i> &nbsp;&nbsp; 카카오계정이 기억나지 않으시나요?</p>
				 	 <a class="p3" href="https://accounts.kakao.com/weblogin/find_password?continue=https://accounts.kakao.com/weblogin/account/info">
				 	 확인방법
				 	 </a>
				 	 </div>
				</c:when>
				<c:otherwise>
					<h1 class="h1 texts">로그인</h1>
					<p class="p1 texts"><i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp; 방문하신 사이트 주소가 아래와 일치한지 확인해주세요</p>
					<p class="p2 texts">https://hwangbit.com</p>
				 	 <a href="https://kauth.kakao.com/oauth/authorize?client_id=0150c62dea4406ba52dbae88e0293319&redirect_uri=http://127.0.0.1:8082/kakaologin&response_type=code">
				 	 	<img src="../resources/image/kakao_login_medium_wide.png"/>
				 	 </a>
				 	 <div class="pBox">
				 	 <p class="p3" style="display:block"><i class="fa-solid fa-circle"></i> &nbsp;&nbsp; 카카오계정으로 간편하게 안전하게 로그인(회원가입)할 수 있습니다.</p>
				 	 <p class="p3" style="display:block"><i class="fa-solid fa-circle"></i> 
				 	 	&nbsp;&nbsp; 다른 방법으로 로그인 하시겠어요?
				 	 	<a class="p3" href="http://127.0.0.1:8082/userLogin">
					 	 다른 방법으로 로그인하기
					 	</a>
				 	 </p>
				 	 <p class="p3"><i class="fa-solid fa-circle"></i> &nbsp;&nbsp; 카카오계정이 기억나지 않으시나요?</p>
				 	 <a class="p3" href="https://accounts.kakao.com/weblogin/find_password?continue=https://accounts.kakao.com/weblogin/account/info">
				 	 확인방법
				 	 </a>
				 	 </div>
				</c:otherwise>
			</c:choose>
			
			
		 	 
		 	 

		</div>
</section>

<!-- footer -->
<%@ include file="../include/footer.jsp" %>


</div>
<!--  -->

<%-- https://kauth.kakao.com/oauth/authorize?client_id=${0150c62dea4406ba52dbae88e0293319}&redirect_uri=${http://127.0.0.1:8082/adduserkko}&response_type=code
https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${0150c62dea4406ba52dbae88e0293319}&redirect_uri=${http://127.0.0.1:8082/adduserkko}

https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=0150c62dea4406ba52dbae88e0293319&redirect_uri=http://127.0.0.1:8082/adduserkko --%>
</body>
</html>