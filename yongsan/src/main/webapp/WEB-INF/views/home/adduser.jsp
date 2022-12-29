<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ page session="false" %>
<html>
<head>
<title>회원가입</title>

<%@ include file="../rscs/basicRscs.jsp" %>


</head>
<body style="min-width:1600px;">

<div class="login-wrapper">

<!-- header -->
<%@ include file="../include/header.jsp" %>

<script>


</script>
<section class="login-box">
		
		<form method="get"class="center-body" name="addUser" action="/yongsancode/memberGroupUpdt">
			
			<a title="황비트" class="logo-link" href="http://127.0.0.1:8080/seoul/" style="margin:0 0 10px; width:150px; height:50px;"></a>
			<span class="title" style="margin:0 0 10px;">회원가입</span>
			
			<input id="memberId"  class="app-inp addUserInput" type="text" name="아이디" placeholder="아이디" onkeyup="dubpleCheck(this,0)"/>
			<div id="memberId_alert" class="alert-box"></div>
			
			<input id="memberPw"  class="app-inp addUserInput" type="password" name="비밀번호" placeholder="비밀번호 : 영문, 숫자, 특수문자를 모두 조합하여 최소 8자리 이상 "/>
			<div id="memberPw_alert" class="alert-box"></div>
			
			<input id="memberPw-chk"  class="app-inp addUserInput" type="password" name="비밀번호 확인" placeholder="비밀번호 확인"/>
			<div id="memberPw-chk_alert" class="alert-box"></div>
			
			<input id="memberNickname"  class="app-inp addUserInput" type="text" name="닉네임" placeholder="닉네임" onkeyup="dubpleCheck(this,1)"/>
			<div id="memberNickname_alert" class="alert-box">
			</div>
				
			<div class="eamilBox">
				<input id="MemberEmail"  class="app-inp" type="text" name="이메일 아이디" placeholder="이메일"/>
				<div style="line-height: 47px;">@</div>
				<select id="MemberEmailAddress" class="app-inp addUserInput" name="이메일 주소">
					<option >email</option>
					<option value="naver.com" >naver.com</option>
					<option value="gmail.com">gmail.com</option>
					<option value="hanmail.com">hanmail.com</option>			
				</select>
			</div>
			<div id="email-alertBox" class="alert-box">
				<div id="MemberEmail_alert" class="alert-box" style="padding:0"></div>
			</div>
			<div class="emailVali">
				<input id="emailVali"  class="app app-inp addUserInput" type="text" name="인증번호" placeholder="인증번호"/>
				<button type="button" id="emailValiBtn" class="app app-long-btn" onclick="requestEmailValidation()">인증번호 받기</button>
			</div>
			<input id="emailValiHddn" type="hidden">
			<div id="emailVali_alert" class="alert-box" ></div>
			
			
			<input id="signBtn" type="button" id="signup-btn" class="app-long-btn" value="가입하기" onclick="loginVali()" style="margin-top:20px;"></input>
		</form>
		
</section>


<!-- footer -->
<%@ include file="../include/footer.jsp" %>


</div>



</body>
<link type="text/css" rel="stylesheet" href="/resources/css/homeComponent/adduser.css">
<script src="/../resources/js/homeComponent/addUser/Validation.js"></script>
<script src="../resources/js/homeComponent/addUser/kkoGetAddr.js"></script>
<!-- <script src="../resources/js/homeComponent/addUser/test.js"></script> -->
</html>


