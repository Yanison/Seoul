<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
	
<link type="text/css" rel="stylesheet" href="./resources/css/adduser.css">


<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="./resources/js/adduser.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>



</head>
<body style="min-width:1600px;">

<div class="login-wrapper">

<!-- header -->
<%@ include file="./include/header.jsp" %>




<section class="login-box">

		<div class="center-body">
			
			<a title="황비트" class="logo-link" href="http://127.0.0.1:8080/seoul/" style="margin:0 0 10px; width:150px; height:50px;"></a>
			<span class="title" style="margin:0 0 10px;">회원가입</span>
			<input id="id"  class="app-inp" type="text" name="myinfo" placeholder="아이디"/>
			<div id="id-alert" class="alert-box"></div>
			<input id="pw"  class="app-inp" type="text" name="myinfo" placeholder="비밀번호 : 영문, 숫자, 특수문자를 모두 조합하여 최소 8자리 이상 "/>
			<div id="pw-alert" class="alert-box"></div>
			<input id="pw_chk"  class="app-inp" type="text" name="myinfo" placeholder="비밀번호 확인"/>
			<div id="pw_chk-alert" class="alert-box"></div>
			<input id="nickname"  class="app-inp" type="text" name="myinfo" placeholder="닉네임"/>
			<div id="nickname-alert" class="alert-box"></div>
			<input id="address"  class="app-inp" type="text" name="myinfo" placeholder="주소"/>
			<div id="address-alert" class="alert-box"></div>
			<input id="tel"  class="app-inp" type="text" name="myinfo" placeholder="전화번호"/>
			<div id="tel-alert" class="alert-box"></div>
			
			<div class="gender-box-wrapper">
				<label class="gender-box">
					<input id="gd-men" type="radio" name="gender" value="m"/>
					<label for="gd-men" >남자</label>
				</label>
				
				<label class="gender-box">
					<input id="gd-woman" type="radio" name="gender" value="w"/>
					<label for="gd-woman">여자</label>
				</label>
			</div>
			<div class="gender-alert-wrapper" >
				<div id="gender-alert" class="alert-box"></div>
			</div>
			
			
			<button id="signup-btn" class="app-long-btn">
				가입하기
			</button>
		</div>


</section>




<!-- footer -->
<%@ include file="./include/footer.jsp" %>


</div>



</body>
</html>




