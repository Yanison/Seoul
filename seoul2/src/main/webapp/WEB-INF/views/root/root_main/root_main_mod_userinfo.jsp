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
	
<link type="text/css" rel="stylesheet" href="./resources/css/root.css">


<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="./resources/js/root.js"></script>
<script src="./resources/js/root_main_mod_userinfo.js"></script>


</head>
<body>

	<input id="current-user-idx" type="hidden" value="${user_idx}"/>

	<div class="body-wrapper">
	
	
			<div class="left-wrapper">
			
				<%@ include file="../root_left_section/leftnavi_main.jsp" %>			
			</div>

			
			<div class="right-wrapper">  
				<section class="right">
					
					<%@ include file="../root_right_section/right_include/layout_right_header.jsp" %>
					
					<div class="search-bar">
					
						<div class="search-bar-title">
						유저 정보 수정
						</div>
					
					
					
					</div>
					
					<main class="form_section">
							

						<div class="form-wrapper">
		
							<h1 class="title" >유저 정보 수정</h1>	
							<form class="user_form" action="" >
								
								<div class="personal_info">
								
								<h4 class="form-title form-contents">기본정보</h4>
								
								<span  class="name form-contents">닉네임</span>
								<input id="nickname" class="form-control form-control-lg form-contents" type="text" placeholder="이름" aria-label=".form-control-lg example" >
								
								<span  class="name form-contents">생년월일</span>
								<input id="dob" class="form-control form-control-lg form-contents" type="text" placeholder="생년월일" aria-label=".form-control-lg example" data-useInfo="${userInfo.dob}" value="${userInfo.dob}">
								
								<span  class="name form-contents">휴대폰 번호</span>
								<input id="" class="form-control form-control-lg form-contents" type="text" placeholder="휴대폰 번호" aria-label=".form-control-lg example">
								
								<span  class="name form-contents">여권영문 이름</span>
								<input id="passPortNameEng" class="form-control form-control-lg form-contents" type="text" placeholder="여권 영문 이름" aria-label=".form-control-lg example" value="${userInfo.passPortNameEng}">
								
								<span  class="name form-contents">이메일</span>
								<input id="email" class="form-control form-control-lg form-contents" type="text" placeholder="이메일" aria-label=".form-control-lg example" value="${userInfo.email}">
								
								<span  class="name form-contents">거주지 주소</span>
								<input id="address" class="form-control form-control-lg form-contents" type="text" placeholder="거주지 주소" aria-label=".form-control-lg example">
								<div class="address-detail-box">
									<input id="addressDetail" class="form-control form-control-lg form-contents" type="text" placeholder="상세주소" aria-label=".form-control-lg example" value="${userInfo.addressDetail}">
									 <div class="form-check">
									  <input id="detail_check1" class="form-check-input form-contents" type="checkbox" value="" id="flexCheckDefault">
									  <label class="form-check-label" for="flexCheckDefault">
									    상세주소 없음
									  </label>
									</div>
								</div>
								</div>
								
								
								
								<div class="occcupation_info">
								
								<h4  class="name form-contents">직업/상세</h4>
								<div class="job_select">
									
									<select id="jobInfo" class="form-select form-select-lg mb-3 form-contents" aria-label=".form-select-lg example">
									  
									  <option value="근로소득자">근로소득자</option>
									  <option value="자영업자">자영업자</option>
									  <option value="무직">무직</option>
									  <option value="주부">주부</option>
									  <option value="종교인">종교인</option>
									  <option value="정년퇴직자">정년퇴직자</option>
									  <option value="학생">학생</option>
									  <option value="정치인">정치인</option>
									</select>
									
									<select id="jobType" class="form-select form-select-lg mb-3 form-contents" aria-label=".form-select-lg example" >
									  
									  <option value="급여소득자">급여소득자</option>
									  <option value="공무원">공무원</option>
									  <option value="카지노/대부업 종사자">카지노/대부업 종사자</option>
									  <option value="교육계 종사자">교육계 종사자</option>
									  <option value="가상자산업 종사자">가상자산업 종사자</option>
									</select>
								
								</div>
								
								<h4 class="form-title form-contents" >직장정보</h4>
								
								<span id="jobName" class="name form-contents">직장명</span>
								<input id="job" class="form-control form-control-lg form-contents" type="text" placeholder="직장명" aria-label=".form-control-lg example" value="${userInfo.jobName}">
								<span  class="name form-contents">직장주소</span>
								<input id="jobAddress" class="form-control form-control-lg form-contents" type="text" placeholder="직장 주소" aria-label=".form-control-lg example" value="${userInfo.jobAddress}">
								<div class="address-detail-box form-contents">
									<span  class="name form-contents">직장주소상세</span>
									<input id="jobAddressDetail" class="form-control form-control-lg" type="text" placeholder="직장 주소상세" aria-label=".form-control-lg example" value="${userInfo.jobAddressDetail}">
									<div class="form-check" style="margin:0 0 20px;">
									  <input id="detail_check2" class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
									  <label class="form-check-label" for="flexCheckDefault">
									    상세주소 없음
									  </label>
									</div>
								</div>
								
								</div>

								
								
							</form>
							
								<div class="button_div">
									<button type="button" id="ok" class="btn btn-primary btn-lg">등록</button>
									<button type="button" id="cancel" class="btn btn-primary btn-lg">취소</button>
								</div>
						</div>
					
					</main>
				</section>

			</div>

		

	
	</div>


</body>
</html>
