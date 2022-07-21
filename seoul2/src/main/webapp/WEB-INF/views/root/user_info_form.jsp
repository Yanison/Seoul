<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>CoinHwang</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
	
<link type="text/css" rel="stylesheet" href="./resources/css/user_info_form.css">


<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="./resources/js/user_info_form.js"></script>


</head>
<body >


<div class="form-wrapper">
	<h1 class="title" >개인정보 관리, 수정사항은 세션호출후 저장</h1>	
	<form class="user_form" action="" >
		<h4 class="form-title form-contents">기본정보</h4>
		
		<span  class="name form-contents">이름</span>
		<input id="id" class="form-control form-control-lg" type="text" placeholder="이름" aria-label=".form-control-lg example">
		
		<span  class="name form-contents">생년월일</span>
		<input id="dob" class="form-control form-control-lg" type="text" placeholder="생년월일" aria-label=".form-control-lg example">
		
		<span  class="name form-contents">휴대폰 번호</span>
		<input id="" class="form-control form-control-lg" type="text" placeholder="휴대폰 번호" aria-label=".form-control-lg example">
		
		<span  class="name form-contents">여권영문 이름</span>
		<input id="passPortNameEng" class="form-control form-control-lg" type="text" placeholder="여권 영문 이름" aria-label=".form-control-lg example">
		
		<span  class="name form-contents">이메일</span>
		<input id="email" class="form-control form-control-lg" type="text" placeholder="이메일" aria-label=".form-control-lg example">
		
		<span  class="name form-contents">거주지 주소</span>
		<input id="" class="form-control form-control-lg" type="text" placeholder="거주지 주소" aria-label=".form-control-lg example">
		<div class="address-detail-box">
			<input id="addressDetail" class="form-control form-control-lg" type="text" placeholder="상세주소" aria-label=".form-control-lg example">
			 <div class="form-check">
			  <input id="detail_check1" class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
			  <label class="form-check-label" for="flexCheckDefault">
			    상세주소 없음 <button type="button" id="console1">콘솔로그</button>
			  </label>
			</div>
		</div>
		
		
		
		<h4 class="form-title form-contents" >직장정보</h4>
		<span  class="name form-contents" style="margin:10px 0;">직업/상세</span>
		<select id="jobInfo" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
		  <option selected>직업</option>
		  <option value="1">근로소득자</option>
		  <option value="2">자영업자</option>
		  <option value="3">무직</option>
		  <option value="4">주부</option>
		  <option value="5">종교인</option>
		  <option value="6">정년퇴직자</option>
		  <option value="7">학생</option>
		  <option value="8">정치인</option>
		</select>
		
		<select id="jobType" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
		  <option selected>상세</option>
		  <option value="1">급여소득자</option>
		  <option value="2">공무원</option>
		  <option value="3">카지노/대부업 종사자</option>
		  <option value="4">교육계 종사자</option>
		  <option value="5">가상자산업 종사자</option>
		</select>
		<span id="jobName" class="name form-contents">직장명</span>
		<input id="job" class="form-control form-control-lg" type="text" placeholder="직장명" aria-label=".form-control-lg example">
		<span  class="name form-contents">직장주소</span>
		<input id="jobAddress" class="form-control form-control-lg" type="text" placeholder="직장 주소" aria-label=".form-control-lg example">
		<div class="address-detail-box">
			<span  class="name form-contents">직장주소상세</span>
			<input id="jobAddressDetail" class="form-control form-control-lg" type="text" placeholder="직장 주소상세" aria-label=".form-control-lg example">
			<div class="form-check" style="margin:0 0 20px;">
			  <input id="detail_check2" class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
			  <label class="form-check-label" for="flexCheckDefault">
			    상세주소 없음 <button type="button" id="console2">콘솔로그</button>
			  </label>
			</div>
		</div>
		
		<div class="button_div">
			<button type="button" id="ok" class="btn btn-primary btn-lg">등록</button>
			<button type="button" id="cancel" class="btn btn-primary btn-lg">취소</button>
		</div>
		
	</form>
</div>
<!-- footer -->
<%@ include file="../include/footer.jsp" %>

</body>
</html>
