<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ page session="false" %>

<html>
<head>
<title>CoinHwang</title>

<%@ include file="../rscs/basicRscs.jsp" %>
<script src="/resources/js/helplcenter.js"></script>
<script src="/resources/js/home.js"></script>

</head>

<div class="wrapperDiv">
	
	<%@ include file="../include/header2.jsp" %>
	
	<main class="main">
		<%@ include file="sideNavi.jsp" %>
		<div class="boardWrite">
			<input type="hidden" name="memberSeq" value="${memberSeq}">
			<input value="상담 유형 :: ${bdOne.bdDiv}" id="boardTitle" class="app-inp boardInput boardTitle contentText bdView" type="text" name="bdTitle" readonly>
			<input value="${bdOne.bdTitle}" id="boardTitle" class="app-inp boardInput boardTitle contentText contentTextTitle bdView" type="text" name="bdTitle" readonly>
			<textarea id="boardContents" class="boardInput boardContents contentText bdView" id="bdContent" name="bdContent" readonly>
				${bdOne.bdContent}
			</textarea>
			<div id="boardWrite_footer" class="boardWrite_footer">
				<c:if test="${bdOne.memberSeq} == ${memberSeq}">
					<button class="uptBtn" type="button"> 글 수정하기 </button>
				</c:if>
			</div>
			<div class="writeCmt">
				<input id="bdSeq" type="hidden" value="${bdOne.bdSeq}" >
				<textarea id="cmTextArea" class="cmTextArea" name="text" ></textarea>
				<button type="button" onclick="cmtWrite(this)" value="${bdOne.bdSeq}"> 댓글달기 </button>
			</div>
			<div id="commentTable" class="commentTable">
				<c:choose>
					<c:when test="${fn:length(selectCmtList) eq 0}}">
					</c:when>
					<c:otherwise>
						<c:forEach items="${selectCmtList}" var="selectCmtList" varStatus="status">
		  					<div class="cmtDiv" id="${selectCmtList.cmtSeq}">
								<span>
								<strong>${selectCmtList.memberNickname}</strong>
								</span>
								<div class="textDiv" id="${selectCmtList.memberSeq}">
									<div>
										${selectCmtList.text}
									</div>
									<div>
										<input id="sessSE" type="hidden" value="${memberSeq}">
										<c:if test="${selectCmtList.memberSeq == memberSeq}">
											<button value="${selectCmtList.cmtSeq}" onclick="cmtDel(this)"><i class="fa-solid fa-xmark"></i></button>
										</c:if>
									</div>
								</div>
								<i><fmt:formatDate value="${selectCmtList.cmtRegDateTime}" pattern="yyyy-MM-dd"/></i>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<div>
					<%@ include file="../include/pagination.jsp" %>
				</div>
			</div>
		</div>
	</main>
</div>

<!-- footer -->
<%@ include file="../include/footer.jsp" %>
</body>

<link type="text/css" rel="stylesheet" href="/resources/css/helpcenter.css">
</html>
