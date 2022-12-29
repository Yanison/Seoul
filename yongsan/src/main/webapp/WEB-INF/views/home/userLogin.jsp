<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>로그인</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
	
<link type="text/css" rel="stylesheet" href="./resources/css/homeComponent/login.css">


<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="/../resources/js/homeComponent/login.js"></script>
<script src="/../resources/js/homeComponent/naverLogin.js"></script>
<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>



</head>
<body style="min-width:1600px;">

<div class="login-wrapper">

<!-- header -->
<%@ include file="../include/header.jsp" %>




<section class="login-box">

		<div class="center-body">
			
			<a title="황비트" class="logo-link" href="/" style="margin:0 0 10px; width:150px; height:50px;"></a>
			<span class="title" style="margin:0 0 10px;">로그인</span>
			
			<input id="memberId"  class="app-inp" type="text" name="아이디" placeholder="아이디" />
			<div id="id-alert" class="alert-box"></div>
			
			<input id="memberPw"  class="app-inp" type="password" name="비밀번호" placeholder="비밀번호"/>
			<div id="pw-alert" class="alert-box"></div>
	
			<input type="button" id="this-login-btn" class="login-btn" value="로그인" onclick="loginVali()">
				
			<a class="login_sns kakao" href="https://kauth.kakao.com/oauth/authorize?client_id=0150c62dea4406ba52dbae88e0293319&redirect_uri=http://127.0.0.1:8082/kakaologin&response_type=code">
				<i class="fa-solid fa-comment fontaws" style="margin-right:10px;"></i>카카오 로그인
			</a>
			<a class="login_sns google" href="/adduser">일반 회원가입</a>
		</div>
</section>



<!-- footer -->
<%@ include file="../include/footer.jsp" %>


</div>



</body>
</html>




