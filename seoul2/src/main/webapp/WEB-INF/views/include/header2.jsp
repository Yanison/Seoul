<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>


<link type="text/css" rel="stylesheet" href="./resources/css/helpcenter.css">
<!-- <script src="./resources/js/helpcenter.js"></script> -->
	
	
	<header class="header2">
		<div class="header-left">
				<a title="황비트" class="logo-link" href="http://127.0.0.1:8080/seoul2/">
						</a>
				<div class="header-left-contents">
					<span id="market" class="headee-contents">거래소</span> 
					<span id="wob" class="headee-contents">입출금</span>
					<span id="trasactionHis" class="headee-contents">투자내역</span> 
					<span id="helpcenter" class="headee-contents">고객센터</span>
					<span id="raise" class="headee-contents">코인동향</span> 
					<c:if test="${not empty root}">
					<span id="rootmenu" class="headee-contents" style="font-weight:900">관리자메뉴</span> 
					</c:if>
				</div>


			</div>

			<div class="header-right">
				<c:if test="${empty me}">
					<span id="login-btn" class="headee-contents">로그인</span> 
					<span id="adduser"class="headee-contents">회원가입</span>
				</c:if>
				
				<c:if test="${not empty me}">
					<span id="logout-btn" class="headee-contents">로그아웃</span> 
					<span class="headee-contents" style="font-weight:800">${me.nickname}</span> 
				</c:if>
			
				
				<span class="headee-contents">다운로드</span>
				<span class="headee-contents">국가선택</span>
			</div>
	</header>