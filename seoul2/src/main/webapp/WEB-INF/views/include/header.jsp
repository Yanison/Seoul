<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<!-- <script src="./resources/js/home.js"></script> -->
<link type="text/css" rel="stylesheet" href="./resources/css/home.css">
<link rel="stylesheet" href="./resources/css/all.min.css">
<script src="./resources/js/header.js"></script>



	<div class="wrapper" style="height: 80px;">
		<header class="header">
			<div class="header-left">
				<a title="황비트" class="logo-link" href="http://127.0.0.1:8080/seoul2/">
						</a>
				<div class="header-left-contents">
					<span id="market" class="headee-contents">거래소</span> 
					<span id="earning" class="headee-contents">who's earning?</span>
					<span id="helpcenter" class="headee-contents">고객센터(만드는중)</span> 
					<span id="raise" class="headee-contents">코인동향</span> 
					<span id="rootmenu" class="headee-contents <c:if test="${me eq root }">active</c:if>">
					관리자메뉴</span> 
				</div>


			</div>

			<div class="header-right">
				<c:if test="${empty me}">
					<span id="login-btn" class="headee-contents">로그인</span> 
					<span id="adduser"class="headee-contents">회원가입</span>
				</c:if>
				
				<c:if test="${not empty me}">
					<span id="logout-btn" class="headee-contents">로그아웃</span> 
					<span class="headee-contents">${me.nickname}님 어서오세요</span> 
				</c:if>
			
				
				<span class="headee-contents">다운로드</span>
				<span class="headee-contents">국가선택</span>
			</div>
		</header>
	</div>