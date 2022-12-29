<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


	<div class="wrapper" style="height: 82px;">
		<header class="header1 header">
			<div class="header-left">
				<a title="황비트" class="logo-link" id="logo-link2" onclick="goHome()"></a>
				<%@ include file="headerNavi.jsp" %>


			</div>
			<div class="header-right">
				<c:choose>
					<c:when test="${empty memberSeq}">
						<a id="userLogin"class="headee-contents" onclick="goLogin()">로그인</a>
						<a id="userSignIn"class="headee-contents" onclick="goUserSignIn()">회원가입</a>
					</c:when>
					<c:otherwise>
						<span id="logout-btn" class="headee-contents">로그아웃</span> 
						<input type="hidden" id="idTokenKko" name="idTokenKko" value="${idTokenKko}"/>
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
		
		<div class="hiddenGroup" style="display:none">
			<input type="hidden" id="cryptoSeq" value="<c:out value="${cryptoSeq}"/>"/>
			<input type="hidden" id="cryptoSym" value="<c:out value="${cryptoSym}"/>"/>
			<input type="hidden" id="memberSeq" value="<c:out value="${memberSeq}"/>"/>
			<input type="hidden" id="memberName" value="<c:out value="${memberSeq}"/>"/>
		</div>
	</div>
	
	<!-- <script src="./resources/js/home.js"></script> -->
<link type="text/css" rel="stylesheet" href="../resources/css/homeComponent/home.css">
<link type="text/css" rel="stylesheet" href="../resources/css/homeComponent/header.css">
<link rel="stylesheet" href="./resources/css/all.min.css">
<script src="/../resources/js/homeComponent/header.js"></script>
<script src="/resources/js/paging.js"></script>