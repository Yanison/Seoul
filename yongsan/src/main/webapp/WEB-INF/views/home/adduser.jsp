<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ page session="false" %>
<html>
<head>
<title>회원가입</title>

<%@ include file="../rscs/basicRscs.jsp" %>

<link type="text/css" rel="stylesheet" href="./resources/css/homeComponent/adduser.css">
<script src="../resources/js/homeComponent/addUser/Validation.js"></script>
<script src="../resources/js/homeComponent/addUser/kkoGetAddr.js"></script>
<!-- <script src="../resources/js/homeComponent/addUser/test.js"></script> -->

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
			
			<input id="memberId"  class="app-inp" type="text" name="memberId" placeholder="아이디"/>
			<!-- <span id="duplechk" class="testbtn">중복체크</span>
			<span id="testVali" class="testbtn">발리첵</span> -->
			<input id="isAllowedId" type="hidden" value="0">
			<div id="memberId-alert" class="alert-box"></div>
			
			<input id="memberPw"  class="app-inp" type="text" name="memberPw" placeholder="비밀번호 : 영문, 숫자, 특수문자를 모두 조합하여 최소 8자리 이상 "/>
			<div id="memberPw-alert" class="alert-box"></div>
			
			<input id="memberPw-chk"  class="app-inp" type="text" placeholder="비밀번호 확인"/>
			<div id="memberPw_chk-alert" class="alert-box"></div>
			
			<input id="memberName"  class="app-inp" type="text" name="memberName" placeholder="이름"/>
			<div id="memberName-alert" class="alert-box"></div>
			
			<input id="memberNickname"  class="app-inp" type="text" name="memberNickname" placeholder="닉네임"/>
			<div id="nickname-alert" class="alert-box"></div>
			
			<input id="MemberTel"  class="app-inp" type="text" name="MemberTel" placeholder="전화번호"/>
			<div id="MemberTel-alert" class="alert-box"></div>
			
			<div class="eamilBox">
				<input id="MemberEmail"  class="app-inp" type="text" name="memberEmailId" placeholder="이메일"/>
				<div style="line-height: 47px;">@</div>
				<select id="EmailId"class="app-inp" name="EmailDM">
					<option value="0">email</option>
					<option value="1" >naver.com</option>
					<option value="2">gmail.com</option>
					<option value="3">hanmail.com</option>			
				</select>
			</div>
			<div id="email-alert" class="alert-box"></div>
			
			<div class="addrBox">
				<div class="srchAddr">
					<input id="kkoAddr" class="app-inp" type="button" value="우편번호 찾기">	
					<input type="text" class="app-inp" id="postcode" name="postCode" placeholder="우편번호"  readonly>					
				</div>
				
				<input type="text" class="app-inp" id="roadAddress" name="memberAddrLoad" placeholder="도로명주소"   readonly>
				
				<input type="text" class="app-inp" id="jibunAddress" name="memberAddrJibun" placeholder="지번주소"  readonly >
				
				<span id="guide" style="color:#999;display:none"></span>
				
				<input type="text" class="app-inp" id="detailAddress" name="addrDetail" placeholder="상세주소">
				
				<div id="addressDetail-alert" class="alert-box"></div>
				
				<input type="text" class="app-inp" id="extraAddress" name="addrLoca"  placeholder="참고항목" readonly>
				
				<div class="srchAddr">
					<input id="lat" class="app-inp" type="text" name="addrLat" placeholder="위도" readonly>
					<input id="long" class="app-inp" type="text" name="addrLong" placeholder="경도" readonly>
				</div>
				
				<div id="addr-alert" class="alert-box"></div>
			</div>
			
			
			<div id="tel-alert" class="alert-box"></div>
			
			<div class="gender-box-wrapper">
				<label class="gender-box">
					<input id="gd-men" type="radio" name="memberGender" value="1"/>
					<label for="gd-men" >남자</label>
				</label>
				
				<label class="gender-box">
					<input id="gd-woman" type="radio" name="memberGender" value="2"/>
					<label for="gd-woman">여자</label>
				</label>
			</div>
			<div class="gender-alert-wrapper" >
				<div id="gender-alert" class="alert-box"></div>
			</div>
			<input type="submit" id="signup-btn" class="app-long-btn" value="가입하기"></input>
			<a href="http://127.0.0.1:8082/adduser">refresh</a>
		</form>
		
</section>


<!-- footer -->
<%@ include file="../include/footer.jsp" %>


</div>



</body>
</html>