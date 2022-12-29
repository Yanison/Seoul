<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>CoinHwang</title>
<%@ include file="../rscs/basicRscs.jsp" %>
</head>
<body style="min-width:1600px;">

<div class="wrapperDiv">
	
	<%@ include file="../include/header2.jsp" %>
	
	<main class="main">
		<%@ include file="sideNavi.jsp" %>
		<form method="post" action="/helpcenter/board/update">
		<div class="boardWrite">
			
			<div id="boardWrite_header" class="boardWrite_header">
				<h1>1:1 문의하기</h1>
				<div class="read">
					<ul>
						<li>상담 문의 처리에 필요한 정보만 기입해주시기 바랍니다.</li>
						<li>요청 드리지 않은 개인정보를 임의로 입력 시 상담이 중단될 수 있습니다.</li>
					</ul>
				</div>
			</div>
			
				<input type="hidden" name="memberSeq" value="${memberSeq}">
				<input type="hidden" name="bdSeq" value="${bdSeq}">
				<select id="category" class="category" name="bdDiv">
					<option value="${bdOne.bdDiv}" selected>문의유형</option>
					<option value="trade">거래</option>
					<option value="feedback">개선요청</option>
					<option value="ect">기타</option>
				</select>
				<input id="boardTitle" class="app-inp boardInput boardTitle" type="text" name="bdTitle" placeholder="제목" value="${bdOne.bdTitle}">
				<textarea id="boardContents" class="boardInput boardContents" id="bdContent" name="bdContent">
				${bdOne.bdContent}
				</textarea>
				<div id="boardWrite_footer" class="boardWrite_footer">
					<button class="boardBtn" > <i class="fa-solid fa-xmark"></i> </button>
					<button type="submit" class="boardBtn"> <i class="fa-solid fa-pen"></i> </button>
				</div>
		</div>
		</form>
	</main>
</div>

<!-- footer -->
<%@ include file="../include/footer.jsp" %>
</body>

<link type="text/css" rel="stylesheet" href="/resources/css/helpcenter.css">
<script src="/resources/js/helpcenter.js"></script>
<script src="./resources/js/home.js"></script>
</html>
