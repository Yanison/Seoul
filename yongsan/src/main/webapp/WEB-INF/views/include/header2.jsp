<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 

<%@ page session="false" %>

	<header class="header2 header">
		<div class="header-left">
				<a title="황비트" id="logo-link2" class="logo-link2" onclick="goHome()"></a>
				<%@ include file="headerNavi.jsp" %>


			</div>
			<div class="header-right">
				<input type="hidden" id="cryptoSeq" name="cryptoSeq" value="${getOnlaodInfo.cryptoSeq}"/>
				<input type="hidden" id="idTokenKko" name="idTokenKko" value="${idTokenKko}"/>
				<input type="hidden" id="memberSeq" name="memberSeq" value="${memberSeq}"/>
				
				<c:choose>
					<c:when test="${empty memberSeq}">
						<a id="userLogin"class="headee-contents" onclick="goLogin()">로그인</a>
						<a id="userSignIn"class="headee-contents" onclick="goUserSignIn()">회원가입</a>
					</c:when>
					<c:otherwise>
						<span id="logout-btn" class="headee-contents" style="color:#fff">로그아웃</span>
						
						<c:choose>
							<c:when test="${idTokenKko eq '2344261226'}">
								
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
	
<link type="text/css" rel="stylesheet" href="/../resources/css/homeComponent/header.css">
<script src="/../resources/js/homeComponent/header.js"></script>
<script src="/resources/js/paging.js"></script>
