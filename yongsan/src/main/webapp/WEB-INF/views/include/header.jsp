<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 

<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!-- <script src="./resources/js/home.js"></script> -->
<link type="text/css" rel="stylesheet" href="../resources/css/homeComponent/home.css">
<link type="text/css" rel="stylesheet" href="../resources/css/homeComponent/header.css">
<link rel="stylesheet" href="./resources/css/all.min.css">
<script src="../resources/js/homeComponent/header.js"></script>



	<div class="wrapper" style="height: 82px;">
		<header class="header1 header">
			<div class="header-left">
				<a title="황비트" class="logo-link" id="logo-link2">
						</a>
				<div class="header-left-contents">
					<span id="exchange" class="headee-contents">거래소</span> 
					<span id="wod" class="headee-contents">입출금</span>
					<span id="trasactionHis" class="headee-contents">투자내역</span> 
					<span id="helpcenter" class="headee-contents">고객센터</span>
					<span id="raise" class="headee-contents">코인동향</span>
				</div>


			</div>
			<div class="header-right">
				<c:choose>
					<c:when test="${empty idTokenKko}">
						<a id="kkoadduser"class="headee-contents" href="http://127.0.0.1:8082/userLoginkko">로그인</a>
						<a id="kkoadduser"class="headee-contents" href="http://127.0.0.1:8082/adduserkko">회원가입</a>
					</c:when>
					<c:otherwise>
						<span id="logout-btn" class="headee-contents">로그아웃</span> 
						<input type="hidden" id="idTokenKko" name="idTokenKko" value="${idTokenKko}"/>
						<c:choose>
							<c:when test="${idTokenKko eq '2344261226'}">
								<span id="adminPage" class="headee-contents" style="font-weight:800;color:rgb(18, 97, 196);" >관리자 페이지</span> 
								<span class="headee-contents" style="font-weight:800">${memberName}</span>
							</c:when>
							<c:otherwise>
								<span class="headee-contents" style="font-weight:800">${memberName}</span>
							</c:otherwise>
						</c:choose>
						<span id="goMyPage" class="headee-contents" id="goMyPafe">MY</span>
					</c:otherwise>
				</c:choose>
			
				
				<span class="headee-contents"><i class="fa-solid fa-bars"></i></span>
			</div>
		</header>
	</div>